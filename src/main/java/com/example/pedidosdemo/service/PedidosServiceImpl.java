package com.example.pedidosdemo.service;

import com.example.pedidosdemo.cache.BumexMemCached;
import com.example.pedidosdemo.config.exception.beans.PersistentException;
import com.example.pedidosdemo.constants.CacheConstants;
import com.example.pedidosdemo.dao.PedidosDAO;
import com.example.pedidosdemo.model.dto.PedidoDTO;
import com.example.pedidosdemo.model.entity.Pedido;
import com.example.pedidosdemo.utils.NumberUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class PedidosServiceImpl implements PedidosService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PedidosDAO pedidosDAOImpl;

    @Override
    public PedidoDTO getById(Integer id) {
        if(NumberUtils.isNullOrLowerThanZero(id))
            throw new IllegalArgumentException("Se debe especificar un id valido de Pedido");
        BumexMemCached cache = BumexMemCached.getInstance();
        PedidoDTO pedidoDTO = (PedidoDTO) cache.get(CacheConstants.PEDIDO_CACHE_KEY + id);

        if(pedidoDTO == null || NumberUtils.isNullOrLowerThanZero(pedidoDTO.getIdPedido()) ){
            Pedido pedido = this.pedidosDAOImpl.select(id);
            if(pedido != null) {
                pedidoDTO = modelMapper.map(pedido, PedidoDTO.class);
                cache.set(CacheConstants.PEDIDO_CACHE_KEY + id, pedidoDTO);
            }
        }
        return pedidoDTO;
    }

    @Override
    public void addPedido(PedidoDTO pedidoDTO) {
        if(pedidoDTO == null)
            throw new IllegalArgumentException("Se deben especificar los datos del Pedido para crearlo.");

        validatePedido(pedidoDTO);

        PedidoDTO pedidoDTOCache = getPedidoFromCache(pedidoDTO);

        if(pedidoDTOCache != null)
           throw new IllegalArgumentException("Ya se encuentra registrado el Pedido.");
        else
            pedidoDTOCache = pedidoDTO;

        this.addPedidoToDatabaseAndCache(pedidoDTOCache);
    }

    private void validatePedido(PedidoDTO pedidoDTO) {
        if(StringUtils.isEmpty(pedidoDTO.getNombre()))
            throw new IllegalArgumentException("Se debe especificar un nombre para el Pedido");
        if(NumberUtils.isNullOrLowerThanZero(pedidoDTO.getMonto()))
            throw new IllegalArgumentException("Se debe especificar un monto para el Pedido");
    }

    private void addPedidoToDatabaseAndCache(PedidoDTO pedidoDTOCache) {
        BumexMemCached cache = BumexMemCached.getInstance();

        try {
            Pedido pedido = modelMapper.map(pedidoDTOCache, Pedido.class);
            this.pedidosDAOImpl.insertOrUpdate(pedido);
            pedidoDTOCache = modelMapper.map(pedido, PedidoDTO.class);
            cache.set(CacheConstants.PEDIDO_CACHE_KEY + pedidoDTOCache.getIdPedido(), pedidoDTOCache);
        } catch (Exception e) {
            throw new PersistentException("Ocurrió un error al agregar un Pedido.", e);
        }
    }

    @Override
    public void editPedido(PedidoDTO pedidoDTO) {
        if(pedidoDTO == null || NumberUtils.isNullOrLowerThanZero(pedidoDTO.getIdPedido()) )
            throw new IllegalArgumentException("Se debe especificar un Pedido existente para guardar los cambios.");

        PedidoDTO pedidoDTOCache = getPedidoFromCache(pedidoDTO);
        if(pedidoDTOCache != null) {
           if(pedidoDTOCache.equals(pedidoDTO))
               throw new IllegalArgumentException("Se deben modificar los datos del Pedido para guardar los cambios.");
        }
        else
            throw new IllegalArgumentException("Se debe especificar un Pedido existente para editarlo.");

        this.editPedidoInDatabaseAndCache(pedidoDTO);
    }

    private void editPedidoInDatabaseAndCache(PedidoDTO pedidoDTOCache) {
        BumexMemCached cache = BumexMemCached.getInstance();
        try {
            Pedido pedido = modelMapper.map(pedidoDTOCache, Pedido.class);
            this.pedidosDAOImpl.insertOrUpdate(pedido);
            pedidoDTOCache = modelMapper.map(pedido, PedidoDTO.class);
            cache.set(CacheConstants.PEDIDO_CACHE_KEY + pedidoDTOCache.getIdPedido(), pedidoDTOCache);
        } catch (Exception e) {
            throw new PersistentException("Ocurrió un error al eliminar un Pedido.", e);
        }
    }

    @Override
    public void deletePedido(PedidoDTO pedidoDTO) {
        if(pedidoDTO == null || NumberUtils.isNullOrLowerThanZero(pedidoDTO.getIdPedido()) )
            throw new IllegalArgumentException("Se debe especificar un Pedido existente para eliminarlo.");

        PedidoDTO pedidoDTOCache = getPedidoFromCache(pedidoDTO);

        if(pedidoDTOCache == null)
            throw new IllegalArgumentException("Se debe especificar un Pedido existente para eliminarlo.");

        this.deletePedidoFromDatabaseAndCache(pedidoDTOCache);
    }

    private void deletePedidoFromDatabaseAndCache(PedidoDTO pedidoDTOCache) {
        BumexMemCached cache = BumexMemCached.getInstance();
        try {
            Pedido pedido = modelMapper.map(pedidoDTOCache, Pedido.class);
            this.pedidosDAOImpl.delete(pedido);
            cache.delete(CacheConstants.PEDIDO_CACHE_KEY + pedidoDTOCache.getIdPedido());
        } catch (Exception e) {
            throw new PersistentException("Ocurrió un error al eliminar un Pedido.", e);
        }

    }

    private PedidoDTO getPedidoFromCache(PedidoDTO pedidoDTO){
        BumexMemCached cache = BumexMemCached.getInstance();
        PedidoDTO pedidoDTOCache = (PedidoDTO) cache.get(CacheConstants.PEDIDO_CACHE_KEY + pedidoDTO.getIdPedido());
        return pedidoDTOCache;
    }


}
