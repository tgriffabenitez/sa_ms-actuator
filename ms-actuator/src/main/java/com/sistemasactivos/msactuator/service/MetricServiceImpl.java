package com.sistemasactivos.msactuator.service;

import com.sistemasactivos.msactuator.model.Metric;
import com.sistemasactivos.msactuator.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MetricServiceImpl extends BaseServiceImpl<Metric, Long>{

    @Autowired
    @Qualifier("ms-persona")
    private WebClient webClient;

    public MetricServiceImpl(BaseRepository<Metric, Long> baseRepository) {
        super(baseRepository);
    }

    public Metric getDiskTotal() throws Exception {
        Metric metric = webClient.get()
                .uri("/disk.total")
                .retrieve()
                .bodyToMono(Metric.class)
                .block();

        return this.save(metric);
    }
}
