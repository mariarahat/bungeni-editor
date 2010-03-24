package org.bungeni.trackchanges;

//~--- non-JDK imports --------------------------------------------------------


//~--- JDK imports ------------------------------------------------------------

import java.util.HashMap;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Ashok Hariharan
 */
public class trackChangesMain extends javax.swing.JPanel implements IChangesContainer {
    private static org.apache.log4j.Logger log         =
        org.apache.log4j.Logger.getLogger(trackChangesMain.class.getName());
    public static JFrame                   parentFrame = null;
    ResourceBundle bundleBase = java.util.ResourceBundle.getBundle("org/bungeni/trackchanges/Bundle");
    int m_prevTabIndex = 0;
    /** Creates new form trackChangesMain */
    public trackChangesMain() {
        initComponents();
        init_Tabs();
    }

    private void init_Tabs() {
        panelTrackChangesOverview overviewPanel    = new panelTrackChangesOverview(parentFrame);
        panelClerkOverview        clerkReviewPanel = new panelClerkOverview(parentFrame);
        panelConsolidateChanges consolidationPanel = new panelConsolidateChanges(parentFrame);

        this.tabContainer.addTab(bundleBase.getString("tabName.membersOverview"), overviewPanel);
        this.tabContainer.addTab(bundleBase.getString("tabName.clerkOverview"), clerkReviewPanel);
        this.tabContainer.addTab(bundleBase.getString("tabName.consolidationOverview"), consolidationPanel);

        this.tabContainer.getModel().addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
                System.out.println("Selected index = " + tabContainer.getSelectedIndex());
                // call update with empty hashmap
                updateCurrentPanel(new HashMap<String, Object>() {
                    {}
                });
                m_prevTabIndex = tabContainer.getSelectedIndex();
            }
        });
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabContainer = new javax.swing.JTabbedPane();
        btnClose = new javax.swing.JButton();

        tabContainer.setFont(new java.awt.Font("DejaVu Sans", 0, 11)); // NOI18N

        btnClose.setFont(new java.awt.Font("Lucida Grande", 0, 10));
        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tabContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(349, 349, 349)
                        .addComponent(btnClose)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClose))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        parentFrame.dispose();
        parentFrame = null;
    }//GEN-LAST:event_btnCloseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JTabbedPane tabContainer;
    // End of variables declaration//GEN-END:variables


    public void updatePanels(HashMap<String, Object> infomap) {

        // to be implemented
    }

    public void updateCurrentPanel(HashMap<String, Object> infomap) {
        //route the update call only when there is a programattic update,
        //not when someone clicks on a tab
        if (infomap.size() > 0 ) {
            ((IChangesPanel) this.tabContainer.getSelectedComponent()).updatePanel(infomap);
        }
    }

 

   

}
