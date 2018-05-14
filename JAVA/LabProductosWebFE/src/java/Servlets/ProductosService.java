package Servlets;

import Control.Control;
import Model.Modelo;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import labProductosBE.JSON.Json;
import labProductosBE.LogicaNegocio.Producto;


@WebServlet(name="ProductosService", urlPatterns={"/ProductosService"})
public class ProductosService extends HttpServlet {

    Control domainModel;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("text/xml");
            String json;
            String accion = request.getParameter("action");
            System.out.println(accion);
            /***/
            Control ctl = new Control();
            Json jsn = new Json();
            String arg;
            int argInt;
            Producto p;
            /****/
            switch (accion) {

                case "productosByNombre":
                    arg = request.getParameter("arg0").replaceAll("\"","");
                    ctl.productosByName(arg);
                    json = jsn.toJson(Modelo.productos);
                    out.write(json);
                    break;
                case "productosByTipo":
                    arg = request.getParameter("arg0").replaceAll("\"", "");
                    argInt = Integer.valueOf(arg);
                    ctl.productosByTipo(Integer.valueOf(arg));
                    json = jsn.toJson(Modelo.productos);
                    out.write(json);
                    break;  
                case "addproducto":
                    arg = request.getParameter("arg0").replaceAll("\"", "");
                    p = (Producto)jsn.toObject(arg, Producto.class);
                    ctl.addProducto(p);
                    out.write(jsn.toJson(true));
                    break;
                case "listarTipos":
                    ctl.listarTiposProductos();
                    out.write(jsn.fromArray(Modelo.tipos));
                    break;
                case "listarProductos":
                    ctl.listarProductos();
                    out.write(jsn.fromArray(Modelo.productos));
                    break;
                default:
                    out.write("eco");
                    break;
                    
            }
        } catch (Exception e) {
                System.out.println(e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    public void init() throws ServletException {
        super.init();
        domainModel = new Control();
    }
}
