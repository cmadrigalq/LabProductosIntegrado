/* global Producto */

$(document).ready(
        _ => {
            $("#buscarNombreBttn").click(e => {
                Producto.buscarPorNombre(
                        $("#buscarNombre").val()
                        );
            });
            $("#buscarTipoBttn").click(e => {
                Producto.buscarPorNombre(
                        $("#sel1 option:selected").attr("id")
                        );
            });
            $("#btnAdd").click(e => {
                let p = Producto.nuevo();
                if (p !== null) {
                    Producto.add(p);
                }
            });

        }
);