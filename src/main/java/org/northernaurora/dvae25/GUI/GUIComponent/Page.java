package org.northernaurora.dvae25.GUI.GUIComponent;

import javax.swing.*;

public abstract class Page extends JPanel {
    public Page(){
        super();
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
    }



    public abstract void init();
    public abstract String getTitleString();
}
