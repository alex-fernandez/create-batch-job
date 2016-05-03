package com.alexfrndz.config;

import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class AppConfig {
    @Bean
    public Client elasticsearchClient() throws UnknownHostException {
        final Settings.Builder settings = Settings.settingsBuilder();
        TransportClient transportClient = new TransportClient.Builder().
                settings(settings)
                .build();
        transportClient = transportClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localdocker"), 9300));
        return transportClient;
    }

    @Bean
    public com.sun.jersey.api.client.Client jerseyClient() {
        ClientConfig config = new DefaultClientConfig();
        com.sun.jersey.api.client.Client jerseyClient = com.sun.jersey.api.client.Client.create(config);
        return jerseyClient;
    }





}
