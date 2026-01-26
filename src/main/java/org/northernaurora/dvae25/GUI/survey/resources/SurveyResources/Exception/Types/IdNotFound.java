package org.northernaurora.dvae25.GUI.survey.resources.SurveyResources.Exception.Types;

import org.northernaurora.dvae25.GUI.survey.resources.SurveyResources.Exception.SurveyResourcesException;

public class IdNotFound extends SurveyResourcesException {
    public IdNotFound(String id){
        super("Element with id \""+id+"\" not found!");
    }
}
