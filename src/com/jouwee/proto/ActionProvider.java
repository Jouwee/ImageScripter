package com.jouwee.proto;

import com.jouwee.proto.processors.AverageGrayscale;
import com.jouwee.proto.processors.AverageImageValue;
import com.jouwee.proto.processors.Binarization;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Action provider
 *
 * @author Jouwee
 */
public class ActionProvider {

    /** List of avaliable actions */
    private static final List<Class> availableActions = new ArrayList<>();

    // TODO: Find a way to correctly load the available actions
    static {
        availableActions.add(AverageGrayscale.class);
        availableActions.add(Binarization.class);
        availableActions.add(AverageImageValue.class);
    }

    /**
     * Returns an unmodifiable list of available actions
     *
     * @return {@code List<Class>}
     */
    public static List<Class> getAvailableActions() {
        return Collections.unmodifiableList(availableActions);
    }

    /**
     * Returns a new instance of the specified {@code actionType}
     *
     * @param actionType Action type
     * @return Action
     * @throws InstantiationException if there was an exception while creating the action
     * @throws IllegalAccessException if the view constructor is private or protected
     */
    public static Action getNewInstance(Class actionType) throws InstantiationException, IllegalAccessException {
        return (Action) actionType.newInstance();
    }

}
