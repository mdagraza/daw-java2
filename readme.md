# Aplicación de Gestión con Persistencia en Base de Datos (JDBC)

Este proyecto consiste en una aplicación Java que interactúa con una base de datos MariaDB para gestionar registros de forma dinámica. El sistema permite realizar las operaciones fundamentales de mantenimiento de datos (CRUD) a través de una interfaz de usuario conectada a un servidor local.

## Funcionalidades Principales

La aplicación permite la gestión completa de una tabla de datos mediante las siguientes funciones:

- **Lectura Completa:** Visualización de todos los registros almacenados en la base de datos.
- **Inserción de Datos:** Capacidad para añadir nuevos registros a la tabla en tiempo real.
- **Eliminación de Registros:** Borrado selectivo de entradas existentes en la base de datos.
- **Consultas Filtradas:** Visualización de registros específicos que cumplen con una condición determinada.
- **Análisis de Datos:** Función para contabilizar cuántos registros cumplen con ciertos criterios específicos.

## Tecnologías Utilizadas

- **Java:** Lenguaje principal para la lógica de la aplicación.
- **JDBC (Java Database Connectivity):** Para la conexión y ejecución de sentencias SQL desde el código Java.
- **MariaDB:** Sistema de gestión de bases de datos relacionales utilizado para el almacenamiento.
- **PhpMyAdmin:** Herramienta utilizada para la administración, diseño y exportación de la estructura de la base de datos.

## Estructura de la Base de Datos

El proyecto incluye un archivo `.sql` necesario para replicar la estructura de la base de datos en cualquier gestor local.

### Requisitos para la Ejecución

1. Disponer de un servidor local (como XAMPP o WAMP) en la dirección `127.0.0.1`.
2. Importar el archivo SQL adjunto para generar la tabla y los datos iniciales.
3. Configurar el conector JDBC de MariaDB en el proyecto Java.

## Detalles de Implementación

- **Seguridad en Consultas:** Implementación de sentencias para evitar errores de sintaxis y asegurar la integridad de los datos.
- **Arquitectura:** Separación de la lógica de conexión y la lógica de negocio para facilitar el mantenimiento del código.
- **Interfaz:** Menú interactivo que guía al usuario a través de todas las operaciones disponibles.

---

_Proyecto desarrollado para la asignatura de Programación del Ciclo Superior en Desarrollo de Aplicaciones Web/Multiplataforma._
