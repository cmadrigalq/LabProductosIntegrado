package Model;

import java.util.ArrayList;
import java.util.List;
import labProductosBE.LogicaNegocio.Producto;
import labProductosBE.LogicaNegocio.TipoProducto;

/**
 * Modelo:
 *
 * @version 1.0.0  
 * @author Cynthia Madrigal Quesada
 * @date 02/03/2018
 */
public class Modelo {
    public static List<TipoProducto>tipos;
    public static List<Producto>productos;
    public static Producto producto;
    static{
        tipos = new ArrayList<>();
        productos = new ArrayList<>();
        producto = new Producto();
    }
}
