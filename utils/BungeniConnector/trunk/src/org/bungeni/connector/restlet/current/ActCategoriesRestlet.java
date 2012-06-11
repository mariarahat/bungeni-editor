/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bungeni.connector.restlet.current;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.util.List;
import org.bungeni.connector.IBungeniConnector;
import org.bungeni.connector.element.ActCategory;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.MediaType;
import org.restlet.data.Method;
import org.restlet.data.Status;

/**
 *
 * @author bzuadmin
 */
public class ActCategoriesRestlet extends Restlet {
     static final String HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "\n<"+ActCategory.PACKAGE_ALIAS+">";
    static final String FOOTER = "\n</"+ActCategory.PACKAGE_ALIAS+">";
    IBungeniConnector bungeniConnector = null;

    public ActCategoriesRestlet(IBungeniConnector bungeniConnector) {
        this.bungeniConnector = bungeniConnector;
    }

    @Override
    public void handle(Request request, Response response) {
        try {
            if (request.getMethod().equals(Method.GET)) {
                List<ActCategory> items = bungeniConnector.getActCategories();
                response.setStatus(Status.SUCCESS_OK);

                XStream xStream = new XStream(new DomDriver());
                xStream.alias(ActCategory.PACKAGE_ALIAS, List.class);
                xStream.alias(ActCategory.CLASS_ALIAS, ActCategory.class);
            
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < items.size(); i++) {
                    builder.append(xStream.toXML(items.get(i))).append("\n");
                }
                String xml = builder.toString();
       
                if (!xml.isEmpty()) {
                    response.setEntity(HEADER + xml + FOOTER, MediaType.TEXT_XML);
                } else {
                }
            } else {
                response.setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
