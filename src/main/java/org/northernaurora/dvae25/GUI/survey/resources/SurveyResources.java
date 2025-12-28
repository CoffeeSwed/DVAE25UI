package org.northernaurora.dvae25.GUI.survey.resources;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.northernaurora.dvae25.GUI.survey.pages.SurveyWelcome;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class SurveyResources {
    private static Logger logger = LogManager.getLogger(SurveyResources.class);


    public static final String Swedish = "swedish";
    public static final String English = "english";

    public static final String TEXTSFILE = "texts.xml";
    private static HashMap<String, Document> documents = new HashMap<>();
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

    /**
     * @param file
     * @param id
     * @param language
     * @return
     * @throws IOException
     * @throws RuntimeException
     * @throws SAXException
     * Expected format
     * <message>
     *     <text id="SurveyWelcomeText">
     *         <swedish></swedish>
     *         <english>You will be presented with multiple choices question which we wish you answered best to your ability.</english>
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
                    Node node = textElement.getElementsByTagName(language).item(0);
                    return node.getTextContent();
                }
            }

            throw new Exception("Item with id "+id+" not found!");


        } catch (Exception e) {
            throw e;
        }
    }
}
