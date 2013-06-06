/*
 * routerCreateTabularMetadataReference_panel.java
 *
 * Created on February 25, 2009, 2:41 PM
 */

package org.bungeni.editor.actions.routers;

import java.awt.Component;
import java.awt.Window;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import org.bungeni.editor.actions.toolbarAction;
import org.bungeni.editor.metadata.TabularMetadataLoader;
import org.bungeni.editor.metadata.TabularMetadataLoader.TabularMetadataModel;
import org.bungeni.editor.selectors.SelectorDialogModes;
import org.bungeni.extutils.BungeniJTableMouseListener;
import org.bungeni.ooo.OOComponentHelper;

/**
 * This is a base class that implements a single abstract method which provides access to the selected metadata
 * It is not recommended to use this class directly but instead derive a clas from this and override the makeReference method.
 * @author  Ashok Hariharan
 */
public abstract class routerCreateTabularMetadataReference_panel extends javax.swing.JPanel implements IRouterSelectorPanel {
    JFrame parentFrame;
    Window containerFrame;
    OOComponentHelper ooDocument;
    toolbarAction theSubAction;
    SelectorDialogModes dialogMode;
    TabularMetadataModel selectedRowModel ;
      private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(routerCreateTabularMetadataReference_panel.class.getName());
 
    /** Creates new form routerCreateSideNote_panel */
    public routerCreateTabularMetadataReference_panel(OOComponentHelper ooDoc, JFrame pFrame, JFrame cFrame) {
        parentFrame = pFrame;
        containerFrame = cFrame;
        ooDocument = ooDoc;
        initComponents();
    }
    
    
    /** Creates new form routerCreateTabularMetadataReference_panel */
    public routerCreateTabularMetadataReference_panel() {
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

        scrollMetadataContainer = new javax.swing.JScrollPane();
        tblRefMetadata = new javax.swing.JTable();
        lblMetaReference = new javax.swing.JLabel();
        btnApply = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        tblRefMetadata.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        tblRefMetadata.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollMetadataContainer.setViewportView(tblRefMetadata);

        lblMetaReference.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/bungeni/editor/actions/routers/Bundle"); // NOI18N
        lblMetaReference.setText(bundle.getString("routerCreateTabularMetadataReference_panel.lblMetaReference.text")); // NOI18N

        btnApply.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        btnApply.setText(bundle.getString("routerCreateTabularMetadataReference_panel.btnApply.text")); // NOI18N
        btnApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApplyActionPerformed(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        btnCancel.setText(bundle.getString("routerCreateTabularMetadataReference_panel.btnCancel.text")); // NOI18N
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(btnApply, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                .addGap(94, 94, 94))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMetaReference, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollMetadataContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblMetaReference, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollMetadataContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnApply, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

private void btnApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplyActionPerformed
// TODO add your handling code here:
                  DefaultTableModel dtm = ((DefaultTableModel)tblRefMetadata.getModel());
                  Vector columns = selectedRowModel.columnVector;
                  int nRow = this.tblRefMetadata.getSelectedRow();
                  Vector vRowData = (Vector) dtm.getDataVector().elementAt(nRow);
                  makeAndInsertReference(dtm, columns, vRowData);
    
}//GEN-LAST:event_btnApplyActionPerformed

private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
// TODO add your handling code here:
     containerFrame.dispose();
}//GEN-LAST:event_btnCancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApply;
    private javax.swing.JButton btnCancel;
    private javax.swing.JLabel lblMetaReference;
    private javax.swing.JScrollPane scrollMetadataContainer;
    private javax.swing.JTable tblRefMetadata;
    // End of variables declaration//GEN-END:variables

    // !+ACTION_RECONF (rm, jan 2012) - aAction is parent to aSubAction
    public void initVariables(OOComponentHelper ooDoc, JFrame parentFrm, toolbarAction aSubAction, SelectorDialogModes dlgMode) {
       parentFrame = parentFrm;
       ooDocument = ooDoc;
       theSubAction = aSubAction;
       dialogMode = dlgMode;
    }

    public void initialize() {
        //override matisse generated table here - as we want to make the table cells read-only
        //by default JTable using a DefaultTableModel provides a JTable wiht editable cells
        tblRefMetadata = new JTable(){
            @Override
            public boolean isCellEditable(int row, int col) {
                //always return false since we want the table read-only
                return false;
            }
        };
        tblRefMetadata.setFont(new java.awt.Font("DejaVu Sans", 0, 10)); // NOI18N
        //connect the table to the scroll view
        scrollMetadataContainer.setViewportView(tblRefMetadata);
        //load the table model with the tabular document metadata
       selectedRowModel = TabularMetadataLoader.getTabularMetadataTableModel(ooDocument,theSubAction.action_value() );
        this.tblRefMetadata.setModel(selectedRowModel.tabularModel);
        this.tblRefMetadata.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //add listener for double click
        this.tblRefMetadata.addMouseListener(new BungeniJTableMouseListener() {
            @Override
            public void wasDoubleClicked(JTable tbl, int nRow, int nCol) {
                  DefaultTableModel dtm = ((DefaultTableModel)tbl.getModel());
                  Vector columns = selectedRowModel.columnVector;
                  Vector vRowData = (Vector) dtm.getDataVector().elementAt(nRow);
                  makeAndInsertReference(dtm, columns, vRowData);
            }
            @Override
            public void wasSingleClicked(JTable tbl, int nRow, int nCol) {
               return;
            }
        });
        
    }

    /**
     * This function takes an input as vector row of seleced table data
     * and creates a reference to the metadata detialed by the Vector row.
     * 
     * The name of the metadata prefix is passed into the class as the subAction.sub_action_value()
     * 
     * @param vData - row of selected table data
     */
    abstract public void makeAndInsertReference(DefaultTableModel model, Vector vCols, Vector vRows);
    
    
    public void setContainerFrame(Window frame) {
       this.containerFrame = frame;
    }

    public Component getPanelComponent() {
       return this;
    }

    public void setOOComponentHelper(OOComponentHelper ooDoc) {
        this.ooDocument = ooDoc;
    }

}
