package org.northernaurora.dvae25.GUI.survey.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.northernaurora.dvae25.GUI.DVGUI;
import org.northernaurora.dvae25.GUI.Survey.Factory.SurveyComponentFactory;
import org.northernaurora.dvae25.GUI.Survey.Factory.Types.SurveyComponentTextTypes;
import org.northernaurora.dvae25.GUI.survey.pages.SurveyPage;
import org.northernaurora.dvae25.GUI.survey.resources.SurveyResources;
import org.northernaurora.dvae25.GUI.survey.resources.SurveyStrings;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class SurveyWelcome extends SurveyPage {
    private static Logger logger = LogManager.getLogger(SurveyWelcome.class);

    @Override
    public String getTitleString() {
        return "Survey";
    }

    @Override
    public String getTitleSubHeading() {
        return "Welcome to this survey!";
    }

    public void addInfo(){
        try {
            JPanel text = SurveyComponentFactory.createTextArea(
                    SurveyResources.getText(SurveyResources.TEXTSFILE, SurveyStrings.WELCOMETEXT.label, SurveyResources.English),
                    SurveyComponentTextTypes.INFO1);
            // create panel for text area

            this.add(text);
        }
        catch (Exception e){
            logger.error(e.getMessage());
        }

    }

    @Override
    public void init() {
        super.init();
        this.addInfo();
    }
}
