package com.example.pedidosdemo.dao;

import com.example.pedidosdemo.model.dto.PedidoDTO;
import com.example.pedidosdemo.model.entity.Pedido;

public interface PedidosDAO {

    void insertOrUpdate(Pedido pedido);

    void delete(Pedido pedido);

    Pedido select(Integer idPedido);
}
