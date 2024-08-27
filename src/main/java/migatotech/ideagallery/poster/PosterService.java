package migatotech.ideagallery.poster;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PosterService {

    private final ThumborClient thumborClient;

    public String getPosterUrl(String posterUrl, String resolution) {
        return String.format("http://localhost:8888/unsafe/%s/%s", resolution, posterUrl);
    }

    public void cachePoster(String imageUrl) {
        // web
        thumborClient.resizeImage(300, 400, imageUrl);
        // mobile
        thumborClient.resizeImage(300, 400, imageUrl);
    }
}
