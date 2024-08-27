package migatotech.ideagallery.comment;

import java.time.Instant;

public record Comment(String text, String userName, Instant createdAt) {
}
