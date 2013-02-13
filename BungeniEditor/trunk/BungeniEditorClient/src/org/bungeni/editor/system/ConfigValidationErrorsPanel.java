/*
 * Copyright (C) 2013 PC
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.bungeni.editor.system;

import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import nl.jj.swingx.gui.modal.JModalFrame;
import org.bungeni.editor.system.ValidateConfiguration.ConfigInfo;
import org.bungeni.editor.system.ValidateConfiguration.ConfigInfo.ErrorInfo;
import org.bungeni.ooo.utils.CommonExceptionUtils;
import org.xml.sax.SAXParseException;

/**
 *
 * @author PC
 */
public class ConfigValidationErrorsPanel extends javax.swing.JPanel {

    JModalFrame parentDlg = null;
    HashMap<String, ConfigInfo> configInfo = null;
    
    /**
     * Creates new form ConfigValidationErrorsPanel
     */
    public ConfigValidationErrorsPanel(JModalFrame dlg, HashMap<String, ConfigInfo> configInfo) {
        initComponents();
        this.parentDlg  = dlg;
        this.configInfo = configInfo;
        initPanel();
    }

    public void initPanel(){
        this.listExceptions.setCellRenderer(new WrappedCellRenderer(600));
        this.txtExceptionInfo.setContentType("text/html");
        List<ErrorInfo> typesWithErrors = new ArrayList<ErrorInfo>(0);
        Iterator<String> types = configInfo.keySet().iterator();
        while(types.hasNext()) {
            String aType = types.next();
            if (configInfo.get(aType).hasExceptions()){
                typesWithErrors.addAll(configInfo.get(aType).getExceptions());
            }
        }
        this.cboErrorTypes.setModel(new DefaultComboBoxModel(typesWithErrors.toArray()));
        this.cboErrorTypes.setSelectedIndex(0);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cboErrorTypes = new javax.swing.JComboBox();
        btnClose = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        splitPane = new javax.swing.JSplitPane();
        scrollExcept = new javax.swing.JScrollPane();
        listExceptions = new javax.swing.JList();
        scrollTxtExcept = new javax.swing.JScrollPane();
        txtExceptionInfo = new javax.swing.JTextPane();

        cboErrorTypes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboErrorTypes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboErrorTypesActionPerformed(evt);
            }
        });

        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTitle.setText("The Active Configuration has Errors !!");

        splitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        listExceptions.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        scrollExcept.setViewportView(listExceptions);

        splitPane.setLeftComponent(scrollExcept);

        scrollTxtExcept.setViewportView(txtExceptionInfo);

        splitPane.setRightComponent(scrollTxtExcept);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(splitPane)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnClose))
                    .addComponent(lblTitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(cboErrorTypes, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboErrorTypes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(splitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClose))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    ExceptionsListModel model = null;
    
    private void cboErrorTypesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboErrorTypesActionPerformed
        // TODO add your handling code here:
        ErrorInfo sType = (ErrorInfo) this.cboErrorTypes.getSelectedItem();
        List<SAXParseException> exceptions = sType.exceptions;
        model = new ExceptionsListModel(exceptions);
        this.listExceptions.setModel(model);
        this.listExceptions.addListSelectionListener(new ListSelectionListener(){

            public void valueChanged(ListSelectionEvent e) {
                 if (e.getValueIsAdjusting() == false) {
                    if (listExceptions.getSelectedIndex() != -1) {
                        int nIndex = listExceptions.getSelectedIndex();
                        SAXParseException exception = model.getItem(nIndex);
                        txtExceptionInfo.setText(
                                "<html>" +
                                 "<h2> Message </h2>" + 
                                 exception.getMessage() +
                                 "<h2> Stack Trace </h2>" +
                                 "<pre>" +
                                 CommonExceptionUtils.getStackTrace(exception) + 
                                 "</pre>" +
                                "</html>"
                                );
                        txtExceptionInfo.setCaretPosition(0);
                        
                    }
                }
            }
            
        });
    }//GEN-LAST:event_cboErrorTypesActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        this.parentDlg.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    
    
class WrappedCellRenderer extends DefaultListCellRenderer {
   public static final String HTML_1 = "<html><body style='width: ";
   public static final String HTML_2 = "px'>";
   public static final String HTML_3 = "</html>";
   private int width;

   public WrappedCellRenderer(int width) {
      this.width = width;
   }

   @Override
   public Component getListCellRendererComponent(JList list, Object value,
         int index, boolean isSelected, boolean cellHasFocus) {
      String text = HTML_1 + String.valueOf(width) + HTML_2 + value.toString()
            + HTML_3;
      return super.getListCellRendererComponent(list, text, index, isSelected,
            cellHasFocus);
   }

}
    
    class ExceptionsListModel extends DefaultListModel{
        List<SAXParseException> exceptions = new ArrayList<SAXParseException>(0);
        public ExceptionsListModel(List<SAXParseException> exceptions) {
            this.exceptions = exceptions;
        }
        
        public SAXParseException getItem(int index) {
            return exceptions.get(index);
        }
        
        @Override
        public int getSize(){
            return this.exceptions.size();
        }
        
        @Override
        public Object getElementAt(int index){
            SAXParseException exception = exceptions.get(index);
            return exception.getMessage();
        }
        
        
        
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JComboBox cboErrorTypes;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JList listExceptions;
    private javax.swing.JScrollPane scrollExcept;
    private javax.swing.JScrollPane scrollTxtExcept;
    private javax.swing.JSplitPane splitPane;
    private javax.swing.JTextPane txtExceptionInfo;
    // End of variables declaration//GEN-END:variables
}
