package migatotech.ideagallery.idea;

import lombok.Data;
import migatotech.ideagallery.category.CategoryEntity;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class NewIdea {
    String title;
    String description;
    List<String> categories;
    String imageUrl;

    public IdeaEntity ToIdeaEntity(Integer creatorId, String creatorName, String imageUrl) {
        IdeaEntity ideaEntity = new IdeaEntity();
        ideaEntity.setCreatorId(creatorId);
        ideaEntity.setTitle(title);
        ideaEntity.setDescription(description);
        ideaEntity.setImageUrl(imageUrl);
        ideaEntity.setCreatorName(creatorName);
        ideaEntity.setCategories(categories.stream().map(c -> {
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setName(c);
            return categoryEntity;
        }).collect(Collectors.toSet()));
        return ideaEntity;
    }
}
