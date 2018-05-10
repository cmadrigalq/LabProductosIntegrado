package labProductosBE.AccesoDatos;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import labProductosBE.LogicaNegocio.TipoProducto;
import oracle.jdbc.internal.OracleTypes;

public class ServicioTipoProducto extends Servicio {

    private static final String insertarTipoProducto = "{call insertar_tipoProducto(?,?,?)}";
    private static final String listarTipoProducto = "{?=call listar_tipoProducto()}";

    public ServicioTipoProducto() {
    }

    public List<TipoProducto>listarTipoProducto() throws GlobalException, NoDataException{
        conectar();
        ResultSet rs = null;
        List<TipoProducto>coleccion = new ArrayList();
        TipoProducto tipoProducto = null;
        CallableStatement pstmt = null;
        try{
            pstmt = conexion.prepareCall(listarTipoProducto);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1);
            while(rs.next()){
                tipoProducto = new TipoProducto(rs.getInt("id_tipo"),
                                                rs.getString("descripcion"),
                                                rs.getFloat("porcentaje"));
                coleccion.add(tipoProducto);
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new GlobalException("Sentencia no válida");
        }finally{
            try{
                if(rs != null)
                    rs.close();
                
                if(pstmt != null)
                    pstmt.close();
                
                desconectar();
            }catch (SQLException e){
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if(coleccion == null || coleccion.isEmpty()){
            throw new NoDataException("No hay datos");
        }
        return coleccion;
    }
    
    public void insertarTipoProducto(TipoProducto tipoProducto) throws GlobalException, NoDataException {
        conectar();
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(insertarTipoProducto);
            pstmt.setInt(1, tipoProducto.getCodigo());
            pstmt.setString(2, tipoProducto.getDescripcion());
            pstmt.setFloat(3, tipoProducto.getPorcentaje());
            boolean resultado = pstmt.execute();
            if (resultado == true) {
                throw new NoDataException("No se realizo la inserción");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("Llave duplicada");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
    }

}
