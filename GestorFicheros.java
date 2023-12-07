package Ejercicio;

import java.io.*;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GestorFicheros {

	// Arrays
	static ArrayList<Libro> libros = new ArrayList<>();
	static ArrayList<Autor> autor = new ArrayList<>();
	static ArrayList<Prestamos> prestamos = new ArrayList<>();

	// MÉTODOS
	// Método para guardar libros en un archivo binario
	public void guardarLibrosBinario(Libro nuevoLibro) {

		libros.add(nuevoLibro);

		try {
			FileOutputStream oos = new FileOutputStream(
					"C:\\Users\\Usuario\\eclipse-workspace\\EjercicioPractico\\libros.bin");
			ObjectOutputStream os = new ObjectOutputStream(oos);

			os.writeObject(libros);
			os.close();

			System.out.println("Se ha creado el libro");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return;
	}

	// Método para leer libros en un archivo binario
	public void leerLibrosBinario() {
		try {

			// Cargar libros existentes si el archivo ya tiene datos
			ObjectInputStream obIn = new ObjectInputStream(
					// Ruta del archivo
					new FileInputStream("C:\\Users\\Usuario\\eclipse-workspace\\EjercicioPractico\\libros.bin"));

			libros = (ArrayList<Libro>) obIn.readObject();

			obIn.close();

			for (Libro libro : libros) {
				System.out.println("El id del libro es " + libro.getId() + " ,su título es " + libro.getTitulo() + "," + "\n"
						+ "el autor es " + libro.getAutor() + " , su año de publicacion es "
						+ libro.getAnioPublicacion() + " , y su género es " + libro.getGenero());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Método para guardar libros en un archivo binario
	public void guardarAutoresBinario(Autor nuevoAutor) {

		autor.add(nuevoAutor);
		try {
			// Método para guardar s en un archivo binario
			FileOutputStream oos = new FileOutputStream(
					"C:\\Users\\Usuario\\eclipse-workspace\\EjercicioPractico\\autor.bin");
			ObjectOutputStream os = new ObjectOutputStream(oos);

			os.writeObject(autor);
			os.close();

			System.out.println("Se ha creado el autor");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return;
	}

	// Método para leer autores en un archivo binario
	public void leerAutoresBinario() {

		try {

			// Cargar libros existentes si el archivo ya tiene datos
			ObjectInputStream obIn = new ObjectInputStream(
					new FileInputStream("C:\\Users\\Usuario\\eclipse-workspace\\EjercicioPractico\\autor.bin"));

			autor = (ArrayList<Autor>) obIn.readObject();
			obIn.close();

			for (Autor autor : autor) {
				System.out.println("El id del autor " + autor.getId() + " ,su nombre es " + autor.getNombre() + "," + "\n" + 
						"su nacionalidad es " + autor.getNacionalidad() + " y su año de nacimiento es "
						+ autor.getAnioNacimiento());
			}

			obIn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Método para leer prestamos
	public void leerPrestamos() {
		System.out.println("Lista préstamos " + prestamos.size());
		for (Prestamos prestamo : prestamos) {
			System.out.println("El id del prestamo es " + prestamo.getId() + ", el libro es " + prestamo.getLibro() + "," 
					+ "\n" + ", la fecha del prestamo es "
					+ prestamo.getFechaPrestamo() + " y la fecha de devolución es " + prestamo.getFechaDevolucion());
		}
	}

	// Método para guardar libros en un archivo binario
	public static void guardarPrestamosTxt(Prestamos nuevoPrestamo) {

		try (BufferedWriter oos = new BufferedWriter(
				new FileWriter("C:\\Users\\Usuario\\eclipse-workspace\\EjercicioPractico\\prestamo.txt"))) {

			oos.write("El id del préstamo es " + nuevoPrestamo.getId() + ".");
			oos.newLine();
			oos.write("Contiene el libro " + nuevoPrestamo.getLibro() + ".");
			oos.newLine();
			oos.write("El nombre del usuario es " + nuevoPrestamo.getNombreUsuario() + ".");
			oos.newLine();
			oos.write("La fecha del prestamo es " + nuevoPrestamo.getFechaPrestamo() + ".");
			oos.newLine();
			oos.write("Su fecha de devolución es " + nuevoPrestamo.getFechaDevolucion() + ".");
			oos.newLine();
			prestamos.add(nuevoPrestamo);

			System.out.println("Se ha creado el prestamo");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return;
	}

	// Método para exportar los datos al XML
	public void exportarDatosXML() {
		try {
			// Crear un nuevo documento XML
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();

			// Crear el elemento raíz
			Element rootElement = document.createElement("biblioteca");
			document.appendChild(rootElement);

			// Exportar libros
			for (Libro libro : libros) {
				Element elementoLibro = document.createElement("libro");
				elementoLibro.setAttribute("id", String.valueOf(libro.getId()));

				Element elementoTitulo = document.createElement("titulo");
				elementoTitulo.appendChild(document.createTextNode(libro.getTitulo()));
				elementoLibro.appendChild(elementoTitulo);

				Element elementoAutor = document.createElement("autor");
				elementoAutor.appendChild(document.createTextNode(libro.getAutor()));
				elementoLibro.appendChild(elementoAutor);

				Element elementoAnioPublicacion = document.createElement("anio_publicacion");
				elementoAnioPublicacion
						.appendChild(document.createTextNode(String.valueOf(libro.getAnioPublicacion())));
				elementoLibro.appendChild(elementoAnioPublicacion);

				Element elementoGenero = document.createElement("genero");
				elementoGenero.appendChild(document.createTextNode(libro.getGenero()));
				elementoLibro.appendChild(elementoGenero);

				rootElement.appendChild(elementoLibro);
			}

			for (Autor autor : autor) {
				Element elementoautor = document.createElement("autor");
				elementoautor.setAttribute("id", String.valueOf(autor.getId()));

				Element elementoNombre = document.createElement("nombre");
				elementoNombre.appendChild(document.createTextNode(autor.getNombre()));
				elementoautor.appendChild(elementoNombre);

				Element elementoAnioNacimiento = document.createElement("anio_nacimiento");
				elementoAnioNacimiento.appendChild(document.createTextNode(String.valueOf(autor.getAnioNacimiento())));
				elementoautor.appendChild(elementoAnioNacimiento);

				Element elementoNacionalidad = document.createElement("nacionalidad");
				elementoNacionalidad.appendChild(document.createTextNode(autor.getNacionalidad()));
				elementoautor.appendChild(elementoNacionalidad);

				rootElement.appendChild(elementoautor);
			}

			// Transformar el documento a XML y escribirlo en un archivo
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // Indentar el XML

			DOMSource source = new DOMSource(document);

			// Reemplazar "C:/java/catalogo_libros.xml" con la ruta y nombre de tu archivo
			// de salida
			StreamResult result = new StreamResult(
					"C:\\Users\\Usuario\\eclipse-workspace\\EjercicioPractico\\catalogo_libros.xml");
			transformer.transform(source, result);

			System.out.println("Datos escritos en el archivo XML correctamente.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Método para importar los datos al XML
	public void importarDatosXML() {
		try {
			// Ruta del archivo
			File file = new File("C:\\Users\\Usuario\\eclipse-workspace\\EjercicioPractico\\catalogo_libros.xml");

			// Crear un nuevo documento XML
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(file);

			// Obtener listas separadas de nodos para los libros y los autores
			NodeList nodeListLibros = document.getElementsByTagName("libro");
			NodeList nodeListAutores = document.getElementsByTagName("autor");

			// Archivo Libros
			try (ObjectOutputStream ossLibros = new ObjectOutputStream(
					new FileOutputStream("C:\\Users\\Usuario\\eclipse-workspace\\EjercicioPractico\\libros.bin"));) {

				Libro libro1 = new Libro(0, null, null, 0, null);

				for (int i = 0; i < nodeListLibros.getLength(); i++) {

					Node node = nodeListLibros.item(i);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;
						// Obtener los elementos del libro
						libro1.setId(Integer.parseInt(element.getAttribute("id")));
						libro1.setTitulo(element.getElementsByTagName("titulo").item(0).getTextContent());
						libro1.setAutor(element.getElementsByTagName("autor").item(0).getTextContent());
						libro1.setAnioPublicacion(Integer
								.parseInt(element.getElementsByTagName("anio_publicacion").item(0).getTextContent()));
						libro1.setGenero(element.getElementsByTagName("genero").item(0).getTextContent());

						libros.add(libro1);
						ossLibros.writeObject(libros);

					}

				}

			} catch (IOException e) {
				e.printStackTrace();
			}
			// Archivo Autor
			try (ObjectOutputStream ossLibros = new ObjectOutputStream(
					new FileOutputStream("C:\\Users\\Usuario\\eclipse-workspace\\EjercicioPractico\\autor.bin"));) {

				Autor autor1 = new Autor(0, null, null, 0);

				for (int i = 0; i < nodeListAutores.getLength(); i++) {

					Node node = nodeListAutores.item(i);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;
						// Obtener los elementos del libro
						autor1.setId(Integer.parseInt(element.getAttribute("id")));
						autor1.setNombre(element.getElementsByTagName("nombre").item(0).getTextContent());
						autor1.setNacionalidad(element.getElementsByTagName("nacionalidad").item(0).getTextContent());
						autor1.setAnioNacimiento(Integer
								.parseInt(element.getElementsByTagName("anio_nacimiento").item(0).getTextContent()));

						autor.add(autor1);
						ossLibros.writeObject(autor);

					}

				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}