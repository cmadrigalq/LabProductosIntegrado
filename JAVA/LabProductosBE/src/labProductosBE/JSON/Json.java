package labProductosBE.JSON;

import com.fasterxml.jackson.databind.ObjectMapper;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import labProductosBE.LogicaNegocio.Producto;
import labProductosBE.LogicaNegocio.TipoProducto;

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
    public String fromArray(List<? extends Object>list){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            //objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            return objectMapper.writeValueAsString(list);
        }catch(Exception ex){
            System.err.println(ex.getCause());
            return "[]";
        }
    }    
        public List<TipoProducto> toArrayTipos(String json){
        ObjectMapper objectMapper = new ObjectMapper();
       // objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            List<LinkedHashMap> hash = objectMapper.readValue(json,List.class);
            List<TipoProducto> res = new ArrayList();
            for(LinkedHashMap h : hash){
                res.add(toTipo(h));
            }
            return res;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
    TipoProducto toTipo(LinkedHashMap h){
        TipoProducto nuevo = new TipoProducto();
                nuevo.setCodigo( (Integer)h.get("codigo") );
                nuevo.setDescripcion((String)h.get("descripcion"));
                nuevo.setPorcentaje(((Double)h.get("porcentaje")).floatValue());
       return nuevo;
    }    
    public List<Producto> toArrayProductos(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        //objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            List<LinkedHashMap<String,String>> hash = objectMapper.readValue(json,List.class);
            List<Producto> res = new ArrayList();
            for(LinkedHashMap h : hash){
                Producto p = new Producto();
                //TODO
                res.add(p);
            }
            return res;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
