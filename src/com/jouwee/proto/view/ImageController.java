package com.jouwee.proto.view;

import static com.jouwee.proto.CommonStates.INPUT;
import static com.jouwee.proto.CommonStates.INPUT_IMAGE;
import com.jouwee.proto.Input;
import com.jouwee.proto.State;
import com.jouwee.proto.mvc.Controller;

/**
 * Image controller
 * 
 * @author Jouwee
 */
public class ImageController extends Controller<State, ImageView> {
    
    /**
     * Change the input frame
     * 
     * @param frame 
     */
    public void changeInputFrame(int frame) {
        Input input = (Input) getModel().get(INPUT);
        getModel().set(INPUT_IMAGE, input.getFrame(frame));
    }
    
}
