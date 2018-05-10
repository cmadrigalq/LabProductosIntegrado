package labProductosBE.LogicaNegocio;

import java.io.Serializable;

public class TipoProducto implements Serializable, Jsonable{
    int codigo;
    String descripcion;
    float porcentaje;

    public TipoProducto() {
    }

    public TipoProducto(int codigo, String descripcion, float porcentaje) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.porcentaje = porcentaje;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(float porcentaje) {
        this.porcentaje = porcentaje;
    }

    @Override
    public String toString() {
        return "TipoProducto{" + "codigo=" + codigo + ", descripcion=" + descripcion + ", porcentaje=" + porcentaje + '}';
    }
    
}
