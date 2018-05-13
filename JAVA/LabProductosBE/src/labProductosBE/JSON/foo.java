package labProductosBE.JSON;

import java.util.ArrayList;
import java.util.List;
import labProductosBE.LogicaNegocio.Producto;
import labProductosBE.LogicaNegocio.TipoProducto;

/**
 * foo:
 *
 * @version 1.0.0  
 * @author Cynthia Madrigal Quesada
 * @date 13/05/2018
 */
public class foo {
    public static void main(String[]args){
        Producto p = new Producto();
        p.setCodigo("la fruta");
        p.setImportado(false);
        p.setNombre("Juanita");
        p.setPrecio(0);
        p.setTipo(new TipoProducto(666,"chanchito",0.0f));
        Json j = new Json();
        String str = j.toJson(p);
        List<Producto> tp= new ArrayList<>();
        tp.add(p);
        System.err.println(str);
        str = j.fromArray(tp);
        System.err.println(str);
        tp = j.toArrayProductos(str);
        System.err.println(j);
        
    }
}
