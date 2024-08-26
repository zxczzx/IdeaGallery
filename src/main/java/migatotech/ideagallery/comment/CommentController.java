package migatotech.ideagallery.comment;

import lombok.RequiredArgsConstructor;
import migatotech.ideagallery.session.Session;
import migatotech.ideagallery.session.SessionHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final Session session;
    private final CommentService commentService;

    @PostMapping
    public void addCommentToIdea(@RequestBody NewComment comment) {
        commentService.addCommentToIdea(comment, SessionHelper.retrieveMandatoryAccountId(session));
    }

    @GetMapping("/{ideaId}")
    public List<Comment> getCommentsForIdea(@PathVariable long ideaId) {
        return commentService.getCommentsForIdea(ideaId);
    }
}
