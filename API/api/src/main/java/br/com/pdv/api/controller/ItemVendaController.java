package br.com.pdv.api.controller;

import br.com.pdv.api.model.ItemVenda;
import br.com.pdv.api.service.ItemVendaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/itemvenda")
public class ItemVendaController {

    private ItemVendaService itemVendaService;

    public ItemVendaController(ItemVendaService service) {
        itemVendaService = service;
      }

      @GetMapping
    public ResponseEntity<List<ItemVenda>> listarTodos(){
        List<ItemVenda> itemVendas = itemVendaService.listarTodos();
        return ResponseEntity.ok(itemVendas);
      }
}
