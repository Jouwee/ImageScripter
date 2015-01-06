package com.jouwee.proto;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;

/**
 * Script engine
 * 
 * @author Jouwee
 */
public class ScriptEngine {

    /** Script's text */
    private String text;
    
    // TODO: Not his responsability
    private Context cx;
    private org.mozilla.javascript.Scriptable scope;
    private Function f;
    
    /**
     * Create a new Script
     * 
     * @param scriptable
     */
    public ScriptEngine() {
        text = "function f(x, y, v) {\nreturn v;\n}";
    }
    
    /**
     * Compiles a callback
     * 
     * @param callback 
     */
    public void compile(Callback callback) {
        try {
             cx = Context.enter();
             scope = cx.initStandardObjects();
             String body = ScriptProvider.def().getFullBody(callback);
             
             System.out.println(body);
             
             f = cx.compileFunction(scope, body, "<cmd>", 1, null);
        } catch(Exception e) {e.printStackTrace();}
    }

    /**
     * Invoke a callback
     * 
     * 
     * @param callback
     * @param params
     * @return 
     */
    public Object invoke(Callback callback, Object... params) {
        try {
             return f.call(cx, scope, scope, params);
        } catch(Exception e) {e.printStackTrace();}
        return null;
    }
    
    /**
     * Returns the script text
     * 
     * @return String
     */
    public String getText() {
        return text;
    }

    /**
     * Defines the script text
     * 
     * @param text 
     */
    public void setText(String text) {
        this.text = text;
    }
    
}
