package com.example.demo.controller;

import com.example.demo.model.domain.Endereco;
import com.example.demo.model.domain.Produto;
import com.example.demo.openfeign.ProdutoEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoEndpoint produtoEndpoint;

    @GetMapping
    public List<Produto> retornaProduto() {
        return produtoEndpoint.retornaProduto();
//        return produtoEndpoint.retornaEndereco();
    }

}
