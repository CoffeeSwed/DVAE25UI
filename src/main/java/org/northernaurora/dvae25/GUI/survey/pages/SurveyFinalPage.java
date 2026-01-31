package org.northernaurora.dvae25.GUI.survey.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.northernaurora.dvae25.GUI.DVGUI;
import org.northernaurora.dvae25.GUI.survey.GUIComponent.SurveyQuestionCheckmark;
import org.northernaurora.dvae25.GUI.survey.factory.SurveyComponentFactory;
import org.northernaurora.dvae25.GUI.survey.factory.Types.SurveyComponentTextTypes;
import org.northernaurora.dvae25.GUI.survey.resources.SurveyLanguages;
import org.northernaurora.dvae25.GUI.survey.resources.SurveyResources.SurveyResources;
import org.northernaurora.dvae25.GUI.survey.resources.SurveyTexts;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

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
                    SurveyResources.getText(SurveyResources.TEXTSFILE, SurveyTexts.FINALPAGE.label, SurveyLanguages.ENGLISH),
                    SurveyComponentTextTypes.INFO1);
            // create panel for text area
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
}
