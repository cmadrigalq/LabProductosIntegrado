package productos.moviles.com.lab_productos.Conexion;

import labProductosBE.JSON.Json;
import labProductosBE.LogicaNegocio.Producto;
import labProductosBE.LogicaNegocio.TipoProducto;

/**
 * Esta clase ya que da una execepción de que no encuentra a  Java.beans.inspector
 * por lo que no puede serializar con JACKSON
 * */
public class JSON extends Json {
    public JSON(){
        super();
    }
    String toJson(TipoProducto tp){
        String str= "{\"_class\":\"TipoProducto\",";
        str += toStr("codigo",tp.getCodigo())+",";
        str += StrtoStr("descripcion",tp.getDescripcion())+",";
        str += toStr("porcentaje",tp.getPorcentaje());
        return str+"}";
    }
    String toJson(Producto p){
        String str= "{\"_class\":\"Producto\",";
        str += StrtoStr("codigo",p.getCodigo())+",";
        str += StrtoStr("nombre",p.getNombre())+",";
        str += toStr("precio",p.getPrecio())+",";
        str += toStr("importado",p.isImportado())+",";
        str += toStr("tipo")+":"+toJson(p.getTipo());
        return str+"}";
    }
    String StrtoStr(String key,String value){
        return toStr(key)+":\"" + value + "\"";
    }
    String toStr(String key){
        return "\""+key+"\"";
    }
    String toStr(String key,Object o){
        return toStr(key)+":"+o.toString();
    }

}
