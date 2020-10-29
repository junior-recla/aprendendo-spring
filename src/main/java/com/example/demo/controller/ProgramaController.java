package com.example.demo.controller;

import com.example.demo.model.dto.ProgramaDTO;
import com.example.demo.service.ProgramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/programa")
public class ProgramaController {

    @Autowired
    private ProgramaService programaService;

    @GetMapping
    public ResponseEntity<List<ProgramaDTO>> list() {
        return new ResponseEntity<List<ProgramaDTO>>(programaService.list(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramaDTO> get(@PathVariable int id) {
        return programaService
                .getByIndex(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProgramaDTO> cria(@RequestBody ProgramaDTO programaDTO) {
        return programaService
                .cria(programaDTO)
                .map(p -> ResponseEntity.created(URI.create("/programa/" + p.getId())).body(p))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProgramaDTO> delete(@PathVariable int id) {
        try {
            programaService.delete(id);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (EmptyResultDataAccessException e) {
            System.err.println("Programa id: " + id + " não existe");
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }
}
