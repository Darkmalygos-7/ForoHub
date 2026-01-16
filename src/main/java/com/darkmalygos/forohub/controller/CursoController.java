package com.darkmalygos.forohub.controller;

import com.darkmalygos.forohub.domain.curso.Curso;
import com.darkmalygos.forohub.domain.curso.CursoService;
import com.darkmalygos.forohub.domain.curso.DatosDetalleCurso;
import com.darkmalygos.forohub.domain.curso.DatosRegistroCurso;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    private final CursoService cursoService;
    public CursoController(CursoService cursoService){
        this.cursoService = cursoService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroCurso datos, UriComponentsBuilder uriComponentsBuilder){
        Curso curso = cursoService.registrar(datos);
        var uri = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleCurso(curso));
    }
}
