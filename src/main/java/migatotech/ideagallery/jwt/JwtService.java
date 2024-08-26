package migatotech.ideagallery.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY = "ThisIsAVerySecretKeyThatWillBeSecure";
    private static final long EXPIRATION_TIME_MS = 86400000; // 1 day

    private final SecretKey key;

    public JwtService() {
        this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public Claims decodeJwt(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String createJwt(String accountId) {
        return Jwts.builder()
                .subject("user")
                .claim("accountId", accountId)
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_MS))
                .signWith(key, Jwts.SIG.HS256)
                .compact();
    }
}