package com.riffo.users;

import com.riffo.users.entity.User;
import com.riffo.users.repository.UserRepository;
import com.riffo.users.service.UserService;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void getAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(
                new User(1L, "John Doe", "john.doe@example.com", 30),
                new User(2L, "Jane Doe", "jane.doe@example.com", 25)));

        List<User> users = userService.getAllUsers();

        assertEquals(2, users.size());
    }

    @Test
    void getById_notExisting_returnsEmpty() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<User> result = userService.getUserById(1L);

        assertEquals(Optional.empty(), result);
        verify(userRepository).findById(1L);
    }

    @Test
    void getById_existing_returnsUser() {
        User u = new User(1L, "Awa", "awa@gmail.com", 22);
        when(userRepository.findById(1L)).thenReturn(Optional.of(u));

        User result = userService.getUserById(1L).get();

        assertEquals("Awa", result.getNom());
        verify(userRepository).findById(1L);
    }
}
