package com.example.ParcialBackendAlejandroPadron.Controller;


import com.example.ParcialBackendAlejandroPadron.Domain.Odontologo;
import com.example.ParcialBackendAlejandroPadron.Service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping
    public ResponseEntity<Odontologo> agregarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.altaOdontologo(odontologo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologo(@PathVariable Long id){
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(id);
        if (odontologoBuscado.isPresent()){
            return ResponseEntity.ok(odontologoBuscado.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public Odontologo actualizarOdontologo(@RequestBody Odontologo odontologo){
        return odontologoService.actualizarOdontologo(odontologo);
    }

    @DeleteMapping("/{id}")
    public void eliminarOdontologo(@PathVariable Long id){
        odontologoService.eliminarOdontologo(id);
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarTodosOdontologos(){
        return ResponseEntity.ok(odontologoService.buscarOdontologos());
    }

}
