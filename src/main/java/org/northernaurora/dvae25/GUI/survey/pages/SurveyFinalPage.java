package org.northernaurora.dvae25.GUI.survey.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.northernaurora.dvae25.GUI.DVGUI;
import org.northernaurora.dvae25.GUI.survey.factory.SurveyComponentFactory;
import org.northernaurora.dvae25.GUI.survey.factory.Types.SurveyComponentTextTypes;
import org.northernaurora.dvae25.GUI.resources.TextLanguages;
import org.northernaurora.dvae25.GUI.resources.SurveyResources.Resources;
import org.northernaurora.dvae25.GUI.survey.Texts;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SurveyFinalPage extends SurveyPage implements ActionListener {
    private static Logger logger = LogManager.getLogger(SurveyFinalPage.class);
    private SurveyOnlineShoppingBehavior differentialPrivacyPage;

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
            JComponent text = SurveyComponentFactory.createJEditorPane(
                    Resources.getText(Resources.TEXTSFILE, Texts.FINALPAGE.label, TextLanguages.ENGLISH),
                    SurveyComponentTextTypes.INFO1);
            // create panel for text area
            text.setBorder(new EmptyBorder(20,0,20,0));

            this.add(text, BorderLayout.PAGE_START);
        }
        catch (Exception e){
            logger.error(e.getMessage());
        }

    }

    public void addQuestions(){
        JPanel questions = new JPanel();
        questions.setLayout(new BoxLayout(questions, BoxLayout.Y_AXIS));


        JPanel nextPagePanel = new JPanel();
        JButton nextPage = new JButton("Submit survey");
        nextPagePanel.setOpaque(false);
        nextPage.setActionCommand(SurveyPage.NEXTPAGECOMMAND);

        JButton previousPage = new JButton("Go back");
        previousPage.setActionCommand(SurveyPage.PREVIOUSPAGECOMMAND);


        nextPagePanel.add(previousPage);
        nextPagePanel.add(nextPage);
        previousPage.addActionListener(this);
        nextPage.addActionListener(this);
        this.add(nextPagePanel, BorderLayout.PAGE_END);
        this.setDifferentialPrivacyPage(new SurveyOnlineShoppingBehavior());


    }

    @Override
    public void init() {
        super.init();
        this.setLayout(new BorderLayout());
        this.addInfo();
        this.addQuestions();
        this.setBorder(null);
    }

    @Override
    public void setDvguiParent(DVGUI dvguiParent) {
        super.setDvguiParent(dvguiParent);
        this.getDvguiParent().setScrollable(true);
    }

    public SurveyOnlineShoppingBehavior getDifferentialPrivacyPage() {
        return differentialPrivacyPage;
    }

    public void setDifferentialPrivacyPage(SurveyOnlineShoppingBehavior differentialPrivacyPage) {
        this.differentialPrivacyPage = differentialPrivacyPage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(SurveyPage.NEXTPAGECOMMAND))
        {
            int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to submit your survey?", "Submit survey?",JOptionPane.YES_NO_OPTION);
            // 0=yes, 1=no, 2=cancel
            if(input == 0){
                System.exit(0);
            }

        }
        if(e.getActionCommand().equals(SurveyPage.PREVIOUSPAGECOMMAND))
        {
            logger.info("PREV PAGE");
            this.getDvguiParent().popPage();

        }
    }

    @Override
    public void onWindowUpdate() {
        if(this.getDvguiParent() != null){
            if(this.getDvguiParent().getWindowSize().width > 300) {
                if(this.getParentBorder() == null) {
                    this.setParentBorder(new EmptyBorder(0, 100, 0, 100));
                }
            }else{
                this.setParentBorder(null);
            }
            this.revalidate();
        }
    }
}
