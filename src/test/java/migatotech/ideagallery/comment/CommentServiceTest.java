package migatotech.ideagallery.comment;

import migatotech.ideagallery.TestData;
import migatotech.ideagallery.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private UserService userService;

    @Mock
    private Clock clock;

    @InjectMocks
    private CommentService commentService;

    @Test
    void addCommentToIdea() {
        var now = Instant.now();
        var newComment = new NewComment("test comment", 1L);
        var userEntity = TestData.createUserEntity();
        var comment = TestData.createCommentEntity(now);

        when(userService.getUserById(any())).thenReturn(userEntity);
        when(clock.instant()).thenReturn(now);

        commentService.addCommentToIdea(newComment, 1);

        verify(commentRepository).save(comment);
    }

    @Test
    void getCommentsForIdea() {
        var now = Instant.now();
        var comment = TestData.createCommentEntity(now);
        var resultComment = new Comment("test comment", "username", now);

        when(commentRepository.findAllByIdeaId(any())).thenReturn(List.of(comment));

        List<Comment> comments = commentService.getCommentsForIdea(1L);

        assertThat(comments).isEqualTo(List.of(resultComment));
    }
}