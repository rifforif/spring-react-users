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

}

