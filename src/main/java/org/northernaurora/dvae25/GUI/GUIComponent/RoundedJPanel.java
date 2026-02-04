package org.northernaurora.dvae25.GUI.GUIComponent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RoundedJPanel extends JPanel {
    private int radius;
    private int[] insets = {0,0,0,0};
    public RoundedJPanel(int radius){
        super();
        this.setOpaque(false);
        this.setRadius(radius);
        this.setBorder(new EmptyBorder(0,0,0,0));
    }



    /**
     * @param radius
     * Will replace indents
     */
    private void setRadius(int radius) {
        this.setRadius(radius,true);
    }

    public int getRadius() {
        return radius;
    }



    public void setRadius(int radius, boolean replace_indents) {
        this.radius = radius;
        if (replace_indents){
            int value = getRadius() / 2;
            this.setInsets(value,value,value,value);
        }
    }



    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getRadius(), getRadius());

        super.paintComponent(g);
    }



    @Override
    public Insets getInsets() {
        return new Insets(this.insets[0], this.insets[1], this.insets[2], this.insets[3]);
    }

    public void setInsets(int top, int left, int bottom, int right){
        this.insets[0] = top;
        this.insets[1] = left;
        this.insets[2] = bottom;
        this.insets[3] = right;
    }

    public int getDefaultInset(){
        return this.getRadius()/2;
    }
}
