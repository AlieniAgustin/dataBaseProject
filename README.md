# CiudadNiñosApp - Gestión de Conexión a Base de Datos con Singleton

Alumnos: 
*Agustin Alieni.*
*Damian Dalio.*
*Hernan Jara.*

Este proyecto contiene una implementación de una aplicación Java que se conecta a una base de datos MySQL.

## 📌 Características principales

* Uso del **patrón Singleton** para gestionar la conexión a la base de datos.
* Menú interactivo en consola con múltiples operaciones sobre los datos.

---

## 🧠 ¿Qué es `DBConnection.java`?

El archivo `DBConnection.java` se encuentra en el paquete `app.ciudadninosapp` y se encarga de **manejar la conexión única** a la base de datos durante la ejecución del programa.

### ✅ Ventajas del patrón Singleton aplicado:

* Solo se crea **una instancia de conexión** a la base de datos.
* Se evita abrir y cerrar conexiones repetidamente.
* Mayor control sobre los recursos utilizados por la aplicación.

### 🔐 Seguridad

> ⚠️ **Importante**: El archivo contiene un campo `password` que deberás modificar por la contraseña correspondiente a tu entorno local:

```java
private static final String password = "TU_CONTRASEÑA";
```

---

## 🎮 ¿Qué hace `App.java`?

Este archivo contiene el `main` del programa y ofrece un menú interactivo que permite:

1. Insertar un nuevo padrino.
2. Eliminar un donante por DNI.
3. Listar padrinos junto con los programas que apadrinan.
4. Calcular el total de aportes mensuales por programa.
5. Mostrar donantes que aportan a más de dos programas.
6. Mostrar donantes con medios de pago y aportes mensuales.

### 👩‍💻 Tecnologías utilizadas:

* Java SE
* JDBC
* MySQL

---

## ⚙️ Cómo ejecutar

1. Cloná el repositorio:

```bash
git clone https://github.com/tu_usuario/ciudadninosapp.git
```

2. Configurá tus credenciales en `DBConnection.java`.
3. Ejecutá la clase `App` desde tu IDE o terminal.

---

## 🏗️ Requisitos

* Java 8 o superior
* Driver JDBC de MySQL
* Base de datos `ciudadninosdb` creada y accesible

---

## 📂 Estructura del proyecto

```
app/
 └── ciudadninosapp/
     ├── App.java
     └── DBConnection.java
```

---
