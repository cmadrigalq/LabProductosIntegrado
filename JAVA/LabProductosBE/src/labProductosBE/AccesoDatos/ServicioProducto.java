package labProductosBE.AccesoDatos;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import labProductosBE.LogicaNegocio.Producto;
import labProductosBE.LogicaNegocio.TipoProducto;
import oracle.jdbc.internal.OracleTypes;

/**
 *
 * @author Manuel Céspedes
 */
public class ServicioProducto extends Servicio {

    private static final String insertarProducto = "{call insertar_producto(?,?,?,?,?)}";
    private static final String listarProductos = "{?=call listar_producto()}";
    private static final String LISTAR_BY_TIPO = "{?=call listar_productoByTipo(?)}";
    private static final String LISTAR_BY_NOMBRE = "{?=call listar_productoByNombre(?)}";
    private static final String LISTAR_BY_CODIGO = "{?=call listar_productoByCod(?)}";

    public ServicioProducto() {
    }

    /**
     * Método que retornar una lista de productos, filtrada por su tipo Cynthia
     * Madrigal Quesada. 28.2.2018
     *
     * @return Coleccion de productos
     * @throws GlobalException
     * @throws NoDataException
     * @param id_tipo Tipo de producto por el cual se desea filtrar
     */
    public List<Producto> listarProductoByTipo(int id_tipo) throws GlobalException, NoDataException {
        conectar();
        ResultSet rs = null;
        List<Producto> coleccion = new ArrayList();
        Producto producto = null;
        CallableStatement pstmt = null;
        boolean imp = false;
        try {
            pstmt = conexion.prepareCall(LISTAR_BY_TIPO);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setInt(2, id_tipo);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);

            //Statement stm = conexion.createStatement();
            //rs = stm.executeQuery(String.format(LISTAR_BY_TIPO, tipo));
            while (rs.next()) {
                imp = rs.getString("importado").equals("S");
                producto = new Producto(rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getInt("precio"),
                        imp,
                        new TipoProducto(
                                rs.getInt("id_tipo"),
                                rs.getString("descripcion"),
                                rs.getFloat("porcentaje")
                        )
                );
                coleccion.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("Sentencia no válida");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if (coleccion == null || coleccion.size() == 0) {
            throw new NoDataException("No hay datos");
        }
        return coleccion;
    }

    public void insertarProducto(Producto producto) throws GlobalException, NoDataException {
        conectar();
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(insertarProducto);
            pstmt.setString(1, producto.getCodigo());
            pstmt.setString(2, producto.getNombre());
            pstmt.setInt(3, producto.getPrecio());
            if (producto.isImportado()) {
                pstmt.setInt(4, 1);
            } else {
                pstmt.setInt(4, 0);
            }
            pstmt.setInt(5, producto.getTipo().getCodigo());
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

    public List<Producto> listarProducto() throws GlobalException, NoDataException {
        conectar();
        ResultSet rs = null;
        List<Producto> coleccion = new ArrayList();
        Producto producto = null;
        CallableStatement pstmt = null;
        boolean imp = false;
        try {
            pstmt = conexion.prepareCall(listarProductos);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                imp = rs.getString("importado").equals("S");
                producto = new Producto(rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getInt("precio"),
                        imp,
                        new TipoProducto(
                                rs.getInt("id_tipo"),
                                rs.getString("descripcion"),
                                rs.getFloat("porcentaje")
                        )
                );
                coleccion.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("Sentencia no válida");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if (coleccion == null || coleccion.size() == 0) {
            throw new NoDataException("No hay datos");
        }
        return coleccion;
    }

    /**
     * Método que retornar una lista de productos, filtrada por nombre Madrigal
     * Quesada. 1.3.2018
     *
     * @return Coleccion de productos
     * @throws GlobalException
     * @throws NoDataException
     * @param nombre Tipo de producto por el cual se desea filtrar
     */
    public List<Producto> listarProductoByNombre(String nombre) throws GlobalException, NoDataException {
        conectar();
        ResultSet rs = null;
        List<Producto> coleccion = new ArrayList();
        Producto producto = null;
        CallableStatement pstmt = null;
        boolean imp = false;
        try {
            pstmt = conexion.prepareCall(LISTAR_BY_NOMBRE);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, nombre);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);

            //Statement stm = conexion.createStatement();
            //rs = stm.executeQuery(String.format(LISTAR_BY_TIPO, tipo));
            while (rs.next()) {
                imp = rs.getString("importado").equals("S");
                producto = new Producto(rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getInt("precio"),
                        imp,
                        new TipoProducto(
                                rs.getInt("id_tipo"),
                                rs.getString("descripcion"),
                                rs.getFloat("porcentaje")
                        )
                );
                coleccion.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("Sentencia no válida");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if (coleccion == null || coleccion.size() == 0) {
            throw new NoDataException("No hay datos");
        }
        return coleccion;
    }

    /**
     * Método que retornar una lista de productos, filtrada por nombre Madrigal
     * Quesada. 1.3.2018
     *
     * @return producto
     * @throws GlobalException
     * @throws NoDataException
     * @param cod codigo de producto por el cual se desea filtrar
     */
    public Producto listarProductoByCod(String cod) throws GlobalException, NoDataException {
        conectar();
        ResultSet rs = null;
        CallableStatement pstmt = null;
        boolean imp = false;
        try {
            pstmt = conexion.prepareCall(LISTAR_BY_CODIGO);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, cod);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);

            //Statement stm = conexion.createStatement();
            //rs = stm.executeQuery(String.format(LISTAR_BY_TIPO, tipo));
            if (rs.next()) {
                imp = rs.getString("importado").equals("S");
                return new Producto(rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getInt("precio"),
                        imp,
                        new TipoProducto(
                                rs.getInt("id_tipo"),
                                rs.getString("descripcion"),
                                rs.getFloat("porcentaje")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("Sentencia no válida");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }

        throw new NoDataException("No hay datos");

    }

}
