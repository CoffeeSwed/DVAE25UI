package org.northernaurora.dvae25.GUI.survey.resources;

public enum SurveyTexts {
    WELCOMETEXT("SurveyWelcomeText"),
    SPENDINGBEHAVIOR("SPENDINGBEHAVIOR"),
    ONLINEBEHAVIOR("ONLINEBEHAVIOR"),
    FINALPAGE("FINAL_PAGE");

    public final String label;
    private SurveyTexts(String label){
        this.label = label;
    }
}
