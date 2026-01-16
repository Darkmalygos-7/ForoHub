package com.darkmalygos.forohub.controller;

import com.darkmalygos.forohub.domain.topico.DatosDetalleTopico;
import com.darkmalygos.forohub.domain.topico.DatosRegistroTopico;
import com.darkmalygos.forohub.domain.topico.Topico;
import com.darkmalygos.forohub.domain.topico.TopicoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    private final TopicoService topicoService;
    public TopicoController(TopicoService topicoService){
        this.topicoService = topicoService;
    }

    @Transactional
    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriComponentsBuilder){
        Topico topico = topicoService.registrar(datos);
        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleTopico(topico));
    }

}
