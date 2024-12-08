package com.example.petapp.config;

import com.example.petapp.controllers.MutationController;
import com.example.petapp.controllers.QueryController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphQLConfig {

    @Bean
    public QueryController queryController() {
        return new QueryController();
    }

    @Bean
    public MutationController mutationController() {
        return new MutationController();
    }
}
