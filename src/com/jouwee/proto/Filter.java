package com.jouwee.proto;

import java.util.List;

/**
 * Abstract class for filters
 * 
 * @author Jouwee
 */
public abstract class Filter extends Iterator {

    /** Callback before every pixel */
    private Callback beforePixel;

    public Filter() {
        beforePixel = new Callback("f", "function f(x, y, v) {\nreturn v;\n}");
    }
    
    @Override
    public void beforeProcessing() {
        super.beforeProcessing();
        // TODO: Find a better place for this
        getScript().compile(beforePixel);
    }
    
    @Override
    public void iteratePixel(int x, int y) {
        int v = processPixel(x, y);
        
        Object o = getScript().invoke(beforePixel, x, y, v);
        if(o instanceof Integer) {
            v = (Integer)o;
        } else {
            v = ((Double) o).intValue();
        }
        getNewImage().setPixelValue(x, y, v);
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
        List<Callback> list = super.getCallbackList();
        list.add(beforePixel);
        return list;
    }
    
}
