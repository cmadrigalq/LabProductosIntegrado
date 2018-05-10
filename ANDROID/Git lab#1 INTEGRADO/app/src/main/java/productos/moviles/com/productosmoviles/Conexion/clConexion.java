package productos.moviles.com.productosmoviles.Conexion;

import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class clConexion {
    final String urlFormat = "http://%s/webProductosFE/";
    final String defaultIP = "192.168.0.12:8081";
    public clConexion() {
    }

    String URL;

    public String getURL() {
        return URL;
    }

    public void setURL(String URL,String ip) {
        if(ip == null || ip.trim().isEmpty())
            ip = defaultIP;
        this.URL = String.format(urlFormat,defaultIP) + URL;
        /*
        ipconfig
        Adaptador de LAN inalámbrica Wi-Fi
        Dirección IPv4
        http://192.168.0.12:8081/webProductosFE/Index.jsp*/
    }

    public String getOutputFromUrl(String url,String ip) {
        this.setURL(url,ip);
        StringBuffer output = new StringBuffer("");
        try {
            InputStream stream = getHttpConnection(this.URL);
            BufferedReader buffer = new BufferedReader(
                    new InputStreamReader(stream));
            String s = "";
            while ((s = buffer.readLine()) != null)
                output.append(s);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return output.toString();
    }
    // Makes HttpURLConnection and returns InputStream
    private static InputStream getHttpConnection(String urlString)
            throws IOException {
        InputStream stream = null;
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            httpConnection.setRequestMethod("GET");
            httpConnection.connect();

            if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                stream = httpConnection.getInputStream();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return stream;
    }
}
