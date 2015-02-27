package com.jouwee.proto;

/**
 * Acition for gathering information from the image
 * 
 * @author Jouwee
 */
public abstract class Gatherer extends Action {
    
    /** Callback - information gathered */
    private static final String CALLBACK_INFORMATION_GATHERED = "informationGathered";

    /**
     * New gatherer
     */
    public Gatherer() {
        super();
        registerCallback(new CallbackHeader(CALLBACK_INFORMATION_GATHERED, new Parameter("value", Object.class, "Value gathered")), new Callback());
    }
    
    @Override
    public void run() {        
        // TODO: Not here
        compileCallbacks();
        // Get the work image
        Image image = (Image) Application.getModel().getState().get("workImage");
        // Processes the work image
        process(image);
        // Invokes the callback
        if (isCallbackEnabled(CALLBACK_INFORMATION_GATHERED)) {
            invoke(CALLBACK_INFORMATION_GATHERED, getValue());
        }
    }
    
    /**
     * Processes the image
     * 
     * @param image 
     */
    public abstract void process(Image image);
    
    /**
     * Returns the value gathered
     * 
     * @return Object
     */
    public abstract Object getValue();
    
}
