package bigbowl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

//Remove the exclude when/if security is needed
@SpringBootApplication( exclude = {SecurityAutoConfiguration.class} )
@EnableScheduling
//@SpringBootApplication( )
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
