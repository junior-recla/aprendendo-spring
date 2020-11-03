package com.example.demo.controller;

import com.example.demo.model.dto.ProgramaDTO;
import com.example.demo.service.ProgramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(ProgramaController.HOME)
public class ProgramaController {

    public final static String HOME = "/programa";

    private final ResponseEntity<Boolean> ACCEPTED = new ResponseEntity<Boolean> (HttpStatus.ACCEPTED);
    private final ResponseEntity<Boolean> NOT_FOUND = new ResponseEntity<Boolean> (HttpStatus.NOT_FOUND);

    @Autowired
    private ProgramaService programaService;

    @GetMapping
    public ResponseEntity<List<ProgramaDTO>> list() {
        return new ResponseEntity<List<ProgramaDTO>>(programaService.list(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramaDTO> get(@PathVariable Integer id) {
        return programaService
                .getByIndex(id)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @PostMapping
    public ResponseEntity<ProgramaDTO> cria(@RequestBody @Validated ProgramaDTO programaDTO) {
        return programaService
                .cria(programaDTO)
                .map(p -> ResponseEntity.created(URI.create(HOME + "/" + p.getId())).body(p))
                .orElseGet(ResponseEntity.badRequest()::build);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean>  delete(@PathVariable Integer id) {
        return programaService.delete(id) ? ACCEPTED : NOT_FOUND;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean>  update(@PathVariable Integer id,
                                           @RequestBody @Validated ProgramaDTO programaDTO){
        programaDTO.setId(id);
        return programaService.update(id, programaDTO) ? ACCEPTED : NOT_FOUND;
    }

}
