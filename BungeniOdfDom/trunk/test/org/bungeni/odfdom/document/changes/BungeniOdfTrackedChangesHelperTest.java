package org.bungeni.odfdom.document.changes;

//~--- non-JDK imports --------------------------------------------------------

import org.apache.log4j.Logger;

import org.bungeni.odfdom.document.BungeniOdfDocumentHelper;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import org.w3c.dom.Element;

import static org.junit.Assert.*;

//~--- JDK imports ------------------------------------------------------------

import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import org.apache.log4j.BasicConfigurator;
import org.odftoolkit.odfdom.dom.element.text.TextChangedRegionElement;

/**
 *
 * @author undesa
 */
public class BungeniOdfTrackedChangesHelperTest {
    private static org.apache.log4j.Logger log       =
        Logger.getLogger(BungeniOdfTrackedChangesHelperTest.class.getName());
    BungeniOdfDocumentHelper               docHelper = null;
BungeniOdfDocumentHelper               revertTestHelper = null;
    public BungeniOdfTrackedChangesHelperTest() {
        BasicConfigurator.configure();
    }

    @BeforeClass
    public static void setUpClass() throws Exception {}

    @AfterClass
    public static void tearDownClass() throws Exception {}

    @Before
    public void setUp() {
        try {
            docHelper = new BungeniOdfDocumentHelper(new File("testdocs/tracked-changes.odt"));
        } catch (Exception ex) {
            log.error(ex);
        }
    }

    @After
    public void tearDown() {}

    /**
     * Test of getTrackedChangeContainer method, of class BungeniOdfTrackedChangesHelper.
     */
    @Test
    public void testGetTrackedChangeContainer() {
        System.out.println("getTrackedChangeContainer");

        Element result = docHelper.getChangesHelper().getTrackedChangeContainer();

        assertEquals("text:tracked-changes", result.getNodeName());
    }

    /**
     * Test of getChangedRegions method, of class BungeniOdfTrackedChangesHelper.
     */
    @Test
    public void testGetChangedRegions() {
        System.out.println("getChangedRegions");

        Element                         changeContainer = docHelper.getChangesHelper().getTrackedChangeContainer();
        ArrayList<TextChangedRegionElement> result          =
            docHelper.getChangesHelper().getChangedRegions(changeContainer);
        int                             nResult         = result.size();

        assertEquals(10, nResult);
    }

    @Test
    public void testGetChangedRegionsByCreator() {
        System.out.println("getChangedRegions");

        Element                         changeContainer = docHelper.getChangesHelper().getTrackedChangeContainer();
        ArrayList<TextChangedRegionElement> result          =
            docHelper.getChangesHelper().getChangedRegionsByCreator(changeContainer, "John Smith");
        int nResult = result.size();

        assertEquals(2, nResult);
    }

    /**
     * Test of getChange method, of class BungeniOdfTrackedChangesHelper.
     */

    /**
     * Test of getStructuredChangeType method, of class BungeniOdfTrackedChangesHelper.
     */
    @Test
    public void testGetStructuredChangeType() {
        System.out.println("getStructuredChangeType");

        Element                                             changeContainer =
            docHelper.getChangesHelper().getTrackedChangeContainer();
        ArrayList<TextChangedRegionElement>                     result          =
            docHelper.getChangesHelper().getChangedRegions(changeContainer);
        BungeniOdfTrackedChangesHelper.StructuredChangeType stype           =
            docHelper.getChangesHelper().getStructuredChangeType(result.get(0));

        assertEquals("deletion", stype.changetype);
    }

    /**
     * Test of getChangeInfo method, of class BungeniOdfTrackedChangesHelper.
     */
    @Test
    public void testGetChangeInfo() {
        System.out.println("getChangeInfo");

        Element                                             changeContainer =
            docHelper.getChangesHelper().getTrackedChangeContainer();
        ArrayList<TextChangedRegionElement>                     result          =
            docHelper.getChangesHelper().getChangedRegions(changeContainer);
        BungeniOdfTrackedChangesHelper.StructuredChangeType stype           =
            docHelper.getChangesHelper().getStructuredChangeType(result.get(0));
        HashMap<String, Object> changes = docHelper.getChangesHelper().getChangeInfo(stype);

        assertEquals(changes.get("dcCreator"), "Ashok Hariharan");
        assertEquals(changes.get("changeType"), "deletion");
        assertEquals(changes.get("changeText"), "Triffids");

        // !+ (rm, jan 2012) - changed the output for the desired change datae
        // date stored in timestamp format
        // assertEquals(changes.get("dcDate"), "Tue, Feb 9, 2010, 12:38:00 PM");
        assertEquals(changes.get("dcDate"), "2010-02-09T12:38:00");

        BungeniOdfTrackedChangesHelper.StructuredChangeType stype2 =
            docHelper.getChangesHelper().getStructuredChangeType(result.get(1));
        HashMap<String, Object> changes2 = docHelper.getChangesHelper().getChangeInfo(stype2);

        System.out.println(changes2.get("dcCreator"));
        System.out.println(changes2.get("changeType"));
        System.out.println(changes2.get("changeText"));
        System.out.println(changes2.get("dcDate"));
        assertEquals(changes2.get("dcCreator"), "Ashok Hariharan");
        assertEquals(changes2.get("changeType"), "insertion");
        assertEquals(changes2.get("changeText"), "Nightingales");
        assertEquals(changes2.get("dcDate"), "2010-02-09T12:38:00");
    }
 
    
}
