
package org.bungeni.editor.panels.loadable.refmgr;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import org.bungeni.editor.panels.impl.BaseLaunchablePanel;
import org.bungeni.editor.panels.impl.ILaunchablePanel;
import org.jdesktop.swingx.JXTaskPane;

/**
 *
 * @author  Ashok
 */
public class referenceManager extends BaseLaunchablePanel {
    
    ArrayList<JXTaskPane> paneGroups = new ArrayList<JXTaskPane>(0);
    
    public final static String __TITLE__ = "Reference Manager";
    public final static Dimension FRAME_DIMENSION = new Dimension(498, 480);
    
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(referenceManager.class.getName());
    private String launchModeClass = "browseReferences";
    private final static String[] launchablePanelClasses = {
        "browseReferences",
        "brokenReferences",
        "externalReferences"
    };

    //AH-23-01-11 converted array to a map
    private final static Map<String,String> mapLaunchablePanelClasses = new HashMap<String,String>(){
        {
        put ("BROWSE_REF",      "browseReferences");
        put ("BROKEN_REF",      "brokenReferences");
        put ("EXTERNAL_REF",    "externalReferences");
        }
    };

    /** Creates new form referenceManager */
    public referenceManager() {
       // initComponents();
    }
    
    public void setLaunchMode(String launchMode) {
        this.launchModeClass = launchMode;
    }

    private void initPaneGroups(){
        initPaneGroup(mapLaunchablePanelClasses.get("BROWSE_REF"));
        initPaneGroup(mapLaunchablePanelClasses.get("BROKEN_REF"));
        initPaneGroup(mapLaunchablePanelClasses.get("EXTERNAL_REF"));
    }


    //AH-23-01-11 removed default initializer below and added a generic initializer for the panes
    /***
    private void initPaneGroups(){
        try {
        for (String launchablePane : launchablePanelClasses) {
            
            JTaskPaneGroup jtpGrp = new JTaskPaneGroup();
            
            ILaunchablePanel panel = org.bungeni.editor.panels.factory.LaunchablePanelFactory.getPanelClass(launchablePane);
            panel.setOOComponentHandle(ooDocument);
            panel.setParentWindowHandle(parentFrame);
            panel.initUI();
            
            jtpGrp.add(panel.getObjectHandle());
            jtpGrp.setTitle(panel.getPanelTitle());
            this.taskPane.add(jtpGrp);
            paneGroups.add(jtpGrp);
            if (this.launchModeClass.equals(launchablePane)) {
                //expand this window
                jtpGrp.setExpanded(true);
            } else {
                jtpGrp.setExpanded(false);
            }
        
        }
        } catch (Exception ex) {
            log.error("initPaneGroups : " + ex.getMessage());
            log.error("initPaneGroups : " + CommonExceptionUtils.getStackTrace(ex));
        }
    }

   **/


    /**
     * Defines a launchable pane
     * @param launchablePane
     */
    private void initPaneGroup(String launchablePane){
            JXTaskPane jtpGrp = new JXTaskPane();

            ILaunchablePanel panel = org.bungeni.editor.panels.factory.LaunchablePanelFactory.getPanelClass(launchablePane);
            panel.setOOComponentHandle(ooDocument);
            panel.setParentWindowHandle(parentFrame);
            panel.initUI();

            jtpGrp.add(panel.getObjectHandle());
            jtpGrp.setTitle(panel.getPanelTitle());
            this.taskPane.add(jtpGrp);
            paneGroups.add(jtpGrp);
            if (this.launchModeClass.equals(launchablePane)) {
                //expand this window
                jtpGrp.setCollapsed(false);
            } else {
                jtpGrp.setCollapsed(true);
            }
    }

    
    private void init() {
        initPaneGroups();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        taskPane = new org.jdesktop.swingx.JXTaskPaneContainer();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(taskPane, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(taskPane, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXTaskPaneContainer taskPane;
    // End of variables declaration//GEN-END:variables
   
    public static void main (String[] args) {
            JFrame f  = new JFrame("Hello");
            f.add(new referenceManager());
                f.setAlwaysOnTop(true);
                f.setSize(new Dimension(498, 440));
               f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
               f.setVisible(true);
         
    }

    @Override
    public Component getObjectHandle() {
        return this;
    }

    @Override
    public void initUI() {
      initComponents();
      init();
    }

    @Override
    public String getPanelTitle() {
        return __TITLE__;
    }
}
