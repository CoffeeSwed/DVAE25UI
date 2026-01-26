package org.northernaurora.dvae25.GUI.survey.GUIComponent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.northernaurora.dvae25.GUI.survey.GUIComponent.Questions.QuestionCheckBox;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;
import java.util.List;

public class SurveyQuestionCheckmark extends JPanel implements ItemListener {
    private static Logger logger = LogManager.getLogger(SurveyQuestionCheckmark.class);

    private Map<String, QuestionCheckBox> answers;
    private JPanel answerPanel;
    private String question;
    private JEditorPane questionEditorPane;
    private boolean multicheck;
    private void init(String question, List<String> answers, boolean multi_check){
        this.setLayout(new BorderLayout());
        this.setQuestion(question);
        this.setAnswerMap(new HashMap<>());
        this.setAnswerPanel(new JPanel());
        this.getAnswerPanel().setLayout(new BoxLayout(this.getAnswerPanel(),BoxLayout.Y_AXIS));
        this.getAnswerPanel().setBackground(Color.WHITE);
        this.add(this.getAnswerPanel(),BorderLayout.CENTER);

        for (String answer : answers){
            this.addAnswer(answer);
        }
        this.setOpaque(true);
        this.setBackground(Color.WHITE);
        this.setBorder(new EmptyBorder(5,5,5,5));
        this.setMulticheck(multi_check);
    }

    public SurveyQuestionCheckmark(String question, List<String> answers){
        super();
        this.init(question,answers, false);

    }

    public SurveyQuestionCheckmark(String question, List<String> answers, boolean multi_check){
        this.init(question,answers,multi_check);

    }

    private Map<String, QuestionCheckBox> getAnswerMap() {
        return answers;
    }

    private void setAnswerMap(Map<String, QuestionCheckBox> questions) {
        this.answers = questions;
    }

    public Set<String> getAnswers(){
        return this.getAnswerMap().keySet();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
        if (this.getQuestionEditorPane() == null){
            this.setQuestionEditorPane(new JEditorPane("text/html", ""));
            this.getQuestionEditorPane().setEditable(false);
            this.getQuestionEditorPane().setFocusable(false);

            this.add(this.getQuestionEditorPane(), BorderLayout.PAGE_START);
            this.getQuestionEditorPane().setOpaque(false);
        }
        this.getQuestionEditorPane().setText(question);
    }

    private JEditorPane getQuestionEditorPane() {
        return questionEditorPane;
    }

    private void setQuestionEditorPane(JEditorPane questionEditorPane) {
        this.questionEditorPane = questionEditorPane;
    }

    public void addAnswer(String question){
        if (!this.getAnswers().contains(question)){
            QuestionCheckBox checkBox = new QuestionCheckBox(question);
            this.getAnswerMap().put(question, checkBox);

            this.getAnswerPanel().add(checkBox);
            checkBox.addItemListener(this);
        }
    }

    public JPanel getAnswerPanel() {
        return answerPanel;
    }

    public void setAnswerPanel(JPanel answerPanel) {
        this.answerPanel = answerPanel;
    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getItem() instanceof QuestionCheckBox){
            if (e.getStateChange() == ItemEvent.SELECTED && !this.isMulticheck()){
                for(String question : this.answers.keySet()){
                    QuestionCheckBox checkbox = this.answers.get(question);
                    if(checkbox != e.getItem()){
                        checkbox.setChecked(false);
                    }
                }
            }
        }
    }

    public boolean isMulticheck() {
        return multicheck;
    }

    public void setMulticheck(boolean multicheck) {
        this.multicheck = multicheck;
    }


}
