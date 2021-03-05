package com.example.demo.openfeign;

import com.example.demo.model.domain.Endereco;
import com.example.demo.model.domain.Produto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "produto", url = "https://produtos-apirest.herokuapp.com/api/produtos")
//@FeignClient(name = "cep", url = "https://viacep.com.br/ws/04101300/json")
//https://viacep.com.br/
//https://domineospring.wordpress.com/2017/06/02/feign-uma-forma-simples-para-consumir-servicos/
public interface ProdutoEndpoint {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public List<Produto> retornaProduto();

//    @RequestMapping(method = RequestMethod.GET, value = "/")
//    public Endereco retornaEndereco();

}
