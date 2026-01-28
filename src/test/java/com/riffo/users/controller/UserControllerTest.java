package com.riffo.users.controller;

import com.riffo.users.controller.UserController;
import com.riffo.users.entity.User;
import com.riffo.users.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void getAllUsers_returnsUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(List.of(
                new User(1L, "Awa", "[EMAIL_ADDRESS]", 22),
                new User(2L, "Moussa", "[EMAIL_ADDRESS]", 25)));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nom").value("Awa"))
                .andExpect(jsonPath("$[1].nom").value("Moussa"));
    }

    @Test
    void getById_existing_returnsUser() throws Exception {
        when(userService.getUserById(1L)).thenReturn(Optional.of(new User(1L, "Awa", "[EMAIL_ADDRESS]", 22)));

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Awa"));
    }

    @Test
    void getById_notExisting_returnsNotFound() throws Exception {
        when(userService.getUserById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isNotFound());
    }
}
