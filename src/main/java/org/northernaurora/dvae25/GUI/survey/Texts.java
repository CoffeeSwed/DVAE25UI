package org.northernaurora.dvae25.GUI.survey;

public enum Texts {
    WELCOMETEXT("SurveyWelcomeText"),
    SPENDINGBEHAVIOR("SPENDINGBEHAVIOR"),
    ONLINEBEHAVIOR("ONLINEBEHAVIOR"),
    FINALPAGE("FINAL_PAGE");

    public final String label;
    private Texts(String label){
        this.label = label;
    }
}
