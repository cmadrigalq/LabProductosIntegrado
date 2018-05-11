package productos.moviles.com.productosmoviles.Conexion;

import java.util.List;

import labProductosBE.LogicaNegocio.Producto;
import labProductosBE.LogicaNegocio.TipoProducto;

public class Proxy {
    List<TipoProducto> tipos;
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
    public List<TipoProducto> getTipos(String ip) {
        String resultado = cl.getOutputFromUrl("?action=listarTipos",ip);
        return (this.tipos = json.toArrayTipos(resultado));
    }
    public void agregar(Producto p,String ip){
       String url = "?action=addproducto&arg0="+json.toJson(p);
       cl.getOutputFromUrl(url,ip);
    }
}
