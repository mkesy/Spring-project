package hello.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/companies").setViewName("companies");
        registry.addViewController("/material/{id}").setViewName("material");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/materialDetails").setViewName("materialDetails");
        registry.addViewController("/details").setViewName("editDetails");
    }

}