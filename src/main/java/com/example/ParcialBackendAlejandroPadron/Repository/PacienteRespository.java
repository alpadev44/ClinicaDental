package com.example.ParcialBackendAlejandroPadron.Repository;

import com.example.ParcialBackendAlejandroPadron.Domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRespository extends JpaRepository<Paciente,Long> {
    Optional<Paciente> findByEmail(String email);
}
