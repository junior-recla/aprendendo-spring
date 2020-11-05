package com.example.demo.controller;

import com.example.demo.model.dto.MentorDTO;
import com.example.demo.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(MentorController.HOME)
public class MentorController {

    public final static String HOME = "/mentor";

    private final ResponseEntity<Boolean> ACCEPTED = new ResponseEntity<Boolean>(HttpStatus.ACCEPTED);
    private final ResponseEntity<Boolean> NOT_FOUND = new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);

    @Autowired
    private MentorService mentorService;

    @GetMapping
    public ResponseEntity<List<MentorDTO>> list() {
        return new ResponseEntity<>(mentorService.list(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MentorDTO> get(@PathVariable Integer id) {
        return mentorService
                .getByIndex(id)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @PostMapping
    public ResponseEntity<MentorDTO> cria(@RequestBody @Validated MentorDTO mentorDTO) {
        return mentorService
                .cria(mentorDTO)
                .map(m -> ResponseEntity.created(URI.create(HOME + "/" + m.getId())).body(m))
                .orElseGet(ResponseEntity.badRequest()::build);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        return mentorService.delete(id) ? ACCEPTED : NOT_FOUND;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Integer id,
                                          @RequestBody @Validated MentorDTO mentorDTO) {
        mentorDTO.setId(id);
        return mentorService.update(id, mentorDTO) ? ACCEPTED : NOT_FOUND;
    }
}
