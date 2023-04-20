package com.sistemasactivos.msactuator.controller;

import com.sistemasactivos.msactuator.model.Base;
import com.sistemasactivos.msactuator.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public abstract class BaseControllerImpl <E extends Base, S extends BaseServiceImpl<E, Long>> implements BaseController<E, Long> {

    @Autowired
    protected S service;


    @GetMapping("/bd/all")
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(service.findAll(), null, 200);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), null, 500);
        }
   }

    @GetMapping("/bd/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.findById(id), null, 200);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), null, 500);
        }
    }

    @DeleteMapping("/bd/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.delete(id), null, 200);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), null, 500);
        }
    }
}


