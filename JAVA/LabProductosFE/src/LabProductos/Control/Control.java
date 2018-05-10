package LabProductos.Control;

import LabProductos.vista.ProductosModel;
import LabProductos.vista.ProductosVista;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import labProductosBE.AccesoDatos.GlobalException;
import labProductosBE.AccesoDatos.NoDataException;
import labProductosBE.AccesoDatos.ServicioProducto;
import labProductosBE.AccesoDatos.ServicioTipoProducto;
import labProductosBE.LogicaNegocio.Producto;
import labProductosBE.LogicaNegocio.TipoProducto;

/**
 * Control:
 *
 * @version 1.0.0
 * @author Cynthia Madrigal Quesada
 * @date 28/02/2018
 */
public class Control {
    ServicioProducto sp;
    ServicioTipoProducto stp;
    List<TipoProducto>tipos;
    List<Producto> tableModel;
    ProductosVista view;
    ProductosModel model;

    public ProductosModel getModel() {
        return model;
    }

    public List<TipoProducto> getTipos() {
        return tipos;
    }

    
    public Control(ProductosVista view, ProductosModel model, ServicioProducto sp,ServicioTipoProducto stp) throws GlobalException, NoDataException {
        model.init();
        this.view = view;
        this.model = model;
        this.sp = sp;
        this.stp = stp;
        view.setController(this);
        view.setModel(model);
        view.init();
    }
    
    /**
     * Método encargado de filtrar por tipo
     * Cynthia Madrigal
     * @param tipo 
     */
    public void findProductoByTipo(int tipo){
        try{
            TipoProducto tp = (TipoProducto)tipos.toArray()[tipo];
            tableModel = sp.listarProductoByTipo(tp.getCodigo());
            model.setMensaje("updateTabla");
            model.setProductos(tableModel);
        }catch(GlobalException | NoDataException ex){
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error durante la consulta a la base de datos" + ex);
        }
    }
    /**
     * Método encargado de filtrar por Nombre
     * Cynthia Madrigal
     * @param tipo 
     */
    public void findProductoByNombre(String nombre){
        try{
            tableModel = sp.listarProductoByNombre(nombre);
            model.setMensaje("updateTabla");
            model.setProductos(tableModel);
        }catch(GlobalException | NoDataException ex){
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error durante la consulta a la base de datos" + ex);
        }
    }
    
    public void insert(Producto p){
        try{
            sp.insertarProducto(p);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);            
        }
    }
    
    /**
     * @throws labProductosBE.AccesoDatos.GlobalException
     * @throws labProductosBE.AccesoDatos.NoDataException
     * @Author: Cynthia Madrigal Quesada
     * @Date : 28.02.2018 Método encargado de llenar la tabla.
     */
    public void llenarTabla() throws GlobalException, NoDataException {
        tableModel = sp.listarProducto();
        if (tableModel == null) {
            return;
        }
        DefaultTableModel modelo = new DefaultTableModel();
        tableModel.forEach(e -> {
            Object[] row = new Object[7];
            row[0] = e.getNombre();
            row[1] = e.isImportado();
            row[2] = e.getPrecio();
            row[3] = e.getTipo().getDescripcion();
            row[4] = e.getTipo().getPorcentaje();
            row[5] = e.getImpuesto();
            row[6] = e.getPrecioFinal();
            modelo.addRow(row);
        });
        view.getTablaProductos().setModel(modelo);
    }

    /**
     * @Author: Cynthia Madrigal Quesada
     * @Date : 28.02.2018 Método encargado de inicializar el combo-box con los
     * tipos de productos disponibles en la base de datos.
     */
    public void cargarTiposProductos() {
        try {
            tipos = stp.listarTipoProducto();
            tipos.forEach(e -> {
                TipoProducto tp = (TipoProducto) e;
                view.getComboBuscaTipo().addItem(tp.getDescripcion());
                view.getComboTipo().addItem(tp.getDescripcion());
            });
        } catch (GlobalException | NoDataException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error durante la consulta a la base de datos" + ex);
        }
    }

}
