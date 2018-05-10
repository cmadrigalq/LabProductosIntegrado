package Servlets;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class Json {
    JSONSerializer serializer;
    JSONDeserializer desSerializer;
    public Json(){
        serializer = new JSONSerializer();
        desSerializer = new JSONDeserializer();
    }
    public String toJson(Object s){
        return serializer.serialize(s);
    }
    public Object toObject(String str,Class _class){
        return desSerializer.deserialize(str, _class);
    }
    
}
