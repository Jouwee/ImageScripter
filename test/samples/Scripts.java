/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package samples;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

/**
 *
 * @author Nicolas
 */
public class Scripts {
    
    public static void main(String[] args) {
        try {
             Context cx = Context.enter();
             Scriptable scope = cx.initStandardObjects();
 
             // Collect the arguments into a single string.
             String s = "function f(x){return x+1} f(7)";
 
             // Now evaluate the string we've colected.
             Object result = cx.evaluateString(scope, s, "<cmd>", 1, null);
 
             // Convert the result to a string and print it.
//             System.err.println(Context.toString(result));
        } catch(Exception e) {e.printStackTrace();}
    }
    
}
