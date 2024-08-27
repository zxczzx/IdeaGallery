package migatotech.ideagallery.like;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import migatotech.ideagallery.session.Session;
import migatotech.ideagallery.session.SessionHelper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {

    private final Session session;
    private final LikeService likeService;

    @Operation(summary = "Add like to idea", security = { @SecurityRequirement(name = "bearer-key") })
    @PostMapping("/{ideaId}")
    public void addLike(@PathVariable Long ideaId) {
        likeService.addLike(ideaId, SessionHelper.retrieveMandatoryAccountId(session));
    }

    @Operation(summary = "Remove like from idea", security = { @SecurityRequirement(name = "bearer-key") })
    @DeleteMapping("/{ideaId}")
    public void removeLike(@PathVariable Long ideaId) {
        likeService.removeLike(ideaId, SessionHelper.retrieveMandatoryAccountId(session));
    }
}
