package org.northernaurora.dvae25.GUI.survey.factory.Types;

public enum SurveyComponentTextTypes {
    HEADER("header"),
    SUBHEADING("subheading"),
    INFO1("info-1");

    public final String label;
    private SurveyComponentTextTypes(String label){
        this.label = label;
    }
}
