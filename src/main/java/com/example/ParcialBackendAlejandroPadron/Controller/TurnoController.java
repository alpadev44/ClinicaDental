package com.example.ParcialBackendAlejandroPadron.Controller;


import com.example.ParcialBackendAlejandroPadron.DTO.TurnoDTO;
import com.example.ParcialBackendAlejandroPadron.Exceptions.BadRequestException;
import com.example.ParcialBackendAlejandroPadron.Exceptions.ResourceNotFoundException;
import com.example.ParcialBackendAlejandroPadron.Service.OdontologoService;
import com.example.ParcialBackendAlejandroPadron.Service.PacienteService;
import com.example.ParcialBackendAlejandroPadron.Service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private TurnoService turnoService;
    private OdontologoService odontologoService;
    private PacienteService pacienteService;

    @Autowired
    public TurnoController(TurnoService turnoService, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoService = turnoService;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<TurnoDTO> registrarTurno(@RequestBody TurnoDTO turno) throws BadRequestException {
        return ResponseEntity.ok(turnoService.guardarTurno(turno));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarTurno(@PathVariable Long id){
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurno(id);

        if (turnoBuscado.isPresent()){
            return ResponseEntity.ok(turnoBuscado.get());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping
    public ResponseEntity<TurnoDTO> actualizarTurno(@RequestBody TurnoDTO turno) throws BadRequestException{
        ResponseEntity<TurnoDTO> respuesta;

        if(pacienteService.buscarPaciente(turno.getPaciente_id())!=null &&
                odontologoService.buscarOdontologo(turno.getOdontologo_id())!=null){
            respuesta = ResponseEntity.ok(turnoService.actualizarTurno(turno));
        }else{
            respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return respuesta;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        turnoService.eliminarTurno(id);
        return ResponseEntity.ok("Turno eliminado con id="+id);
    }

    @GetMapping
    public ResponseEntity<List<TurnoDTO>> buscarTodosTurnos(){
        return ResponseEntity.ok(turnoService.buscarTodosTurnos());
    }

}
