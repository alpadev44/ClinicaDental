package com.example.ParcialBackendAlejandroPadron.Service;

import com.example.ParcialBackendAlejandroPadron.Domain.Odontologo;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void altaOdontologo() {
        Odontologo guardarOdontologo= new Odontologo("MDG234", "Maria","Guzman");
        Odontologo odontologoGuardado=odontologoService.altaOdontologo(guardarOdontologo);
        assertEquals(1L,odontologoGuardado.getId());
    }

    @Test
    @Order(2)
    void buscarOdontologo() {
        Long buscarId=1L;
        Optional<Odontologo> odontologoBuscado=odontologoService.buscarOdontologo(buscarId);
        assertNotNull(odontologoBuscado.get());
    }

    @Test
    @Order(3)
    void buscarOdontologos() {
        List<Odontologo> odontologos=odontologoService.buscarOdontologos();
        assertEquals(1,odontologos.size());
    }

    @Test
    @Order(4)
    void actualizarOdontologo() {
        Odontologo actualizarOdontologo2= new Odontologo("Padron", "Alejandro", "100");
        Odontologo actualizarOdontologo3= new Odontologo("Petro", "Gustavo", "166");
        odontologoService.actualizarOdontologo(actualizarOdontologo2);
        odontologoService.actualizarOdontologo(actualizarOdontologo3);
        Optional<Odontologo> odontologoActualizado=odontologoService.buscarOdontologo(1L);
        Optional<Odontologo> odontologoActualizado2=odontologoService.buscarOdontologo(1L);

        assertEquals(odontologoActualizado2.get().getNombre(),odontologoActualizado.get().getNombre());
    }

    @Test
    @Order(5)
    void eliminarOdontologo() {
        Long eliminarId=1L;
        odontologoService.eliminarOdontologo(eliminarId);
        Optional<Odontologo> odontologoEliminado=odontologoService.buscarOdontologo(eliminarId);
        assertFalse(odontologoEliminado.isPresent());
    }
}