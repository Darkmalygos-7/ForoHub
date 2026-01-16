package com.darkmalygos.forohub.domain.usuario;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "usuarios")
@Entity(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String contrasena;


    public Usuario(@Valid DatosRegistroUsuario datos) {
        this.id = null;
        this.nombre = datos.nombre();
        this.email = datos.email();
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
