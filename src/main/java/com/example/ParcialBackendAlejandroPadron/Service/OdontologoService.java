package com.example.ParcialBackendAlejandroPadron.Service;


import com.example.ParcialBackendAlejandroPadron.Domain.Odontologo;
import com.example.ParcialBackendAlejandroPadron.Repository.OdontologoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    private static final Logger LOGGER = Logger.getLogger(OdontologoService.class);

    private OdontologoRepository odontologoRepository;
    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    public Odontologo altaOdontologo(Odontologo odontologo){
        LOGGER.info("Se registro el odontologo de nombre: " + odontologo.getNombre());
        return odontologoRepository.save(odontologo);
    }

    public Optional<Odontologo> buscarOdontologo(Long id){
        LOGGER.info("Se busco odontologo con ID: " + id);
        return odontologoRepository.findById(id);
    }
    public List<Odontologo> buscarOdontologos(){
        LOGGER.info("Se listaron todos los odontologos");
        return odontologoRepository.findAll();
    }

    public Odontologo actualizarOdontologo(Odontologo odontologo){
        LOGGER.info("Se actualizo el odontologo con ID: " + odontologo.getId());
        return odontologoRepository.save(odontologo);
    }

    public void eliminarOdontologo(Long id){
        LOGGER.warn("Se elimino el odontologo con ID: " + id);
        odontologoRepository.deleteById(id);
    }


}
