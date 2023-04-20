package com.sistemasactivos.msactuator.controller;

import com.sistemasactivos.msactuator.model.Base;
import com.sistemasactivos.msactuator.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


public abstract class BaseControllerImpl <E extends Base, S extends BaseServiceImpl<E, Long>> implements BaseController<E, Long> {

    @Autowired
    protected S service;

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        return null;
   }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(Long aLong) {
        return null;
    }

    @PostMapping("/")
    public ResponseEntity<?> save(E entity) {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(Long aLong, E entity) {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<?> delete(Long aLong) {
        return null;
    }
}


