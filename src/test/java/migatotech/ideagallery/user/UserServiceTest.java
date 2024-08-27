package migatotech.ideagallery.user;

import migatotech.ideagallery.TestData;
import migatotech.ideagallery.exception.DataNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void getUserByIdNotFound() {
        when(userRepository.findById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getUserById("1"))
                .isInstanceOf(DataNotFoundException.class)
                .hasMessage("User not found");
    }

    @Test
    void createUser() {
        var user = new User("1", "username", "email");
        var userEntity = TestData.createUserEntity();
        userEntity.setEmail("email");

        when(userRepository.save(any())).thenReturn(userEntity);

        var userId = userService.createUser(user);

        assertThat(userId).isEqualTo("1");
        verify(userRepository).save(userEntity);
    }
}