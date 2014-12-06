/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jouwee.proto.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

/**
 * Serializer and deserializer for interfaces using GSon
 * 
 * @author Maciek Makowski (Using his source found at http://stackoverflow.com/questions/4795349/how-to-serialize-a-class-with-an-interface)
 * @author Jouwee
 * @param <T>
 */
public class InterfaceAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T> {

    @Override
    public JsonElement serialize(T object, Type type, JsonSerializationContext context) {
        JsonObject wrapper = new JsonObject();
        wrapper.addProperty("type", object.getClass().getName());
        wrapper.add("data", context.serialize(object));
        return wrapper;
    }

    @Override
    public T deserialize(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject wrapper = (JsonObject) elem;
        JsonElement typeName = get(wrapper, "type");
        JsonElement data = get(wrapper, "data");
        Type actualType = typeForName(typeName);
        return context.deserialize(data, actualType);
    }

    /**
     * Get the type based on the name
     * @param typeElem
     * @return 
     */
    private Type typeForName(JsonElement typeElem) {
        try {
            return Class.forName(typeElem.getAsString());
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e);
        }
    }

    /**
     * Get some data
     * 
     * @param wrapper
     * @param memberName
     * @return JsonElement
     */
    private JsonElement get(JsonObject wrapper, String memberName) {
        final JsonElement elem = wrapper.get(memberName);
        if (elem == null) {
            throw new JsonParseException("No '" + memberName + "' member found in what was expected to be an interface wrapper");
        }
        return elem;
    }
}
