package com.darkmalygos.forohub.domain.topico;

import com.darkmalygos.forohub.domain.curso.Curso;
import com.darkmalygos.forohub.domain.curso.CursoRepository;
import com.darkmalygos.forohub.domain.usuario.Usuario;
import com.darkmalygos.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {
    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;
    public TopicoService(TopicoRepository topicoRepository, UsuarioRepository usuarioRepository, CursoRepository cursoRepository){
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
    }

    public Topico registrar(DatosRegistroTopico datos){
        if(topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())){
            throw new ValidationException("Ya existe un tópico con el mismo título y mensaje");
        }
        Usuario usuario = usuarioRepository.findById(datos.autorId()).orElseThrow(() -> new ValidationException("Usuario no existe"));
        Curso curso = cursoRepository.findById(datos.cursoId()).orElseThrow(() -> new ValidationException("Curso no existe"));
        Topico topico = new Topico(datos.titulo(), datos.mensaje(),usuario, curso);
        return topicoRepository.save(topico);
    }
}
