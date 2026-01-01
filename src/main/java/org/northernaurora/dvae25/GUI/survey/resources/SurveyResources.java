package org.northernaurora.dvae25.GUI.survey.resources;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class SurveyResources {
    private static Logger logger = LogManager.getLogger(SurveyResources.class);


    public static final String Swedish = "swedish";
    public static final String English = "english";

    public static final String TEXTSFILE = "texts.xml";
    public static final String HAS_RAW_RESOURCE = "has_raw_resource";

    private static HashMap<String, Document> documents = new HashMap<>();
    private static HashMap<String, String> raw_contents = new HashMap<>();

    public static Document getDocument(String file) throws IOException, RuntimeException, SAXException {
        if (documents.containsKey(file)){
            return documents.get(file);
        }

        InputStream is = SurveyResources.class
                .getClassLoader()
                .getResourceAsStream(file);
        if  (is != null){
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = null;
            try {
                builder = factory.newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                throw new RuntimeException(e);
            }
            Document document = builder.parse(is);
            document.getDocumentElement().normalize();
            documents.put(file,document);
            return document;
        }
        throw new FileNotFoundException("Could not find resource "+file+"!");
    }

    public static String getRawStringContent(String file) throws IOException, RuntimeException, SAXException {
        if (raw_contents.containsKey(file)){
            return raw_contents.get(file);
        }

        InputStream is = SurveyResources.class
                .getClassLoader()
                .getResourceAsStream(file);
        if  (is != null){
            String str = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            raw_contents.put(file,str);
            return str;
        }
        throw new FileNotFoundException("Could not find resource "+file+"!");
    }

    /**
     * @param file
     * @param id
     * @param language
     * @return
     * @throws IOException
     * @throws RuntimeException
     * @throws SAXException
     * Expected format
     * <?xml version="1.0" encoding="UTF-8"?>
     * <message>
     *     <text id="Example">
     *         <swedish>Hej</swedish>
     *         <english>Hi</english>
     *     </text>
     *     <text id="SurveyWelcomeText">
     *         <swedish>&lt;html&gt;
     *             &lt;head&gt;
     *             &lt;style&gt;
     *             &lt;/style&gt;
     *             &lt;/head&gt;
     *             &lt;body&gt;
     *             Du kommer att få flervalsfrågor som vi ber dig att besvara efter bästa förmåga. &lt;br&gt;&lt;br&gt;&lt;b&gt;Alla svar anonymiseras.&lt;/b&gt;
     *             &lt;/body&gt;
     *             &lt;/html&gt;</swedish>
     *         <english has_raw_resource="True">texts-raw/english/SurveyWelcomeText.html</english>
     *     </text>
     * </message>
     */
    public static String getText(String file, String id, String language) throws IOException, RuntimeException, SAXException, Exception {
        try{
            Document document = SurveyResources.getDocument(file);
            for (int index = 0; index < document.getElementsByTagName("text").getLength(); index++){
                Element textElement = (Element)
                        document.getElementsByTagName("text").item(index);
                logger.debug("Checking item {}", textElement.getAttribute("id"));
                if (textElement.getAttribute("id").equals(id)){
                    Element node = (Element) textElement.getElementsByTagName(language).item(0);
                    if (node.getAttribute(SurveyResources.HAS_RAW_RESOURCE) != null){
                        if (node.getAttribute(SurveyResources.HAS_RAW_RESOURCE).equalsIgnoreCase("true")) {
                            String location = node.getTextContent();
                            return SurveyResources.getRawStringContent(location);
                        }

                    }
                    return node.getTextContent();

                }
            }

            throw new Exception("Item with id "+id+" not found!");


        } catch (Exception e) {
            throw e;
        }
    }
}
