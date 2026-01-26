package org.northernaurora.dvae25.GUI.GUIComponent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PageContainer extends JPanel implements Scrollable, PropertyChangeListener {
    private static final Logger logger = LogManager.getLogger(PageContainer.class);
    private Page activePage;
    public PageContainer() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
    }

    /**
     * Let preferred size expand naturally based on children
     */
    @Override
    public Dimension getPreferredSize() {
        int width = 0;
        int height = 0;
        for (Component comp : getComponents()) {
            Dimension d = comp.getPreferredSize();
            width = Math.max(width, d.width);
            height += d.height;
        }
        if (this.getMaximumSize().getWidth() > width){
            width = (int)this.getMaximumSize().getWidth();
        }
        /*if (this.getMaximumSize().getHeight() > height){
            height = (int)this.getMaximumSize().getHeight();
        }*/
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
            this.remove(this.getActivePage());

        }
        this.activePage = activePage;
        activePage.addPropertyChangeListener("background",this);
        this.setBackground(activePage.getBackground());
        this.add(activePage,BorderLayout.CENTER);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Color oldColor = (Color) evt.getOldValue();
        Color newColor = (Color) evt.getNewValue();

        logger.info("Changed background color to : "+newColor);
        this.setBackground(newColor);
    }
}