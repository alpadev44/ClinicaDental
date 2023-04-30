package com.example.ParcialBackendAlejandroPadron.Repository;

import com.example.ParcialBackendAlejandroPadron.Domain.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<Turno,Long> {
}
