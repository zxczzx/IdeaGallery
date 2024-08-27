package migatotech.ideagallery.forTesting;

import lombok.RequiredArgsConstructor;
import migatotech.ideagallery.jwt.JwtService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
@RequiredArgsConstructor
@ConditionalOnExpression("'${environment}'.equals('test')")
public class SessionController {
    private final JwtService jwtService;

    @GetMapping
    public String getSession(@RequestParam String userId) {
        return jwtService.createJwt(userId);
    }
}
