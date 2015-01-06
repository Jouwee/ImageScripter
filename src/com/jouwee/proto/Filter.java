package com.jouwee.proto;

import java.util.List;

/**
 * Abstract class for filters
 * 
 * @author Jouwee
 */
public abstract class Filter extends Iterator {

    /** Callback - Before pixel */
    private final Callback beforePixel;

    /**
     * New filter
     */
    public Filter() {
        super();
        beforePixel = new Callback("beforePixel", "return value;", new Callback.Return(), new Callback.Parameter("value", Integer.class, "Pixel value"));
    }
    
    @Override
    public void beforeProcessing() {
        super.beforeProcessing();
    }
    
    @Override
    public void iteratePixel(int x, int y) {
        int value = processPixel(x, y);
        Object r = Application.getModel().getScriptEngine().invoke(beforePixel, value);
        if (r instanceof Double) {
            value = ((Double) r).intValue();
        } else {
            value = (Integer) r;
        }
        getNewImage().setPixelValue(x, y, value);
    }
    
    /**
     * Process a pixel, returning the new value for it
     * 
     * @param x
     * @param y
     * @return int
     */
    public abstract int processPixel(int x, int y);

    @Override
    public List<Callback> getCallbackList() {
        List<Callback> callbackList = super.getCallbackList();
        callbackList.add(beforePixel);
        return callbackList;
    }

}
