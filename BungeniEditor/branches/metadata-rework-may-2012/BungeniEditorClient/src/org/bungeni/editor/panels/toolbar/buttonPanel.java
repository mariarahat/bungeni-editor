/*
 * buttonPanel.java
 *
 * Created on Apr 27, 2009, 11:58:51 AM
 */

package org.bungeni.editor.panels.toolbar;

import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingConstants;

/**
 *
 * @author Ashok Hariharan
 */
public class buttonPanel extends javax.swing.JPanel {
    private BungeniToolbarActionElement actionElement = null;
    /** Creates new form buttonPanel */
    public buttonPanel() {
        initComponents();
    }

    /**
     * Sets the button text for the button action and adds an action listener to it.
     * @param text - the display text for the button
     * @param btnListener - the action listener associated wiht the button
     */
    public buttonPanel(String text, String tooltipText, ActionListener btnListener, BungeniToolbarActionElement aElement) {
        this();
        setButtonText(text);
        setButtonTooltip(tooltipText);
        this.btnAction.setHorizontalAlignment(SwingConstants.LEADING);
        setButtonActionListener(btnListener);
        setActionElement(aElement);
    }

    public BungeniToolbarActionElement getActionElement() {
        return actionElement;
    }

    private  void setActionElement(BungeniToolbarActionElement aElement) {
        this.actionElement = aElement;
    }

    private void setButtonText(String text) {
            btnAction.setText(text);
    }

   private void setButtonTooltip(String text){
       this.btnAction.setToolTipText(text);
   }

   private void setButtonActionListener(ActionListener btnListener) {
        this.btnAction.addActionListener(btnListener);
    }

    public void enableActionButton (boolean bState) {
        this.btnAction.setEnabled(bState);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAction = new javax.swing.JButton();

        btnAction.setBackground(new java.awt.Color(143, 204, 48));
        btnAction.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        btnAction.setText("?");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAction, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnAction)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAction;
    // End of variables declaration//GEN-END:variables

}
