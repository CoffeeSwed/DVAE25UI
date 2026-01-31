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

public class SurveyWelcome extends SurveyPage implements ActionListener {
    private static Logger logger = LogManager.getLogger(SurveyWelcome.class);
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
                    SurveyResources.getText(SurveyResources.TEXTSFILE, SurveyTexts.WELCOMETEXT.label, SurveyLanguages.ENGLISH),
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

        questions.add(new SurveyQuestionCheckmark("How old are you?", Arrays.stream(new String[]{"20-29", "30-39", "40-49", "50-59", "60-69", "70+"}).toList()));
        questions.add(new SurveyQuestionCheckmark(
                "What is your current occupation? (Select <b>all</b> that apply)",
                Arrays.stream(new String[]{
                        "Unemployed",
                        "Full-time employment",
                        "Part-time employment",
                        "Student",
                }).toList(), true
        ));

        questions.add(new SurveyQuestionCheckmark(
                "Which following experiences with surveys do you have? (Select <b>all</b> that apply)",
                Arrays.stream(new String[]{
                        "No experience <b>participating</b> in surveys",
                        "<b>Participated</b> in at least one survey",
                        "No previous experience being a part of a survey <b>design</b> process",
                        "Involved in the <b>design</b> process of at least one survey"
                }).toList()
        , true));

        /*questions.add(new SurveyQuestionCheckmark(
                "What is your experience with <b>designing</b> surveys?",
                Arrays.stream(new String[]{
                        "No previous experience participating in the survey design process",
                        "Involved in the design process of at least one survey",
                }).toList()
        ));*/

        questions.setBackground(this.getBackground());
        questions.setBorder(new EmptyBorder(20,20,20,20));
        this.add(questions, BorderLayout.CENTER);
        JPanel nextPagePanel = new JPanel();
        JButton nextPage = new JButton("Continue survey");
        nextPagePanel.add(nextPage);
        nextPagePanel.setOpaque(false);
        nextPage.setActionCommand(SurveyPage.NEXTPAGECOMMAND);


        this.add(nextPagePanel, BorderLayout.PAGE_END);
        this.setDifferentialPrivacyPage(new SurveyOnlineShoppingBehavior());
        nextPage.addActionListener(this);
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
            this.getDvguiParent().addPage(this.getDifferentialPrivacyPage());
    }
}
