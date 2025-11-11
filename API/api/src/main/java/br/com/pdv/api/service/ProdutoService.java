package br.com.pdv.api.service;

import br.com.pdv.api.model.Produto;
import br.com.pdv.api.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository repo) {
        produtoRepository = repo;
    }

    public Produto cadastrarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }
}
