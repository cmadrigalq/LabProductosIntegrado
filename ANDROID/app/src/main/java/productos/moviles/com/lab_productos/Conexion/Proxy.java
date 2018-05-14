package productos.moviles.com.lab_productos.Conexion;

import java.util.HashMap;
import java.util.List;

import labProductosBE.LogicaNegocio.Producto;
import labProductosBE.LogicaNegocio.TipoProducto;

public class Proxy {
    List<TipoProducto> tipos;
    List<Producto> productos;
    clConexion cl;
    JSON json;
    static Proxy proxy = new Proxy();
    private Proxy(){
        cl = new clConexion();
        json = new JSON();
    }
    public static Proxy instancia(){
        if(proxy == null)
            Proxy.proxy = new Proxy();
        return Proxy.proxy;
    }
    public List<TipoProducto> getTipos(){
        return this.tipos;
    }
    public List<Producto> getProductos(){
        return this.productos;
    }
    public List<TipoProducto> getTipos(String ip) {
        String resultado = cl.getOutputFromUrl("?action=listarTipos",ip,"GET",null);
        return (this.tipos = json.toArrayTipos(resultado));
    }
    public List<Producto> getProductos(String ip) {
        String resultado = cl.getOutputFromUrl("?action=listarProductos",ip,"GET",null);
        return (this.productos = json.toArrayProductos(resultado));
    }
    public void agregar(Producto p,String ip){
       String url = "?action=addproducto";
       HashMap<String,Object> args = new HashMap<>();
       args.put("arg0",json.toJson(p));
       cl.getOutputFromUrl(url,ip,"POST",args);
    }
}
