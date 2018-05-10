package LabProductos.vista;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import labProductosBE.LogicaNegocio.Producto;

public class ProductosTableModel extends AbstractTableModel {
    List<Producto> rows;
    int[] cols;

    public ProductosTableModel(int[] cols, List<Producto> rows){
        this.cols=cols;
        this.rows=rows;
        initColNames();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public String getColumnName(int col){
        return colNames[cols[col]];
    }
 
    
    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public Object getValueAt(int row, int col) {
        Producto p = rows.get(row);
        switch (cols[col]){
            case CODIGO: return p.getCodigo();
            case NOMBRE: return p.getNombre();
            case PRECIO: return p.getPrecio();
            case IMPORTADO: return p.isImportado();
            case TIPO_PRODUCTO: return p.getTipo();
            case PORCENTAJE: return p.getTipo().getPorcentaje();
            case IMPUESTO: return p.getImpuesto();
            case PRECIO_FINAL: return p.getPrecioFinal();
            default: return "";
        }
    }    

    public Producto getRowAt(int row) {
        return rows.get(row);
    }
    
    private void initColNames(){
        colNames[CODIGO]= "Codigo";
        colNames[NOMBRE]= "Nombre";
        colNames[PRECIO]= "Precio";
        colNames[IMPORTADO]= "Importado";
        colNames[TIPO_PRODUCTO]= "Tipo_Producto";
        colNames[PORCENTAJE]= "Porcentaje";
        colNames[IMPUESTO]= "Impuesto";
        colNames[PRECIO_FINAL]= "Precio_Final";
    }
    
    String[] colNames = new String[11];
    public static final int CODIGO = 0;
    public static final int NOMBRE = 1;
    public static final int PRECIO=2;
    public static final int IMPORTADO =3;  
    public static final int TIPO_PRODUCTO=4;
    public static final int PORCENTAJE=5;
    public static final int IMPUESTO=6;
    public static final int PRECIO_FINAL=7;
}
