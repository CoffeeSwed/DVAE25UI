package org.northernaurora.dvae25.GUI;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.northernaurora.dvae25.GUI.Survey.pages.SurveyPage;
import org.northernaurora.dvae25.GUI.Survey.pages.SurveyWelcome;

import java.awt.*;

public class Main{
    private static Logger logger = LogManager.getLogger(Main.class);
    static void main(String[] args){
        DVGUI gui = new DVGUI();
        SurveyWelcome surveyPage = new SurveyWelcome();
        gui.addPage(surveyPage);


    }
}


