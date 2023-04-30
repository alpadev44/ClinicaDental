package com.example.ParcialBackendAlejandroPadron.Security;


import com.example.ParcialBackendAlejandroPadron.Domain.Usuario;
import com.example.ParcialBackendAlejandroPadron.Domain.UsuarioRol;
import com.example.ParcialBackendAlejandroPadron.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CargadoraDeDatos implements ApplicationRunner {
    private UsuarioRepository usuarioRepository;
    @Autowired
    public CargadoraDeDatos(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        BCryptPasswordEncoder cifrador= new BCryptPasswordEncoder();


        String passACifrar="123";
        String passCifrada=cifrador.encode(passACifrar);
        Usuario usuarioAInsertar= new Usuario("Alejandro","Padron",
                "ale@mailto.com",passCifrada, UsuarioRol.ROLE_USER);
        usuarioRepository.save(usuarioAInsertar);


        String passCifrada2=cifrador.encode("1239");
        Usuario usuarioAInsertar2= new Usuario("Juanito","Perez",
                "admin@mailto.com",passCifrada2, UsuarioRol.ROLE_ADMIN);
        usuarioRepository.save(usuarioAInsertar2);

    }
}
