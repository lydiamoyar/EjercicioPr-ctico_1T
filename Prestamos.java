package Ejercicio;

import java.io.Serializable;
import java.sql.Date;

public class Prestamos implements Serializable {

	private int id;
	private String libro;
	private String nombreUsuario;
	private String fechaPrestamo;
	private String fechaDevolucion;

	// CONSTRUCTOR
	public Prestamos(int id, String libro, String nombreUsuario, String fechaPrestamo, String fechaDevolucion) {

		this.id = id;
		this.libro = libro;
		this.nombreUsuario = nombreUsuario;
		this.fechaPrestamo = fechaPrestamo;
		this.fechaDevolucion = fechaDevolucion;
	}

	// GETTERS Y SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibro() {
		return libro;
	}

	public void setLibro(String libro) {
		this.libro = libro;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(String fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public String getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(String fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

}
