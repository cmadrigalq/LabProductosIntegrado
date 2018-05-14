CREATE TABLE TipoProducto(
id_tipo int,
descripcion varchar(50),
porcentaje float,
constraint pkTipoProducto primary key (id_tipo)
);
/

CREATE TABLE Producto(
codigo varchar(30),
nombre varchar(50),
precio int,
importado varchar(1),
id_tipo int,
constraint pkProducto primary key (codigo),
constraint fkTipoProducto foreign key (id_tipo) references TipoProducto(id_tipo)
);
/

CREATE OR REPLACE procedure insertar_tipoProducto(xid_tipo int, xdescripcion varchar, xporcentaje float)
AS
BEGIN
 insert into TipoProducto(id_tipo, descripcion, porcentaje)
 values (xid_tipo,xdescripcion,xporcentaje);
END;
/

CREATE OR REPLACE procedure insertar_producto(xcodigo varchar, xnombre varchar, xprecio int, ximportado varchar, xid_tipo int)
AS
BEGIN
 insert into Producto(codigo, nombre, precio, importado, id_tipo)
 values (xcodigo,xnombre,xprecio,ximportado,xid_tipo);
END;
/

CREATE OR REPLACE PACKAGE types 
as
TYPE ref_cursor is ref cursor;
END;
/

CREATE OR REPLACE FUNCTION listar_producto
RETURN types.ref_cursor
AS
producto_cursor types.ref_cursor;
BEGIN
OPEN producto_cursor FOR
select Producto.codigo, Producto.nombre, Producto.precio, Producto.importado, Producto.id_tipo, 
TipoProducto.descripcion, TipoProducto.porcentaje from Producto,TipoProducto
where Producto.id_tipo=TipoProducto.id_tipo;
RETURN producto_cursor;
END;
/

CREATE OR REPLACE FUNCTION listar_tipoProducto
RETURN types.ref_cursor
AS 
tipoProducto_cursor types.ref_cursor;
BEGIN
OPEN tipoProducto_cursor FOR
select id_tipo, descripcion, porcentaje from TipoProducto;
return tipoProducto_cursor;
END;
/

CREATE OR REPLACE FUNCTION listar_productoByTipo(idTipo IN Producto.id_tipo%TYPE)
RETURN Types.ref_cursor 
AS
producto_cursor types.ref_cursor;
BEGIN
OPEN producto_cursor FOR
select p.codigo,p.nombre,p.precio,p.importado,p.id_tipo,t.id_tipo,t.descripcion,t.porcentaje
FROM Producto p INNER JOIN TipoProducto t on p.id_tipo = t.id_tipo 
WHERE p.id_tipo = idTipo;
RETURN producto_cursor;
END;
/

SELECT listar_productoByTipo(1) FROM DUAL;

-- Insert into TipoProducto
EXECUTE insertar_tipoProducto(1,'Can. Básica',5);
EXECUTE insertar_tipoProducto(2,'Popular',10);
EXECUTE insertar_tipoProducto(3,'Suntuario',15);
EXECUTE insertar_tipoProducto(4,'Agrícola',1);
EXECUTE insertar_tipoProducto(5,'Farmacéutico',7);
EXECUTE insertar_tipoProducto(6,'Químico',20);

-- Insert into Producto
EXECUTE insertar_producto('p1','Arroz Tico 1K',500,'N',1);
EXECUTE insertar_producto('p2','Frijol Tico 1K',450,'N',1);
EXECUTE insertar_producto('p3','Azúcar Tico 1K',400,'N',1);
EXECUTE insertar_producto('p4','Gaseosa 2L',900,'S',2);
EXECUTE insertar_producto('p5','Pasta Dental 100g',1500,'S',2);
EXECUTE insertar_producto('p6','Shampoo',2100,'S',2);
EXECUTE insertar_producto('p7','Vino Jerez',10000,'S',3);
EXECUTE insertar_producto('p8','Manzana Tica 1k',1000,'N',4);
EXECUTE insertar_producto('p9','Lechuga 1K',750,'N',4);
EXECUTE insertar_producto('p10','Aspirina',100,'S',5);
EXECUTE insertar_producto('p11','Cloro 250ML',500,'S',6);