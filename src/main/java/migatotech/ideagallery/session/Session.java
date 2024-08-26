package migatotech.ideagallery.session;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Data
@NoArgsConstructor
@Component
@RequestScope
public class Session {

    public static final String ACCOUNT_CLAIM = "accountId";

    private Map<String, List<String>> claims;


    public Optional<Integer> accountId() {
        return Optional.ofNullable(claims)
                .map(c -> c.get(ACCOUNT_CLAIM))
                .map(ids -> Integer.parseInt(ids.getFirst()));
    }

    private Optional<List<String>> getClaimAsList(String claim) {
        return Optional.ofNullable(claims).map(c -> c.get(claim));
    }
}

