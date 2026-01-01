package org.northernaurora.dvae25.GUI.survey.factory;

import org.northernaurora.dvae25.GUI.GUIComponent.RoundedScrollPane;
import org.northernaurora.dvae25.GUI.survey.factory.Types.SurveyComponentTextTypes;
import org.northernaurora.dvae25.GUI.survey.factory.Types.SurveyComponentPanelTypes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SurveyComponentFactory {
    public static int FontSize(SurveyComponentTextTypes type){
        switch (type){
            case HEADER -> {
                return 34;
            }
            case SUBHEADING -> {
                return 20;
            }
            default -> {return 16;}
        }
    }

    public static Color getBackground(SurveyComponentTextTypes type){
        switch (type){
            default -> {return new Color(0,0,0,0);}
        }
    }

    public static Color getBackground(SurveyComponentPanelTypes type){
        switch (type){
            default -> {return new Color(0,0,0,0);}
        }
    }



    public static JLabel createLabel(String text, SurveyComponentTextTypes type){
        JLabel label = new JLabel(text);

        switch (type){
            default -> {
                label.setFont(new Font(label.getFont().getFontName(), Font.PLAIN,
                        SurveyComponentFactory.FontSize(type)));
                label.setAlignmentX(Component.CENTER_ALIGNMENT);
            }
        }

        return label;
    }

    public static JComponent createJEditorPane(String text, SurveyComponentTextTypes type){
        JEditorPane textArea = new JEditorPane("text/html", "");
        textArea.setText(text);
        textArea.setEditable(false);
        JPanel textPanel = new JPanel();
        RoundedScrollPane scrollpane = new RoundedScrollPane(textArea, 20);
        scrollpane.setBorder(new EmptyBorder(0,0,0,0));
        //Add to textPanel also!
        textPanel.setOpaque(false);
        switch (type){
            default -> {
                textArea.setFont(new Font(textArea.getFont().getFontName(), Font.PLAIN,
                        SurveyComponentFactory.FontSize(type)));
                textArea.setAlignmentX(Component.CENTER_ALIGNMENT);
                textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
                textArea.setBorder(new EmptyBorder(5,5,5,5));
                textPanel.setBorder(new EmptyBorder(10,10,10,10));
                scrollpane.setInsets(0, scrollpane.getDefaultInset(), 0, scrollpane.getDefaultInset());
            }
        }
        textPanel.add(scrollpane);
        scrollpane.setBackground(Color.white);


        return textPanel;


    }
}
