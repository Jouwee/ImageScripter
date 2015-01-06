package com.jouwee.proto;

import java.util.ArrayList;
import java.util.List;

/**
 * Acition for gathering information from the image
 * 
 * @author Jouwee
 */
public abstract class Gatherer extends Action implements Scriptable {
    
    /** Callback - information gathered */
    private final Callback informationGathered;

    public Gatherer() {
        super();
        informationGathered = new Callback("informationGathered",
                new Callback.Parameter("value", Object.class, "Value gathered"));
    }
    
    @Override
    public void run() {
        compileCallbacks();
        // Get the work image
        Image image = (Image) Application.getModel().getState().get("workImage");
        // Processes the work image
        process(image);
        // Invokes the callback
        System.out.println("return: " + Application.getModel().getScriptEngine().invoke(informationGathered, getValue()));
//        getScript().invoke(informationGathered, getValue());
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

    @Override
    public void compileCallbacks() {
        for (Callback callback : getCallbackList()) {
            Application.getModel().getScriptEngine().compile(callback);
        }
    }
    
    @Override
    public List<Callback> getCallbackList() {
        List<Callback> callbacks = new ArrayList<>();
        callbacks.add(informationGathered);
        return callbacks;
    }
    
}
