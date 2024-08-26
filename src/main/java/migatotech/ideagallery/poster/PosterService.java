package migatotech.ideagallery.poster;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PosterService {

    private final ImgProxyClient imgProxyClient;

    public String getPosterUrl(String posterUrl, String resolution) {
        return String.format("http://localhost:18080/unsafe/%s/plain/%s", resolution, posterUrl);
    }

    public void cachePoster(String imageUrl) {
        // web
        imgProxyClient.resizeImage("800x800", imageUrl);
        // mobile
        imgProxyClient.resizeImage("400x400", imageUrl);
    }
}
