package migatotech.ideagallery.poster;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "thumbor", url = "http://localhost:8888")
public interface ThumborClient {

    @GetMapping("/unsafe/{width}x{height}/{imageUrl}")
    String resizeImage(@PathVariable Integer width, @PathVariable Integer height, @PathVariable String imageUrl);
}
