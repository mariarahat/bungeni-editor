
package org.bungeni.editor.rules;

import java.util.ArrayList;
import java.util.HashMap;
import org.bungeni.editor.rulesimpl.BaseStructuralRule;
import org.bungeni.editor.rulesimpl.StructuralError;
import org.bungeni.odfdom.section.BungeniOdfSectionHelper;
import org.odftoolkit.odfdom.dom.element.text.TextSectionElement;

/**
 * For an input section this class determines if the child sections are in the correct order.
 * Order is checked for each child by determing the preceeding and following section -- if a
 * either of these is out of place - the child section is flagged as being in the wrong position
 *
 * @author Ashok Hariharan
 */
public class OrderOfChildSections extends BaseStructuralRule {
     private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(OrderOfChildSections.class.getName());
     TextSectionElement xThisSection = null;
    BungeniOdfSectionHelper odfSectionHelper = null;
    String thisSectionName = "";
    String thisSectionType = "";

    private static String INVALID_PRECEEDING_POSITION = "One or more preceeding sections for this section are invalid";
    private static String INVALID_FOLLOWING_POSITION = "One or more following sections for this section are invalid";

    public OrderOfChildSections(){
        super();
    }

    @Override
    public boolean applyRule(String forThisSectionName) {
          if (setup(forThisSectionName)) {
            //check if children can be unordered
            //if yes -- return true
            //else check order of children
            if (checkIsUnordered()) {
                return true;
            } else
                return checkOrderOfChildren();
         }
        return false;
    }

    /**
     * Check order of children for the section being set up
     * @param forThisSectionName
     * @return
     */
   private boolean setup(String forThisSectionName) {
        boolean bState = false;
       try {
       odfSectionHelper = new BungeniOdfSectionHelper(odfDocument);
       xThisSection = odfSectionHelper.getSection(forThisSectionName);
       thisSectionType = odfSectionHelper.getSectionType(xThisSection);
       thisSectionName = forThisSectionName;
       bState = true;
       } catch (Exception ex) {
           log.error("setup : " + ex.getMessage());
       } finally {
           return bState;
       }
    }


   private boolean checkIsUnordered(){
       boolean bState = this.ruleParserEngine.areSectionTypeChildrenUnordered(thisSectionType);
       return bState;
   }

   private boolean checkOrderOfChildren() {
       //get the child sections
      boolean bCheckPre = false, bCheckFol = false;
      try {
          ArrayList<TextSectionElement> listofSections = odfSectionHelper.getChildSections(xThisSection);
           for (int i = 0; i < listofSections.size(); i++) {
               TextSectionElement odfSection = listofSections.get(i);
               String childSectionType =  this.odfSectionHelper.getSectionType(odfSection);
               String childSectionName = odfSection.getTextNameAttribute();
               //for the current child section - get the actual preceeding section type
               //and see if it matches with any of the rule preceeding section types
               checkState precState =  checkPreceeding(childSectionType, i, listofSections);
               if (!precState.checkState) {
                      StructuralError returnError = this.makeStructuralError(false,
                                thisSectionType,
                                childSectionType,
                                thisSectionName,
                                childSectionName,
                                getName(), INVALID_PRECEEDING_POSITION,
                                precState.failedSectionType, precState.failedSectionName);
                    log.error("preceding error addded :" + returnError);
                    errorLog.add(returnError);
               }
               checkState follState = checkFollowing(childSectionType, i, listofSections);
               if (!follState.checkState) {
                      StructuralError returnError = this.makeStructuralError(false,
                                thisSectionType,
                                childSectionType,
                                thisSectionName,
                                childSectionName,
                                getName(), INVALID_FOLLOWING_POSITION, follState.failedSectionType, follState.failedSectionName);
                    log.error("preceding error addded :" + returnError);
                    errorLog.add(returnError);
               }
           }
      } catch (Exception ex) {
          log.error("checkOrderOfChildren : " + ex.getMessage());
      } finally {
          System.out.println("no. of erros = " + errorLog.size());
          return bCheckPre & bCheckFol;
      }
   }

   private boolean checkPrecedingException (String childSectionType) {
       HashMap<String,Boolean> returnMap = this.ruleParserEngine.getSectionTypeOrderException(childSectionType);
       if (returnMap.isEmpty()) {
           return false;
       }
       if (returnMap.containsKey("preceding")) {
           return returnMap.get("preceding");
       }
       return false;
   }

    private boolean checkFollowingException (String childSectionType) {
      HashMap<String,Boolean> returnMap = this.ruleParserEngine.getSectionTypeOrderException(childSectionType);
       if (returnMap.isEmpty()) {
           return false;
       }
       if (returnMap.containsKey("following")) {
           return returnMap.get("following");
       }
       return false;
   }

   private checkState checkPreceeding(String childSectionType, int currentIdx ,
           ArrayList<TextSectionElement> listofChildren) {
       checkState cs = new checkState();
       if (checkPrecedingException(childSectionType) == true ) {
           return cs;
       }
       //get the allowed preceeding types from the rule
       ArrayList<String> allowedPreceedingTypes = this.ruleParserEngine.getPreceedingSectionTypes(thisSectionType, childSectionType);
       String actualPreceedingType = "";
       //get the actual preceeding type
       if (currentIdx == 0) {
           //we are at the first section
           actualPreceedingType = "AT_FIRST";
       } else {
           actualPreceedingType = this.odfSectionHelper.getSectionType(listofChildren.get(currentIdx-1));
       }

       if (actualPreceedingType.equals("AT_FIRST")) {
           return cs;
       }

        if (checkPrecedingException(actualPreceedingType) == true)
           return cs;


       if (allowedPreceedingTypes.contains(actualPreceedingType))
           return cs;
       else {
           TextSectionElement failSection = listofChildren.get(currentIdx -1);
           cs.checkState = false;
           cs.failedSectionName = failSection.getTextNameAttribute();
           cs.failedSectionType = actualPreceedingType;
           return cs;
       }
   }

   public class checkState {
       Boolean checkState;
       String failedSectionType;
       String failedSectionName;

       public checkState (){
         checkState = true;
       }
   }

   private checkState checkFollowing(String childSectionType, int currentIdx , ArrayList<TextSectionElement> listofChildren) {
        checkState cs = new checkState();
  //get the allowed preceeding types from the rule
       if (checkFollowingException(childSectionType) == true ) {
           return cs;
       }

       ArrayList<String> allowedFollowingTypes = this.ruleParserEngine.getFollowingSectionTypes(thisSectionType, childSectionType);
       String actualFollowingType = "";

       //get the actual preceeding type
       if (currentIdx == listofChildren.size() - 1) {
           //we are at the last section section
           actualFollowingType = "AT_LAST";
       } else {
           //get the following section type
           actualFollowingType = this.odfSectionHelper.getSectionType(listofChildren.get(currentIdx+1));
       }
       
       if (checkFollowingException(actualFollowingType) == true)
           return cs;
       

       if (allowedFollowingTypes.contains(actualFollowingType))
           return cs;
       else {
            TextSectionElement otextSection = listofChildren.get(currentIdx+1);
            cs.checkState = false;
            cs.failedSectionType = actualFollowingType;
            cs.failedSectionName = otextSection.getTextNameAttribute();
           return cs;
       }
   }


  
}
