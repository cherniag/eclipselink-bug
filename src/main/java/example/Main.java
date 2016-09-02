package example;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.WebSocketAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAutoConfiguration(exclude = {
        MongoDataAutoConfiguration.class,
        WebSocketAutoConfiguration.class,
        SpringDataWebAutoConfiguration.class,
        MultipartAutoConfiguration.class,
        MongoRepositoriesAutoConfiguration.class
})
@ComponentScan("example")
@Import(Config.class)
public class Main {


    public static void main(String[] args) {
        new SpringApplicationBuilder(Main.class).run(args);
    }

}
