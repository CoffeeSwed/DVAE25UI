package org.northernaurora.dvae25.GUI.resources.SurveyResources.Exception.Types;

import org.northernaurora.dvae25.GUI.resources.SurveyResources.Exception.SurveyResourcesException;

public class CouldNotOpenXMLDocument extends SurveyResourcesException {
    public CouldNotOpenXMLDocument(String resource, Exception e){
        super("Could not open XML document resource \""+resource+"\"!",e.getCause());
    }
}
