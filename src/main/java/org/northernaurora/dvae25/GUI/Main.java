package org.northernaurora.dvae25.GUI;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.northernaurora.dvae25.GUI.survey.pages.SurveyPage;
import org.northernaurora.dvae25.GUI.survey.pages.SurveyWelcome;

import java.awt.*;

public class Main{
    private static final Logger logger = LogManager.getLogger(Main.class);
    static void main(String[] args){
        //logger.info(System.setProperty("sun.java2d.uiScale", "1.45"));
        DVGUI gui = new DVGUI();

        SurveyWelcome surveyPage = new SurveyWelcome();
        gui.addPage(surveyPage);
    }
}


