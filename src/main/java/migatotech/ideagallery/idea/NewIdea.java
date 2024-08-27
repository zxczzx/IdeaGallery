package migatotech.ideagallery.idea;

import lombok.Data;
import migatotech.ideagallery.category.CategoryEntity;

import java.util.Set;

@Data
public class NewIdea {
    String title;
    String description;
    Set<String> categories;
    String imageUrl;

    public IdeaEntity ToIdeaEntity(Integer creatorId, String creatorName, String imageUrl, Set<CategoryEntity> categories) {
        IdeaEntity ideaEntity = new IdeaEntity();
        ideaEntity.setCreatorId(creatorId);
        ideaEntity.setTitle(title);
        ideaEntity.setDescription(description);
        ideaEntity.setImageUrl(imageUrl);
        ideaEntity.setCreatorName(creatorName);
        ideaEntity.setCategories(categories);
        ideaEntity.setLikes(0);
        return ideaEntity;
    }
}
