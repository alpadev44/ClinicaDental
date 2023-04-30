package com.example.ParcialBackendAlejandroPadron.Controller;


import com.example.ParcialBackendAlejandroPadron.Domain.Paciente;
import com.example.ParcialBackendAlejandroPadron.Exceptions.ResourceNotFoundException;
import com.example.ParcialBackendAlejandroPadron.Service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public Paciente registrarPaciente(@RequestBody Paciente paciente){
        return pacienteService.guardarPaciente(paciente);
    }

    @PutMapping
    public Paciente actualizarPaciente(@RequestBody Paciente paciente){
        return pacienteService.actualizarPaciente(paciente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPacienteXId(@PathVariable Long id){
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(id);
        if (pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok("Eliminación del paciente con id= "+id+ "con éxito");
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> buscarTodosPacientes(){
        return ResponseEntity.ok(pacienteService.buscarTodosPacientes());
    }

    @GetMapping("/buscar/email/{email}")
    public ResponseEntity<Paciente> buscarPacientePorEmail(@PathVariable String email){
        Optional<Paciente> pacienteBuscado= pacienteService.buscarXEmail(email);
        if (pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

}
