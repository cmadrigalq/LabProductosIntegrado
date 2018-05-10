/* global Prodcuto */

class Producto {
    constructor(codigo, nombre, precio, importado, tipo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.importado = importado;
        this.tipo = tipo;
    }

    static nuevo() {
        let p = new Producto(
                $("#codigo").val(),
                $("#nombre").val(),
                parseInt($("#precio").val()),
                $("#checkbox").is(':checked'),
                new TipoProducto(
                        $("#sel12 option:selected").attr("id")
                        , "*", 0.0
                        )
                );
        if (isNaN(p.precio)) {
            alert("verifique que el precio se un valor numerico");
            return null;
        }
        return p;
    }

    static add(prdt) {
        Proxy(
                "ProductosService",
                "addproducto",
                "POST",
                res => {
                    if (res === true) {
                        alert("ELEMENTO AÑADIDO!");
                        Producto.buscarPorNombre(prdt.nombre);
                    } else {
                        alert("El producto no se ha podido añadir, verifique la conexión, y que el producto no exista ya :( ")
                    }
                },
                prdt
                );
    }

    static procesarRespuesta(res) {
        if (!Array.isArray(res)) {
            return alert("ha ocurrido algun error");
        }
        let objetos = res.reduce(
                (resultado, producto) => {
            let obj = {
                Nombre: producto.nombre,
                Importado: producto.importado,
                Precio: producto.precio,
                tipoProducto: producto.tipo.descripcion,
                porcentaje: producto.tipo.porcentaje,
                impuesto: producto.precio / producto.tipo.porcentaje,
                precioFinal: producto.precio + (producto.precio / producto.tipo.porcentaje)
            };
            resultado.push(obj);
            return resultado;
        },
                []);
        let table = new Table("tabla",
                ["Nombre", "Importado", "Precio", "Tipo de producto", "Porcentaje", "Impuesto", "Precio Final"],
                objetos,
                ["Nombre", "Importado", "Precio", "tipoProducto", "porcentaje", "impuesto", "precioFinal"]
                );
        table.createTable();
    }
    static buscarPorNombre(nombre = "") {
        Proxy(
                "ProductosService",
                "productosByNombre",
                "POST",
                res => {
                    Producto.procesarRespuesta(res);
                },
                nombre
                );
    }
    static buscarPorTipo(tipo = 0) {
        Proxy(
                "ProductosService",
                "productosByTipo",
                "POST",
                res => {
                    Producto.procesarRespuesta(res);
                },
                tipo
                );
    }

}