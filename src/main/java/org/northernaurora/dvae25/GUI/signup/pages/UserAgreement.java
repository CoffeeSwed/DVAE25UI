package org.northernaurora.dvae25.GUI.signup.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.northernaurora.dvae25.GUI.GUIComponent.Page;
import org.northernaurora.dvae25.GUI.GUIComponent.RoundedJPanel;
import org.northernaurora.dvae25.GUI.GUIComponent.RoundedScrollPane;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserAgreement extends Page implements ActionListener {
    private static final Logger logger = LogManager.getLogger(UserAgreement.class);
    private static final String USER_AGREEMENT = "USER_AGREEMENT";
    private static final DPPrivacy dpprivacy = new DPPrivacy();
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

    public void addAll(){
        this.center.removeAll();
        this.addLabel("Privacy Notice");
        this.addTextArea("To help improve the user experience and share statistics with various stakeholders, data will be collected from your system along with usage information from the application.");
        this.addTextArea("\nThe information that may be collected includes the following:\n");
        this.addTextArea("\t1. System hardware");
        this.addTextArea("\t2. System operating system");
        this.addTextArea("\t3. System language");
        this.addTextArea("\t4. Installed applications");
        this.addTextArea("\t5. Application usage information");
        this.addTextArea("\t6. Application settings");
        this.addTextArea("\t7. Gender");
        this.addTextArea("\t8. Age");
        this.addTextArea("\t9. Occupation field");



        JButton button = new JButton("How is my privacy protected?");
        button.setAlignmentX(0.5f);
        button.addActionListener(this);
        button.setActionCommand(USER_AGREEMENT);
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
        this.onWindowUpdate();
        this.add(center);

    }

    public RoundedJPanel getCenter() {
        return center;
    }

    public void setCenter(RoundedJPanel center) {
        this.center = center;
    }

    @Override
    public void onWindowUpdate() {
        if(this.getDvguiParent() != null){
            int wantedWidth = 1080;
            int width = Math.min(wantedWidth,this.getDvguiParent().getWindowSize().width);
            float heigth = this.getDvguiParent().getWindowSize().height;
            heigth = 8*heigth/10.0f;
            heigth = Math.max(heigth,400);
            this.getCenter().setMaximumSize(new Dimension(wantedWidth,(int)heigth));
            this.getCenter().setMinimumSize(new Dimension(width,(int)heigth));
            logger.info("Center min size now : "+this.getCenter().getMinimumSize());

            this.revalidate();
            this.repaint();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(USER_AGREEMENT.equals(e.getActionCommand())){
            this.getDvguiParent().addPage(dpprivacy);
        }
    }


}
