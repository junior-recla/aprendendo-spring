package com.example.demo.controller;

import com.example.demo.model.dto.AlunoDTO;
import com.example.demo.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(AlunoController.home)
public class AlunoController {

    public final static String home = "/aluno";
    private final ResponseEntity ACCEPTED = new ResponseEntity(HttpStatus.ACCEPTED);
    private final ResponseEntity NOT_FOUND = new ResponseEntity(HttpStatus.NOT_FOUND);

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> list() {
        return new ResponseEntity<List<AlunoDTO>>(alunoService.list(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> get(@PathVariable Integer id) {
        return alunoService
                .getByIndex(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AlunoDTO> cria(@RequestBody AlunoDTO alunoDTO) {
        return alunoService
                .cria(alunoDTO)
                .map(a -> ResponseEntity.created(URI.create(home + "/" + a.getId())).body(a))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        return alunoService.delete(id) ? ACCEPTED : NOT_FOUND;
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoDTO> update(@PathVariable Integer id,
                                           @RequestBody AlunoDTO alunoDTO) {
        alunoDTO.setId(id);
        return alunoService.update(id, alunoDTO) ? ACCEPTED : NOT_FOUND;
    }

}
