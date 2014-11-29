package com.jouwee.proto;

import com.jouwee.proto.annotations.ViewMeta;
import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * View for images
 * 
 * @author Jouwee
 */
@ViewMeta(name = "Image viewer")
public class ImageView extends View implements PropertyChangeListener {

    /** Image */
    private JLabel image;
    
    /**
     * Creates a new Image View
     * 
     * @param model
     */
    public ImageView(Model model) {
        super(model);
        initGui();
    }
    
    /**
     * Initializes the graphical user interface
     */
    private void initGui() {
        setupImageLabel();
        setupViewAndPlaceComponents();
        getModel().getState().addPropertyChangeListener("outputImage", this);
    }
    
    /**
     * Sets up the image label
     */
    private void setupImageLabel() {
        image = new JLabel();
        updateImage();
    }
    
    /**
     * Update the image
     */
    private void updateImage() {
        Image outputImage = (Image) getModel().getState().get("outputImage");
        image.setIcon(new ImageIcon(outputImage.getBufferedImage().getSubimage(0, 0, outputImage.getWidth(), outputImage.getHeight())));
        revalidate();
        repaint();
    }
    
    /**
     * Sets up the view and place the components. The child components should've been previously set up.
     */
    private void setupViewAndPlaceComponents() {
        setLayout(new BorderLayout());
        add(image);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        updateImage();
    }
    
}
