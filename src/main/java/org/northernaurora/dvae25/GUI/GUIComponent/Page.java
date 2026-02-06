package org.northernaurora.dvae25.GUI.GUIComponent;

import org.northernaurora.dvae25.GUI.DVGUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public abstract class Page extends JPanel {
    private boolean has_init = false;
    private Border parentBorder;
    private DVGUI dvguiParent;

    public Page(){
        super();
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(0,0,0,0));
        this.setParentBorder(null);
    }



    public void init(){
        this.has_init = true;
    }
    public abstract String getTitleString();

    public DVGUI getDvguiParent() {
        return dvguiParent;
    }

    public void setDvguiParent(DVGUI dvguiParent) {
        this.dvguiParent = dvguiParent;
    }


    public boolean Isinitialized() {
        return has_init;
    }

    public void onWindowUpdate(){
        
    }

    public Border getParentBorder() {
        return parentBorder;
    }

    public void setParentBorder(Border parentBorder) {
        this.parentBorder = parentBorder;
        if(this.getDvguiParent() != null){
            this.getDvguiParent().updateParentBorder();
        }
    }


}
