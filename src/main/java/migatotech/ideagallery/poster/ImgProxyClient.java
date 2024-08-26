package migatotech.ideagallery.poster;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "my-service", url = "http://localhost:18080")
public interface ImgProxyClient {

    @GetMapping("/unsafe/{resolution}/plain/{imageUrl}")
    String resizeImage(@PathVariable String resolution, @PathVariable String imageUrl);
}
