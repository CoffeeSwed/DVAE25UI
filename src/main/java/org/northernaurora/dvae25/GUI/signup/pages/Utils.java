package org.northernaurora.dvae25.GUI.signup.pages;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import java.awt.*;

public class Utils {

    public static final String BASE = "/Signup/";
    public static final Color BACKGROUND_COLOR = Color.WHITE;
    public static JTextArea createTextArea(String content){
        JTextArea area = new JTextArea(content);
        area.setBackground(BACKGROUND_COLOR);
        area.setFont(getTextFont());
        area.setOpaque(false);
        area.setLineWrap(true);
        return area;
    }
    public static JEditorPane createEditorPane(String content){
        JEditorPane pane = new JEditorPane("text/html", content);
        pane.setEditable(false);
        HTMLDocument doc = (HTMLDocument) pane.getDocument();
        doc.setBase(Utils.class.getResource(BASE));
        pane.setBackground(BACKGROUND_COLOR);
        pane.setOpaque(false);

        return pane;
    }

    public static JLabel createLabel(String privacyNotice) {
        JLabel label = new JLabel(privacyNotice);
        label.setBackground(BACKGROUND_COLOR);
        label.setOpaque(false);
        label.setAlignmentX(0.5f);
        label.setFont(getLabelFont());
        return label;
    }

    public static Font getLabelFont(){
        return new Font("Arial",Font.BOLD,32);
    }

    public static Font getTextFont(){
        return new Font("Arial",Font.PLAIN,24);
    }
}
