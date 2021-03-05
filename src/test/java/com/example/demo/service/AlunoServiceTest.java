package com.example.demo.service;

import com.example.demo.factory.AlunoFactory;
import com.example.demo.model.domain.Aluno;
import com.example.demo.model.dto.AlunoDTO;
import com.example.demo.model.mapper.AlunoMapper;
import com.example.demo.repository.AlunoRepository;
import com.example.demo.util.AlunoCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.function.Function;

@ExtendWith(MockitoExtension.class)
class AlunoServiceTest {

    @InjectMocks
    private AlunoService alunoService;

    @Mock
    private AlunoRepository alunoRepositoryMock;

    @Mock
    private AlunoFactory alunoFactoryMock;

    private final Boolean ATIVO = Boolean.TRUE;

    @Spy
    AlunoMapper mapper = Mappers.getMapper(AlunoMapper.class);

    @Test
    @DisplayName("Retorna uma lista de alunosDTO dentro de uma página quando ocorrer com sucesso")
    void page_RetornaUmaListaDeAlunosDTODentroDeUmaPagina_QuandoOcorrerComSucesso() {
        Aluno aluno = AlunoCreator.createValidAluno();
        List alunoList = List.of(aluno);
        Page<Aluno> pageAluno = new PageImpl<>(alunoList);
        AlunoDTO alunoDTO = AlunoCreator.createValidAlunoDTO();

        Mockito.when(alunoRepositoryMock.findAllByAtivo(PageRequest.of(0,3), ATIVO)).thenReturn(pageAluno);
        Mockito.when(alunoRepositoryMock.findAllAlunoAtivoEProgramas(alunoList)).thenReturn(alunoList);

        Page<AlunoDTO> pageAlunoTest = alunoService.page(PageRequest.of(0,3) );
        Assertions.assertAll(
                () -> Assertions.assertEquals( pageAlunoTest.toList().get(0).getNome(), aluno.getNome() ),
                () -> Assertions.assertEquals( pageAlunoTest.toList().get(0).getId(), 1 )
        );

    }

}