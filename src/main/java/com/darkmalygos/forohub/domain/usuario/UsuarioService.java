package com.darkmalygos.forohub.domain.usuario;

import com.darkmalygos.forohub.infra.security.DatosTokenJWT;
import com.darkmalygos.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    public  UsuarioService(PasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository, AuthenticationManager authenticationManager, TokenService tokenService){
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public Usuario registrar(DatosRegistroUsuario datos){
        Usuario usuario = new Usuario(datos);
        usuario.setContrasena(passwordEncoder.encode(datos.contrasena()));
        return usuarioRepository.save(usuario);
    }

    public DatosTokenJWT iniciarSesion(DatosAutenticacion datos) {
        var authToken = new UsernamePasswordAuthenticationToken(
                datos.email(),
                datos.contrasena()
        );
        var autentificacion = authenticationManager.authenticate(authToken);
        Usuario usuario = (Usuario) autentificacion.getPrincipal();
        String token = tokenService.generarToken(usuario);

        return new DatosTokenJWT(token);
    }
}
