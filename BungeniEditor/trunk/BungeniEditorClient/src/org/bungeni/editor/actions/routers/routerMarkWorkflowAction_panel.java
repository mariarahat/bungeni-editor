/*
 * routerMarkWorkflowAction_panel.java
 *
 * Created on March 12, 2009, 3:08 PM
 */

package org.bungeni.editor.actions.routers;

import java.awt.Component;
import java.awt.Window;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.bungeni.editor.actions.toolbarAction;
import org.bungeni.editor.selectors.SelectorDialogModes;
import org.bungeni.error.BungeniValidatorState;
import org.bungeni.ooo.OOComponentHelper;
import org.bungeni.ooo.ooDocMetadata;
import org.bungeni.utils.BungeniEditorProperties;

/**
 *
 * @author  undesa
 */
public class routerMarkWorkflowAction_panel extends JPanel implements IRouterSelectorPanel {
    JFrame parentFrame;
    Window containerFrame;
    OOComponentHelper ooDocument;
    //toolbarAction theAction;
    toolbarAction theSubAction;
    SelectorDialogModes dialogMode;
    
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(routerMarkWorkflowAction_panel.class.getName());
    SimpleDateFormat attributeDateFormat;
    /** Creates new form routerMarkWorkflowAction_panel */
    public routerMarkWorkflowAction_panel() {
        try {
            initComponents();
            String attribDateFormat = BungeniEditorProperties.getEditorProperty("attributeDateFormat");
            attributeDateFormat = new SimpleDateFormat(attribDateFormat);
        } catch (Exception ex) {
            log.error("routerMarkWorkflowAction_panel : " + ex.getMessage());
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cboActionType = new javax.swing.JComboBox();
        lblActionType = new javax.swing.JLabel();
        dtActionDate = new org.jdesktop.swingx.JXDatePicker();
        lbActionDate = new javax.swing.JLabel();
        btnApply = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        cboActionType.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        cboActionType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Hearing", "Amendment", "Postponement" }));

        lblActionType.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/bungeni/editor/actions/routers/Bundle"); // NOI18N
        lblActionType.setText(bundle.getString("routerMarkWorkflowAction_panel.lblActionType.text")); // NOI18N

        dtActionDate.setFont(new java.awt.Font("DejaVu Sans", 0, 10));

        lbActionDate.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        lbActionDate.setText(bundle.getString("routerMarkWorkflowAction_panel.lbActionDate.text")); // NOI18N

        btnApply.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        btnApply.setText(bundle.getString("routerMarkWorkflowAction_panel.btnApply.text")); // NOI18N
        btnApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApplyActionPerformed(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        btnCancel.setText(bundle.getString("routerMarkWorkflowAction_panel.btnCancel.text")); // NOI18N
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cboActionType, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblActionType))
                    .addComponent(btnApply, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbActionDate)
                    .addComponent(dtActionDate, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblActionType)
                    .addComponent(lbActionDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboActionType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtActionDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnApply)
                    .addComponent(btnCancel)))
        );
    }// </editor-fold>//GEN-END:initComponents

private void btnApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplyActionPerformed
// TODO add your handling code here:
    try {
        String actionType = this.cboActionType.getSelectedItem().toString();
        Date actionDate = this.dtActionDate.getDate();
        //first we build the metadata variable and then we apply the metadata
        //then we check if it already exists
        //if it does not exist, we create a reference to it
        //it it exists, we apply the metadata and then create reference to it.
        if (makeMetadataEntry(actionType, actionDate))
            makeAndInsertReference(actionType, actionDate);
        containerFrame.dispose();
    } catch (Exception ex) {
        log.error("btnApplyActionPerformed : "+ ex.getMessage());
    }
}//GEN-LAST:event_btnApplyActionPerformed


public static final String BUNGENI_WORKFLOW_EVENT_PREFIX = "BungeniWorkflowEvent:";

public boolean makeMetadataEntry(String actionType, Date actionDate) {
    boolean bMetaEntry = false;
    try {
            String sActionname = theSubAction.action_value();
            String sMetaname = BUNGENI_WORKFLOW_EVENT_PREFIX + sActionname;
            String strActionDate = attributeDateFormat.format(actionDate);
            sMetaname = sMetaname + ":"+ strActionDate + ":" + actionType;
            String metaValue = sActionname + "~" + actionType + "~" + strActionDate ;
            if (ooDocument.propertyExists(sMetaname)) {
                bMetaEntry = true;
            } else {
                ooDocMetadata docMeta = new ooDocMetadata (ooDocument);
                docMeta.AddProperty(sMetaname, metaValue);
            }
        bMetaEntry = true;
    } catch (Exception ex) {
        
        log.error("makeMetadataEntry :" + ex.getMessage());
    } finally {
        return bMetaEntry;
    }
}

/**
 * The default format for the Action reference is as follows  :
 * BungeniWorkflowEvent:actionName; actionType; actionDate; #serialNo;
 * @param actionType
 * @param actionDate
 */
public void makeAndInsertReference(String actionType, Date actionDate){
        //the action name is just some descriptive text about the action
        String actionName = theSubAction.action_value();
        String strActionDate = attributeDateFormat.format(actionDate);
        String fullRefString = BUNGENI_WORKFLOW_EVENT_PREFIX  + actionName + ":" + strActionDate +  ":" + actionType ;
        String documentRefString = fullRefString + " ;#1";
        int i = 1;
        while (ooDocument.getReferenceMarks().hasByName(documentRefString) ) {
            documentRefString = fullRefString + " ;#" + ++i;
        }
        //substitute the subAction value with the documentRefstring as we are chaining to another router action to set the refereced
        theSubAction.setActionValue(documentRefString);
        routerCreateReference rcf = new routerCreateReference();
        // BungeniValidatorState bvState = rcf.route_TextSelectedInsert(theAction, theSubAction, parentFrame, ooDocument);
        BungeniValidatorState bvState = rcf.route_TextSelectedInsert(theSubAction, parentFrame, ooDocument);
}

private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
// TODO add your handling code here:
    containerFrame.dispose();
}//GEN-LAST:event_btnCancelActionPerformed

    @Override
    public void initialize() {
        
    }

    @Override
    public Component getPanelComponent() {
        return this;
    }

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApply;
    private javax.swing.JButton btnCancel;
    private javax.swing.JComboBox cboActionType;
    private org.jdesktop.swingx.JXDatePicker dtActionDate;
    private javax.swing.JLabel lbActionDate;
    private javax.swing.JLabel lblActionType;
    // End of variables declaration//GEN-END:variables

    public void initVariables(OOComponentHelper ooDoc, JFrame parentFrm, toolbarAction aSubAction, SelectorDialogModes dlgMode) {
        this.ooDocument = ooDoc;
        this.parentFrame = parentFrm;
        this.theSubAction = aSubAction;
        this.dialogMode = dlgMode;
    }

    public void setContainerFrame(Window frame) {
        this.containerFrame = frame;
    }

    public void setOOComponentHelper (OOComponentHelper ooDoc) {
        this.ooDocument = ooDoc;
    }

}
