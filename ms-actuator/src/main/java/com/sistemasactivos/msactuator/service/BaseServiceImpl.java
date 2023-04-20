package com.sistemasactivos.msactuator.service;

import com.sistemasactivos.msactuator.model.Base;
import com.sistemasactivos.msactuator.repository.BaseRepository;

import java.io.Serializable;
import java.util.List;

public abstract class BaseServiceImpl <E extends Base, ID extends Serializable> implements BaseService<E, ID> {

    private BaseRepository<E, ID> baseRepository;

    public BaseServiceImpl(BaseRepository<E, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    public List<E> findAll() throws Exception {
        return null;
    }

    @Override
    public E findById(ID id) throws Exception {
        return null;
    }

    @Override
    public E save(E entity) throws Exception {
        return null;
    }

    @Override
    public E update(ID id, E entity) throws Exception {
        return null;
    }

    @Override
    public boolean delete(ID id) throws Exception {
        return false;
    }
}
