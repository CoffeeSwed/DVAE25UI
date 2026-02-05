package org.northernaurora.dvae25.GUI;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.northernaurora.dvae25.GUI.GUIComponent.Page;
import org.northernaurora.dvae25.GUI.signup.pages.RegistrationHome;
import org.northernaurora.dvae25.GUI.survey.pages.SurveyWelcome;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Enumeration;
import java.util.HashMap;

public class Main{
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void setDefaultFont(Font font) {
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, new FontUIResource(font));
            }
        }
    }

    static void main(String[] args){

        setDefaultFont(new Font("Arial",Font.PLAIN,24));
        DVGUI gui = new DVGUI();

        HashMap<String, Page> pages = new HashMap<>();

        pages.put("Survey", new SurveyWelcome());
        pages.put("Signup", new RegistrationHome());

        Object[] keyset = pages.keySet().toArray();
        int n = JOptionPane.showOptionDialog(gui.getActiveJFrame(),
                "Which GUI?",
                "SELECT",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                keyset, null);
        //int n = 1;

        gui.addPage(pages.get(keyset[n]));

    }
}


