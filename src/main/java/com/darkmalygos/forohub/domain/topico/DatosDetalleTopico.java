package com.darkmalygos.forohub.domain.topico;

import com.darkmalygos.forohub.domain.curso.Curso;
import com.darkmalygos.forohub.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Boolean status,
        String Usuario,
        String curso
) {
    public DatosDetalleTopico(Topico topico){
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getUsuario().getNombre(),
                topico.getCurso().getNombre()
        );
    }
}
