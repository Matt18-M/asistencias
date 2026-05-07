package com.krakedev.asistencias.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.krakedev.asistencias.entidades.Estudiante;

@Service
public class ServicioEstudiantes {

	private ArrayList<Estudiante> estudiantes = new ArrayList<>();

	public Estudiante agregar(Estudiante estudiante) {

		Estudiante existente = buscarPorCedula(estudiante.getCedula());

		if (existente != null) {
			return null;
		}

		estudiantes.add(estudiante);
		return estudiante;
	}

	public Estudiante buscarPorCedula(String cedula) {

		for (Estudiante e : estudiantes) {
			if (e.getCedula().equals(cedula)) {
				return e;
			}
		}

		return null;
	}

	public boolean eliminar(String cedula) {

		Estudiante estudiante = buscarPorCedula(cedula);

		if (estudiante != null) {
			estudiantes.remove(estudiante);
			return true;
		}

		return false;
	}

	public Estudiante actualizar(String cedula, Estudiante nuevo) {

		Estudiante estudiante = buscarPorCedula(cedula);

		if (estudiante != null) {
			estudiante.setNombre(nuevo.getNombre());
			estudiante.setApellido(nuevo.getApellido());
		}

		return estudiante;
	}

	public ArrayList<Estudiante> listar() {
		return estudiantes;
	}

}