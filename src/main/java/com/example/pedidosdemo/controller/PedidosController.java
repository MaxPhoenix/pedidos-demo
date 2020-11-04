package com.example.pedidosdemo.controller;

import com.example.pedidosdemo.model.dto.PedidoDTO;
import com.example.pedidosdemo.service.PedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {

    @Autowired
    private PedidosService pedidosServiceImpl;

    @GetMapping(value = "{idPedido}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PedidoDTO getById(@PathVariable Integer idPedido){
        return pedidosServiceImpl.getById(idPedido);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addPedido(@RequestBody PedidoDTO pedidoDTO){
        pedidosServiceImpl.addPedido(pedidoDTO);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void editPedido(@RequestBody PedidoDTO pedidoDTO){
        pedidosServiceImpl.editPedido(pedidoDTO);
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deletePedido(@RequestBody PedidoDTO pedidoDTO){
        pedidosServiceImpl.deletePedido(pedidoDTO);
    }
}
