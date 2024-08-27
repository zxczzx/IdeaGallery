package migatotech.ideagallery.comment;

import lombok.RequiredArgsConstructor;
import migatotech.ideagallery.user.UserService;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final Clock clock;

    public void addCommentToIdea(NewComment comment, Integer userId) {
        var user = userService.getUserById(userId.toString());

        var newComment = new CommentEntity();
        newComment.setIdeaId(comment.ideaId());
        newComment.setText(comment.text());
        newComment.setUser(user);
        newComment.setCreatedAt(clock.instant());
        commentRepository.save(newComment);
    }

    public List<Comment> getCommentsForIdea(long ideaId) {
        return commentRepository.findAllByIdeaId(ideaId).stream()
                .map(ce -> new Comment(ce.getText(), ce.getUser().getName(), ce.getCreatedAt()))
                .toList();
    }
}
