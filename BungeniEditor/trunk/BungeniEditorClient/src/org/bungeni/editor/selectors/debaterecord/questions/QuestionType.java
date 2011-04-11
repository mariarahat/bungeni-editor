package org.bungeni.editor.selectors.debaterecord.questions;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.bungeni.db.BungeniClientDB;
import org.bungeni.db.DefaultInstanceFactory;
import org.bungeni.db.QueryResults;
import org.bungeni.db.SettingsQueryFactory;
import org.bungeni.editor.selectors.BaseMetadataPanel;
import org.bungeni.editor.selectors.debaterecord.question.ObjectQuestionType;
import org.bungeni.extutils.BungeniEditorProperties;
import org.bungeni.ooo.OOComponentHelper;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author  Ashok Hariharan
 */
public class QuestionType extends BaseMetadataPanel {

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(QuestionType.class.getName());
    DocumentBuilderFactory dbfInstance = null;
    DocumentBuilder dbuilder = null;
    XPath xpathInstance = null;

    /** Creates new form QuestionTitle */
    public QuestionType() {
        try {
            dbfInstance = DocumentBuilderFactory.newInstance();
            dbfInstance.setNamespaceAware(false);
            dbuilder = dbfInstance.newDocumentBuilder();
            xpathInstance = XPathFactory.newInstance().newXPath();
            initComponents();
        } catch (ParserConfigurationException ex) {
            log.error("QuestionType initialization error ", ex);
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

        lblQuestionType = new javax.swing.JLabel();
        cboQuestionType = new javax.swing.JComboBox();

        lblQuestionType.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/bungeni/editor/selectors/debaterecord/questions/Bundle"); // NOI18N
        lblQuestionType.setText(bundle.getString("QuestionType.lblQuestionType.text")); // NOI18N
        lblQuestionType.setName("lbl_question_title"); // NOI18N

        cboQuestionType.setFont(new java.awt.Font("DejaVu Sans", 0, 11));
        cboQuestionType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblQuestionType, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
            .addComponent(cboQuestionType, 0, 265, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblQuestionType)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cboQuestionType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

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
        String editSectionName = getContainerPanel().getEditSectionName();
        if (editSectionName.length() > 0) {
            
            HashMap<String, String> sectionMeta = new HashMap<String, String>();
            ObjectQuestionType oqt = (ObjectQuestionType) this.cboQuestionType.getSelectedItem();

            sectionMeta.put("BungeniQuestionType", oqt.getOntologyURI());
            sectionMeta.put("BungeniQuestionTypeShowAs", oqt.getOntologyShowAs());

            getContainerPanel().getOoDocument().setSectionMetadataAttributes(editSectionName, sectionMeta);

        }
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
        OOComponentHelper ooDoc = getContainerPanel().getOoDocument();
        HashMap<String, String> sectionMeta = new HashMap<String, String>();
        String newSectionName = (getContainerPanel()).mainSectionName;
        ObjectQuestionType oqs = ((ObjectQuestionType)this.cboQuestionType.getSelectedItem());
        String sURI = oqs.getOntologyURI();
        String sShowAs = oqs.getOntologyShowAs();
        sectionMeta.put("BungeniQuestionType", sURI);
        sectionMeta.put("BungeniQuestionTypeShowAs", sShowAs);

        ooDoc.setSectionMetadataAttributes(newSectionName, sectionMeta);
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboQuestionType;
    private javax.swing.JLabel lblQuestionType;
    // End of variables declaration//GEN-END:variables

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
    public void commonInitFields() {
        try {
            initComboSelect();
        } catch (SAXException ex) {
            log.error("in initComboSelect " , ex);
        } catch (IOException ex) {
            log.error("in initComboSelect " , ex);
        }
    }

    private void initComboSelect() throws SAXException, IOException, SAXException, SAXException, SAXException {
        Vector<ObjectQuestionType> questionTypeObjects = getQuestionTypeObjects();
        this.cboQuestionType.setModel(new DefaultComboBoxModel(questionTypeObjects));
        AutoCompleteDecorator.decorate(this.cboQuestionType);
    }

    private String getOntologyXmlFile() {

        String activeDocumentMode = BungeniEditorProperties.getEditorProperty("activeDocumentMode");

        String ontologyQuery =
                SettingsQueryFactory.Q_FETCH_TOOLBAR_CONFIG_BYTYPE(
                activeDocumentMode,
                "question_ontologies");

        BungeniClientDB instance = BungeniClientDB.defaultConnect();
        QueryResults qr = instance.ConnectAndQuery(ontologyQuery);

        String[] toolbarXML = qr.getSingleColumnResult("TOOLBAR_XML");
        String ontologyXML = toolbarXML[0].replace('/', File.separatorChar);
        ontologyXML = DefaultInstanceFactory.DEFAULT_INSTALLATION_PATH() + File.separator + ontologyXML;
        return ontologyXML;
    }


    private Vector<ObjectQuestionType> getQuestionTypeObjects() throws SAXException, IOException {
        Vector<ObjectQuestionType> questionTypes = new Vector<ObjectQuestionType>();

        String ontologyXML = getOntologyXmlFile();

        Document ontoDoc = dbuilder.parse(new File(ontologyXML));
        NodeList questionOntologyNodes = null;
        try {
            questionOntologyNodes = (NodeList) xpathInstance.evaluate("//ontology[@type='question']", ontoDoc, XPathConstants.NODESET);
        } catch (XPathExpressionException ex) {
            log.error("Error while evaluating ontology xpath ", ex);
        }
        /**
         *
        <ontology
        type="question"
        href="/ontology/concept/questions.private.notice">
        <showAs xml:lang="eng">Questions By Private Notice</showAs>
        </ontology>
         *
         */
        if (questionOntologyNodes != null) {

            for (int i = 0; i < questionOntologyNodes.getLength(); i++) {
                
                Element ontoElement = (Element) questionOntologyNodes.item(i);
                String sHref = ontoElement.getAttribute("href");
                Locale defLocale = Locale.getDefault();
                //we capture the iso3 default language for the current locale
                //xml config uses iso3 language representation
                String iso3Language = defLocale.getISO3Language();
                Node contextNode = questionOntologyNodes.item(i);
                
                Node foundNode = null;
                try {
                    foundNode = (Node) xpathInstance.evaluate("./showAs[@lang='" + iso3Language + "']", contextNode, XPathConstants.NODE);
                } catch (XPathExpressionException ex) {
                    log.error("Node for language : " + iso3Language + " not found", ex);
                }
                String showAs = foundNode.getTextContent();
                ObjectQuestionType ost = new ObjectQuestionType(sHref, showAs);
                questionTypes.add(ost);

            }
        }
        return questionTypes;
    }


 

}