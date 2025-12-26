package org.northernaurora.dvae25.GUI.Survey.pages;

import org.northernaurora.dvae25.GUI.Survey.Factory.SurveyComponentFactory;
import org.northernaurora.dvae25.GUI.Survey.Factory.Types.SurveyComponentTextTypes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class SurveyWelcome extends SurveyPage{
    @Override
    public String getTitleString() {
        return "Survey";
    }

    @Override
    public String getTitleSubHeading() {
        return "Welcome to this survey!";
    }

    public void addInfo(){
        JPanel text = SurveyComponentFactory.createTextArea(
                        "Actually, the JTextArea always has the correct size so all lines of text are visible. What you experience is probably that you wrapped the text area in a JScrollPane. Just omit the scroll pane and make the text area a direct child of the container.\n" +
                        "\n" +
                        "Another solution is to listen to resize events of the text area and size the scroll pane accordingly. This way, you can grow to a certain size and then start to display scroll bars (for example, when someone pastes 500KB of text into the text area).\n",
                SurveyComponentTextTypes.INFO1);
        // create panel for text area

        this.add(text);


    }

    @Override
    public void init() {
        super.init();
        this.addInfo();
    }
}
