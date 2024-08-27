package migatotech.ideagallery.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return StreamSupport.stream(categoryRepository.findAll().spliterator(), false)
                .map(categoryEntity -> new Category(categoryEntity.getName()))
                .toList();
    }

    public Set<CategoryEntity> getAndSaveNonExistingCategories(Set<String> categories) {
        return categories.stream().map(c -> categoryRepository.findByName(c).orElseGet(() -> {
            var categoryEntity = new CategoryEntity();
            categoryEntity.setName(c);
            return categoryRepository.save(categoryEntity);
        })).collect(Collectors.toSet());
    }
}
