package com.jouwee.proto;

import java.awt.BorderLayout;
import java.io.InputStream;
import javax.swing.JPanel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * View panel container
 * 
 * @author Jouwee
 */
public class ViewPanelContainer extends JPanel {

    // TODO: Container inside a container
    
    /**
     * Create a new view panel container
     */
    public ViewPanelContainer() {
        super(new BorderLayout());
    }
    
    /**
     * Load the layout from a xml file
     * 
     * @param stream 
     */
    public void loadFrom(InputStream stream) {
        try {
            // TODO: Flexible reading
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(stream);
            NodeList containerList = doc.getElementsByTagName("container");
            for (int temp = 0; temp < containerList.getLength(); temp++) {
                Node container = containerList.item(temp);
                NodeList viewList = container.getChildNodes();
                int i = 0;
                for (int t2 = 0; t2 < viewList.getLength(); t2++) {
                    Node view = viewList.item(t2);
                    if (view.getNodeType() == Node.ELEMENT_NODE) {
                        i++;
                        Element eElement = (Element) view;
                        String className = eElement.getElementsByTagName("class").item(0).getTextContent();
                        ViewPanel viewPanel = new ViewPanel();
                        viewPanel.setView(ViewProvider.getNewInstance((Class<View>) Class.forName(className)));
                        switch(i) {
                            case 1:
                                add(viewPanel, BorderLayout.WEST); break;
                            case 2:
                                add(viewPanel, BorderLayout.CENTER); break;
                            case 3:
                                add(viewPanel, BorderLayout.EAST); break;
                        }
                        
                    }
                }
            }
        } catch(Exception e) {
            // TODO: Default error handling
            e.printStackTrace();
        }
    }
    
}
