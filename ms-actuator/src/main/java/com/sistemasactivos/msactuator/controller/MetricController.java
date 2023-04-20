package com.sistemasactivos.msactuator.controller;

import com.sistemasactivos.msactuator.model.Metric;
import com.sistemasactivos.msactuator.service.MetricServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/metric")
public class MetricController extends BaseControllerImpl<Metric, MetricServiceImpl> {

    @Autowired
    public MetricServiceImpl service;

    @GetMapping("/{uri}/{ms}")
    public ResponseEntity<?> getData(@PathVariable String uri, @PathVariable String ms) throws Exception {
        return ResponseEntity.status(200).body(service.getMetric(uri, ms));
    }
}

