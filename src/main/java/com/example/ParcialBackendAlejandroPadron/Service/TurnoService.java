package com.example.ParcialBackendAlejandroPadron.Service;


import com.example.ParcialBackendAlejandroPadron.DTO.TurnoDTO;
import com.example.ParcialBackendAlejandroPadron.Domain.Odontologo;
import com.example.ParcialBackendAlejandroPadron.Domain.Paciente;
import com.example.ParcialBackendAlejandroPadron.Domain.Turno;
import com.example.ParcialBackendAlejandroPadron.Exceptions.BadRequestException;
import com.example.ParcialBackendAlejandroPadron.Exceptions.ResourceNotFoundException;
import com.example.ParcialBackendAlejandroPadron.Repository.OdontologoRepository;
import com.example.ParcialBackendAlejandroPadron.Repository.PacienteRespository;
import com.example.ParcialBackendAlejandroPadron.Repository.TurnoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    private static final Logger LOGGER = Logger.getLogger(TurnoService.class);
    private PacienteRespository pacienteRespository;
    private OdontologoRepository odontologoRepository;
    private TurnoRepository turnoRepository;
    @Autowired
    public TurnoService(PacienteRespository pacienteRespository, OdontologoRepository odontologoRepository, TurnoRepository turnoRepository) {
        this.pacienteRespository = pacienteRespository;
        this.odontologoRepository = odontologoRepository;
        this.turnoRepository = turnoRepository;
    }


    public TurnoDTO guardarTurno(TurnoDTO turno) throws BadRequestException {
        Optional<Paciente> pacienteBuscado = pacienteRespository.findById(turno.getPaciente_id());
        Optional<Odontologo> odontologoBuscado = odontologoRepository.findById(turno.getOdontologo_id());
        if(pacienteBuscado.isPresent() && odontologoBuscado.isPresent()){
            LOGGER.info("Se registro el turnoDTO con ID: " + turno.getId());
            return convertirTurnoaTurnoDTO(turnoRepository.save(convertirTurnoDTOaTurno(turno)));
        }
        else {
            throw new BadRequestException("Error. No se puede registrar el turno, verifique los id de Odontologo y Paciente.");
        }
    }

    public Optional<TurnoDTO> buscarTurno(Long id){
        LOGGER.info("Comenzo la búsqueda del TurnoDTO con ID: " + id);
        Optional<Turno> turnoBuscado = turnoRepository.findById(id);
        if (turnoBuscado.isPresent()){
            LOGGER.info("Se encontro el TurnoDTO con ID: " + id);
            return Optional.of(convertirTurnoaTurnoDTO(turnoBuscado.get()));
        }
        else {
            LOGGER.info("El turnoDTO no existe");
            return Optional.empty();
        }
    }

    public TurnoDTO actualizarTurno(TurnoDTO turno) throws BadRequestException{
        LOGGER.info("Comenzo la actualizacion del TurnoDTO con ID: " + turno.getId());
        Optional<Paciente> pacienteBuscado = pacienteRespository.findById(turno.getPaciente_id());
        Optional<Odontologo> odontologoBuscado = odontologoRepository.findById(turno.getOdontologo_id());
        if(pacienteBuscado.isPresent() && odontologoBuscado.isPresent()){
            LOGGER.info("Se actualizo el TurnoDTO con ID: " + turno.getId());
            return convertirTurnoaTurnoDTO(turnoRepository.save(convertirTurnoDTOaTurno(turno)));
        }
        else {
            throw new BadRequestException("Error. No se puede actualizar el turno, verifique los id de Odontologo y Paciente.");
        }
    }


    public List<TurnoDTO> buscarTodosTurnos(){
        List<Turno> listaTurnos = turnoRepository.findAll();
        List<TurnoDTO> listaTurnosDTO = new ArrayList<>();
        for (Turno listaTurno : listaTurnos) {
            listaTurnosDTO.add(convertirTurnoaTurnoDTO(listaTurno));
        }
        LOGGER.info("Se listaron todos los turnosDTO");
        return listaTurnosDTO;
    }

    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        Optional<Turno> turnoBuscado = turnoRepository.findById(id);
        if (turnoBuscado.isPresent()){
            LOGGER.warn("Se elimino turnoDTO con ID: "+ id);
            turnoRepository.deleteById(id);
        }else{
            throw new ResourceNotFoundException("Error. No existe el turno con id= "+id);
        }

    }

    private Turno convertirTurnoDTOaTurno(TurnoDTO turnoDTO){
        LOGGER.info("Se convierte de turno a un turnoDTO");
        Turno turno = new Turno();
        Paciente paciente = new Paciente();
        Odontologo odontologo = new Odontologo();

        LOGGER.info("Se carga la informacion de turno al turnoDTO");
        turno.setId(turnoDTO.getId());
        turno.setFecha(turnoDTO.getFecha());
        paciente.setId(turnoDTO.getPaciente_id());
        paciente.setNombre(turnoDTO.getNombre_paciente());
        odontologo.setId(turnoDTO.getOdontologo_id());
        odontologo.setNombre(turnoDTO.getNombre_odontologo());

        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        LOGGER.info("Salida de turnoDTO a turno");
        return turno;
    }

    private TurnoDTO convertirTurnoaTurnoDTO(Turno turno){
        TurnoDTO turnoDTO = new TurnoDTO();
        LOGGER.info("Se convierte de turnoDTO a un turno");
        turnoDTO.setId(turno.getId());
        turnoDTO.setOdontologo_id(turno.getOdontologo().getId());
        turnoDTO.setPaciente_id(turno.getPaciente().getId());
        turnoDTO.setFecha(turno.getFecha());
        turnoDTO.setNombre_odontologo(turno.getOdontologo().getNombre());
        turnoDTO.setNombre_paciente(turno.getPaciente().getNombre());
        LOGGER.info("Salida de turno a turnoDTO");
        return turnoDTO;
    }
}
