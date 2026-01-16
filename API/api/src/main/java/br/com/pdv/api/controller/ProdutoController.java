package br.com.pdv.api.controller;

import br.com.pdv.api.model.Produto;
import br.com.pdv.api.service.ProdutoService;
import org.springdoc.core.service.GenericResponseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProdutoController {

    private final GenericResponseService responseBuilder;
    private ProdutoService produtoService;

    public ProdutoController(ProdutoService service, GenericResponseService responseBuilder) {
        produtoService = service;
        this.responseBuilder = responseBuilder;
    }

    @PostMapping("/cadastrarProduto")
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody Produto produto){
        produtoService.cadastrarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }




}
