package org.northernaurora.dvae25.GUI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.northernaurora.dvae25.GUI.GUIComponent.Page;
import org.northernaurora.dvae25.GUI.GUIComponent.PageContainer;
import org.northernaurora.dvae25.GUI.GUIComponent.PageContainerListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Stack;

public class DVGUI implements PropertyChangeListener, PageContainerListener, ComponentListener {
    private static final Logger logger = LogManager.getLogger(DVGUI.class);

    private Dimension windowSize;
    private JFrame activeFrame = null;
    private Stack<Page> pageStack;
    private PageContainer pageContainer;
    private Color background;
    private Component upperGlue = Box.createVerticalGlue() ,lowerGlue = Box.createVerticalGlue();
    public DVGUI() {
        this.initialize();
    }


    public Container getActiveContentPane() {

        return this.getActiveJFrame().getContentPane();
    }

    public JFrame getActiveJFrame() {
        return activeFrame;
    }

    public Stack<Page> clonePageStack() {
        return (Stack<Page>) pageStack.clone();
    }

    private Stack<Page> getPageStack(){
        return this.pageStack;
    }

    private void setPageStack(Stack<Page> pageStack) {
        this.pageStack = pageStack;
    }

    private PageContainer getPageContainer() {
        return pageContainer;
    }

    /**
     * @return new pageContainer
     */
    private PageContainer newPageContainer() {
        if (this.pageContainer != null){
            this.pageContainer.removePropertyChangeListener("background",this);
            this.pageContainer.removeListener(this);
        }
        this.pageContainer = new PageContainer();
        this.pageContainer.addPropertyChangeListener("background",this);
        pageContainer.setAlignmentY(Component.CENTER_ALIGNMENT);
        pageContainer.addListener(this);
        return this.pageContainer;
    }

    private void initialize() {
        logger.debug("Creating new Active Frame");
        this.activeFrame = new JFrame();
        this.getActiveContentPane().setLayout(new BoxLayout(this.getActiveContentPane(),BoxLayout.Y_AXIS));
        this.getActiveJFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getActiveJFrame().setSize(new Dimension(900, 900));
        this.setPageStack(new Stack<>());
        this.getActiveJFrame().setVisible(true);
        this.setScrollable(false);
        this.getActiveContentPane().addComponentListener(this);
        this.newPageContainer();
        this.setScrollable(true);
        this.activeFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        logger.debug("Created new Active Frame");

    }


    public void addPage(Page page) {
        logger.debug("Setting page to : "+page.getClass().toString());
        page.setDvguiParent(this);
        this.getActiveJFrame().setTitle(page.getTitleString());

        if (this.getPageStack().size() != 0){
            logger.debug("Removing activate page!");

            Page current = this.getPageStack().lastElement();
            logger.debug("Removed activate page!");
        }
        if (!page.Isinitialized())
            page.init();
        this.getPageStack().addLast(page);
        this.getPageContainer().setActivePage(page);
        logger.debug("Set page to : "+page.getClass().toString());

        this.revalidate();
        this.repaint();



    }

    public void popPage(){
        if (this.getPageStack().size() > 1){
            this.getPageStack().pop();
            this.addPage(this.getPageStack().pop());
        }
    }



    public void setScrollable(boolean bool){
        if ((this.isScrollable() && !bool) || (!this.isScrollable() && bool)) {

            this.getActiveContentPane().removeAll();
            this.getActiveContentPane().add(this.upperGlue, 0.33f);
            if(bool){
                JScrollPane jScrollPane = new JScrollPane(this.getPageContainer());
                jScrollPane.setBorder(new EmptyBorder(0,0,0,0));
                jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                jScrollPane.getViewport().setBackground(this.getBackground());
                this.getActiveContentPane().add(jScrollPane,0.0f);//, BorderLayout.CENTER);
            }else{

                this.getActiveContentPane().add(this.getPageContainer());//, BorderLayout.CENTER);
            }
            this.getActiveContentPane().add(this.lowerGlue);


        }


    }

    public boolean isScrollable(){
        return (this.getScrollable() != null);
    }

    public JScrollPane getScrollable(){
        for (Component comp : this.getActiveContentPane().getComponents()){
            if (comp.getClass() == JScrollPane.class){
                return (JScrollPane) comp;
            }
        }
        return null;
    }



    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Color newColor = (Color) evt.getNewValue();
        this.setBackground(newColor);
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
        if(this.getScrollable() != null){
            this.getScrollable().getViewport().setBackground(this.getBackground());
        }
        this.getActiveContentPane().setBackground(this.getBackground());
    }

    public Dimension getWindowSize() {
        return this.getActiveContentPane().getBounds().getSize();
    }

    public void revalidate(){
        this.getActiveContentPane().revalidate();
    }

    public void repaint(){
        this.getActiveContentPane().repaint();
    }

    @Override
    public void newSize(Dimension newSize) {
        //this.revalidate();
        //this.repaint();
        logger.info("New dimension : "+newSize);
    }


    @Override
    public void componentResized(ComponentEvent e) {
        this.getPageContainer().handleComponentSize();
        this.getPageContainer().registerWindowSizeChange(this.getWindowSize());

    }

    @Override
    public void componentMoved(ComponentEvent e) {
        this.getPageContainer().handleComponentSize();
    }

    @Override
    public void componentShown(ComponentEvent e) {
        this.getPageContainer().handleComponentSize();
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        this.getPageContainer().handleComponentSize();
    }
}

