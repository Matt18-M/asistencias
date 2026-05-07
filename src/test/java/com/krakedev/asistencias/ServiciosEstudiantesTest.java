package com.krakedev.asistencias;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.krakedev.asistencias.entidades.Estudiante;
import com.krakedev.asistencias.services.ServicioEstudiantes;

public class ServiciosEstudiantesTest {

	private ServicioEstudiantes servicio;

	@BeforeEach
	public void inicializar() {
		servicio = new ServicioEstudiantes();
	}

	@Test
	public void testAgregarEstudiante() {

		Estudiante estudiante = new Estudiante();
		estudiante.setCedula("123");
		estudiante.setNombre("Juan");
		estudiante.setApellido("Perez");

		Estudiante resultado = servicio.agregar(estudiante);

		assertNotNull(resultado);
		assertEquals("Juan", resultado.getNombre());
	}

	@Test
	public void testNoAgregarDuplicado() {

		Estudiante estudiante1 = new Estudiante();
		estudiante1.setCedula("123");
		estudiante1.setNombre("Juan");
		estudiante1.setApellido("Perez");

		Estudiante estudiante2 = new Estudiante();
		estudiante2.setCedula("123");
		estudiante2.setNombre("Maria");
		estudiante2.setApellido("Lopez");

		servicio.agregar(estudiante1);

		Estudiante resultado = servicio.agregar(estudiante2);

		assertNull(resultado);
	}

	@Test
	public void testBuscarPorCedula() {

		Estudiante estudiante = new Estudiante();
		estudiante.setCedula("456");
		estudiante.setNombre("Carlos");
		estudiante.setApellido("Ruiz");

		servicio.agregar(estudiante);

		Estudiante encontrado = servicio.buscarPorCedula("456");

		assertNotNull(encontrado);
		assertEquals("Carlos", encontrado.getNombre());
	}

	@Test
	public void testBuscarNoExistente() {

		Estudiante encontrado = servicio.buscarPorCedula("999");

		assertNull(encontrado);
	}

	@Test
	public void testActualizarEstudiante() {

		Estudiante estudiante = new Estudiante();
		estudiante.setCedula("789");
		estudiante.setNombre("Pedro");
		estudiante.setApellido("Mena");

		servicio.agregar(estudiante);

		Estudiante actualizado = new Estudiante();
		actualizado.setNombre("Pedro Actualizado");
		actualizado.setApellido("Mena Actualizado");

		Estudiante resultado = servicio.actualizar("789", actualizado);

		assertNotNull(resultado);
		assertEquals("Pedro Actualizado", resultado.getNombre());
		assertEquals("Mena Actualizado", resultado.getApellido());
	}

	@Test
	public void testEliminarEstudiante() {

		Estudiante estudiante = new Estudiante();
		estudiante.setCedula("111");
		estudiante.setNombre("Ana");
		estudiante.setApellido("Torres");

		servicio.agregar(estudiante);

		boolean eliminado = servicio.eliminar("111");

		assertTrue(eliminado);
		assertNull(servicio.buscarPorCedula("111"));
	}

	@Test
	public void testEliminarNoExistente() {

		boolean eliminado = servicio.eliminar("000");

		assertFalse(eliminado);
	}

}