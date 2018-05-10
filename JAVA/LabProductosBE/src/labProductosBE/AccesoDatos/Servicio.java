package labProductosBE.AccesoDatos;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Manuel Céspedes
 */
public class Servicio {

    protected Connection conexion = null;

    public Servicio() {
    }

    public void conectar() throws GlobalException {
        try {
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            Properties prop = new Properties();
            prop.put("charSet", "UTF-8");
            prop.put("user", "sys as sysdba");
            prop.put("password", "root");
            DriverManager.registerDriver((Driver) oracle.jdbc.OracleDriver.class.newInstance());
            conexion = DriverManager.getConnection(url,prop);
            //JOptionPane.showMessageDialog(null,"Conectado correctamente \n");
        } catch (Exception e) {
            throw new GlobalException("No se ha podido conectar "+e);
        }
    }

    public void desconectar() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
        }
    }

}
