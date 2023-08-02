# Aplicativo Web para la Gestión Gerencial, Control y Recaudo de Parqueaderos

Este es un proyecto que consiste en un aplicativo web desarrollado para facilitar la gestión gerencial, control y recaudo de parqueaderos. El sistema proporciona una serie de funciones clave que permiten llevar un control exhaustivo y eficiente de las operaciones del parqueadero. A continuación, se describen las principales características y funcionalidades del sistema:

## Características del Sistema

### 1. Autenticación y Seguridad

El sistema cuenta con un sistema de autenticación y seguridad que permite el acceso exclusivo a usuarios registrados por el administrador. Se requiere un inicio de sesión para acceder a las funcionalidades del aplicativo, asegurando la confidencialidad de la información.

### 2. Registro de Entrada y Salida de Vehículos

El sistema facilita el registro de la entrada y salida de vehículos mediante el ingreso de la placa del vehículo. De esta forma, se lleva un registro detallado de las actividades del parqueadero.

### 3. Generación de Reportes en PDF

El sistema tiene la capacidad de generar un archivo PDF con información completa de las actividades del parqueadero. Los reportes incluyen la cantidad de vehículos, los ingresos económicos totales, la fecha y el usuario responsable de generar el reporte. Además, el sistema enviará automáticamente un correo electrónico al gerente con el archivo adjunto, notificándole la contabilidad.

### 4. Búsqueda Personalizada

El aplicativo permite realizar búsquedas personalizadas para facilitar la revisión de información específica. El administrador puede filtrar vehículos por número de placa o por fecha, visualizando solo aquellos registros que cumplan con el filtro.

### 5. Registro de Planes de Mensualidad

El sistema permite el registro de vehículos que deseen adquirir un plan de mensualidad. De esta manera, se lleva un control preciso de los clientes con planes especiales.

### 6. Gestión de Precios

El gerente tiene la opción de incrementar el precio del servicio del parqueadero y el sistema automáticamente realizará los cálculos de los pagos utilizando el nuevo precio.

### 7. Visualización de Datos en Tiempo Real

El aplicativo proporciona datos precisos en tiempo real, lo que facilita el control total del parqueadero y la toma de decisiones basada en información actualizada.

### 8. Descarga de Informes de Ingresos

El administrador puede descargar informes de ingresos con filtros de fechas, lo que permite obtener el total de ingresos por cada tipo de vehículo en períodos mensuales, trimestrales o anuales. Estos informes son valiosos para orientar la toma de decisiones en beneficio del parqueadero.

## Tecnologías Utilizadas

El desarrollo de este aplicativo web se basó en las siguientes tecnologías:

- **Spring Boot:** Se utilizó como el framework principal para el desarrollo del backend del aplicativo. Spring Boot ofrece un entorno ágil y eficiente para la creación de aplicaciones web.
- **JasperReport:** Esta herramienta se empleó para generar los reportes en formato PDF, brindando una presentación clara y estructurada de la información.
- **MySQL:** Se utilizó como la base de datos del sistema, proporcionando un almacenamiento seguro y confiable de los datos del parqueadero.
- **Thymeleaf:** Fue utilizado como el motor de plantillas para la generación de las vistas del aplicativo web, facilitando la presentación de información dinámica al usuario.
- **Data JPA:** Se empleó como la capa de persistencia para interactuar con la base de datos de manera eficiente y sencilla.
- **DAO (Data Access Object):** Se implementó el patrón DAO para separar la lógica de negocio de la lógica de acceso a datos, lo que mejora la modularidad y mantenibilidad del sistema.
- **Tareas Programadas con Scheduled:** Se programaron tareas automáticas para ejecutarse en momentos específicos, lo que permite automatizar ciertas funciones y mantener la actualización constante del sistema.
- **Paginator:** Se implementó un paginador para mejorar la experiencia del usuario al visualizar grandes conjuntos de datos, dividiendo la información en páginas más manejables.

## Instalación y Configuración

Para instalar y configurar el aplicativo web, se deben seguir los siguientes pasos:

1. Clonar el repositorio del proyecto desde GitHub.
2. Configurar las credenciales de la base de datos MySQL en el archivo de configuración correspondiente (application.properties).
3. Ejecutar el aplicativo utilizando Spring Boot.