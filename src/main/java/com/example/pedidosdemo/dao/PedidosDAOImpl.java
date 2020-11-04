package com.example.pedidosdemo.dao;

import com.example.pedidosdemo.model.dto.PedidoDTO;
import com.example.pedidosdemo.model.entity.Pedido;
import org.springframework.stereotype.Repository;

@Repository
public class PedidosDAOImpl implements PedidosDAO {

    @Override
    public void insertOrUpdate(Pedido pedido) {

    }

    @Override
    public void delete(Pedido pedido) {

    }

    @Override
    public Pedido select(Integer idPedido) {
        return null;
    }
}
