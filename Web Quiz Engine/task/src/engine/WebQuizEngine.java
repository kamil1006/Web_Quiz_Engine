package engine;

import engine.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;

@ComponentScan(basePackages = "engine")
@SpringBootApplication
public class WebQuizEngine {

    //public static List<UserDto> lista = new ArrayList<>();
    public static List<User> lista = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(WebQuizEngine.class, args);
    }

}
