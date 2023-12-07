# Práctica

## Proyecto realizado por Lydia Moya Ruiz

## MANEJO DE FICHEROS

# Objetivo

El objetivo principal de este proyecto es crear una aplicación en Java que funcione como un sistema de gestión para una biblioteca. La aplicación debe permitir realizar operaciones como agregar, leer, actualizar y eliminar,(CRUD), información sobre libros y autores, así como llevar un registro de préstamos y devoluciones en formato txt. Para almacenar los datos de manera eficiente, se utilizarán varios tipos de archivos, como binarios, de texto, objetos y XML.

# Estructura del Proyecto

Clase Principal: En esta clase encontramos el menú principal en cuál el usuario debe de interactuar con él para el registro de los datos. Depende de su respuesta eligirá hacer una acción u otra, ya que se pueden gestionar libros, autores y prestamos. Por último podemos importar o exportar datos en XML.

Clases Libro, Autor y Préstamos: Dentro de estas clases tienen el mismo contenido, es decir, el contructor, los getters y los setters correspondientes de cada una de las clases. Estas clases procesan los datos procesados de la clase principal y los envía a GestorFicheros que este se encarga de crear los ficheros con los datos recogidos anteriormente de estas clases.

GestorFicheros: Esta clase, como se ha mencionado anteriormente, recoge los datos de las clases anteriores, por lo que genera los ficheros correspondientes y devuélve de forma correcta los datos insertados en el método que se haya especificado, ya sea por consola o los archivos que se haya especificado desde la clase principal.

## Clases

# Libro

La clase Libro se utiliza para gestionar la información relacionada con libros. Contiene el id del libro, el título de este, su autor, el año de publicación y el género literario al que corresponde. Se pueden hacer operaciones como crear libros, mostrar una lista con todos los datos obtenidos, actualizar los datos de los libros y eliminarlos.

# Autor

La clase Autor se utiliza para gestionar la información relacionada con autores. Contiene el id del autor, el nombre de este, su autor, su nacionalidad y su año de nacimiento. Como se ha mencionado anteriormente en Libro, se pueden hacer operaciones como crear autores, mostrar una lista con todos los datos obtenidos, actualizar los datos de los autores y eliminarlos.

# Préstamos

La clase Prestamos se utiliza para gestionar la información relacionada con los prestamos. Contiene el id del préstamo, el nombre del usuario, la fecha del préstamo y la fecha de devolución de este. Como se ha mencionado anteriormente en Libro y Autor, se pueden hacer operaciones como crear préstamos, mostrar una lista con todos los datos obtenidos, actualizar los datos de los préstamos y eliminarlos.

## Exportar datos a archivos XML

Para generar un archivo XML, es necesario primero crear los archivos binarios de Libro y Autor. Estos archivos se generan en los menús de gestión de las respectivas clases. Una vez que se hayan generado estos archivos con los datos correspondientes, en el menú principal se elige la opción de importar/exportar a XML y se selecciona la función de exportar.

## Importar datos desde archivos XML

Al importar un XML, no importa si se tienen o no los archivos binarios mencionados en el proceso de exportación, lo único necesario son los archivos XML. En el submenú de selección de exportar/importar XML, se elige la opción de importar y el programa solicitará la selección del archivo que se desea importar. La única condición es que los archivos tienen que estar en la misma carpeta donde se generaron, ya que es allí donde el programa buscará. Una vez importados, se generan automáticamente los archivos binarios con los datos que se introdujeron al exportar los archivos anteriores.