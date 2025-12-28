package org.northernaurora.dvae25.GUI.survey.pages;

import org.northernaurora.dvae25.GUI.GUIComponent.Page;
import org.northernaurora.dvae25.GUI.Survey.Factory.SurveyComponentFactory;
import org.northernaurora.dvae25.GUI.Survey.Factory.Types.SurveyComponentTextTypes;
import org.northernaurora.dvae25.GUI.Survey.Factory.Types.SurveyComponentPanelTypes;

import javax.swing.*;
import java.awt.*;

public class SurveyPage extends Page {

    @Override
    public void init() {
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
        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));

        panel.add(SurveyComponentFactory.createLabel(this.getTitleString(), SurveyComponentTextTypes.TITLE));
        panel.add(SurveyComponentFactory.createLabel(this.getTitleSubHeading(), SurveyComponentTextTypes.SUBHEADING));
        panel.setBackground(SurveyComponentFactory.getBackground(SurveyComponentPanelTypes.TITLEHEADING));
        this.add(panel);
    }

}
