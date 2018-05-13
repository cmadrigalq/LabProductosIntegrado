package labProductosBE.LogicaNegocio;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import java.io.Serializable;
@JsonTypeInfo(include=As.WRAPPER_OBJECT, use=Id.CLASS)
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
