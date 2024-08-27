package migatotech.ideagallery.jwt;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class JwtServiceTest {

    @InjectMocks
    private JwtService service;

    @Test
    void decodeJwtTest() {
        var token = service.createJwt("1");
        Claims claims = service.decodeJwt(token);
        assertThat(claims.getSubject()).isEqualTo("user");
        assertThat(claims.get("accountId", String.class)).isEqualTo("1");
    }

    @Test
    void createJwtTest() {
        String jwt = service.createJwt("1");

        System.out.println(jwt);
        assertThat(jwt).isNotNull();
    }
}