package org.northernaurora.dvae25.GUI.survey.GUIComponent.Questions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.northernaurora.dvae25.GUI.survey.GUIComponent.SurveyQuestionCheckmark;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class QuestionCheckBox extends JPanel implements ItemListener, ItemSelectable {
    private static Logger logger = LogManager.getLogger(SurveyQuestionCheckmark.class);

    private JCheckBox checkBox;
    private JEditorPane checkEditorPane;
    private List<ItemListener> listeners;
    public QuestionCheckBox(String text){
        super();
        this.setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));

        JCheckBox questionCheck = new JCheckBox("");
        this.add(questionCheck);
        questionCheck.addItemListener(this);
        JEditorPane editorPane = new JEditorPane("text/html", text);
        editorPane.setEditable(false);
        editorPane.setFocusable(false);
        editorPane.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                questionCheck.setSelected(!questionCheck.isSelected());
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        this.add(editorPane);
        this.setBackground(Color.white);
        this.setBorder(new EmptyBorder(0,20,0,0));
        this.setAlignmentX(0.5f);
        questionCheck.setBackground(Color.WHITE);
        this.setOpaque(true);
        this.setCheckBox(questionCheck);
        this.setCheckEditorPane(editorPane);
        this.setListeners(new ArrayList<>());
    }

    public JCheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(JCheckBox checkBox) {
        this.checkBox = checkBox;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        ItemEvent custom = new ItemEvent(this,
                ItemEvent.ITEM_STATE_CHANGED,
                this,
                e.getStateChange());
        for(ItemListener listener : this.getListeners()){
            listener.itemStateChanged(custom);
        }
    }

    public JEditorPane getCheckEditorPane() {
        return checkEditorPane;
    }

    public void setCheckEditorPane(JEditorPane checkEditorPane) {
        this.checkEditorPane = checkEditorPane;
    }

    public String getText(){
        return this.checkEditorPane.getText();
    }

    public void setText(String text){
        this.checkEditorPane.setText(text);
    }

    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
        if (this.getCheckEditorPane() != null)
            this.getCheckEditorPane().setBackground(bg);
        if (this.getCheckBox() != null)
            this.getCheckBox().setBackground(bg);
    }

    public void setChecked(boolean checked){
        this.getCheckBox().setSelected(checked);
    }

    public boolean isChecked(){
        return this.getCheckBox().isSelected();
    }

    private List<ItemListener> getListeners() {
        return listeners;
    }

    private void setListeners(List<ItemListener> listeners) {
        this.listeners = listeners;
    }


    @Override
    public Object[] getSelectedObjects() {
        if (!this.getCheckBox().isSelected()) {
            return null;
        }
        Object[] selectedObjects = new Object[1];
        selectedObjects[0] = getText();
        return selectedObjects;
    }

    @Override
    public void addItemListener(ItemListener l) {
        if (!this.getListeners().contains(l))
            this.getListeners().add(l);
    }

    @Override
    public void removeItemListener(ItemListener l) {
        this.getListeners().remove(l);
    }
}
