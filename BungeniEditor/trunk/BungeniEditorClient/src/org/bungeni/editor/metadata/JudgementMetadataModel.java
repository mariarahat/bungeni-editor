package org.bungeni.editor.metadata;

//~--- non-JDK imports --------------------------------------------------------
import org.bungeni.ooo.OOComponentHelper;
import org.bungeni.ooo.ooDocMetadata;

/**
 * Extended metadata model for the debaterecord document type
 *
 * @author undesa
 */
public class JudgementMetadataModel extends BaseEditorDocMetaModel {

    public static final String[] GROUPED_METADATA = {};
    private String BungeniLanguageCode = "";
    private String BungeniFamily = "";
    private String BungeniDomain = "";
    private String BungeniCaseType = "";
    private String BungeniCity = "";
    private String BungeniCaseNo = "";
    private String BungeniIssuedOn = "";
    private String BungeniYear = "";
    
    public static final String[] THIS_METAMODEL = {
        "BungeniLanguageCode",
        "BungeniFamily",
        "BungeniDomain",
        "BungeniCaseType",
        "BungeniCity",
        "BungeniCaseNo",
        "BungeniIssuedOn",
        "BungeniYear"
    };

    public JudgementMetadataModel() {
        super();
    }

    @Override
    public void setup() {
        super.setup();
        this.docMeta.put("BungeniLanguageCode", BungeniLanguageCode);
        this.docMeta.put("BungeniFamily", BungeniFamily);
        this.docMeta.put("BungeniDomain", BungeniDomain);
        this.docMeta.put("BungeniCaseType", BungeniCaseType);
        this.docMeta.put("BungeniCity", BungeniCity);
        this.docMeta.put("BungeniCaseNo", BungeniCaseNo);
        this.docMeta.put("BungeniIssuedOn", BungeniIssuedOn);
        this.docMeta.put("BungeniYear", BungeniYear);
    }

    public String getBungeniLanguageCode() {
        return BungeniLanguageCode;
    }

    public void setBungeniLanguageCode(String BungeniLangCode) {
        this.BungeniLanguageCode = BungeniLangCode;
    }

    public void setBungeniFamily(String BungeniFamily) {
        this.BungeniFamily = BungeniFamily;
    }

    public String getBungeniFamily() {
        return BungeniFamily;
    }

    public String getBungeniDomain() {
        return BungeniDomain;
    }

    public void setBungeniDomain(String BungeniDomain) {
        this.BungeniDomain = BungeniDomain;
    }

    public String getBungeniCaseType() {
        return BungeniCaseType;
    }

    public void setBungeniCaseType(String BungeniCaseType) {
        this.BungeniCaseType = BungeniCaseType;
    }

    public String getBungeniCity() {
        return BungeniCity;
    }

    public void setBungeniCity(String BungeniCity) {
        this.BungeniCity = BungeniCity;
    }

    public String getBungeniCaseNo() {
        return BungeniCaseNo;
    }

    public void setBungeniCaseNo(String BungeniCaseNo) {
        this.BungeniCaseNo = BungeniCaseNo;
    }

    public String getBungeniDate() {
        return BungeniIssuedOn;
    }

    public void setBungeniDate(String BungeniDate) {
        this.BungeniIssuedOn = BungeniDate;
    }

    public String getBungeniYear() {
        return BungeniYear;
    }

    public void setBungeniYear(String BungeniYear) {
        this.BungeniYear = BungeniYear;
    }
}
