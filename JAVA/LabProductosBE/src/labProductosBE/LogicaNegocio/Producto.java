package labProductosBE.LogicaNegocio;

import java.io.Serializable;

/**
 *
 * @author Manuel Céspedes
 */
public class Producto implements Serializable, Jsonable{

    String codigo;
    String nombre;
    int precio;
    boolean importado;
    TipoProducto tipo;

    public Producto() {
        this.codigo = "";
        this.nombre = "";
        this.precio = 0;
        this.importado = false;
        this.tipo = null;
    }

    public Producto(String codigo, String nombre, int precio, boolean importado, TipoProducto tipo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.importado = importado;
        this.tipo = tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public boolean isImportado() {
        return importado;
    }

    public void setImportado(boolean importado) {
        this.importado = importado;
    }

    public TipoProducto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }

    public double getImpuesto() {
        float porcentaje = this.getTipo().getPorcentaje();
        int precioBruto = this.getPrecio();
        if (this.isImportado()) {
            return (porcentaje / 100 * precioBruto) + ((porcentaje / 100 * precioBruto) * 0.5);
        } else {
            return (porcentaje / 100 * precioBruto);
        }
    }
    
    public int getPrecioFinal(){
        int precioNeto = (int) Math.floor(this.getPrecio() + this.getImpuesto());
        return precioNeto;
    }

    @Override
    public String toString() {
        return "Producto{" + "codigo=" + codigo + ", nombre=" + nombre + ", precio=" + precio + ", importado=" + importado + ", tipo=" + tipo + '}';
    }

}
