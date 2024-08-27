package migatotech.ideagallery.idea;

import lombok.RequiredArgsConstructor;
import migatotech.ideagallery.category.CategoryService;
import migatotech.ideagallery.exception.DataNotFoundException;
import migatotech.ideagallery.like.LikeRepository;
import migatotech.ideagallery.poster.PosterService;
import migatotech.ideagallery.user.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IdeaService {
    private final UserService userService;
    private final IdeaRepository ideaRepository;
    private final LikeRepository likeRepository;
    private final CategoryService categoryService;
    private final PosterService posterService;

    @Transactional
    public void createIdea(NewIdea newIdea, Integer creatorId) {
        var user = userService.getUserById(creatorId.toString());
        posterService.cachePoster(newIdea.getImageUrl());
        var categories = categoryService.getAndSaveNonExistingCategories(newIdea.getCategories());
        IdeaEntity ideaEntity = newIdea.ToIdeaEntity(creatorId, user.getName(), newIdea.getImageUrl(), categories);
        ideaRepository.save(ideaEntity);
    }

    public Idea getIdea(long ideaId, String resolution, Integer userId) {
        var ideaEntity = ideaRepository.findById(ideaId).orElseThrow(() -> new DataNotFoundException("Idea not found"));
        var idea = new Idea(ideaEntity);
        var isLikedByMe = userId != null && likeRepository.findByUserIdAndIdeaId(userId, ideaId).isPresent();
        idea.setIsLikedByMe(isLikedByMe);
        idea.setImageUrl(posterService.getPosterUrl(ideaEntity.getImageUrl(), resolution));
        return idea;
    }

    public List<Idea> getAllIdeas(List<String> categories, String resolution, Integer userId, Pageable pageable) {
        List<IdeaEntity> ideaEntities;
        if (categories != null && !categories.isEmpty()) {
            ideaEntities = ideaRepository.findByCategoryName(categories, pageable);
        } else {
            ideaEntities = ideaRepository.findAll(pageable).getContent();
        }

        var idea = ideaEntities.stream().map(Idea::new).toList();
        idea.forEach(i -> {
            var isLikedByMe = userId != null && likeRepository.findByUserIdAndIdeaId(userId, i.getId()).isPresent();
            i.setImageUrl(posterService.getPosterUrl(i.getImageUrl(), resolution));
            i.setIsLikedByMe(isLikedByMe);
        });
        return idea;
    }

    public void incrementLikes(Long ideaId, Integer userId) {
        ideaRepository.findByIdAndCreatorId(ideaId, userId).ifPresent(ideaEntity -> ideaEntity.setLikes(ideaEntity.getLikes() + 1));
    }

    public void decrementLikes(Long ideaId, Integer userId) {
        ideaRepository.findByIdAndCreatorId(ideaId, userId).ifPresent(ideaEntity -> ideaEntity.setLikes(ideaEntity.getLikes() - 1));
    }

    public List<Idea> getMyIdeas(String resolution, Integer integer, Pageable pageable) {
        var ideaEntities = ideaRepository.findAllByCreatorId(integer, pageable);
        var idea = ideaEntities.stream().map(Idea::new).toList();
        idea.forEach(i -> i.setImageUrl(posterService.getPosterUrl(i.getImageUrl(), resolution)));
        return idea;
    }
}
