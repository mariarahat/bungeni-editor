/*
 * panelReportByOrder.java
 *
 * Created on Jun 3, 2010, 12:09:47 PM
 */

package org.bungeni.trackchanges.ui;

import javax.swing.JFrame;

/**
 *
 * @author Ashok Hariharan
 */
public class panelReportByOrder extends panelChangesBase {
    private static org.apache.log4j.Logger log            =
        org.apache.log4j.Logger.getLogger(panelReportByOrder.class.getName());
    /** Creates new form panelReportByOrder */
    public panelReportByOrder() {
        super();
        initComponents();
    }

      public panelReportByOrder(JFrame parentFrm, String pName) {
           super(parentFrm, pName);
            PANEL_FILTER_REVIEW_STAGE = "ReportsView";
            PANEL_REVIEW_STAGE = "ClerkReview";
            initComponents();
            initialize();
            loadFilesFromFolder();
     }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollAmendments = new javax.swing.JScrollPane();
        listAmendmentsByorder = new javax.swing.JList();
        btnMoveUp = new javax.swing.JButton();
        btnMoveDown = new javax.swing.JButton();
        btnFinish = new javax.swing.JButton();

        listAmendmentsByorder.setFont(new java.awt.Font("DejaVu Sans", 0, 10)); // NOI18N
        listAmendmentsByorder.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        scrollAmendments.setViewportView(listAmendmentsByorder);

        btnMoveUp.setFont(new java.awt.Font("DejaVu Sans", 0, 10)); // NOI18N
        btnMoveUp.setText("Move UP");

        btnMoveDown.setFont(new java.awt.Font("DejaVu Sans", 0, 10)); // NOI18N
        btnMoveDown.setText("Move DOWN");

        btnFinish.setFont(new java.awt.Font("DejaVu Sans", 0, 10)); // NOI18N
        btnFinish.setText("Finish");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnMoveUp, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMoveDown)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFinish, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(298, 298, 298))
            .addComponent(scrollAmendments, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(scrollAmendments, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMoveUp)
                    .addComponent(btnMoveDown)
                    .addComponent(btnFinish))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFinish;
    private javax.swing.JButton btnMoveDown;
    private javax.swing.JButton btnMoveUp;
    private javax.swing.JList listAmendmentsByorder;
    private javax.swing.JScrollPane scrollAmendments;
    // End of variables declaration//GEN-END:variables

    private void initialize() {
       
    }

    

}
