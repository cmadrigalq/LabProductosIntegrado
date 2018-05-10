class Table {
    constructor(div, encabezados, objetos, atributos) {
        this.div = "#" + div;
        this.encabezados = encabezados;
        this.objetos = objetos;
        this.atributos = atributos;
    }
    createTable() {
        $(this.div).empty();
        let tabla = document.createElement("table");
        tabla.setAttribute("class", "datagrid scroll_table");
        $(this.div).append(tabla);
        tabla.appendChild(this.createHeader());
        tabla.appendChild(this.createBody());
    }
    createHeader() {
        let head = document.createElement("thead");
        head.appendChild(this.createRow(null, this.encabezados, 0));
        return head;
    }
    createRow(obj, ats, idx, isHeader = true) {
        let row = document.createElement("tr");
        row.idx = idx;
        if (isHeader) {
            for (var e in ats) {
                row.appendChild(this.Cell(ats[e], idx, isHeader));
            }
        } else {
            this.atributos.forEach(att => {
                row.appendChild(this.Cell(obj[att], null, obj, idx, isHeader));
            });
        }
        return row;
    }
    Cell(value, idx = 0, hdr) {
        value = value === "" ? "-" : value;
        let cell = document.createElement(hdr ? "th" : "td");
        if (value instanceof Date) {
            value = dateToText(value);
        }
        if (typeof value === "object") {
            cell.appendChild(value);
        } else
            cell.innerHTML = value;
        return cell;
    }
    createBody() {
        let body = document.createElement("tbody");
        this.objetos.forEach(
                (e, i) => {
            body.appendChild(this.createRow(e, this.atributos, i, false));
        }
        );
        return body;
    }

}