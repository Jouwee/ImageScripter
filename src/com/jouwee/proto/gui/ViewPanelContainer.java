package com.jouwee.proto.gui;

import com.jouwee.proto.mvc.View;
import com.jouwee.proto.ExceptionHandler;
import com.jouwee.proto.gui.JMultiSplit;
import com.jouwee.proto.ViewProvider;
import java.awt.BorderLayout;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * View panel container
 *
 * @author Jouwee
 */
public class ViewPanelContainer extends JPanel {

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
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(stream);
            NodeList containerList = doc.getElementsByTagName("container");
            Node rootContainer = containerList.item(0);
            add(loadFrom((Element) rootContainer));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            ExceptionHandler.handle(e);
        }
    }

    /**
     * Load the layout from a xml node
     *
     * @param node
     * @return JMultiSplit
     */
    private JMultiSplit loadFrom(Element node) {
        String splitType = node.getAttribute("split");
        JMultiSplit split = new JMultiSplit(splitType.equals("horizontal") ? JMultiSplit.HORIZONTAL : JMultiSplit.VERTICAL);
        for (Element element : new NodeListIterator(node.getChildNodes())) {
            try {
                int size = Integer.parseInt(element.getAttribute("size"));
                JComponent component = null;
                switch (element.getNodeName()) {
                    case "view":
                        component = loadViewFrom(element);
                        break;
                    case "container":
                        component = loadFrom(element);
                        break;
                }
                split.add(component, (Object)size);
            } catch(IllegalStateException e) {
                ExceptionHandler.handle(e);
            }   
        }
        return split;
    }

    /**
     * Loads a view from a xml node
     * 
     * @param node
     * @return JComponent
     * @throws IllegalStateException 
     */
    private JComponent loadViewFrom(Element node) throws IllegalStateException {
        View view = tryCreateView(node.getAttribute("class"));
        ViewPanel viewPanel = new ViewPanel();
        viewPanel.setView(view);
        return viewPanel;
    }
    
    /**
     * Tries to crate a new view
     * 
     * @param className
     * @return View
     */
    private View tryCreateView(String className) throws IllegalStateException {
        try {
            return ViewProvider.getNewInstance(className);
        } catch(InstantiationException | IllegalAccessException | NoSuchMethodException | IllegalArgumentException |
                InvocationTargetException | ClassNotFoundException ex) {
            throw new IllegalStateException("Invalid view type " + className, ex);
        }
    }
    
    /**
     * Node list iterator
     */
    private class NodeListIterator implements Iterator<Element>, Iterable<Element> {

        /** Node list */
        private final NodeList nodeList;
        /** Current index */
        private int i;

        /**
         * Create new iterator
         * 
         * @param nodeList 
         */
        public NodeListIterator(NodeList nodeList) {
            this.nodeList = nodeList;
            i = 0;
        }
        
        @Override
        public boolean hasNext() {
            int j = i;
            while (j < nodeList.getLength()) {
                Node node = nodeList.item(j);
                j++;
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public Element next() {
            while (true) {
                Node node = nodeList.item(i);
                i++;
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    return (Element) node;
                }
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported");
        }

        @Override
        public Iterator<Element> iterator() {
            return this;
        }
        
    }

}
