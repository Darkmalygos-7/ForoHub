package com.darkmalygos.forohub.controller;

import com.darkmalygos.forohub.domain.usuario.DatosAutenticacion;
import com.darkmalygos.forohub.domain.usuario.Usuario;
import com.darkmalygos.forohub.domain.usuario.UsuarioService;
import com.darkmalygos.forohub.infra.security.DatosTokenJWT;
import com.darkmalygos.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    private final UsuarioService usuarioService;
    public  AuthenticationController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }
    @PostMapping
    public ResponseEntity iniciarSesion(@Valid @RequestBody DatosAutenticacion datos){
        return ResponseEntity.ok(usuarioService.iniciarSesion(datos));
    }
}
