package com.jouwee.proto.view;

import com.jouwee.proto.CommonStates;
import com.jouwee.proto.Image;
import com.jouwee.proto.Model;
import com.jouwee.proto.State;
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
public class ImageView extends View<State> implements PropertyChangeListener, CommonStates {

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
        getModel().addPropertyChangeListener(OUTPUT_IMAGE, this);
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
        if (getModel().get(OUTPUT_IMAGE) == null) {
            image.setIcon(null);
            image.setText("No input");
        } else {
            Image outputImage = (Image) getModel().get(OUTPUT_IMAGE);
            image.setIcon(new ImageIcon(outputImage.getBufferedImage().getSubimage(0, 0, outputImage.getWidth(), outputImage.getHeight())));
            image.setText(null);
        }
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

    @Override
    public void updateModel(Model model) {
        setModel(model.getState());
    }
    
}
