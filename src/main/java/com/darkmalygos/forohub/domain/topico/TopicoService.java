package com.darkmalygos.forohub.domain.topico;

import com.darkmalygos.forohub.domain.curso.Curso;
import com.darkmalygos.forohub.domain.curso.CursoRepository;
import com.darkmalygos.forohub.domain.usuario.Usuario;
import com.darkmalygos.forohub.domain.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    @Transactional(readOnly = true)
    public Page<DatosListaTopicos> listar(Pageable paginacion){
        return topicoRepository.findAllByStatusTrue(paginacion).map(DatosListaTopicos::new);
    }

    public Topico detallar(Long id){
        return topicoRepository.getReferenceById(id);
    }

    public Topico actualizar(@NotNull Long id, DatosActualizacionTopicos datos) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        if(!optionalTopico.isPresent()){
            throw new ValidationException("Topico no encontrado");
        }
        Topico topico = optionalTopico.get();
        boolean duplicado = topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje());
        if (duplicado && (!topico.getTitulo().equals(datos.titulo()) || !topico.getMensaje().equals(datos.mensaje()))) {
            throw new IllegalArgumentException("Tópico duplicado");
        }
        Curso curso = null;
        if (datos.cursoId() != null) {
            curso = cursoRepository.findById(datos.cursoId())
                    .orElseThrow(() -> new EntityNotFoundException("Curso no encontrado"));
        }
        topico.actualizarTopico(datos,curso);
        return topico;
    }

    public Topico eliminar(Long id) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        if(!optionalTopico.isPresent()){
            throw new ValidationException("Topico no encontrado");
        }
        Topico topico = optionalTopico.get();
        topico.eliminar();
        return topico;
    }
}
