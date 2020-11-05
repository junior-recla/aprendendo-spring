package com.example.demo.controller;

import com.example.demo.model.dto.MentoriaDTO;
import com.example.demo.service.MentoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(MentoriaController.HOME)
public class MentoriaController {

    public final static String HOME = "/mentoria";

    private final ResponseEntity<Boolean> ACCEPTED = new ResponseEntity<Boolean>(HttpStatus.ACCEPTED);
    private final ResponseEntity<Boolean> NOT_FOUND = new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);

    @Autowired
    private MentoriaService mentoriaService;

    @GetMapping
    public ResponseEntity<List<MentoriaDTO>> list() {
        return new ResponseEntity<>(mentoriaService.list(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MentoriaDTO> get(@PathVariable Integer id) {
        return mentoriaService
                .getByIndex(id)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @PostMapping
    public ResponseEntity<MentoriaDTO> cria(@RequestBody @Validated MentoriaDTO mentoriaDTO) {
        return mentoriaService
                .cria(mentoriaDTO)
                .map(a -> ResponseEntity.created(URI.create(HOME + "/" + a.getId())).body(a))
                .orElseGet(ResponseEntity.badRequest()::build);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        return mentoriaService.delete(id) ? ACCEPTED : NOT_FOUND;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Integer id,
                                          @RequestBody @Validated MentoriaDTO mentoriaDTO) {
        mentoriaDTO.setId(id);
        return mentoriaService.update(id, mentoriaDTO) ? ACCEPTED : NOT_FOUND;
    }

}
