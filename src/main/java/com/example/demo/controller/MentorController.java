package com.example.demo.controller;

import com.example.demo.model.dto.MentorDTO;
import com.example.demo.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/mentor")
public class MentorController {

    @Autowired
    private MentorService mentorService;

    @GetMapping
    public ResponseEntity<List<MentorDTO>> list() {
        return new ResponseEntity<List<MentorDTO>>(mentorService.list(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MentorDTO> get(@PathVariable Integer id) {
        return mentorService
                .getByIndex(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MentorDTO> cria(@RequestBody MentorDTO mentorDTO) {
        return mentorService
                .cria(mentorDTO)
                .map(m -> ResponseEntity.created(URI.create("/mentor/" + m.getId())).body(m))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MentorDTO> delete(@PathVariable Integer id) {
        try {
            mentorService.delete(id);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (EmptyResultDataAccessException e) {
            System.err.println("Mentor id: " + id + " não existe");
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
