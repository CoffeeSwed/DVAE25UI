package org.northernaurora.dvae25.GUI.Survey.Factory.Types;

public enum SurveyComponentTextTypes {
    TITLE("title"),
    SUBHEADING("subheading"),
    INFO1("info-1");

    public final String label;
    private SurveyComponentTextTypes(String label){
        this.label = label;
    }
}
