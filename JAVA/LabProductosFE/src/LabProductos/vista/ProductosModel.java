package LabProductos.vista;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observer;
import labProductosBE.LogicaNegocio.Producto;

public class ProductosModel extends java.util.Observable{
    Producto filter; 
    ProductosTableModel productos;
    HashMap<String,String> errores;
    String mensaje;

    
    public ProductosModel() {
    }

    public void init(){ 
        filter = new Producto();
        clearErrors();        
        List<Producto> rows = new ArrayList<>();
        this.setProductos(rows);
    }
    
    public void setProductos(List<Producto> p) {
        int[] cols = {
            ProductosTableModel.NOMBRE,
            ProductosTableModel.IMPORTADO,
            ProductosTableModel.PRECIO,
            ProductosTableModel.TIPO_PRODUCTO,
            ProductosTableModel.PORCENTAJE,
            ProductosTableModel.IMPUESTO,
            ProductosTableModel.PRECIO_FINAL

        };
        this.productos = new ProductosTableModel(cols, p);
        setChanged();
        notifyObservers();      
    }
    
    public Producto getFilter() {
        return filter;
    }
   
    public void setFilter(Producto filter) {
        this.filter = filter;
    }
    
     public ProductosTableModel getProductos() {
        return productos;
    }

    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
        setChanged();
        notifyObservers();
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public HashMap<String, String> getErrores() {
        return errores;
    }

    public void setErrores(HashMap<String, String> errores) {
        this.errores = errores;
    }
    
    public void clearErrors(){
        setErrores(new HashMap<>());
        setMensaje(""); 
    }
    
    public void commit(){
        setChanged();
        notifyObservers();       
    }
}
