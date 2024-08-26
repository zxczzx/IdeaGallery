package migatotech.ideagallery.comment;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity, Long> {
    @Query("SELECT c FROM CommentEntity c JOIN FETCH c.user WHERE c.ideaId = :ideaId order by c.createdAt DESC")
    List<CommentEntity> findAllByIdeaId(@Param("ideaId") Long ideaId);
}
