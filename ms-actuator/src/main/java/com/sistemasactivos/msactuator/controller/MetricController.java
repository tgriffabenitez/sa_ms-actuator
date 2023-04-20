package com.sistemasactivos.msactuator.controller;

import com.sistemasactivos.msactuator.model.Metric;
import com.sistemasactivos.msactuator.service.MetricServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/disk")
public class MetricController extends BaseControllerImpl<Metric, MetricServiceImpl> {

    @Autowired
    public MetricServiceImpl service;

    @GetMapping("/disk-total")
    public ResponseEntity<?> getDiskTotal() throws Exception {
        return ResponseEntity.status(200).body(service.getDiskTotal());
    }
}
