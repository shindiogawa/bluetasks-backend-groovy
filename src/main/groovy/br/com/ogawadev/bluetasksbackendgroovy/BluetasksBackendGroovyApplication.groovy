package br.com.ogawadev.bluetasksbackendgroovy

import br.com.ogawadev.bluetasksbackendgroovy.domain.task.Task
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer

@SpringBootApplication
class BluetasksBackendGroovyApplication implements RepositoryRestConfigurer{

    static void main(String[] args) {
        SpringApplication.run(BluetasksBackendGroovyApplication, args)
    }

    @Override
    void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Task.class)
    }
}
