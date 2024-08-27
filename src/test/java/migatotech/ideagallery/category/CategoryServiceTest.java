package migatotech.ideagallery.category;

import migatotech.ideagallery.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void getAllCategories() {
        var name = "category1";
        var categoryEntity = TestData.createCategoryEntity();

        when(categoryRepository.findAll()).thenReturn(List.of(categoryEntity));

        var categories = categoryService.getAllCategories();

        assertThat(categories).hasSize(1);
        assertThat(categories.getFirst().name()).isEqualTo(name);
    }

    @Test
    void getAndSaveNonExistingCategories() {
        Set<String> categories = Set.of("category1", "category2");
        var categoryEntity = TestData.createCategoryEntity(1, "category1");
        var categoryEntity2 = TestData.createCategoryEntity(2, "category2");

        when(categoryRepository.findByName(any())).thenReturn(Optional.of(categoryEntity))
                .thenReturn(Optional.of(categoryEntity2));

        var result = categoryService.getAndSaveNonExistingCategories(categories);

        assertThat(result).hasSize(2);
        assertThat(result).isEqualTo(Set.of(categoryEntity, categoryEntity2));
        verify(categoryRepository, times(2)).findByName(any());
        verify(categoryRepository, never()).save(any());
    }

    @Test
    void saveNonExistingCategories() {
        Set<String> categories = Set.of("category1", "category2");
        var categoryEntity = TestData.createCategoryEntity(1, "category1");
        var categoryEntity2 = TestData.createCategoryEntity(2, "category2");

        when(categoryRepository.save(any())).thenReturn(categoryEntity).thenReturn(categoryEntity2);
        when(categoryRepository.findByName(any())).thenReturn(Optional.empty());

        var result = categoryService.getAndSaveNonExistingCategories(categories);

        assertThat(result).hasSize(2);
        assertThat(result).isEqualTo(Set.of(categoryEntity, categoryEntity2));
        verify(categoryRepository, times(2)).findByName(any());
        verify(categoryRepository, times(2)).save(any());
    }
}