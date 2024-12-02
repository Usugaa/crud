package com.api.crud.services;

import com.api.crud.models.UserModel;
import com.api.crud.repositories.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserService userService;
    private IUserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(IUserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    void testGetUsers() {
        ArrayList<UserModel> users = new ArrayList<>();
        UserModel user = new UserModel();
        user.setFirstName("Carlos");
        user.setLastName("Restrepo");
        user.setEmail("carlos@gmail.com");
        assertTrue(users.add(user));

        when(userRepository.findAll()).thenReturn(users);

        ArrayList<UserModel> result = userService.getUsers();

        assertEquals(1, result.size());
        assertEquals("Carlos", result.get(0).getFirstName());
    }


    @Test
    void testSaveUser() {
        UserModel user = new UserModel();
        user.setFirstName("Carlos");
        user.setLastName("Restrepo");
        user.setEmail("carlos@gmail.com");

        when(userRepository.save(user)).thenReturn(user);

        UserModel result = userService.saveUser(user);

        assertNotNull(result);
        assertEquals("Carlos", result.getFirstName());
    }

    @Test
    void testGetById() {
        UserModel user = new UserModel();
        user.setId(1L);
        user.setFirstName("Carlos");
        user.setLastName("Restrepo");
        user.setEmail("carlos@gmail.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<UserModel> result = userService.getById(1L);

        assertTrue(result.isPresent());
        assertEquals("Carlos", result.get().getFirstName());
    }

    @Test
    void testDeleteUser() {
        long userId = 1L;
        doNothing().when(userRepository).deleteById(userId);

        boolean result = userService.deleteUser(userId);

        assertTrue(result);
        verify(userRepository, times(1)).deleteById(userId);
    }
}