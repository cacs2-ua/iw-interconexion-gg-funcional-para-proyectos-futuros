package tpvv;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner printAppUrl(Environment env) {
        return args -> {
            String port = env.getProperty("server.port", "8123");
            String contextPath = env.getProperty("server.servlet.context-path", "");
            System.out.println("La aplicación se encuentra disponible en http://localhost:" + port + contextPath);
        };
    }
}
