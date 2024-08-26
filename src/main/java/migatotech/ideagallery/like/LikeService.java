package migatotech.ideagallery.like;

import lombok.AllArgsConstructor;
import migatotech.ideagallery.idea.IdeaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final IdeaService ideaService;

    @Transactional
    public void addLike(Long ideaId, Integer userId) {
        likeRepository.save(new LikeEntity(ideaId, userId));
        ideaService.incrementLikes(ideaId, userId);
    }

    @Transactional
    public void removeLike(Long ideaId, Integer userId) {
        likeRepository.deleteByIdeaIdAndUserId(ideaId, userId);
        ideaService.decrementLikes(ideaId, userId);
    }

    public boolean isLiked(Long ideaId, Integer userId) {
        return likeRepository.findByUserIdAndIdeaId(userId, ideaId).isPresent();
    }
}
