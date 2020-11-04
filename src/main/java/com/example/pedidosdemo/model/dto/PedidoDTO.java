package com.example.pedidosdemo.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class PedidoDTO implements Serializable {

    private Integer idPedido;
    private String nombre;
    private BigDecimal monto;
    private Double descuento;

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoDTO pedidoDTO = (PedidoDTO) o;
        return Objects.equals(idPedido, pedidoDTO.idPedido) &&
                Objects.equals(nombre, pedidoDTO.nombre) &&
                Objects.equals(monto, pedidoDTO.monto) &&
                Objects.equals(descuento, pedidoDTO.descuento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPedido, nombre, monto, descuento);
    }
}
