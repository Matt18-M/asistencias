package com.krakedev.asistencias;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.krakedev.asistencias.entidades.Asistencia;
import com.krakedev.asistencias.entidades.Estudiante;
import com.krakedev.asistencias.entidades.RegistroAsistencia;
import com.krakedev.asistencias.services.ServicioAsistencia;
import com.krakedev.asistencias.services.ServicioEstudiantes;

public class ServiciosAsistenciaTest {

	private ServicioEstudiantes servicioEstudiantes;
	private ServicioAsistencia servicioAsistencia;

	@BeforeEach
	public void inicializar() {

		servicioEstudiantes = new ServicioEstudiantes();

		servicioAsistencia = new ServicioAsistencia(servicioEstudiantes);
	}

	@Test
	public void testRegistrarAsistencia() {

		Estudiante estudiante = new Estudiante();
		estudiante.setCedula("123");
		estudiante.setNombre("Juan");
		estudiante.setApellido("Perez");

		servicioEstudiantes.agregar(estudiante);

		RegistroAsistencia registro = servicioAsistencia.registrarAsistencia("123");

		assertNotNull(registro);
		assertEquals("Juan", registro.getEstudiante().getNombre());

		assertEquals("P", registro.getAsistencia().getEstado());
	}

	@Test
	public void testRegistrarAsistenciaNoExistente() {

		RegistroAsistencia registro = servicioAsistencia.registrarAsistencia("999");

		assertNull(registro);
	}

	@Test
	public void testConsultarAsistencia() {

		Estudiante estudiante = new Estudiante();
		estudiante.setCedula("456");
		estudiante.setNombre("Maria");
		estudiante.setApellido("Lopez");

		servicioEstudiantes.agregar(estudiante);

		servicioAsistencia.registrarAsistencia("456");
		servicioAsistencia.registrarAsistencia("456");

		ArrayList<Asistencia> asistencias = servicioAsistencia.consultarAsistencia("456");

		assertEquals(2, asistencias.size());
	}

	@Test
	public void testConsultarAsistenciaSinRegistros() {

		ArrayList<Asistencia> asistencias = servicioAsistencia.consultarAsistencia("000");

		assertEquals(0, asistencias.size());
	}

}