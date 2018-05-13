package productos.moviles.com.lab_productos.Conexion;

import java.util.List;

import labProductosBE.JSON.Json;
import labProductosBE.LogicaNegocio.Producto;
import labProductosBE.LogicaNegocio.TipoProducto;

public class Proxy {
    List<TipoProducto> tipos;
    clConexion cl;
    Json json;
    static Proxy proxy = new Proxy();
    private Proxy(){
        cl = new clConexion();
        json = new Json();
    }
    public static Proxy instancia(){
        if(proxy == null)
            Proxy.proxy = new Proxy();
        return Proxy.proxy;
    }
    public List<TipoProducto> getTipos(){
        return this.tipos;
    }
    public List<TipoProducto> getTipos(String ip) {
        String resultado = cl.getOutputFromUrl("?action=listarTipos",ip);
        return (this.tipos = json.toArrayTipos(resultado));
    }
    public void agregar(Producto p,String ip){
       String url = "?action=addproducto&arg0="+json.toJson(p);
       cl.getOutputFromUrl(url,ip);
    }
}
