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

        var commentEntity = new CommentEntity();
        commentEntity.setIdeaId(comment.ideaId());
        commentEntity.setText(comment.text());
        commentEntity.setUser(user);
        commentEntity.setCreatedAt(clock.instant());
        commentRepository.save(commentEntity);
    }

    public List<Comment> getCommentsForIdea(Long ideaId) {
        return commentRepository.findAllByIdeaId(ideaId).stream()
                .map(ce -> new Comment(ce.getText(), ce.getUser().getName(), ce.getCreatedAt()))
                .toList();
    }
}
