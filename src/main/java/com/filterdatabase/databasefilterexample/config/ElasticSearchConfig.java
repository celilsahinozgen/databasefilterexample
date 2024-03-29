//package com.filterdatabase.databasefilterexample.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.client.ClientConfiguration;
//
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.data.elasticsearch.client.RestClients;
//import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
//
//@Configuration
//public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {
//
//
//    @Override
//    @Bean
//    public RestHighLevelClient elasticsearchClient() {
//
//        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
//                .connectedTo("localhost:9200")
//                .build();
//
//        return RestClients.create(clientConfiguration).rest();
//    }
//}
