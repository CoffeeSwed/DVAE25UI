package org.northernaurora.dvae25.GUI.Survey.Factory;

import org.northernaurora.dvae25.GUI.Survey.Factory.Types.SurveyComponentTextTypes;
import org.northernaurora.dvae25.GUI.Survey.Factory.Types.SurveyComponentPanelTypes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SurveyComponentFactory {
    public static int FontSize(SurveyComponentTextTypes type){
        switch (type){
            case TITLE -> {
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

    public static JPanel createTextArea(String text, SurveyComponentTextTypes type){
        JTextArea textArea = new JTextArea();
        textArea.setText(text);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        JPanel textPanel = new JPanel();

        //Add to textPanel also!
        switch (type){
            default -> {
                textArea.setFont(new Font(textArea.getFont().getFontName(), Font.PLAIN,
                        SurveyComponentFactory.FontSize(type)));
                textArea.setAlignmentX(Component.CENTER_ALIGNMENT);
                textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
                textPanel.setOpaque(false);
                JScrollPane scrollpane = new JScrollPane(textArea);
                scrollpane.setBorder(new EmptyBorder(10,10,10,10));
                scrollpane.setBackground(Color.WHITE);
                textPanel.setBorder(new EmptyBorder(10,10,10,10));
                textPanel.add(scrollpane);
            }
        }
        return textPanel;


    }
}
