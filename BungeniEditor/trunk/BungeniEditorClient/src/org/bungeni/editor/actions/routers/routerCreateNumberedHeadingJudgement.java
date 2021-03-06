/*
 *  Copyright (C) 2012 UN/DESA Africa i-Parliaments Action Plan
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either version 3
 *  of the License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.bungeni.editor.actions.routers;

//~--- non-JDK imports --------------------------------------------------------

import com.sun.star.text.XParagraphCursor;
import org.bungeni.editor.actions.toolbarAction;
import org.bungeni.error.BungeniMsg;
import org.bungeni.error.BungeniValidatorState;
import org.bungeni.ooo.OOComponentHelper;

//~--- JDK imports ------------------------------------------------------------

import com.sun.star.text.XTextCursor;
import com.sun.star.text.XTextViewCursor;

import org.bungeni.ooo.ooQueryInterface;

/**
 *
 * @author Ashok Hariharan
 */
public class routerCreateNumberedHeadingJudgement extends defaultRouter {
    private static org.apache.log4j.Logger log                   =
        org.apache.log4j.Logger.getLogger(routerCreateSection.class.getName());
    private static final String __referencePrefix__ = "numRef";
     private  static final String __referenceNameSeparator__ = ":";
    /** Creates a new instance of routerCreateNumberedHeading */
    public routerCreateNumberedHeadingJudgement() {}

    /*
     * This router action marks a heading as a numbered heading and write protects it after applying the associated style
     * apply_numbered_heading:style_name
     */
    // !+ACTION_RECONF (rm, jan 2012) - removed toolbarAction as var, class
    // toolbarAction is deprecated
    @Override
    //public BungeniValidatorState route_TextSelectedInsert(toolbarSubAction action, toolbarSubAction subAction,
    public BungeniValidatorState route_TextSelectedInsert(toolbarAction subAction,
            javax.swing.JFrame pFrame, OOComponentHelper ooDocument) {
            //first get heading span
            String styleName = subAction.action_value();
            XTextViewCursor xviewCursor =  ooDocument.getViewCursor();
            XTextCursor xheadCursor = xviewCursor.getText().createTextCursorByRange(xviewCursor);
            XParagraphCursor xparaCursor = ooQueryInterface.XParagraphCursor(xheadCursor);
            xparaCursor.gotoNextParagraph(false);
            xparaCursor.gotoPreviousParagraph(true);
            // xparaCursor
            //set the heading style over the cursor
            ooDocument.setStyleOverCursor(styleName, xheadCursor);
            //add a number reference over the view cursor selection.
            int i = 1;
            String newRefNo  = __referencePrefix__ + __referenceNameSeparator__ + i;
            while (ooDocument.getReferenceMarks().hasByName(newRefNo) ) {
                newRefNo = __referencePrefix__ + __referenceNameSeparator__ +  (++i) ;
            }
            routerCreateReference rcf = new routerCreateReference();
            //update the action value with the reference name
            subAction.setActionValue(newRefNo);

            //rcf.route_TextSelectedInsert(action, subAction, pFrame, ooDocument);
            rcf.route_TextSelectedInsert(subAction, pFrame, ooDocument);

        return new BungeniValidatorState(true, new BungeniMsg("SUCCESS"));
    }

  

   

 
}
