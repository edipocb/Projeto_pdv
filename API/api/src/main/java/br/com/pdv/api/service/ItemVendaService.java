package br.com.pdv.api.service;

import br.com.pdv.api.controller.ItemVendaController;
import br.com.pdv.api.model.ItemVenda;
import br.com.pdv.api.repository.ItemVendaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemVendaService {

    private final ItemVendaRepository itemVendaRepository;

    public ItemVendaService(ItemVendaRepository service) {
        itemVendaRepository = service;
    }

    public List<ItemVenda> listarTodos() {
        return itemVendaRepository.findAll();
    }

}
