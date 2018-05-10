/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Modelo;
import labProductosBE.AccesoDatos.GlobalException;
import labProductosBE.AccesoDatos.NoDataException;
import labProductosBE.AccesoDatos.ServicioProducto;
import labProductosBE.AccesoDatos.ServicioTipoProducto;
import labProductosBE.LogicaNegocio.Producto;

/**
 *
 * @author Manuel Céspedes
 */
public class Control {

    ServicioProducto svp;
    ServicioTipoProducto svtp;

    public Control() {
        svp = new ServicioProducto();
        svtp = new ServicioTipoProducto();

    }

    public void listarTiposProductos() throws GlobalException, NoDataException {
        try {
            Modelo.tipos = svtp.listarTipoProducto();
        } catch (GlobalException | NoDataException ex) {
            throw ex;
        }
    }
    
    /**
     Cyn Madrigal
     */
    public void addProducto(Producto p) throws NoDataException, GlobalException {
        try {
            svp.insertarProducto(p);
        } catch (GlobalException | NoDataException ex) {
            throw ex;
        }
    }
    
    
    /*
    Cyn Madrigal
    */
    public void productosByName(String name) throws GlobalException, NoDataException{
        try {
            Modelo.productos = svp.listarProductoByNombre(name);
        } catch (GlobalException | NoDataException ex) {
            throw ex;
        }
    }
    /*
    Cyn Madrigal
    */
    public void productosByTipo(int tipo) throws GlobalException, NoDataException{
        try {
            Modelo.productos = svp.listarProductoByTipo(tipo);
        } catch (GlobalException | NoDataException ex) {
            throw ex;
        }
    }

    public void listarProductos() throws GlobalException, NoDataException {
        try {
            svp.listarProducto();
        } catch (GlobalException | NoDataException ex) {
            throw ex;
        }

    }


}
