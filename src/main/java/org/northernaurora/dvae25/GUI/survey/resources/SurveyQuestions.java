package org.northernaurora.dvae25.GUI.survey.resources;

public enum SurveyQuestions {
    ONLINEHABITS("ONLINE_HABITS"),
    ONLINEREGRET("ONLINE_REGRET"),
    ONLINEADBLOCK("ONLINE_ADBLOCK"),
    ONLINEADSVIDEO("ONLINE_ADS_VIDEO"),

    ONLINESHOPPING("ONLINE_SHOPPING");

    public final String label;
    private SurveyQuestions(String label){
        this.label = label;
    }
}
