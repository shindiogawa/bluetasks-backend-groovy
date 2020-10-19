package br.com.ogawadev.bluetasksbackendgroovy

import br.com.ogawadev.bluetasksbackendgroovy.domain.task.Task
import groovy.util.logging.Slf4j
import org.apache.logging.slf4j.SLF4JLogger
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer
import org.springframework.validation.Validator
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean

@SpringBootApplication
@Slf4j
class BluetasksBackendGroovyApplication implements RepositoryRestConfigurer{


    static void main(String[] args) {
        SpringApplication.run(BluetasksBackendGroovyApplication, args)
        log.info("Bluetasks in action")
    }

    @Override
    void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Task.class)
        config.getCorsRegistry()
            .addMapping("/**")
            .allowedOrigins("*") // nome dos hosts para liberar
            .allowedMethods("GET","POST","PUT","DELETE")

        log.info("Repository CORS setup... OK!")
    }

    @Bean
    Validator validator() {
        return new LocalValidatorFactoryBean()
    }

    @Override
    void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
        Validator validator = validator()
        validatingListener.addValidator("beforeCreate",validator)
        validatingListener.addValidator("beforeSave",validator)
        log.info("Configure Validator ... OK!")
    }
}
