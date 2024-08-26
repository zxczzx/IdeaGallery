package migatotech.ideagallery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class IdeaGalleryApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdeaGalleryApplication.class, args);
    }

}
