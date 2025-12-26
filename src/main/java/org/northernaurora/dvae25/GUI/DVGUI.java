package org.northernaurora.dvae25.GUI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.northernaurora.dvae25.GUI.GUIComponent.Page;
import org.northernaurora.dvae25.GUI.GUIComponent.PageContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Stack;

public class DVGUI {
    private static Logger logger = LogManager.getLogger(DVGUI.class);


    private JFrame ActivateFrame = null;
    private Stack<Page> pageStack;
    private PageContainer pageContainer;
    public DVGUI() {
        this.initialize();




    }

    private Container getActiveContentPane() {

        return this.getActiveJFrame().getContentPane();
    }

    private JFrame getActiveJFrame() {
        return ActivateFrame;
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
        this.pageContainer = new PageContainer();
        return this.pageContainer;
    }

    private void initialize() {
        logger.debug("Creating new Active Frame");
        ActivateFrame = new JFrame();
        this.getActiveContentPane().setLayout(new BorderLayout());


        this.getActiveJFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getActiveJFrame().setSize(new Dimension(720, 480));
        this.setPageStack(new Stack<>());
        this.getActiveJFrame().setVisible(true);

        this.getActiveContentPane().add(this.newPageContainer(), BorderLayout.CENTER);
        logger.debug("Created new Active Frame");

    }


    public void addPage(Page page) {
        logger.debug("Setting page to : "+page.getClass().toString());
        page.init();
        this.getActiveJFrame().setTitle(page.getTitleString());

        if (this.getPageStack().size() != 0){
            logger.debug("Removing activate page!");

            Page current = this.getPageStack().lastElement();
            this.getPageContainer().remove(current);
            logger.debug("Removed activate page!");
        }
        this.getPageStack().addLast(page);
        this.getPageContainer().add(page, BorderLayout.CENTER);
        logger.debug("Set page to : "+page.getClass().toString());
        this.refresh();
    }
    /**
     * Repaints and revalidates.
     */
    public void refresh(){
        this.getActiveContentPane().revalidate();
        this.getActiveContentPane().repaint();
    }


}

