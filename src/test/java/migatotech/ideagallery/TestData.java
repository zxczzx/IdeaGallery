package migatotech.ideagallery;

import migatotech.ideagallery.category.CategoryEntity;
import migatotech.ideagallery.comment.CommentEntity;
import migatotech.ideagallery.idea.IdeaEntity;
import migatotech.ideagallery.idea.NewIdea;
import migatotech.ideagallery.user.UserEntity;

import java.time.Instant;
import java.util.Set;

public class TestData {

    public static NewIdea createNewIdea() {
        var newIdea = new NewIdea();
        newIdea.setTitle("New Title");
        newIdea.setDescription("New Description");
        newIdea.setImageUrl("https://www.image.com");
        newIdea.setCategories(Set.of("category1"));
        return newIdea;
    }

    public static UserEntity createUserEntity() {
        var userEntity = new UserEntity();
        userEntity.setId("1");
        userEntity.setName("username");
        return userEntity;
    }

    public static CategoryEntity createCategoryEntity() {
        var categoryEntity = new CategoryEntity();
        categoryEntity.setId(1);
        categoryEntity.setName("category1");
        return categoryEntity;
    }

    public static CategoryEntity createCategoryEntity(Integer id, String name) {
        var categoryEntity = new CategoryEntity();
        categoryEntity.setId(id);
        categoryEntity.setName(name);
        return categoryEntity;
    }

    public static CommentEntity createCommentEntity(Instant now) {
        var comment = new CommentEntity();
        comment.setIdeaId(1L);
        comment.setText("test comment");
        comment.setUser(createUserEntity());
        comment.setCreatedAt(now);
        return comment;
    }

    public static IdeaEntity createIdeaEntity() {
        var ideaEntity = new IdeaEntity();
        ideaEntity.setId(1L);
        ideaEntity.setTitle("New Title");
        ideaEntity.setDescription("New Description");
        ideaEntity.setImageUrl("https://www.image.com");
        ideaEntity.setCategories(Set.of(createCategoryEntity()));
        ideaEntity.setCreatorId(1);
        ideaEntity.setCreatorName("username");
        ideaEntity.setLikes(0);
        return ideaEntity;
    }
}
