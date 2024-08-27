package migatotech.ideagallery.user;

import lombok.RequiredArgsConstructor;
import migatotech.ideagallery.exception.DataNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserEntity getUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new DataNotFoundException("User not found"));
    }

    public String createUser(User user) {
        var userEntity = new UserEntity();
        userEntity.setId(user.id());
        userEntity.setName(user.name());
        userEntity.setEmail(user.email());
        return userRepository.save(userEntity).getId();
    }
}
