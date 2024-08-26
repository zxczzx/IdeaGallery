package migatotech.ideagallery.idea;

import migatotech.ideagallery.category.CategoryEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface IdeaRepository extends PagingAndSortingRepository<IdeaEntity, Long>, CrudRepository<IdeaEntity, Long> {
    List<IdeaEntity> findAllByCategories(Set<CategoryEntity> categories, Pageable pageable);

    Optional<IdeaEntity> findByIdAndCreatorId(Long ideaId, Integer creatorId);

    List<IdeaEntity> findAllByCreatorId(Integer creatorId, Pageable pageable);
}
