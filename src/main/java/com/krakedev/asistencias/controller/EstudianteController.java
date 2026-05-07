package com.krakedev.asistencias.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakedev.asistencias.entidades.Estudiante;
import com.krakedev.asistencias.services.ServicioEstudiantes;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

	private final ServicioEstudiantes servicioEstudiantes;

	public EstudianteController(ServicioEstudiantes servicioEstudiantes) {
		this.servicioEstudiantes = servicioEstudiantes;
	}

	@PostMapping
	public Estudiante agregar(@RequestBody Estudiante estudiante) {
		return servicioEstudiantes.agregar(estudiante);
	}

	@GetMapping
	public ArrayList<Estudiante> listar() {
		return servicioEstudiantes.listar();
	}

	@GetMapping("/{cedula}")
	public Estudiante buscar(@PathVariable String cedula) {
		return servicioEstudiantes.buscarPorCedula(cedula);
	}

	@PutMapping("/{cedula}")
	public Estudiante actualizar(@PathVariable String cedula, @RequestBody Estudiante nuevo) {

		return servicioEstudiantes.actualizar(cedula, nuevo);
	}

	@DeleteMapping("/{cedula}")
	public boolean eliminar(@PathVariable String cedula) {
		return servicioEstudiantes.eliminar(cedula);
	}

}