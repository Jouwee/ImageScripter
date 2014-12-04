package com.jouwee.proto;

/**
 * The second level of abstraction towards image processing
 *
 * @author Jouwees
 */
public abstract class Processor implements Action {

    @Override
    public void run() {

        // TODO: Should get it from somewhere else

        // Get the work image
        Image image = (Image) Application.getModel().getState().get("workImage");
        // Processes and save the work image
        Application.getModel().getState().set("workImage", process(image));
    }

    /**
     * Process the input image, return a new instance of the image, with the changes
     *
     * @param image Original image
     * @return Image
     */
    public abstract Image process(Image image);

}
