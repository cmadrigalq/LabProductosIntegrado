package productos.moviles.com.productosmoviles.Conexion;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import labProductosBE.JSON.Json;
import labProductosBE.LogicaNegocio.Producto;
import labProductosBE.LogicaNegocio.TipoProducto;

public class JSON extends Json {
    public JSON(){
        super();
    }
    public List<TipoProducto> toArrayTipos(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            TipoProducto[] res = objectMapper.readValue(json,TipoProducto[].class);
            return Arrays.asList(res);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
    public List<Producto> toArrayProductos(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            Producto[] res = objectMapper.readValue(json,Producto[].class);
            return Arrays.asList(res);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
