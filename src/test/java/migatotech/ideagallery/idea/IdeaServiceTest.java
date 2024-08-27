package migatotech.ideagallery.idea;

import migatotech.ideagallery.TestData;
import migatotech.ideagallery.category.CategoryService;
import migatotech.ideagallery.like.LikeRepository;
import migatotech.ideagallery.poster.PosterService;
import migatotech.ideagallery.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IdeaServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private IdeaRepository ideaRepository;

    @Mock
    private LikeRepository likeRepository;

    @Mock
    private CategoryService categoryService;

    @Mock
    private PosterService posterService;

    @InjectMocks
    private IdeaService ideaService;

    @Test
    void createIdea() {
        var newIdea = TestData.createNewIdea();
        var userEntity = TestData.createUserEntity();
        var categoryEntity = TestData.createCategoryEntity();

        var ideaEntity = new IdeaEntity();
        ideaEntity.setTitle("New Title");
        ideaEntity.setDescription("New Description");
        ideaEntity.setImageUrl(newIdea.getImageUrl());
        ideaEntity.setCategories(Set.of(categoryEntity));
        ideaEntity.setCreatorId(Integer.parseInt(userEntity.getId()));
        ideaEntity.setCreatorName(userEntity.getName());
        ideaEntity.setLikes(0);

        when(ideaRepository.save(any())).thenReturn(ideaEntity);
        when(userService.getUserById(any())).thenReturn(userEntity);
        when(categoryService.getAndSaveNonExistingCategories(any())).thenReturn(Set.of(categoryEntity));

        ideaService.createIdea(newIdea, 1);

        verify(ideaRepository).save(ideaEntity);
    }

    @Test
    void getIdea() {
        when(ideaRepository.findById(any())).thenReturn(Optional.of(TestData.createIdeaEntity()));
        when(posterService.getPosterUrl(any(), any())).thenReturn("https://example.com/image.png");

        var idea = new Idea();
        idea.setId(1L);
        idea.setTitle("New Title");
        idea.setDescription("New Description");
        idea.setImageUrl("https://example.com/image.png");
        idea.setCategories(List.of("category1"));
        idea.setLikes(0);
        idea.setIsLikedByMe(false);

        var result = ideaService.getIdea(1L, "400x400", null);

        assertThat(result).isEqualTo(idea);
    }

    @Test
    void getAllIdeas() {
        when(ideaRepository.findByCategoryName(any(), any())).thenReturn(List.of(TestData.createIdeaEntity()));
        when(posterService.getPosterUrl(any(), any())).thenReturn("https://example.com/image.png");

        var idea = new Idea();
        idea.setId(1L);
        idea.setTitle("New Title");
        idea.setDescription("New Description");
        idea.setImageUrl("https://example.com/image.png");
        idea.setCategories(List.of("category1"));
        idea.setLikes(0);
        idea.setIsLikedByMe(false);

        var result = ideaService.getAllIdeas(List.of("category1"), "400x400", null, PageRequest.of(0, 10));

        assertThat(result).isEqualTo(List.of(idea));
    }

    @Test
    void incrementLikes() {
        var idea = TestData.createIdeaEntity();
        when(ideaRepository.findByIdAndCreatorId(any(), any())).thenReturn(Optional.of(idea));

        ideaService.incrementLikes(1L, 1);

        verify(ideaRepository).findByIdAndCreatorId(1L, 1);
        assertThat(idea.getLikes()).isEqualTo(1);
    }

    @Test
    void decrementLikes() {
        var idea = TestData.createIdeaEntity();
        idea.setLikes(1);
        when(ideaRepository.findByIdAndCreatorId(any(), any())).thenReturn(Optional.of(idea));

        ideaService.decrementLikes(1L, 1);

        verify(ideaRepository).findByIdAndCreatorId(1L, 1);
        assertThat(idea.getLikes()).isEqualTo(0);
    }

    @Test
    void getMyIdeas() {
        when(ideaRepository.findAllByCreatorId(any(), any())).thenReturn(List.of(TestData.createIdeaEntity()));
        when(posterService.getPosterUrl(any(), any())).thenReturn("https://example.com/image.png");

        var idea = new Idea();
        idea.setId(1L);
        idea.setTitle("New Title");
        idea.setDescription("New Description");
        idea.setImageUrl("https://example.com/image.png");
        idea.setCategories(List.of("category1"));
        idea.setLikes(0);

        var result = ideaService.getMyIdeas("400x400", null, PageRequest.of(0, 10));

        assertThat(result).isEqualTo(List.of(idea));
    }
}