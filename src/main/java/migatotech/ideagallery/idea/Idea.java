package migatotech.ideagallery.idea;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import migatotech.ideagallery.category.CategoryEntity;

import java.util.List;

@Data
@NoArgsConstructor
public class Idea {
    @JsonIgnore Long id;
    String title;
    String description;
    List<String> categories;
    Integer likes;
    Boolean isLikedByMe;
    String imageUrl;

    public Idea(IdeaEntity ideaEntity) {
        this.id = ideaEntity.getId();
        this.title = ideaEntity.getTitle();
        this.description = ideaEntity.getDescription();
        this.categories = ideaEntity.getCategories().stream().map(CategoryEntity::getName).toList();
        this.likes = ideaEntity.getLikes();
        this.imageUrl = ideaEntity.getImageUrl();
    }
}
