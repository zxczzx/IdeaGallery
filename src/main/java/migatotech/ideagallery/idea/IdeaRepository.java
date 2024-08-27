package migatotech.ideagallery.idea;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IdeaRepository extends PagingAndSortingRepository<IdeaEntity, Long>, CrudRepository<IdeaEntity, Long> {

    @Query("SELECT i FROM IdeaEntity i JOIN i.categories c WHERE c.name in :categoryNames")
    List<IdeaEntity> findByCategoryName(List<String> categoryNames, Pageable pageable);

    Optional<IdeaEntity> findByIdAndCreatorId(Long ideaId, Integer creatorId);

    List<IdeaEntity> findAllByCreatorId(Integer creatorId, Pageable pageable);
}
