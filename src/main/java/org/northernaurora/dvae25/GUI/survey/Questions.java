package org.northernaurora.dvae25.GUI.survey;

public enum Questions {
    ONLINEHABITS("ONLINE_HABITS"),
    ONLINEREGRET("ONLINE_REGRET"),
    ONLINEADBLOCK("ONLINE_ADBLOCK"),
    ONLINEADSVIDEO("ONLINE_ADS_VIDEO"),
    ONLINEADSIMAGES("ONLINE_ADS_IMAGE"),
    ONLINEADSPURCHASES("ONLINE_ADS_PURCHASES"),

    ONLINESHOPPING("ONLINE_SHOPPING");

    public final String label;
    private Questions(String label){
        this.label = label;
    }
}
