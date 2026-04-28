package org.example.tiendalemlah.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConfiguracionWeb implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Mapeo para condiciones (ya existía)
        registry.addViewController("/condiciones")
                .setViewName("condiciones");

        registry.addViewController("/sobre-nosotros")
                .setViewName("sobre-nosotros");
    }
}
