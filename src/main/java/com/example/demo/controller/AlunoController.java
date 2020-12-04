package com.example.demo.controller;

import com.example.demo.model.dto.AlunoDTO;
import com.example.demo.service.AlunoService;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

@RestController
@RequestMapping(AlunoController.HOME)
public class AlunoController {

    public final static String HOME = "/aluno";

    private final HttpStatus ACCEPTED = HttpStatus.ACCEPTED;

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    @ApiOperation(value = "Busca Alunos",
            notes = "Retorna uma página contendo alunos")
//    http://localhost:8080/aluno?sort=classe,desc&sort=nome,asc
//    @PageableDefault(size = 3, sort = {"nome", "id"}, direction = Sort.Direction.DESC)
//    @SortDefault.SortDefaults({
//            @SortDefault(sort = "nome", direction = Sort.Direction.DESC),
//            @SortDefault(sort = "id", direction = Sort.Direction.ASC)
//    })
    public ResponseEntity<Page<AlunoDTO>> page(@PageableDefault(size = 3) Pageable pageable) {
        Page<AlunoDTO> pageContent = alunoService.page(pageable);
        return ResponseEntity.ok(pageContent);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Retorna um único aluno pelo ID")
    public ResponseEntity<AlunoDTO> get(@PathVariable Integer id) {
        return alunoService
                .getByIndex(id)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @PostMapping
    @ApiOperation(value = "Cadastra um aluno")
    public ResponseEntity<AlunoDTO> cria(@RequestBody @Validated AlunoDTO alunoDTO) {
        return alunoService
                .cria(alunoDTO)
                .map(a -> ResponseEntity.created(URI.create(HOME + "/" + a.getId())).body(a))
                .orElseGet(ResponseEntity.badRequest()::build);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta um aluno")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        AlunoDTO alunoDTO = alunoService.delete(id);
        return ResponseEntity.status(ACCEPTED).body(alunoDTO);

    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza os dados de uma aluno")
    public ResponseEntity<?> update(@PathVariable Integer id,
                                    @RequestBody @Validated AlunoDTO alunoDTO) {
        alunoDTO.setId(id);
        AlunoDTO alunoDTOAlterado = alunoService.update(id, alunoDTO);
        return ResponseEntity.status(ACCEPTED).body(alunoDTOAlterado);
    }

}
