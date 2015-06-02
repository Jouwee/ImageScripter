package com.jouwee.proto;

import java.util.HashMap;
import java.util.Map;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;

/**
 * Script engine
 *
 * @author Jouwee
 */
public class ScriptEngine {

    /** Contexts linked with the thread ids */
    private final Map<Long, Context> contexts;
    /** Execution scope */
    private Scriptable scope;
    /** Update scope function */
    private Function updateScopeFunction;
    /** Compiled functions */
    private final Map<CallbackHeader, Function> compiledFunctions;

    /**
     * Create a new Script
     */
    public ScriptEngine() {
        contexts = new HashMap<>();
        compiledFunctions = new HashMap<>();
    }

    /**
     * Initializes the scripting engine
     */
    public void init() {
        scope = getContext().initStandardObjects();
        getContext().evaluateString(scope, "var $scope = {}", "<cmd>", 1, null);
        updateScopeFunction = getContext().compileFunction(scope, "function updateScope(action) {$scope.action = action;}", "<cmd>", 1, null);
    }

    /**
     * Compiles a callback
     *
     * @param header
     * @param callback
     */
    public void compile(CallbackHeader header, Callback callback) {
        try {
            String body = ScriptProvider.def().getFullBody(callback, header);
            Function f = getContext().compileFunction(scope, body, "<cmd>", 1, null);
            compiledFunctions.put(header, f);
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
    }

    /**
     * Invoke a callback
     *
     * @param actionScope
     * @param header
     * @param callback
     * @param params
     * @return Object
     */
    public Object invoke(Action actionScope, CallbackHeader header, Callback callback, Object... params) {
        try {
            
            updateScopeFunction.call(getContext(), scope, scope, new Object[] {actionScope});
            
            return compiledFunctions.get(header).call(getContext(), scope, scope, params);
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
        return null;
    }

    /**
     * Returns the context associated with the current thread
     *
     * @return Context
     */
    public Context getContext() {
        return getContext(Thread.currentThread().getId());
    }

    /**
     * Returns the context associated with the {@code threadId}
     *
     * @param threadId Thread id
     * @return Context
     */
    public Context getContext(long threadId) {
        if (!contexts.containsKey(threadId)) {
            contexts.put(threadId, Context.enter());
        }
        return contexts.get(threadId);
    }

}
