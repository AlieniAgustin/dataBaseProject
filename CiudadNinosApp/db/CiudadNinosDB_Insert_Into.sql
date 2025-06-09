insert into Padrino
    (dni, nombre, apellido, fecha_nacimiento, tel_movil, tel_fijo, cod_postal, direccion, facebook, email)
values
    ('12345678', 'María', 'González', '1975-03-15', '1156789123', '1145678912', '1406', 'Av. San Martín 123', 'maria.gonzalez', 'maria.gonzalez@gmail.com'),
    ('87654321', 'Carlos', 'Ramírez', '1982-07-22', '1167891234', '1143216789', '1425', 'Calle Rivadavia 456', 'carlos.ramirez', 'carlos.ramirez@gmail.com'),
    ('23456789', 'Lucía', 'Fernández', '1990-12-05', '1178912345', '1134567890', '1408', 'Pasaje Italia 789', 'lucia.fernandez', 'lucia.fernandez@gmail.com'),
    ('34567890', 'Juan', 'Martínez', '1985-10-01', '1151234567', '1147654321', '1410', 'Calle Córdoba 101', 'juan.martinez', 'juan.martinez@gmail.com'),
    ('45678901', 'Sofía', 'López', '1978-05-21', '1162345678', '1148765432', '1407', 'Av. Belgrano 202', 'sofia.lopez', 'sofia.lopez@gmail.com'),
    ('56789012', 'Diego', 'Pérez', '1969-03-30', '1153456789', '1149876543', '1420', 'Pasaje Mendoza 303', 'diego.perez', 'diego.perez@gmail.com'),
    ('67890123', 'Ana', 'Torres', '1992-08-17', '1164567890', '1140987654', '1415', 'Calle Corrientes 404', 'ana.torres', 'ana.torres@gmail.com'),
    ('78901234', 'Miguel', 'Sosa', '1988-11-11', '1155678901', '1141098765', '1409', 'Av. Santa Fe 505', 'miguel.sosa', 'miguel.sosa@gmail.com'),
    ('89012345', 'Clara', 'Vega', '1977-09-27', '1166789012', '1142109876', '1423', 'Calle Lavalle 606', 'clara.vega', 'clara.vega@gmail.com'),
    ('90123456', 'Jorge', 'Díaz', '1983-04-05', '1157890123', '1143210987', '1405', 'Av. Independencia 707', 'jorge.diaz', 'jorge.diaz@gmail.com');

insert into Contacto
    (dni, fecha_rechazo_adhesion, estado, fecha_primer_contacto, fecha_baja, fecha_alta)
values
    ('12345678', null, 'activo', '2022-01-15', null, '2022-01-16'),
    ('87654321', '2023-03-10', 'rechazado', '2023-01-05', null, '2023-01-06'),
    ('23456789', null, 'inactivo', '2021-07-20', '2022-12-01', '2021-07-21'),
    ('34567890', null, 'activo', '2020-05-15', null, '2020-05-16'),
    ('45678901', '2022-10-10', 'rechazado', '2022-08-01', null, '2022-08-02'),
    ('56789012', null, 'baja', '2019-09-25', '2023-01-30', '2019-09-26'),
    ('67890123', null, 'activo', '2023-04-01', null, '2023-04-02'),
    ('78901234', null, 'activo', '2023-02-10', null, '2023-02-11'),
    ('89012345', null, 'inactivo', '2021-11-05', '2022-11-05', '2021-11-06'),
    ('90123456', null, 'activo', '2022-07-07', null, '2022-07-08');

insert into Donante
    (dni, ocupacion, cuit_cuil)
values
    ('12345678', 'Profesor', '20-12345678-9'),
    ('87654321', 'Médico', '27-87654321-5'),
    ('34567890', 'Abogado', '23-34567890-1'),
    ('45678901', 'Ingeniera', '27-45678901-8'),
    ('56789012', 'Contador', '20-56789012-2'),
    ('67890123', 'Técnica en Informática', '27-67890123-6'),
    ('78901234', 'Psicólogo', '23-78901234-4');

insert into Programa
    (nro_programa, descripcion, nombre)
values
    (22, 'Cocinamos locro con alumnos del primario', 'Cocina de locro'),
    (21, 'Cocinamos empanadas con alumnos del secundario', 'Cocina de empenadas'),
    (17, 'Campaña de recolección de libros infantiles', 'Campaña libros'),
    (3, 'Ciclo de cine con debate para adolescentes', 'Cine debate juvenil'),
    (11, 'Taller de huerta orgánica para familias', 'Huerta comunitaria');

insert into Medio_De_Pago (nombre_titular, tipo_tarjeta)
values 
    ('Laura Gómez', 'Credito'),         
    ('Pedro Suárez', 'Credito'),        
    ('Lucía Fernández', 'Debito'),      
    ('Martín Díaz', 'Transferencia'),   
    ('Sofía Herrera', 'Debito'),        
    ('Juan Torres', 'Credito'),         
    ('Natalia Vega', 'Transferencia');  

insert into Aporte
    (dni, nro_programa, monto, frecuencia, id_medio_pago)
values
    ('87654321', 22, 233.20, 'semestral', 1),
    ('87654321', 21, 500.20, 'mensual', 1),
    ('34567890', 11, 15000.50, 'mensual', 2),
    ('23456789', 17, 15000.50, 'semestral', 4),
    ('34567890', 3, 21000.00, 'mensual', 3);


insert into Tarjeta_Credito (id_medio_pago, nro_tarjeta, nombre_tarjeta, fecha_vencimiento)
values
    (1, '4111111111111111', 'Visa', '2026-05-31'),
    (2, '5500000000000004', 'MasterCard', '2027-11-30'),
    (6, '340000000000009',  'American Express', '2028-09-15');

insert into Debito_o_Transferencia (id_medio_pago, cbu, nro_cuenta, nombre_banco, sucursal_banco, tipo_cuenta)
values
    (3, '0720000330000000123456', '00012345678', 'Banco Nación', 'Sucursal Central', 'Caja de ahorro'),
    (4, '0070123460000001234567', '00123456789', 'Banco Galicia', 'Sucursal Palermo', 'Cuenta corriente'),
    (5, '0720055530000000111122', '00098765432', 'Banco Santander', 'Sucursal Belgrano', 'Caja de ahorro'),
    (7, '2850590940099999999999', '00987654321', 'Banco Macro', 'Sucursal Córdoba', 'Cuenta corriente');


/*Vistas Generales*/
SELECT * from Padrino;
SELECT * from Contacto;
SELECT * from Donante;
SELECT * from Programa;
SELECT * from Medio_De_Pago;
SELECT * from Aporte;
SELECT * from Debito_o_Transferencia;
SELECT * from Tarjeta_Credito;

SELECT COUNT(*) FROM Aporte;



/*Prueba trigger*/
delete from Donante where dni = '12345678';

/*Chequeo*/
SELECT * from Donante;
SELECT * from Auditoria_Donante;

/*Devolver por cada programa, el total de aportes mensuales.*/
SELECT nro_programa, SUM(monto) as total_aporte_mensual
FROM Aporte 
WHERE frecuencia = 'mensual'
GROUP BY nro_programa;

/*Devolver los donantes que aportan a más de dos programas.*/
SELECT a.dni, p.nombre, p.apellido 
FROM Aporte a 
NATURAL JOIN Padrino p
GROUP BY a.dni
HAVING COUNT(DISTINCT a.nro_programa) > 2;

/*Listado  de  Donantes  con  aportes  mensuales  y  los  datos  de  los  medios  de pago.*/
SELECT 
    a.dni,
    m.nombre_titular,
    m.tipo_tarjeta,
    
    -- Datos específicos de tarjeta de crédito
    tc.nro_tarjeta,
    tc.nombre_tarjeta,
    tc.fecha_vencimiento,

    -- Datos específicos de débito/transferencia
    dt.cbu,
    dt.nro_cuenta,
    dt.nombre_banco,
    dt.sucursal_banco,
    dt.tipo_cuenta

FROM Aporte a
JOIN Medio_De_Pago m ON a.id_medio_pago = m.id_medio_pago
LEFT JOIN Tarjeta_Credito tc ON m.id_medio_pago = tc.id_medio_pago
LEFT JOIN Debito_o_Transferencia dt ON m.id_medio_pago = dt.id_medio_pago
WHERE a.frecuencia = 'mensual';


