package org.northernaurora.dvae25.GUI.signup.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.northernaurora.dvae25.GUI.GUIComponent.Page;
import org.northernaurora.dvae25.GUI.GUIComponent.RoundedJPanel;
import org.northernaurora.dvae25.GUI.GUIComponent.RoundedScrollPane;
import org.northernaurora.dvae25.GUI.resources.SurveyResources.Exception.SurveyResourcesException;
import org.northernaurora.dvae25.GUI.resources.SurveyResources.Resources;
import org.northernaurora.dvae25.GUI.resources.TextLanguages;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DPPrivacy extends Page implements ActionListener {
    private static final Logger logger = LogManager.getLogger(DPPrivacy.class);
    private static final String USER_AGREEMENT = "USER_AGREEMENT";
    private RoundedScrollPane scrollPane;
    private JPanel center;
    @Override
    public String getTitleString() {
        return null;
    }

    public void addTextArea(String text){
        this.getCenter().add(Utils.createTextArea(text));
    }

    public void addEditorPane(String text){
        this.getCenter().add(Utils.createEditorPane(text));
    }

    private void addLabel(String privacyNotice) {
        this.getCenter().add(Utils.createLabel(privacyNotice));
    }

    public void addAll(){
        this.center.removeAll();

        JButton button = new JButton("Return");
        button.setAlignmentX(0.5f);
        button.addActionListener(this);
        button.setActionCommand(USER_AGREEMENT);
        try {
            this.addEditorPane(Resources.getText(Resources.TEXTSFILE,USER_AGREEMENT, TextLanguages.ENGLISH));
        } catch (SurveyResourcesException e) {
            throw new RuntimeException(e);
        }

        this.center.add(button);

    }



    @Override
    public void init() {
        super.init();
        this.setBackground(Utils.BACKGROUND_COLOR);
        this.setCenter(new RoundedJPanel(20));

        this.getCenter().setBackground(Utils.PANEL_COLOR);
        this.getCenter().setLayout(new BoxLayout(center,BoxLayout.Y_AXIS));
        this.addAll();
        this.setScrollPane(new RoundedScrollPane(center,20));
        this.getScrollPane().setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.onWindowUpdate();
        this.add(this.getCenter());

    }

    public JPanel getCenter() {
        return center;
    }

    public void setCenter(JPanel center) {
        this.center = center;
    }

    @Override
    public void onWindowUpdate() {
        if(this.getDvguiParent() != null){
            int wantedWidth = 1080;
            int width = Math.min(wantedWidth,this.getDvguiParent().getWindowSize().width);
            float heigth = this.getDvguiParent().getWindowSize().height;
            heigth = 8*heigth/10.0f;
            heigth = Math.max(heigth,800);
            width = (this.getDvguiParent().getWindowSize().width - width)/2;
            this.revalidate();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(USER_AGREEMENT.equals(e.getActionCommand())){
            this.getDvguiParent().popPage();
        }
    }


    public RoundedScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(RoundedScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }
}
