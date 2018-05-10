package LabProductosFE;

import LabProductos.Control.Control;
import LabProductos.vista.ProductosModel;
import LabProductos.vista.ProductosVista;
import labProductosBE.AccesoDatos.GlobalException;
import labProductosBE.AccesoDatos.NoDataException;
import labProductosBE.AccesoDatos.ServicioProducto;
import labProductosBE.AccesoDatos.ServicioTipoProducto;

/**
 *
 * @author Manuel Céspedes
 */
public class Aplicacion {

    /**
     * @param args the command line arguments
     * @throws labProductosBE.AccesoDatos.NoDataException
     * @throws labProductosBE.AccesoDatos.GlobalException
     */
    public static void main(String[] args) throws NoDataException, GlobalException {
        // TODO code application logic here
        ServicioProducto servicioProducto = new ServicioProducto();
        ServicioTipoProducto servicioTipoProducto = new ServicioTipoProducto();
        ProductosModel model = new ProductosModel();
        ProductosVista view = new ProductosVista();
        Control controller = new Control(view, model, servicioProducto, servicioTipoProducto);
        view.setVisible(true);
    }

}

