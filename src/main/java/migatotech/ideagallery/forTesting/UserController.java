package migatotech.ideagallery.forTesting;

import lombok.RequiredArgsConstructor;
import migatotech.ideagallery.user.User;
import migatotech.ideagallery.user.UserService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@ConditionalOnExpression("'${environment}'.equals('test')")
public class UserController {
    private final UserService userService;

    @PostMapping
    public String createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
}
