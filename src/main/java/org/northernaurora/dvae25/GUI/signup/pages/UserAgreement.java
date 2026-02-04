package org.northernaurora.dvae25.GUI.signup.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.northernaurora.dvae25.GUI.GUIComponent.Page;
import org.northernaurora.dvae25.GUI.GUIComponent.RoundedJPanel;
import org.northernaurora.dvae25.GUI.resources.SurveyResources.Exception.SurveyResourcesException;
import org.northernaurora.dvae25.GUI.resources.SurveyResources.Resources;
import org.northernaurora.dvae25.GUI.resources.TextLanguages;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.io.IOException;

public class UserAgreement extends Page {
    private static final Logger logger = LogManager.getLogger(UserAgreement.class);

    private RoundedJPanel center;
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

    public void addTexts(){
        this.center.removeAll();
        this.addLabel("Privacy notice");
        this.addTextArea("To help improve the user experience and share statistics with various shareholders, data will be collected from your system along with usage information from the application.");
    }



    @Override
    public void init() {
        super.init();
        this.setBackground(new Color(50,50,255));
        this.setCenter(new RoundedJPanel(20));

        this.getCenter().setBackground(Color.WHITE);
        this.getCenter().setLayout(new BoxLayout(center,BoxLayout.Y_AXIS));
        this.addTexts();
        this.onWindowResize(this.getDvguiParent().getWindowSize());
        this.add(center);
    }

    public RoundedJPanel getCenter() {
        return center;
    }

    public void setCenter(RoundedJPanel center) {
        this.center = center;
    }

    @Override
    public void onWindowResize(Dimension newSize) {
        if(this.getDvguiParent() != null){
            int maxWidth = 1080;
            int width = Math.min(maxWidth,this.getCenter().getPreferredSize().width);
            this.getCenter().setMaximumSize(new Dimension(width,this.getCenter().getPreferredSize().height));
            logger.info("Center max size now : "+this.getCenter().getMaximumSize());
            this.revalidate();
            this.repaint();
        }
    }
}
