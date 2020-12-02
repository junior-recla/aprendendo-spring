package com.example.demo.controller;

import com.example.demo.model.dto.AlunoDTO;
import com.example.demo.service.AlunoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping(AlunoController.HOME)
//@Api(value="API REST Alunos")
public class AlunoController {

    public final static String HOME = "/aluno";

    private final HttpStatus ACCEPTED = HttpStatus.ACCEPTED;

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    @ApiOperation(value="Retorna uma lista contendo todos alunos")
    public ResponseEntity<List<AlunoDTO>> list() {
        return new ResponseEntity<>(alunoService.list(), ACCEPTED);
    }

    @GetMapping("/{id}")
    @ApiOperation(value="Retorna um único aluno pelo ID")
    public ResponseEntity<AlunoDTO> get(@PathVariable Integer id) {
        return alunoService
                .getByIndex(id)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @PostMapping
    @ApiOperation(value="Cadastra um aluno")
    public ResponseEntity<AlunoDTO> cria(@RequestBody @Validated AlunoDTO alunoDTO) {
        return alunoService
                .cria(alunoDTO)
                .map(a -> ResponseEntity.created(URI.create(HOME + "/" + a.getId())).body(a))
                .orElseGet(ResponseEntity.badRequest()::build);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value="Deleta um aluno")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        AlunoDTO alunoDTO = alunoService.delete(id);
        return ResponseEntity.status(ACCEPTED).body(alunoDTO);

    }

    @PutMapping("/{id}")
    @ApiOperation(value="Atualiza os dados de uma aluno")
    public ResponseEntity<?> update(@PathVariable Integer id,
                                    @RequestBody @Validated AlunoDTO alunoDTO) {
        alunoDTO.setId(id);
        AlunoDTO alunoDTOAlterado = alunoService.update(id, alunoDTO);
        return ResponseEntity.status(ACCEPTED).body(alunoDTOAlterado);
    }

}
