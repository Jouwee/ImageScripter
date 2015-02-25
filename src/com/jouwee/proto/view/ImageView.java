package com.jouwee.proto.view;

import com.jouwee.proto.mvc.View;
import com.jouwee.proto.CommonStates;
import static com.jouwee.proto.CommonStates.INPUT;
import com.jouwee.proto.Image;
import com.jouwee.proto.Input;
import com.jouwee.proto.Model;
import com.jouwee.proto.State;
import com.jouwee.proto.annotations.ViewMeta;
import com.jouwee.proto.gui.ImageViewer;
import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * View for images
 * 
 * @author Jouwee
 */
@ViewMeta(name = "Image viewer", controller = ImageController.class)
public class ImageView extends View<State, ImageController> implements PropertyChangeListener, CommonStates {

    /** Image */
    private ImageViewer image;
    /** Input slider */
    private JSlider inputSlider;
    
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
        setupImageViewer();
        setupInputSlider();
        setupViewAndPlaceComponents();
        getModel().addPropertyChangeListener(OUTPUT_IMAGE, this);
        getModel().addPropertyChangeListener(INPUT, this);
    }
    
    /**
     * Sets up the image
     */
    private void setupImageViewer() {
        image = new ImageViewer();
        updateImage();
    }
    
    /**
     * Sets up the input slider
     */
    private void setupInputSlider() {
        if (inputSlider == null) {
            inputSlider = new JSlider();
            // TODO: Bad listener
            inputSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    if (getController() != null) {
                        getController().changeInputFrame(inputSlider.getValue());
                    }
                }
            });
        }
        if (getModel().get(INPUT) != null) {
            Input input = (Input) getModel().get(INPUT);
            inputSlider.setMaximum(input.getFrameCount() - 1);
        }
    }
    
    /**
     * Update the image
     */
    private void updateImage() {
        if (getModel().get(OUTPUT_IMAGE) == null) {
            image.setImage(null);
        } else {
            Image outputImage = (Image) getModel().get(OUTPUT_IMAGE);
            image.setImage(outputImage);
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
        add(inputSlider, BorderLayout.SOUTH);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(OUTPUT_IMAGE)) {
            updateImage();
        }
        if (evt.getPropertyName().equals(INPUT)) {
            setupInputSlider();
        }
    }

    @Override
    public void updateModel(Model model) {
        setModel(model.getState());
    }
    
}
