<%-- 
    Document   : Index
    Created on : 01/03/2018, 06:28:02 PM
    Author     : Manuel Céspedes
--%>

<%@page import="Model.Modelo"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="labProductosBE.LogicaNegocio.TipoProducto"%>
<%@page import="Control.Control"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Control control = new Control();
    String tiposOption = "";
    final String option = "<option id='%d' value='%s'>%s</option>\n";
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
              integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" 
              integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" 
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css"/>
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script type="text/javascript" src="javaScript/Producto.js"></script>
        <script type="text/javascript" src="javaScript/Proxy.js"></script>
        <script type="text/javascript" src="javaScript/TipoProducto.js"></script>
        <script type="text/javascript" src="javaScript/genTable.js"></script>
        <script type="text/javascript" src="javaScript/eventos.js"></script>
    </head>
    <body>
        <%@ include file="NavBar.jspf" %>
        <table>
            <form action="#">
                <tr>
                    <td id="columBusquedaLbl">
                        <label>Nombre</label>
                    </td>
                    <td id="columBusqueda">
                        <div class="input-group">
                            <input id="buscarNombre" type="text" class="form-control" placeholder="Nombre" name="buscarNombre">
                            <div class="input-group-btn">
                                <button id="buscarNombreBttn" class="btn btn-default"><i class="glyphicon glyphicon-search"></i></button>
                            </div>
                        </div>
                    </td>
                    <td id="columBusquedaLbl"> 
                        <label>Tipo</label>
                    </td>
                    <td id="columBusqueda">
                        <div class="input-group">
                            <select class="form-control" id="sel1">
                                <%
                                    try {
                                        control.listarTiposProductos();
                                        List<TipoProducto> list = Modelo.tipos;
                                        for (TipoProducto t : list) {
                                            tiposOption += (String.format(option,
                                                    t.getCodigo(),
                                                    t.getDescripcion(),
                                                    t.getDescripcion()));
                                        }
                                        out.println(tiposOption);
                                    } catch (Exception ex) {
                                        out.print("alert(" + ex.toString() + ")");
                                    }
                                %>
                            </select>
                            <div class="input-group-btn">
                                <button id="buscarTipoBttn" 
                                        class="btn btn-default" 
                                        type="submit"><i class="glyphicon glyphicon-search"></i></button>
                            </div>
                            <br/>
                            <br/>
                        </div>
                    </td>
                </tr>
            </form>
        </table>
        <table id="tablaProductos" class="table table-striped table-hover display">
            <thead>
            </thead>
            <tbody id="listadoProductos">

            </tbody>
        </table>
        <div id="tabla"></div>

        <table>
            <form>
                <tr>
                    <td id="columFormLbl"><label for="codigo">Codigo</label></td>
                    <td id="columForm">
                        <div class="input-group">
                            <input id="codigo" type="text" class="form-control" placeholder="Codigo" name="codigo">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td id="columFormLbl"><label for="nombre">Nombre</label></td>
                    <td id="columForm">
                        <div class="input-group">
                            <input id="nombre" type="text" class="form-control" placeholder="Nombre" name="nombre">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td id="columFormLbl"><label for="Precio">Precio</label></td>
                    <td id="columForm">
                        <div class="input-group">
                            <input id="precio" type="text" class="form-control" placeholder="Precio" name="precio">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td id="columFormLbl"><label for="importado">Importado</label></td>
                    <td>
                        <div class="checkbox">
                            <label><input type="checkbox" value=""></label>
                        </div
                    </td>
                </tr>
                <tr>
                    <td id="columFormLbl"><label for="tipo">Tipo</label></td>
                    <td>
                        <div class="input-group">
                            <select class="form-control" id="sel12">
                                <%
                                    try {
                                        out.println(tiposOption);
                                    } catch (Exception ex) {
                                        out.print("alert(" + ex.toString() + ")");
                                    }
                                %>
                            </select>
                        </div>
                    </td>
                    <td>
                        <div class="btn-group">
                            <button type="button"
                                    id="btnAdd"
                                    class="btn btn-primary">Agregar</button>
                        </div>
                    </td>
                </tr>
            </form>
        </table>
    </body>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
    <!--<script type="text/javascript" src="javaScript/Productojs.js"></script>-->
    <!--
    <script type="text/javascript" src="javaScript/JsonUtils.js"></script>
    <script type="text/javascript" src="javaScript/Proxy.js"></script>
    <script type="text/javascript" src="javaScript/Producto.js"></script>

    <script> // Modelo
        function Modelo() {
            this.Modelo();
        }

        Modelo.prototype = {
            Modelo: function () {
            }
        };
    </script>
    <script> // Control
        function Control(modelo, vista) {
            this.Control(modelo, vista);
        }

        Control.prototype = {
            Control: function (modelo, vista) {
                this.modelo = modelo;
                this.vista = vista;
                Proxy.getProductos(
                        function (result) {
                            modelo.productos = result;
                            //vista.loadList(modelo.productos, "listadoProductos");
                        });
            }
        };

    </script>
    <script> // Vista
        var modelo;
        var control;
        function pageLoad() {
            $(document).ready(function () {
                modelo = new Modelo();
                control = new Control(modelo, window);
                
            });
        }

            /*$('#example').DataTable({
                "language": {
                    "lengthMenu": "Mostrar _MENU_ registros por página",
                    "zeroRecords": "No se encontraron registros coincidentes",
                    "info": "Mostrando página _PAGE_ de _PAGES_",
                    "infoEmpty": "No hay registros disponibles",
                    "infoFiltered": "(filtrado de _MAX_ registros totales)",
                    "search": "Buscar",
                    "loadingRecords": "Cargando...",
                    "processing": "Procesando...",
                    "decimal": ",",
                    "thousands": ".",
                    "paginate": {
                        "first": "Primero",
                        "last": "Último",
                        "next": "Siguiente",
                        "previous": "Anterior"
                    }
                },
                "scrollX": true
            });*/
        
        document.addEventListener("DOMContentLoaded", pageLoad);
    </script>
    -->
</html>
