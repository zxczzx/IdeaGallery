package migatotech.ideagallery.like;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_likes")
public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long ideaId;
    private Integer userId;

    public LikeEntity() {}

    public LikeEntity(Long ideaId, Integer userId) {
        this.ideaId = ideaId;
        this.userId = userId;
    }
}
