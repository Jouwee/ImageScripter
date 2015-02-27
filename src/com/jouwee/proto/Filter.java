package com.jouwee.proto;

/**
 * Abstract class for filters
 * 
 * @author Jouwee
 */
public abstract class Filter extends Iterator {

    /** Callback - Before pixel */
    private static final String CALLBACK_BEFORE_PIXEL = "beforePixel";

    /**
     * New filter
     */
    public Filter() {
        super();
        registerCallback(new CallbackHeader(CALLBACK_BEFORE_PIXEL, new Parameter("value", Object.class, "Value gathered")), new Callback("return value;"));
    }
    
    @Override
    public void beforeProcessing() {
        super.beforeProcessing();
    }
    
    @Override
    public void iteratePixel(int x, int y, int channel) {
        int value = processPixel(x, y, channel);
        Object r;
        if (isCallbackEnabled(CALLBACK_BEFORE_PIXEL)) {
            r = invoke(CALLBACK_BEFORE_PIXEL, value);
            if (r instanceof Double) {
                value = ((Double) r).intValue();
            } else {
                value = (Integer) r;
            }
        }
        getNewImage().setPixelValue(x, y, channel, value);
    }
    
    /**
     * Process a pixel, returning the new value for it
     * 
     * @param x
     * @param y
     * @param channel
     * @return int
     */
    public abstract int processPixel(int x, int y, int channel);

}
