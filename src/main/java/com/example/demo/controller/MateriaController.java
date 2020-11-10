package com.example.demo.controller;

import com.example.demo.model.dto.MateriaDTO;
import com.example.demo.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(MateriaController.HOME)
public class MateriaController {

    public final static String HOME = "/materia";

    private final ResponseEntity<Boolean> ACCEPTED = new ResponseEntity<Boolean>(HttpStatus.ACCEPTED);
    private final ResponseEntity<Boolean> NOT_FOUND = new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);

    @Autowired
    private MateriaService materiaService;

    @GetMapping
    public ResponseEntity<List<MateriaDTO>> list() {
        return new ResponseEntity<>(materiaService.list(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaDTO> get(@PathVariable Integer id) {
        return materiaService
                .getByIndex(id)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @PostMapping
    public ResponseEntity<MateriaDTO> cria(@RequestBody @Validated MateriaDTO materiaDTO) {
        return materiaService
                .cria(materiaDTO)
                .map(m -> ResponseEntity.created(URI.create(HOME + "/" + m.getId())).body(m))
                .orElseGet(ResponseEntity.badRequest()::build);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        return materiaService.delete(id) ? ACCEPTED : NOT_FOUND;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Integer id,
                                          @RequestBody @Validated MateriaDTO materiaDTO) {
        materiaDTO.setId(id);
        return materiaService.update(id, materiaDTO) ? ACCEPTED : NOT_FOUND;
    }

}
