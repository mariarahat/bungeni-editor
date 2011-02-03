/*
 * panelEditDocumentMetadata.java
 *
 * Created on October 31, 2007, 10:54 PM
 */

package org.bungeni.editor.metadata;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.WindowConstants;

/**
 *
 * @author  Administrator
 */
public class panelEditDocumentMetadata extends javax.swing.JPanel {
    private DocumentMetadata theMetadata;
    private JDialog theParent;
    private int theControlModeType;
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(panelEditDocumentMetadata.class.getName());
   
    public static int CONTROL_TYPE_DATETIME = 0 ;
    public static int CONTROL_TYPE_STRING = 1 ;
    
    private Date ctlDateValue;
    private String ctlStringValue;
    private boolean cancelClicked = false;
    
    /** Creates new form panelEditDocumentMetadata */
    public panelEditDocumentMetadata(DocumentMetadata obj, JDialog p) {
        initComponents();
        theMetadata = obj;
        theParent = p;
        if (obj.getDataType().equals("string"))
            theControlModeType = CONTROL_TYPE_STRING;
        else if (obj.getDataType().equals("datetime"))
            theControlModeType = CONTROL_TYPE_DATETIME;
        setVisibleControls (theControlModeType);
        setControlData(theControlModeType);
    }
    
    private void setVisibleControls (int theType) {
       if (theType == CONTROL_TYPE_DATETIME) {
       
           this.editDateLbl.setVisible(true);
           this.editDateTxt.setVisible(true);
           this.editStringLbl.setVisible(false);
           this.editStringTxt.setVisible(false);
      
       } else if (theType == CONTROL_TYPE_STRING) {
           
           this.editDateLbl.setVisible(false);
           this.editDateTxt.setVisible(false);
           this.editStringLbl.setVisible(true);
           this.editStringTxt.setVisible(true);
       }
    }
    
    private void setControlData (int controlModeType) {
         try {
            if (controlModeType == CONTROL_TYPE_DATETIME) {
                String metaValue = this.theMetadata.getValue().trim();
                SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd");
                if (metaValue.length() == 0)
                    this.editDateTxt.setDate(new Date());
                else
                    this.editDateTxt.setDate(formatter.parse(metaValue));
            } else if (controlModeType == CONTROL_TYPE_STRING) {
                String metaValue = this.theMetadata.getValue();
                this.editStringTxt.setText(metaValue);
            }
       } catch (ParseException ex) {
                log.debug("setControlData : " + ex.getMessage());
            }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        editStringLbl = new javax.swing.JLabel();
        editStringTxt = new javax.swing.JTextField();
        editDateLbl = new javax.swing.JLabel();
        editDateTxt = new org.jdesktop.swingx.JXDatePicker();
        btnApply = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        editStringLbl.setText("Edit Text Value");

        editDateLbl.setText("Edit Date Value");

        btnApply.setText("Apply");
        btnApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApplyActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(editStringTxt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 133, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(editStringLbl, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 121, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(editDateLbl, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
                        .addContainerGap(142, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, editDateTxt, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                            .add(btnApply))
                        .add(16, 16, 16)
                        .add(btnCancel)
                        .add(59, 59, 59))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(editStringLbl)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(editStringTxt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(editDateLbl)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(editDateTxt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 10, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnApply)
                    .add(btnCancel)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
// TODO add your handling code here:
        cancelClicked = true;
        theParent.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    public boolean isCancelClicked(){
        return cancelClicked;
    }
    private void btnApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplyActionPerformed
        //TODO add your handling code here:
        if (theControlModeType == CONTROL_TYPE_DATETIME) {
             Date ctlValue = this.editDateTxt.getDate();
             SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
             theMetadata.setValue(formatter.format(ctlValue));
        } else if (theControlModeType == CONTROL_TYPE_STRING) {
             String ctlValue = this.editStringTxt.getText();
             theMetadata.setValue (ctlValue);
        }
        theParent.dispose();
    }//GEN-LAST:event_btnApplyActionPerformed
    
    public DocumentMetadata getDocumentMetadata() {
        return this.theMetadata;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApply;
    private javax.swing.JButton btnCancel;
    private javax.swing.JLabel editDateLbl;
    private org.jdesktop.swingx.JXDatePicker editDateTxt;
    private javax.swing.JLabel editStringLbl;
    private javax.swing.JTextField editStringTxt;
    // End of variables declaration//GEN-END:variables
        
    public static JDialog Launch(DocumentMetadata obj) {
          JDialog editMetaDlg;
             editMetaDlg = new JDialog();
             editMetaDlg.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
             //initDebaterecord.setPreferredSize(new Dimension(420, 300));
             panelEditDocumentMetadata panel = new panelEditDocumentMetadata(obj, editMetaDlg);
             editMetaDlg.getContentPane().add(panel);
             editMetaDlg.setTitle("Edit Metadata");
             editMetaDlg.pack();
             editMetaDlg.setLocationRelativeTo(null);
             editMetaDlg.setModal(true);
             editMetaDlg.setVisible(true);
             //selectSectionDlg.setAlwaysOnTop(true);  
             return editMetaDlg;
    }
    
}
