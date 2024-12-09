# Relax App

## **Objetivo del Proyecto**

El objetivo principal de este proyecto es dar a conciencia y apoyar a las personsas que necesiten ayuda psicológica y, por alguna razón, no puedan salir de sus casas ya sean por problemas de transporte o por aislamiento social brindándoles herramientas como chats con psicólogos y psiquiatras y, a la vez, ejercicios ya sean de meditación, respiración, etc. Para poder apoyarlos aunque no hablen con un profesional. A su vez, hay un calendario donde se puede actualizar cada determinado tiempo la emoción que sienten demostradas con Emojis y, al finalizar el día, puedan ver en retrospectiva los sentimmientos que han llegado a sentir ese día.

---

## **¿Quién lo utilizaría?**

El público objetivo de la aplicación sería cualquier persona que necesite de algún tipo de atención psicológica o solo quieran mejorar su salud mental.

---

## **¿Cómo monetizar la aplicación?**
Explica las posibles estrategias para generar ingresos. 


---

## **Impacto en los Usuarios**
La aplicación permitirá a los usuarios acceder a servicios de apoyo psicológico sin importar su ubicación, ayudándoles a manejar sus emociones y estrés. Además, dará herramientas de autoayuda, como ejercicios de meditación y respiración, que mejorarán la salud mental y emocional de los usuarios, mejorando su calidad de vida.

---

## **Lenguaje de Programación**
Indica el lenguaje utilizado y una breve descripción. 

---

## **Versión Mínima de Android**
Indica la versión más antigua de Android que soportará la aplicación. 


---

## **Estructura Organizacional del Proyecto**
Explica cómo está organizado el proyecto. Ejemplo:  
- Carpeta `models/` para las clases de datos.
La carpeta models esta dentro de la carpeta de las pantallas, ya que basicamente ocupamos los modelos para poder replicar las respuestas de la API y que de esa manera el Android Studio lea los datos y los pueda mapear.
  
- Carpeta `views/` para las interfaces de usuario.
La carpeta views seran todas las vistas del usuario, que a su vez estas estan dentro de una carpeta con el nombre de la pantalla de la vista para poder diferencial cual es cual.
  
- Carpeta `viewModels/` para la lógica de negocio.
La carpeta viewModels esta organizada por cada vista, ya que cada vista tiene su propia logica detras donde se emplean funciones para utilizarlas en las vistas.
---

## **Patrón de Diseño**
La aplicación sigue el patrón MVVM (Model-View-ViewModel), donde:

Model: Representa los datos de la aplicación.
View: Es la interfaz de usuario (UI).
ViewModel: Actúa como el intermediario entre la UI y los datos, gestionando la lógica de presentación y la interacción con los datos.

---

## **Herramienta para la Creación de Vistas**
Indica la tecnología utilizada.   

---

## **Arquitectura Cliente-Servidor**
Describe cómo interactúa la aplicación con un servidor. 
Para poder tener una correcta comunicacion con el servidor, primero tenemos que configurar las rutas en una clase que creamos llamada Api Service, donde van a ir los @POST, cabe destacar que tienen que ser iguales al backend ya que si la ruta puesta esta mal, el servidor no va a poder recibir correctamente la peticion, una vez hechas las rutas, tenemos que crear las funciones que se van a implementar y que parametros va a llevar cada una. Posteriormente tenemos que crear data clases que pongan exactamente la respuesta del servidor, ya que para lograr un correcto mapeo de los datos en Android Studio, tenemos que hacer que la respuesta JSON sea igual en Android Studio. Una vez logrado todo lo mencionado anteriormente, en los ViewModels, tenemos que llamar a las funciones que creamos para poder implementarlas en la vista y que cuando se intente utilizar logica del backend desde el frontend funcione correctamente.
---

## **Verbos Comunes del Protocolo HTTP**
Menciona ejemplos. Ejemplo:  
- **GET**: @GET(users/getUser), este get lo que haria seria traer toda la informacion del usuario.
- **POST**: @POST(login), este post pasaria los datos que el usuario proporciono en el frontend al backend para que se verifiquen y garantizar si la informacion es correcta o no.
- **DELETE**: @(notification/deleteNotification), el delete como su nombre lo indica, actua para eliminar, basicamente las funciones que tenemos que eliminan datos pasan por el protocolo DELETE.

---

## **Estatus de Respuesta del Servidor**
200 OK: La solicitud fue exitosa y el servidor ha devuelto la respuesta esperada.
400 Bad Request: La solicitud es incorrecta o malformada.
401 Unauthorized: El usuario no está autorizado para realizar la acción solicitada.
404 Not Found: El recurso solicitado no se encuentra en el servidor.
500 Internal Server Error: El servidor ha encontrado un error inesperado.

## **Características Técnicas**

### Base64
- **¿Para qué sirve?**
Base64 se utiliza para codificar datos binarios (como imágenes o archivos) en un formato de texto. Se emplea en la aplicación para enviar imágenes o archivos adjuntos durante las consultas con psicólogos.
    

### Data Class  
- **¿Qué es?**
Una Data Class es una clase en Kotlin que se utiliza principalmente para almacenar datos. Se emplea para representar entidades como usuarios, consultas y emociones.
  

### Navegación
- **¿Cómo funciona?**
Para cada pantalla creada se tiene que poner en un archivo de Rutas, esto con el fin de poder saber como se llaman las rutas que vamos a proporcionar a la aplicacion, una vez hecho esto, el siguiente paso seria poner las rutas en un archivo de navegacion, como por ejemplo, "MyAppNavigationApp", en este archivo es donde va toda la logica de navegacion, es el papa por asi decirlo, aqui es donde se ponen todas las rutas que tiene la pantalla. Es importante tomar en cuenta que para que la navegacion funciones correctamente tenemos que hacer que cada vista tenga el parametro de navController, ya que sin este parametro nunca se va a implementar la navegacion en las pantallas.
  
### SharedPreferences
- **¿Qué es?**
El SharedPreference es una manera de poder guardar datos en la memoria del dispositivo para su uso futuro, como por ejemplo, el token de la sesion o el id del usuario con la sesion activa. Estos datos se mantienen incluso cuando la aplicación se cierra o el dispositivo se reinicia. Sin embargo, para quitar el SharedPreference y no dejar rastro se tiene que eliminar de tu dispositivo el cache de la aplicacion.
- **¿Cómo se usa?**
Se tiene que hacer un documento donde se inicialice una instancia de SharedPreference, una vez hecha la instancia, se pueden guardar datos para poder usarse en diferentes partes de la aplicacion. No es recomendable guardar datos grandes ya que no tiene un buen nivel de seguridad.


### Permisos de la aplicación 
- **¿Cómo se agregan?**
Los permisos de la aplicacion se guardan desde al AndroidManifest. Este archivo es esencial para configurar aspectos importantes de la aplicacion, como por ejemplo, el nombre, el icono de la aplicacion, y los permisos necesarios. Los permisos permiten a la aplicacion acceder la internet, la cámara, o el almacenamiento.

---

## **Generación del APK**
Explica el proceso. 

---

## **Librerías Utilizadas**
Lista las librerías clave del proyecto. Ejemplo:  
- Retrofit: Para manejo de solicitudes HTTP.  
  

---

## **Debugging y Logs**
Describe cómo manejar errores. Ejemplo:  
- Utiliza `Log.d("TAG", "Mensaje")` para depuración en tiempo de ejecución.  


---

## **Firebase Cloud Messaging**
- **¿Qué es y cómo funciona?**  


---

## **Archivo Scratch**
Describe el propósito de un archivo Scratch.   

---
