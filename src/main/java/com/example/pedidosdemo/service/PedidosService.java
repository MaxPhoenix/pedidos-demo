package com.example.pedidosdemo.service;

import com.example.pedidosdemo.model.dto.PedidoDTO;

public interface PedidosService {

    PedidoDTO getById(Integer id);

    void addPedido(PedidoDTO pedidoDTO);

    void editPedido(PedidoDTO pedidoDTO);

    void deletePedido(PedidoDTO pedidoDTO);

}
