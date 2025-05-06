package com.epam.training.food;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringApplicationStarter {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class)) {
            Application application = applicationContext.getBean(Application.class);
            application.run();
        }
    }
}
