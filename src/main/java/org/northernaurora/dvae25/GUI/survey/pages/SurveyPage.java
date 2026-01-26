package org.northernaurora.dvae25.GUI.survey.pages;

import org.northernaurora.dvae25.GUI.GUIComponent.Page;
import org.northernaurora.dvae25.GUI.survey.GUIComponent.SurveyPageHeading;
import org.northernaurora.dvae25.GUI.survey.factory.SurveyComponentFactory;
import org.northernaurora.dvae25.GUI.survey.factory.Types.SurveyComponentTextTypes;
import org.northernaurora.dvae25.GUI.survey.factory.Types.SurveyComponentPanelTypes;

import javax.swing.*;
import java.awt.*;

public class SurveyPage extends Page {
    public static String NEXTPAGECOMMAND = "NEXTPAGE";
    public static String PREVIOUSPAGECOMMAND = "PREVIOUSPAGE";

    @Override
    public void init() {
        super.init();
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.addTitleComponents();
        this.setBackground(new Color(255,232,162));



    }

    @Override
    public String getTitleString() {
        return "SurveyPage";
    }

    public String getTitleSubHeading(){
        return "hi";
    }

    public void addTitleComponents(){
        if (this.getTitleSubHeading() != null  || this.getTitleString() != null){
            this.add(new SurveyPageHeading(this.getTitleString(),this.getTitleSubHeading()));
        }
    }

}
