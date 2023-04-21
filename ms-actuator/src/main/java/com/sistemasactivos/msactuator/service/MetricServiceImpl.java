package com.sistemasactivos.msactuator.service;

import com.sistemasactivos.msactuator.model.Metric;
import com.sistemasactivos.msactuator.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MetricServiceImpl extends BaseServiceImpl<Metric, Long>{

    public MetricServiceImpl(BaseRepository<Metric, Long> baseRepository) {
        super(baseRepository);
    }

    @Autowired
    @Qualifier("ms-persona")
    private WebClient webClientPersona;

    @Autowired
    @Qualifier("ms-categoria")
    private WebClient webClientCategoria;


    // Constantes para identificar los microservicios y las métricas que se van a consultar
    private static final String MS_PERSONA = "ms-persona";
    private static final String MS_CATEGORIA = "ms-categoria";
    private static final String URI_DISK_TOTAL = "/disk.total";
    private static final String URI_MEMORY_USED = "/jvm.memory.used";
    private static final String URI_CPU_USAGE = "/system.cpu.usage";


    // En casode no poder establecer la conexión con el microservicio, se muestra un mensaje de error
    // indicando el microservicio que no se pudo conectar.
    private void handleException(String ms) {
        System.out.println("Error: No se pudo establecer la conexión con el microservicio " + ms);
    }


    // Obtiene la métrica del microservicio indicado en el parámetro ms, la url y el webclient
    private Metric getMetric(String url, String ms, WebClient webClient) {
        Metric metric = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Metric.class)
                .block();

        if (metric != null){
            metric.setMs(ms);
            return metric;

        } else {
            System.out.println("El microservicio " + ms + " está apagado.");
            return null;
        }
    }

    @Scheduled(fixedRate = 10000)
    public void getMetricFreeDiskPersona() {
        try {
            Metric metric = getMetric(URI_DISK_TOTAL, MS_PERSONA, webClientPersona);
            if (metric != null)
                save(metric);

            else
                System.out.println("No se puedo realizar la consulta al microservicio " + MS_PERSONA);

        } catch (Exception e) {
            handleException(MS_PERSONA);
        }
    }

    @Scheduled(fixedRate = 1000)
    public void getMetricMemoryPersona() {
        try {
            Metric metric = getMetric(URI_MEMORY_USED, MS_PERSONA, webClientPersona);
            if (metric != null)
                save(metric);

            else
                System.out.println("No se puedo realizar la consulta al microservicio " + MS_PERSONA);

        } catch (Exception e) {
            handleException(MS_PERSONA);
        }
    }

    @Scheduled(fixedRate = 1000)
    public void getMetricFreeDiskCategoria() {
        try {
            Metric metric = getMetric(URI_DISK_TOTAL, MS_CATEGORIA, webClientCategoria);
            if (metric != null)
                save(metric);

            else
                System.out.println("No se puedo realizar la consulta al microservicio " + MS_CATEGORIA);

        } catch (Exception e) {
            handleException(MS_CATEGORIA);
        }
    }

    @Scheduled(fixedRate = 1000)
    public void getMetricMemoryCategoria() {
        try {
            Metric metric = getMetric(URI_MEMORY_USED, MS_CATEGORIA, webClientCategoria);
            if (metric != null)
                save(metric);

            else
                System.out.println("No se puedo realizar la consulta al microservicio " + MS_CATEGORIA);

        } catch (Exception e) {
            handleException(MS_CATEGORIA);
        }
    }

    @Scheduled(fixedRate = 1000)
    public void getMetricSystemCpuUsagePersona() {
        try {
            Metric metric = getMetric(URI_CPU_USAGE, MS_PERSONA, webClientPersona);
            if (metric != null)
                save(metric);

            else
                System.out.println("No se puedo realizar la consulta al microservicio " + MS_PERSONA);

        } catch (Exception e) {
            handleException(MS_PERSONA);
        }
    }

    @Scheduled(fixedRate = 1000)
    public void getMetricSystemCpuUsageCategoria() {
        try {
            Metric metric = getMetric(URI_CPU_USAGE, MS_CATEGORIA, webClientCategoria);
            if (metric != null)
                save(metric);

            else
                System.out.println("No se puedo realizar la consulta al microservicio " + MS_CATEGORIA);

        } catch (Exception e) {
            handleException(MS_CATEGORIA);
        }
    }

}
