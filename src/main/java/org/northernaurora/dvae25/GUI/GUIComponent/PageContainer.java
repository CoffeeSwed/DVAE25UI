package org.northernaurora.dvae25.GUI.GUIComponent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PageContainer extends JPanel implements Scrollable, PropertyChangeListener, ComponentListener {
    private static final Logger logger = LogManager.getLogger(PageContainer.class);
    private Page activePage;
    public PageContainer() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBorder(null);
        this.addComponentListener(this);

    }

    /**
     * Let preferred size expand naturally based on children
     */
    @Override
    public Dimension getPreferredSize() {
        int width = 0;
        int height = 0;
        for (Component comp : getComponents()) {
            comp.revalidate();
            Dimension d = comp.getPreferredSize();
            width += d.width;
            height = Math.max(height,d.height);
        }
        return new Dimension(width, height);
    }

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return getPreferredSize();
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 16;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 64;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return true;  // Let width fill the JScrollPane
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return false; // Height can scroll if needed
    }



    public Page getActivePage() {
        return activePage;
    }

    public void setActivePage(Page activePage) {
        if (this.getActivePage() != null){
            this.getActivePage().removePropertyChangeListener("background",this);
        }
        this.removeAll();
        activePage.addPropertyChangeListener("background",this);
        this.setBackground(activePage.getBackground());
        activePage.setAlignmentX(0.5f);
        this.add(Box.createHorizontalGlue());
        this.add(activePage);
        this.add(Box.createHorizontalGlue());
        this.activePage = activePage;
        this.refresh();


    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Color oldColor = (Color) evt.getOldValue();
        Color newColor = (Color) evt.getNewValue();

        this.setBackground(newColor);
    }

    public void refresh(){
        this.revalidate();
        if(this.getActivePage() != null){

            if(this.getActivePage().getMaximumSize().equals(this.getPreferredSize()) || this.getPreferredSize() == null){
                return;
            }
            this.getActivePage().setMaximumSize(this.getPreferredSize());
        }
        this.getActivePage().repaint();

    }

    public void resized(){
        this.refresh();
    }

    @Override
    public void componentResized(ComponentEvent e) {
        if(e.getSource() == this) {
            this.refresh();
        }
    }

    @Override
    public void componentMoved(ComponentEvent e) {

        //this.refresh();
    }

    @Override
    public void componentShown(ComponentEvent e) {
        //this.refresh();
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        //this.refresh();
    }
}