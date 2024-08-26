package migatotech.ideagallery.like;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends CrudRepository<LikeEntity, Long> {
    Optional<LikeEntity> findByUserIdAndIdeaId(Integer userId, Long ideaId);
    void deleteByIdeaIdAndUserId(Long ideaId, Integer userId);
}
