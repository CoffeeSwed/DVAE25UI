package org.northernaurora.dvae25.GUI.resources.SurveyResources;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.northernaurora.dvae25.GUI.resources.TextLanguages;
import org.northernaurora.dvae25.GUI.resources.SurveyResources.Exception.SurveyResourcesException;
import org.northernaurora.dvae25.GUI.resources.SurveyResources.Exception.Types.CouldNotOpenXMLDocument;
import org.northernaurora.dvae25.GUI.resources.SurveyResources.Exception.Types.IdNotFound;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Resources {
    private static final Logger logger = LogManager.getLogger(Resources.class);

    public static final String TEXTSFILE = "texts.xml";
    public static final String QUESTIONSFILE = "questions.xml";

    public static final String IS_RAW_RESOURCE = "is_raw_resource";

    private static final ConcurrentHashMap<String, Document> documents = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, String> raw_contents = new ConcurrentHashMap<>();

    public static Document getDocument(String file) throws IOException, RuntimeException, SAXException {
        if (documents.containsKey(file)){
            return documents.get(file);
        }

        try (InputStream is = Resources.class
                .getClassLoader()
                .getResourceAsStream(file)) {
            if (is != null) {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder;
                try {
                    builder = factory.newDocumentBuilder();
                } catch (ParserConfigurationException e) {
                    throw new RuntimeException(e);
                }
                Document document = builder.parse(is);
                document.getDocumentElement().normalize();
                documents.put(file, document);

                return document;
            }
        }
        throw new FileNotFoundException("Could not find resource "+file+"!");
    }

    public static String getRawContent(String file) throws IOException {
        if (raw_contents.containsKey(file)){
            return raw_contents.get(file);
        }

        try (InputStream is = Resources.class
                .getClassLoader()
                .getResourceAsStream(file)) {

            if (is != null) {
                String str = new String(is.readAllBytes(), StandardCharsets.UTF_8);
                raw_contents.put(file, str);
                return str;
            }
        }

        throw new FileNotFoundException("Could not find resource "+file+"!");


    }

    /**
     * @param file
     * @param id
     * @param language
     * @return
     * @throws SurveyResourcesException
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
    public static String getText(String file, String id, TextLanguages language) throws SurveyResourcesException {
        try{
            Document document = Resources.getDocument(file);
            for (int index = 0; index < document.getElementsByTagName("text").getLength(); index++){
                Element textElement = (Element)
                        document.getElementsByTagName("text").item(index);
                if (textElement.getAttribute("id").equals(id)){
                    Element node = (Element) textElement.getElementsByTagName(language.tag).item(0);
                    if (node.getAttribute(Resources.IS_RAW_RESOURCE).equalsIgnoreCase("true")) {
                        String location = node.getTextContent();
                        return Resources.getRawContent(location);
                    }


                    return node.getTextContent();

                }
            }

            throw new IdNotFound(id);


        }catch (IdNotFound e) {
            throw e;
        } catch (Exception e) {
            throw new CouldNotOpenXMLDocument(file,e);
        }
    }

    public static String getQuestionText(String file, String id, TextLanguages language) throws SurveyResourcesException {
        try{
            Document document = Resources.getDocument(file);
            for (int index = 0; index < document.getElementsByTagName("question").getLength(); index++){
                Element questionElement = (Element)
                        document.getElementsByTagName("question").item(index);
                if (questionElement.getAttribute("id").equals(id)){
                    Element node = (Element) ((Element) questionElement.getElementsByTagName("question_text").item(0)).getElementsByTagName(language.tag).item(0);
                    if (node.getAttribute(Resources.IS_RAW_RESOURCE).equalsIgnoreCase("true")) {
                        String location = node.getTextContent();
                        return Resources.getRawContent(location);
                    }


                    return node.getTextContent();

                }
            }
            throw new IdNotFound(id);




        }catch (IdNotFound e) {
            throw e;
        }
        catch (Exception e) {
            throw new CouldNotOpenXMLDocument(file,e);
        }
    }

    public static List<String> getQuestionAnswers(String file, String id, TextLanguages language) throws SurveyResourcesException {
        try{
            List<String> answers = new ArrayList<>();
            Document document = Resources.getDocument(file);
            for (int index = 0; index < document.getElementsByTagName("question").getLength(); index++){
                Element questionElement = (Element)
                        document.getElementsByTagName("question").item(index);
                if (questionElement.getAttribute("id").equals(id)){
                    NodeList nodeList = questionElement.getElementsByTagName("question_answer");
                    int length = nodeList.getLength();
                    for(int i = 0; i < length; i++){
                        Element element = (Element) nodeList.item(i);
                        element = (Element) element.getElementsByTagName(language.tag).item(0);
                        if (element.getAttribute(Resources.IS_RAW_RESOURCE).equalsIgnoreCase("true")) {
                            String location = element.getTextContent();
                            answers.add(Resources.getRawContent(location));
                            continue;
                        }


                        answers.add(element.getTextContent());
                    }

                    return answers;
                }
            }

            throw new IdNotFound(id);


        }catch (IdNotFound e) {
            throw e;
        } catch (Exception e) {
            throw new CouldNotOpenXMLDocument(file,e);
        }
    }
}
