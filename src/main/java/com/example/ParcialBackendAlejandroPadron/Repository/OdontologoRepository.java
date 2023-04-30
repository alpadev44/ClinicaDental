package com.example.ParcialBackendAlejandroPadron.Repository;


import com.example.ParcialBackendAlejandroPadron.Domain.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo,Long> {
}
