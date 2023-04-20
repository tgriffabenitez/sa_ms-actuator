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
    private WebClient webClientPersona;

    @Autowired
    @Qualifier("ms-categoria")
    private WebClient webClientCategoria;

    public MetricServiceImpl(BaseRepository<Metric, Long> baseRepository) {
        super(baseRepository);
    }

    public Metric getMetric(String uri, String ms) throws Exception {
        WebClient webClient;

        // verifico cual es el microservicio que me esta pidiendo la metrica
        // y creo el webclient correspondiente
        if (ms.equals("ms-persona")) {
             webClient = webClientPersona;
        } else if (ms.equals("ms-categoria")) {
            webClient = webClientCategoria;
        } else {
            throw new Exception("No existe el microservicio");
        }

        Metric metric = webClient.get()
                .uri("/" + uri)
                .retrieve()
                .bodyToMono(Metric.class)
                .block();

        if (metric == null)
            throw new Exception("No existe la metrica");

        metric.setMs(ms);

        // guardo el metric en la base de datos
        return this.save(metric);
    }

}
