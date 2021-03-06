/*
 * QuestionText.java
 *
 * Created on August 12, 2008, 2:06 PM
 */

package org.bungeni.editor.selectors.debaterecord.motions;

import java.awt.Component;
import java.util.HashMap;
import org.bungeni.editor.selectors.BaseMetadataPanel;
import org.bungeni.extutils.CommonDocumentUtilFunctions;

/**
 *
 * @author  Ashok Hariharan
 */
public class MotionText extends BaseMetadataPanel {

    public static final String __PANEL_NAME__ = "MotionText";

    /** Creates new form QuestionText */
    public MotionText() {
        super();
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollQuestionText = new javax.swing.JScrollPane();
        txtMotionText = new javax.swing.JTextPane();
        lblQuestionText = new javax.swing.JLabel();
        btnPaste = new javax.swing.JButton();

        scrollQuestionText.setName("scroll_question_text"); // NOI18N

        txtMotionText.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        txtMotionText.setName("txt_question_text"); // NOI18N
        scrollQuestionText.setViewportView(txtMotionText);

        lblQuestionText.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/bungeni/editor/selectors/debaterecord/motions/Bundle"); // NOI18N
        lblQuestionText.setText(bundle.getString("MotionText.lblQuestionText.text")); // NOI18N
        lblQuestionText.setName("lbl_question_text"); // NOI18N

        btnPaste.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        btnPaste.setText(bundle.getString("MotionText.btnPaste.text")); // NOI18N
        btnPaste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPasteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblQuestionText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(btnPaste, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(scrollQuestionText, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuestionText)
                    .addComponent(btnPaste, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollQuestionText, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnPasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasteActionPerformed
        // TODO add your handling code here:
        String motionText = this.txtMotionText.getText();
        this.pasteTextIntoDocument(motionText);
    }//GEN-LAST:event_btnPasteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPaste;
    private javax.swing.JLabel lblQuestionText;
    private javax.swing.JScrollPane scrollQuestionText;
    private javax.swing.JTextPane txtMotionText;
    // End of variables declaration//GEN-END:variables
public String getPanelName() {
        return getName();
    }

    public Component getPanelComponent() {
        return this;
    }


    @Override
    public boolean doCancel() {
        return true;
    }

    @Override
    public boolean doReset() {
        return true;
    }

    @Override
    public boolean preFullEdit() {
        return true;
    }

    @Override
    public boolean processFullEdit() {
             String strText = this.txtMotionText.getText();
        CommonDocumentUtilFunctions.writeToClipboard(strText);
        return true;
    }

    @Override
    public boolean postFullEdit() {
        return true;
    }

    @Override
    public boolean preFullInsert() {
        return true;
    }

    @Override
    public boolean processFullInsert() {
        return true;
    }

    @Override
    public boolean postFullInsert() {
        return true;
    }

    @Override
    public boolean preSelectEdit() {
        return true;
    }

    @Override
    public boolean processSelectEdit() {
        return true;
    }

    @Override
    public boolean postSelectEdit() {
        return true;
    }

    @Override
    public boolean preSelectInsert() {
        return true;
    }

    @Override
    public boolean processSelectInsert() {
        //copy question text to clipboard
        String strText = this.txtMotionText.getText();
        CommonDocumentUtilFunctions.writeToClipboard(strText);
        /*Clipboard clipBrd = new Clipboard("Clipboard.QuestionText");
        Transferable copyToClipboard = new StringSelection(strText);
        clipBrd.setContents(copyToClipboard, null);*/
        return true;
    }
    
    

    @Override
    public boolean postSelectInsert() {
       return true;
    }

    @Override
    public boolean validateSelectedEdit() {
        return true;
    }

    @Override
    public boolean validateSelectedInsert() {
        return true;
    }

    @Override
    public boolean validateFullInsert() {
        return true;
    }

    @Override
    public boolean validateFullEdit() {
        return true;
    }
    @Override
    protected void initFieldsSelectedEdit() {
        return;
    }

    @Override
    protected void initFieldsSelectedInsert() {
        return;
    }

    @Override
    protected void initFieldsInsert() {
        return;
    }

    @Override
    protected void initFieldsEdit() {
        return;
    }
    
    @Override
    public boolean doUpdateEvent(){
        HashMap<String,String> selectionData = (getContainerPanel()).selectionData;   
        this.txtMotionText.setText(selectionData.get("MOTION_TEXT"));
        return true;
    }
}
