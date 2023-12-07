package Ejercicio;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.lang.model.element.Element;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Principal {

	private static Scanner scanner = new Scanner(System.in);
	// Arrays
	private static List<Autor> listaAutores = new ArrayList<>();
	private static List<Libro> listaLibros = new ArrayList<>();
	private static List<Prestamos> listaPrestamos = new ArrayList<>();

	// Auto incremento
	private static int idAutorActual = 1;
	private static int idLibroActual = 1;
	private static int idPrestamoActual = 1;
	private static GestorFicheros gestorFicheros = new GestorFicheros();

	
	// MENÚ PRINCIPAL
	private static void mostrarMenu() {
		System.out.println("Bienvenido al Sistema de Gestión de Biblioteca");
		System.out.println("1. Gestionar Libros");
		System.out.println("2. Gestionar Autores");
		System.out.println("3. Gestionar Préstamos");
		System.out.println("4. Exportar/Importar Datos (XML)");
		System.out.println("5. Salir");
		System.out.print("Seleccione una opción: ");
	}

	
	// MAIN
	public static void main(String[] args) {
		boolean salir = false;
		while (!salir) {
			mostrarMenu();
			int opcion = scanner.nextInt();
			// Menú
			switch (opcion) {
			case 1:
				// Gestionar libros
				gestionarLibros();
				break;
			case 2:
				// Gestionar autores
				gestionarAutores();
				break;
			case 3:
				// Gestionar préstamos
				gestionarPrestamos();
				break;
			case 4:
				// Exportar/Importar datos con XML
				gestionarExportImportXML();
				break;
			case 5:
				salir = true;
				break;
			default:
				System.out.println("Opción no válida. Por favor, intente de nuevo.");
			}
		}
	}

	
	// CASO 1 MENÚ PRINCIPAL (Gestor de libros)
	private static void gestionarLibros() {
		boolean salirLibros = false;

		// Menú CRUD de Libros
		while (!salirLibros) {
			System.out.println("Seleccione una opción para Libros:");
			System.out.println("1. Crear libro");
			System.out.println("2. Mostrar libros");
			System.out.println("3. Actualizar libro");
			System.out.println("4. Eliminar libro");
			System.out.println("5. Volver al Menú Principal");

			int opcion = scanner.nextInt();
			scanner.nextLine();

			// CRUD de Libros
			switch (opcion) {
			case 1:
				// Crear Libros
				// Introducción de datos
				System.out.println("Ingresa el título del libro:");
				String titulo = scanner.nextLine();

				System.out.println("Ingresa el autor del libro:");
				String autor = scanner.nextLine();

				System.out.println("Ingresa el año de publicación del libro:");
				int anioPublicacion = scanner.nextInt();

				System.out.println("Ingresa el género del libro:");
				String genero = scanner.next();

				gestorFicheros.guardarLibrosBinario(new Libro (idLibroActual, titulo, autor, anioPublicacion, genero));
				
				Libro nuevoLibro = new Libro(idLibroActual, titulo, autor, anioPublicacion, genero);
				listaLibros.add(nuevoLibro);
				idLibroActual++;

				// Comprobación
				System.out.println("El libro se ha agregado con éxito.");
				break;
			case 2:
				// Mostrar Libros
				gestorFicheros.leerLibrosBinario();
				break;
			case 3:
				// Actualizar Libros
				System.out.println("Ingrese el ID del libro a actualizar:");
				int idLibroActualizar = scanner.nextInt();
				scanner.nextLine();

				Libro libroExistenteActualizar = encontrarLibroPorId(idLibroActualizar);

				if (libroExistenteActualizar != null) {
					System.out.println("Ingrese el nuevo título del libro:");
					libroExistenteActualizar.setTitulo(scanner.nextLine());

					System.out.println("Ingrese el nuevo autor del libro:");
					libroExistenteActualizar.setAutor(scanner.nextLine());

					System.out.println("Ingrese el nuevo año de publicación del libro:");
					libroExistenteActualizar.setAnioPublicacion(scanner.nextInt());

					System.out.println("Ingrese el nuevo género del libro:");
					libroExistenteActualizar.setGenero(scanner.nextLine());

					System.out.println("Libro actualizado con éxito.");
				} else {
					System.out.println("No se encontró un libro con el ID proporcionado.");
				}
				break;
			case 4:
				// Eliminar Libros
				System.out.println("Ingrese el ID del libro a eliminar:");
				int idLibroEliminar = scanner.nextInt();
				scanner.nextLine();

				Libro libroExistenteEliminar = encontrarLibroPorId(idLibroEliminar);

				if (libroExistenteEliminar != null) {
					listaLibros.remove(libroExistenteEliminar);
					System.out.println("Libro eliminado con éxito.");
				} else {
					System.out.println("No se encontró un libro con el ID proporcionado.");
				}
				break;
			case 5:
				// Salir al menú principal
				salirLibros = true;
				break;
			default:
				System.out.println("Opción no válida. Por favor, intente de nuevo.");
			}
		}
	}

	
	// CASO 2 MENÚ PRINCIPAL (Gestor de autores)
	private static void gestionarAutores() {
		boolean salirAutores = false;

		// Menú CRUD de autores
		while (!salirAutores) {
			System.out.println("Seleccione una opción para Autores:");
			System.out.println("1. Crear autor");
			System.out.println("2. Mostrar autor");
			System.out.println("3. Actualizar autor");
			System.out.println("4. Eliminar autor");
			System.out.println("5. Volver al Menú Principal");

			int opcion = scanner.nextInt();
			scanner.nextLine();

			// CRUD de Libros
			switch (opcion) {
			case 1:
				// Crear Autores
				// Introducción de datos
				System.out.println("Ingresa el nombre del autor:");
				String nombre = scanner.nextLine();

				System.out.println("Ingresa la nacionalidad del autor:");
				String nacionalidad = scanner.nextLine();

				System.out.println("Ingresa el año de nacimiento del autor:");
				int anioNacimiento = scanner.nextInt();

				gestorFicheros.guardarAutoresBinario(new Autor(idAutorActual, nombre, nacionalidad, anioNacimiento));
				
				Autor nuevoAutor = new Autor(idAutorActual, nombre, nacionalidad, anioNacimiento);
				listaAutores.add(nuevoAutor);
				idAutorActual++;

				// Comprobación
				System.out.println("El autor se ha agregado con éxito.");
				break;
			case 2:
				// Mostrar Autores
				gestorFicheros.leerAutoresBinario();
			case 3:
				// Actualizar Autores
				System.out.println("Ingrese el ID del autor a actualizar:");
				int idAutorActualizar = scanner.nextInt();
				scanner.nextLine();

				Autor autorExistenteActualizar = encontrarAutorPorId(idAutorActualizar);

				if (autorExistenteActualizar != null) {
					System.out.println("Ingrese el nuevo nombre del autor:");
					autorExistenteActualizar.setNombre(scanner.nextLine());

					System.out.println("Ingrese la nueva nacionalidad del autor:");
					autorExistenteActualizar.setNacionalidad(scanner.nextLine());

					System.out.println("Ingrese el nuevo año de nacimiento del autor:");
					autorExistenteActualizar.setAnioNacimiento(scanner.nextInt());

					// Comprobación
					System.out.println("Autor actualizado con éxito.");
				} else {
					System.out.println("No se encontró un autor con el ID proporcionado.");
				}
				break;
			case 4:
				// Eliminar Autores
				System.out.println("Ingrese el ID del autor a eliminar:");
				int idAutorEliminar = scanner.nextInt();
				scanner.nextLine();

				Autor autorExistenteEliminar = encontrarAutorPorId(idAutorEliminar);

				if (autorExistenteEliminar != null) {
					listaAutores.remove(autorExistenteEliminar);

					// Comprobación
					System.out.println("Autor eliminado con éxito.");
				} else {
					System.out.println("No se encontró un autor con el ID proporcionado.");
				}
				break;
			case 5:
				// Salir al menú principal
				salirAutores = true;
				break;
			default:
				System.out.println("Opción no válida. Por favor, intente de nuevo.");
			}
		}

	}

	
	// CASO 3 MENÚ PRINCIPAL (Gestor de préstamos)
	private static void gestionarPrestamos() {
		boolean salirPrestamos = false;
		// Menú CRUD de préstamos
		while (!salirPrestamos) {
			System.out.println("Seleccione una opción para Préstamos:");
			System.out.println("1. Registrar préstamo");
			System.out.println("2. Mostrar préstamos");
			System.out.println("3. Actualizar préstamo");
			System.out.println("4. Eliminar préstamo");
			System.out.println("5. Volver al Menú Principal");

			int opcion = scanner.nextInt();
			scanner.nextLine();

			// CRUD de préstamos
			switch (opcion) {
			case 1:
				// Crear Prestamos
				// Introducción de datos
				System.out.println("Ingrese el id del libro:");
				String idlibro = scanner.nextLine();

				System.out.println("Ingrese el nombre del usuario:");
				String nombreUsuario = scanner.nextLine();

				System.out.println("Ingrese la fecha de préstamo (AAAA-MM-DD):");
				String fechaPrestamoStr = scanner.nextLine();
				System.out.println("Ingrese la fecha de devolución (AAAA-MM-DD):");
				String fechaDevolucionStr = scanner.nextLine();

				Prestamos nuevoPrestamo = new Prestamos(idPrestamoActual, idlibro, nombreUsuario, fechaPrestamoStr,
						fechaDevolucionStr);
				listaPrestamos.add(nuevoPrestamo);
				GestorFicheros.guardarPrestamosTxt(nuevoPrestamo);
				idPrestamoActual++;

				if (fechaPrestamoStr != null && fechaDevolucionStr != null) {
					// Comprobación
					System.out.println("Préstamo registrado con éxito.");
				} else {
					System.out.println("Error al ingresar las fechas. El préstamo no se registró.");
				}
				break;
			case 2:
				// Mostrar Préstamos
				gestorFicheros.leerPrestamos();
				break;
			case 3:
				// Actualizar Préstamos
				System.out.println("Ingrese el ID del préstamo a actualizar:");
				int idPrestamoActualizar = scanner.nextInt();
				scanner.nextLine();

				Prestamos prestamoExistenteActualizar = encontrarPrestamosPorId(idPrestamoActualizar);

				if (prestamoExistenteActualizar != null) {
					System.out.println("Ingrese el nuevo nombre del libro:");
					prestamoExistenteActualizar.setLibro(scanner.nextLine());

					System.out.println("Ingrese el nuevo nombre del usuario:");
					prestamoExistenteActualizar.setNombreUsuario(scanner.nextLine());

					System.out.println("Ingrese la nueva fecha de préstamo (AAAA-MM-DD):");
					prestamoExistenteActualizar.setFechaPrestamo(scanner.nextLine());

					System.out.println("Ingrese la nueva fecha de devolución (AAAA-MM-DD):");
					prestamoExistenteActualizar.setFechaDevolucion(scanner.nextLine());

					// Comprobación
					System.out.println("Préstamo actualizado con éxito.");

				} else {
					System.out.println("No se encontró un préstamo con el ID proporcionado.");
				}
				break;
			case 4:
				// Eliminar Préstamos
				System.out.println("Ingrese el ID del préstamo a eliminar:");
				int idPrestamoEliminar = scanner.nextInt();
				scanner.nextLine();

				Prestamos prestamoExistenteEliminar = encontrarPrestamosPorId(idPrestamoEliminar);

				if (prestamoExistenteEliminar != null) {
					listaPrestamos.remove(prestamoExistenteEliminar);
					System.out.println("Préstamo eliminado con éxito.");
				} else {
					System.out.println("No se encontró un préstamo con el ID proporcionado.");
				}
				break;
			case 5:
				// Salir al menú principal
				salirPrestamos = true;
				break;
			default:
				System.out.println("Opción no válida. Por favor, intente de nuevo.");
			}
		}
	}

	
	// CASO 4 MENÚ PRINCIPAL (Gestor importación y exportación de XML)
	private static void gestionarExportImportXML() {
		//Menú
		System.out.println("Seleccione una opción para Exportar/Importar datos con XML:");
		System.out.println("1. Exportar datos a XML");
		System.out.println("2. Importar datos desde XML");

		int opcion = scanner.nextInt();
		scanner.nextLine();

		switch (opcion) {
		case 1:
			gestorFicheros.exportarDatosXML();
			break;
		case 2:
			gestorFicheros.importarDatosXML();
			break;
		default:
			System.out.println("Opción no válida. Por favor, intente de nuevo.");
		}
	}

	
	// MÉTODOS
	// Método dentro de gestionarLibros
	private static Libro encontrarLibroPorId(int idLibro) {
		for (Libro libro : listaLibros) {
			System.out.println("ID del libro actual: " + libro.getId());
			if (libro.getId() == idLibro) {
				return libro;
			}
		}
		System.out.println("No se encontró un libro con el ID: " + idLibro);
		return null;
	}

	// Método dentro de gestionarAutores
	private static Autor encontrarAutorPorId(int idAutor) {
		for (Autor autor : listaAutores) {
			System.out.println("ID del autor actual: " + autor.getId());
			if (autor.getId() == idAutor) {
				return autor;
			}
		}
		System.out.println("No se encontró un autor con el ID: " + idAutor);
		return null;
	}

	// Método dentro de gestionarPrestamos
	private static Prestamos encontrarPrestamosPorId(int idPrestamos) {
		for (Prestamos prestamos : listaPrestamos) {
			System.out.println("ID del prestamo actual: " + prestamos.getId());
			if (prestamos.getId() == idPrestamos) {
				return prestamos;
			}
		}
		System.out.println("No se encontró un prestamo con el ID: " + idPrestamos);
		return null;
	}

}
