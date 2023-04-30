package com.example.ParcialBackendAlejandroPadron.Service;

import com.example.ParcialBackendAlejandroPadron.Domain.Paciente;
import com.example.ParcialBackendAlejandroPadron.Exceptions.ResourceNotFoundException;
import com.example.ParcialBackendAlejandroPadron.Repository.PacienteRespository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private static final Logger LOGGER = Logger.getLogger(PacienteService.class);
    private PacienteRespository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRespository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }


    public Paciente guardarPaciente(Paciente paciente){
        LOGGER.info("Se registro el paciente de nombre: " + paciente.getNombre());
        return pacienteRepository.save(paciente);
    }
    public List<Paciente> buscarTodosPacientes(){
        LOGGER.info("Se listaron todos los pacientes");
        return pacienteRepository.findAll();
    }
    public Optional<Paciente> buscarXEmail(String email){
        LOGGER.info("Se busco paciente con Email: " + email);
        return pacienteRepository.findByEmail(email);
    }

    public Paciente actualizarPaciente(Paciente paciente){
        LOGGER.info("Se actualizo el paciente con ID: " + paciente.getId());
        return pacienteRepository.save(paciente);
    }
    public Optional<Paciente> buscarPaciente(Long id){
        LOGGER.info("Se busco paciente con ID: " + id);
        return pacienteRepository.findById(id);
    }

    public void eliminarPaciente(Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado= pacienteRepository.findById(id);
        if (pacienteBuscado.isPresent()){
            pacienteRepository.deleteById(id);
            LOGGER.warn("Se elimino el paciente con ID: " + id);
        }
        else{
            throw new ResourceNotFoundException("Error. No existe el paciente con id= "+id);
        }
    }
}
