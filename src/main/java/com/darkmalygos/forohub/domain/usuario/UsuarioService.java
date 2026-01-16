package com.darkmalygos.forohub.domain.usuario;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    public  UsuarioService(PasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository){
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario registrar(DatosRegistroUsuario datos){
        Usuario usuario = new Usuario(datos);
        usuario.setContrasena(passwordEncoder.encode(datos.contrasena()));
        return usuarioRepository.save(usuario);
    }
}
