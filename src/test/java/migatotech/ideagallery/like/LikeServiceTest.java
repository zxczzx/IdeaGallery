package migatotech.ideagallery.like;

import migatotech.ideagallery.idea.IdeaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LikeServiceTest {

    @Mock
    private LikeRepository likeRepository;

    @Mock
    private IdeaService ideaService;

    @InjectMocks
    private LikeService likeService;

    @Test
    void addLike() {
        var likeEntity = new LikeEntity(1L, 1);

        when(likeRepository.findByUserIdAndIdeaId(any(), any())).thenReturn(Optional.empty());

        likeService.addLike(1L, 1);

        verify(likeRepository).save(any());
        verify(ideaService).incrementLikes(1L, 1);
    }

    @Test
    void addTwoLikes() {
        var likeEntity = new LikeEntity(1L, 1);

        when(likeRepository.findByUserIdAndIdeaId(any(), any())).thenReturn(Optional.of(likeEntity));

        likeService.addLike(1L, 1);

        verify(likeRepository, never()).save(any());
        verifyNoInteractions(ideaService);
    }

    @Test
    void removeLike() {
        var likeEntity = new LikeEntity(1L, 1);

        when(likeRepository.findByUserIdAndIdeaId(any(), any())).thenReturn(Optional.of(likeEntity));

        likeService.removeLike(1L, 1);

        verify(likeRepository).deleteByIdeaIdAndUserId(any(), any());
        verify(ideaService).decrementLikes(1L, 1);
    }

    @Test
    void removeLikeTwice() {
        var likeEntity = new LikeEntity(1L, 1);

        when(likeRepository.findByUserIdAndIdeaId(any(), any())).thenReturn(Optional.empty());

        likeService.removeLike(1L, 1);

        verify(likeRepository, never()).deleteByIdeaIdAndUserId(any(), any());
        verifyNoInteractions(ideaService);
    }

    @Test
    void isLiked() {
        when(likeRepository.findByUserIdAndIdeaId(any(), any())).thenReturn(Optional.empty());

        var result = likeService.isLiked(1L, 1);

        assertThat(result).isFalse();
    }

    @Test
    void isLikedExists() {
        when(likeRepository.findByUserIdAndIdeaId(any(), any())).thenReturn(Optional.of(new LikeEntity(1L, 1)));

        var result = likeService.isLiked(1L, 1);

        assertThat(result).isTrue();
    }
}