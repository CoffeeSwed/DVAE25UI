package org.northernaurora.dvae25.GUI.survey.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.northernaurora.dvae25.GUI.DVGUI;
import org.northernaurora.dvae25.GUI.GUIComponent.Page;
import org.northernaurora.dvae25.GUI.survey.GUIComponent.SurveyQuestionCheckmark;
import org.northernaurora.dvae25.GUI.survey.factory.SurveyComponentFactory;
import org.northernaurora.dvae25.GUI.survey.factory.Types.SurveyComponentTextTypes;
import org.northernaurora.dvae25.GUI.survey.resources.SurveyLanguages;
import org.northernaurora.dvae25.GUI.survey.resources.SurveyQuestions;
import org.northernaurora.dvae25.GUI.survey.resources.SurveyResources.SurveyResources;
import org.northernaurora.dvae25.GUI.survey.resources.SurveyTexts;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SurveyOnlineSocialMediaBehavior extends SurveyPage implements ActionListener {
    private static final Logger logger = LogManager.getLogger(SurveyOnlineSocialMediaBehavior.class);
    private JPanel centerPanel;
    private Page nextPage;

    @Override
    public String getTitleString() {
        return "Survey";
    }

    @Override
    public String getTitleSubHeading() {
        return "Differential Privacy";
    }

    public void addInfo(){
        try {
            JComponent text = SurveyComponentFactory.createJEditorPane(
                    SurveyResources.getText(SurveyResources.TEXTSFILE, SurveyTexts.ONLINEBEHAVIOR.label, SurveyLanguages.ENGLISH),
                    SurveyComponentTextTypes.INFO1);
            // create panel for text area
            this.add(text, BorderLayout.PAGE_START);
        }
        catch (Exception e){
            logger.error("Could not add Info Panels, error : ",e);
        }

    }

    public void addQuestions(){
        JPanel questions = new JPanel();
        questions.setLayout(new BoxLayout(questions, BoxLayout.Y_AXIS));

        try {
            questions.add(new SurveyQuestionCheckmark(
                    SurveyResources.getQuestionText(SurveyResources.QUESTIONSFILE, SurveyQuestions.ONLINEHABITS.label, SurveyLanguages.ENGLISH),
                    SurveyResources.getQuestionAnswers(SurveyResources.QUESTIONSFILE, SurveyQuestions.ONLINEHABITS.label, SurveyLanguages.ENGLISH)
            ));
            questions.add(new SurveyQuestionCheckmark(
                    SurveyResources.getQuestionText(SurveyResources.QUESTIONSFILE, SurveyQuestions.ONLINEADBLOCK.label, SurveyLanguages.ENGLISH),
                    SurveyResources.getQuestionAnswers(SurveyResources.QUESTIONSFILE, SurveyQuestions.ONLINEADBLOCK.label, SurveyLanguages.ENGLISH)
            ));

            questions.add(new SurveyQuestionCheckmark(
                    SurveyResources.getQuestionText(SurveyResources.QUESTIONSFILE, SurveyQuestions.ONLINEADSVIDEO.label, SurveyLanguages.ENGLISH),
                    SurveyResources.getQuestionAnswers(SurveyResources.QUESTIONSFILE, SurveyQuestions.ONLINEADSVIDEO.label, SurveyLanguages.ENGLISH)
            ));





        } catch (Exception e) {
            logger.error("Could not add questions, exception received : ",e);
        }



        questions.setBackground(this.getBackground());
        questions.setBorder(new EmptyBorder(20,20,20,20));
        this.getCenterPanel().add(questions);
        JPanel nextPagePanel = new JPanel();
        nextPagePanel.setLayout(new FlowLayout());
        JButton previousPage = new JButton("Go back");
        previousPage.setActionCommand(SurveyPage.PREVIOUSPAGECOMMAND);

        JButton nextPage = new JButton("Continue survey");
        nextPage.setActionCommand(SurveyPage.NEXTPAGECOMMAND);

        nextPagePanel.add(previousPage);
        nextPagePanel.add(nextPage);
        nextPagePanel.setOpaque(false);
        nextPagePanel.setAlignmentX(0.5f);
        this.add(nextPagePanel, BorderLayout.PAGE_END);
        nextPage.addActionListener(this);
        previousPage.addActionListener(this);

    }

    @Override
    public void init() {
        super.init();
        this.nextPage = new SurveyFinalPage();
        this.setLayout(new BorderLayout());
        this.setCenterPanel(new JPanel());
        this.getCenterPanel().setLayout(new BoxLayout(this.getCenterPanel(),BoxLayout.Y_AXIS));
        this.add(this.getCenterPanel(),BorderLayout.CENTER);
        this.addInfo();
        this.addQuestions();
    }

    @Override
    public void setDvguiParent(DVGUI dvguiParent) {
        super.setDvguiParent(dvguiParent);
        this.getDvguiParent().setScrollable(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(SurveyPage.PREVIOUSPAGECOMMAND.equals(e.getActionCommand()))
            this.getDvguiParent().popPage();
        if(SurveyPage.NEXTPAGECOMMAND.equals(e.getActionCommand()))
            this.getDvguiParent().addPage(this.nextPage);
    }

    public JPanel getCenterPanel() {
        return centerPanel;
    }

    public void setCenterPanel(JPanel centerPanel) {
        this.centerPanel = centerPanel;
    }
}
