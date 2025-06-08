create database
if not exists ciudadninosdb;
use ciudadninosdb;

drop table if exists Padrino;
create table Padrino
(
    dni varchar(10) not null,
    nombre varchar(20) not null,
    apellido varchar(20) not null,
    fecha_nacimiento date not null,
    tel_movil varchar(20) not null,
    tel_fijo varchar(20) not null,
    cod_postal varchar(10) not null,
    direccion varchar(30) not null,
    facebook varchar(20) not null,
    email varchar(40) not null,

    primary key(dni),

    constraint control_long_dni check (char_length(dni) >= 7),
    constraint control_telefonos check (tel_movil <> tel_fijo)
);

drop table if exists Contacto;
create table Contacto
(
    dni varchar(10) not null,
    fecha_rechazo_adhesion date default null,
    estado varchar(30) not null,
    fecha_primer_contacto date not null,
    fecha_baja date default null,
    fecha_alta date not null,

    primary key(dni),

    key fk_contacto
    (dni),

    constraint fk_contacto foreign key
    (dni) references Padrino(dni)
    on delete cascade,

    constraint control_estado_valido check
    (estado in ('activo', 'inactivo', 'rechazado', 'baja', 'otro')),

    constraint control_fechas_logicas check
    (
        fecha_primer_contacto <= fecha_alta
        and (fecha_baja is null or fecha_baja >= fecha_alta)
        and (fecha_rechazo_adhesion is null or fecha_rechazo_adhesion >= fecha_primer_contacto)
    )
);

drop table if exists Donante;
create table Donante
(
    dni varchar(10) not null,
    ocupacion varchar(40) not null,
    cuit_cuil varchar(20) not null,

    primary key(dni),

    key fk_donante (dni),
    constraint fk_donante foreign key (dni) references Padrino(dni) on delete cascade,
    constraint uq_cuit_cuil unique (cuit_cuil)
);

drop table if exists Programa;
create table Programa
(
    nro_programa int not null,
    descripcion varchar(100) not null,
    nombre varchar(20) not null,

    primary key(nro_programa),
    constraint uq_nombre unique (nombre)
);

drop table if exists Medio_De_Pago;
create table Medio_De_Pago
(
	id_medio_pago int not null auto_increment,
	nombre_titular varchar(50) not null,
	tipo_tarjeta enum('Credito', 'Debito', 'Transferencia') not null,
	
	primary key (id_medio_pago)
);

drop table if exists Aporte;
create table Aporte
(
    dni varchar(10) not null,
    nro_programa int not null,  
    monto DECIMAL(10, 2) not null,
    frecuencia enum('semestral', 'mensual') not null,
    id_medio_pago int not null,

    primary key(dni, nro_programa),

    key fk_aporte1 (dni),
    key fk_aporte2 (nro_programa),
    key fk_aporte3 (id_medio_pago),

    constraint fk_aporte1 foreign key (dni) references Donante(dni) on delete cascade,
    constraint fk_aporte2 foreign key (nro_programa) references Programa(nro_programa) on delete cascade,
    constraint fk_aporte3 foreign key (id_medio_pago) references Medio_De_Pago(id_medio_pago) on delete cascade
);

drop table if exists Tarjeta_Credito;
create table Tarjeta_Credito
(
	id_medio_pago int not null,
	nro_tarjeta varchar(20) UNIQUE not null,
	nombre_tarjeta varchar(30) not null,
	fecha_vencimiento date not null,

	primary key(id_medio_pago),
	foreign key(id_medio_pago) references Medio_De_Pago(id_medio_pago) on delete cascade
);

drop table if exists Debito_o_Transferencia;
create table Debito_o_Transferencia
(
	id_medio_pago int not null,
	cbu varchar(22) not null UNIQUE,
	nro_cuenta varchar(20) not null,
	nombre_banco varchar(50) not null,
	sucursal_banco varchar(50) not null,
	tipo_cuenta varchar(30) not null,
	
	primary key (id_medio_pago),
	foreign key (id_medio_pago) references Medio_De_Pago(id_medio_pago) on delete cascade
);


drop table if exists Auditoria_Donante;
create table Auditoria_Donante
(
		dni_donante varchar(10) not null,
        fecha_delete date not null,
        usuario varchar(100) not null
);

delimiter $$

drop trigger if exists auditoria_delete_donante;
create trigger auditoria_delete_donante
after delete on Donante
for each row
begin
    insert into Auditoria_Donante (dni_donante, fecha_delete, usuario)
    values 
		(OLD.dni, NOW(), SUBSTRING_INDEX(CURRENT_USER(), '@', 1));
end$$

delimiter ;
