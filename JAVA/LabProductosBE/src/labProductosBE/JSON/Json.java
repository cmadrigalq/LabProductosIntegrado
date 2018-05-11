package labProductosBE.JSON;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Json {
    JSONSerializer serializer;
    JSONDeserializer desSerializer;
    public Json(){
        serializer = new JSONSerializer();
        desSerializer = new JSONDeserializer();
    }
    public String toJson(Object s){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.USE_EQUALITY_FOR_OBJECT_ID);
            return objectMapper.writeValueAsString(s);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(Json.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
    public Object toObject(String str,Class _class){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.USE_EQUALITY_FOR_OBJECT_ID);
            return objectMapper.readValue(str,_class);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(Json.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        } catch (IOException ex) {
            Logger.getLogger(Json.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
    public String fromArray(List<? extends Object>list){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.USE_EQUALITY_FOR_OBJECT_ID);
            return objectMapper.writeValueAsString(list);
        }catch(Exception ex){
            System.err.println(ex.getCause());
            return "[]";
        }
    }    
}
