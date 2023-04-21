package com.sistemasactivos.msactuator.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
@EnableScheduling
public class WebFluxConfig implements WebFluxConfigurer {

    @Bean
    @Qualifier("ms-persona") // Le pongo un nombre para poder inyectarlo en el servicio
    public WebClient getMsPersona() {
        // url del microservicio persona
        return createWebClient("http://localhost:8084/actuator/metrics", "admin1", "123");
    }

    @Bean
    @Qualifier("ms-categoria") // Le pongo un nombre para poder inyectarlo en el servicio
    public WebClient getMsCategoria() {
        // url del microservicio persona
        return createWebClient("http://localhost:8083/actuator/metrics", "admin2", "321");
    }

    /**
     * Crea un cliente web para consumir los microservicios
     */
    private WebClient createWebClient(String baseUrl, String username, String password) {
        HttpClient httpClient = HttpClient.create()
                .tcpConfiguration(client ->
                        client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
                                .doOnConnected(conn -> conn
                                        .addHandlerLast(new ReadTimeoutHandler(10))
                                        .addHandlerLast(new WriteTimeoutHandler(10))));

        ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient.wiretap(true));

        return WebClient.builder()
                .baseUrl(baseUrl) // url del microservicio a usar
                .clientConnector(connector)
                .defaultHeaders(httpHeaders -> httpHeaders.setBasicAuth(username, password)) // credenciales de acceso a la api
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}