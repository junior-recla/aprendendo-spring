package com.example.demo.controller;

import com.example.demo.model.dto.AlunoDTO;
import com.example.demo.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(AlunoController.home)
public class AlunoController {

    public final static String home = "/aluno";

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
        try {
            alunoService.delete(id);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (EmptyResultDataAccessException e) {
            System.err.println(home + "id: " + id + " não existe");
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
