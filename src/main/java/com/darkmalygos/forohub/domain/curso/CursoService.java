package com.darkmalygos.forohub.domain.curso;

import org.springframework.stereotype.Service;

@Service
public class CursoService {
    private final CursoRepository cursoRepository;
    public CursoService(CursoRepository cursoRepository){
        this.cursoRepository = cursoRepository;
    }

    public Curso registrar(DatosRegistroCurso datos){
        Curso curso = new Curso(datos);
        return cursoRepository.save(curso);
    }
}
