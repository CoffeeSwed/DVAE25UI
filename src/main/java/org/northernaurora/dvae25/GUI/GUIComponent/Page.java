package org.northernaurora.dvae25.GUI.GUIComponent;

import org.northernaurora.dvae25.GUI.DVGUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public abstract class Page extends JPanel {
    private boolean has_init = false;

    public Page(){
        super();
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(0,0,0,0));
    }



    public void init(){
        this.has_init = true;
    }
    public abstract String getTitleString();
    private DVGUI dvguiParent;

    public DVGUI getDvguiParent() {
        return dvguiParent;
    }

    public void setDvguiParent(DVGUI dvguiParent) {
        this.dvguiParent = dvguiParent;
    }


    public boolean Isinitialized() {
        return has_init;
    }

    public void onWindowResize(Dimension newSize){
        
    }

}
