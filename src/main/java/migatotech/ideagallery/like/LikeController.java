package migatotech.ideagallery.like;

import lombok.RequiredArgsConstructor;
import migatotech.ideagallery.session.Session;
import migatotech.ideagallery.session.SessionHelper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {

    private final Session session;
    private final LikeService likeService;

    @PostMapping("/{ideaId}")
    public void addLike(Long ideaId) {
        likeService.addLike(ideaId, SessionHelper.retrieveMandatoryAccountId(session));
    }

    @DeleteMapping("/{ideaId}")
    public void removeLike(Long ideaId) {
        likeService.removeLike(ideaId, SessionHelper.retrieveMandatoryAccountId(session));
    }
}
