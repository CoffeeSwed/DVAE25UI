package org.northernaurora.dvae25.GUI.GUIComponent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class PageContainer extends JPanel implements Scrollable, PropertyChangeListener, ComponentListener {
    private static final Logger logger = LogManager.getLogger(PageContainer.class);
    private Page activePage;
    private ArrayList<PageContainerListener> listeners = new ArrayList<>();
    private Component leftGlue = Box.createHorizontalGlue();
    private Component rightGlue = Box.createHorizontalGlue();
    public PageContainer() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBorder(null);
        this.addComponentListener(this);
        this.leftGlue.addComponentListener(this);
        this.rightGlue.addComponentListener(this);

    }

    public void removeListener(PageContainerListener listener){
        this.listeners.remove(listener);
    }

    public void addListener(PageContainerListener listener){
        if(!this.listeners.contains(listener)){
            this.listeners.add(listener);
        }
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
            this.getActivePage().removeComponentListener(this);
        }
        this.removeAll();
        activePage.addPropertyChangeListener("background",this);
        this.setBackground(activePage.getBackground());
        activePage.setAlignmentX(0.5f);
        this.add(this.leftGlue,0.5f);
        this.add(activePage);
        this.add(this.rightGlue,0.5f);
        this.activePage = activePage;
        this.handleComponentSize();
        activePage.addComponentListener(this);


    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Color oldColor = (Color) evt.getOldValue();
        Color newColor = (Color) evt.getNewValue();

        this.setBackground(newColor);
    }

    public void handleComponentSize(){
        SwingUtilities.invokeLater(() -> {
            if(this.getActivePage() != null){

                Dimension newSize = this.getPreferredSize();
                if(this.getActivePage().getMaximumSize().equals(newSize) || newSize == null){
                    return;
                }
                this.getActivePage().setMaximumSize(newSize);
                for (PageContainerListener listener : this.listeners){
                    listener.newSize(newSize);
                }
                SwingUtilities.invokeLater(() -> {
                    this.getActivePage().onWindowUpdate();
                    SwingUtilities.invokeLater(() -> {
                        this.handleComponentSize();
                    });
                });



            }
        });

    }

    public void registerWindowSizeChange(Dimension newSize){
        SwingUtilities.invokeLater(() -> {
            if(this.getActivePage() != null){


                if(this.getActivePage() != null){
                    this.getActivePage().onWindowUpdate();
                }

            }
        });
    }

    @Override
    public void componentResized(ComponentEvent e) {
        this.handleComponentSize();

    }

    @Override
    public void componentMoved(ComponentEvent e) {
        this.handleComponentSize();
    }

    @Override
    public void componentShown(ComponentEvent e) {
        this.handleComponentSize();
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        this.handleComponentSize();
    }
}