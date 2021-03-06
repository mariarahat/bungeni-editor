package org.bungeni.ooo.transforms.loadable;

//~--- non-JDK imports --------------------------------------------------------

import com.sun.star.beans.PropertyValue;
import com.sun.star.frame.XStorable;
import java.io.File;
import java.util.ArrayList;
import org.bungeni.extutils.CommonFileFunctions;
import org.bungeni.extutils.MessageBox;
import org.bungeni.ooo.OOComponentHelper;
import org.bungeni.ooo.transforms.impl.BungeniDocTransform;
import org.bungeni.ooo.utils.CommonExceptionUtils;

/**
 *
 * @author ashok
 */
public class HTMLTransform extends BungeniDocTransform {
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(HTMLTransform.class.getName());
    private static String EXPORT_EXT = "html";
    private String EXPORT_OUTPUT_FILE = "";
    /** Creates a new instance of HTMLTransform */
    public HTMLTransform() {
        super();
    }

    public boolean transform(OOComponentHelper ooDocument) {
        boolean bState = false;

        try {

            // 1 - query interface the document for the storable url
            File fopenDocumentFile = null;

            if (ooDocument.isDocumentOnDisk()) {
                String sDocUrl = ooDocument.getDocumentURL();

                // set the path to the output xml file
                fopenDocumentFile = CommonFileFunctions.convertUrlToFile(sDocUrl);

                String fullFileName = fopenDocumentFile.getName();
                String ext          = fullFileName.substring(fullFileName.lastIndexOf(".") + 1, fullFileName.length());
                String pref         = fullFileName.substring(0, fullFileName.lastIndexOf("."));

                EXPORT_OUTPUT_FILE = fopenDocumentFile.getParentFile().getPath() + File.separator + pref + "." + EXPORT_EXT;

                // get temporary output file
                OutputFile            outpdfFile   = new OutputFile(EXPORT_OUTPUT_FILE, EXPORT_EXT);
                String uriOutFile = outpdfFile.getFullFile().toURI().toString();
                try {
                    XStorable       docStore  = ooDocument.getStorable();
                    //String          urlString = (String) getParams().get("StoreToUrl");
                    PropertyValue[] props     = getTransformProps().toArray(new PropertyValue[getTransformProps().size()]);

                    docStore.storeToURL(uriOutFile, props);
                    bState = true;
                } catch (com.sun.star.io.IOException ex) {
                    log.error("transform : " + ex.getMessage());
                    log.error("transform : " + CommonExceptionUtils.getStackTrace(ex));
                }


            } else {
                MessageBox.OK("Please save the document before trying to transform it!");
                bState = false;
            }
        } catch (Exception ex) {
            log.error("transform : " + ex.getMessage());
            log.error("transform : " + CommonExceptionUtils.getStackTrace(ex));
        }

        return bState;
    }


    private ArrayList<PropertyValue> getTransformProps() {
        ArrayList<PropertyValue> props = new ArrayList<PropertyValue>();
        PropertyValue            prop0 = new PropertyValue();

        prop0.Name  = "FilterName";
        prop0.Value = "HTML (StarWriter)";
        props.add(prop0);

        return props;
    }
}
