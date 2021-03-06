package org.bungeni.editor.panels.impl;

//~--- non-JDK imports --------------------------------------------------------

import org.bungeni.ooo.OOComponentHelper;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Component;

import javax.swing.JFrame;

/**
 * This is the interface used by launhable panel classes
 * @author Ashok Hariharan
 */
public interface ILaunchablePanel {

    /**
     * Set the OpenOffice component handle in the Panel class
     */
    public void setOOComponentHandle(OOComponentHelper ooComponent);

    /**
     * Get the current object handle as a "Component" type object
     */
    public Component getObjectHandle();

    public void initUI();

    public void setParentWindowHandle(JFrame c);

    public String getPanelTitle();

    public JFrame getParentWindowHandle();
}
