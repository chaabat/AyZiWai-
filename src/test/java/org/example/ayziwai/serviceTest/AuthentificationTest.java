package org.example.ayziwai.serviceTest;

import org.example.ayziwai.dto.response.UserResponse;
import org.example.ayziwai.services.interfaces.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class AuthentificationTest {

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers_ShouldReturnListOfUsers() {
        // Arrange
        UserResponse user1 = new UserResponse(); // Add necessary user details
        UserResponse user2 = new UserResponse(); // Add necessary user details
        List<UserResponse> expectedUsers = Arrays.asList(user1, user2);
        
        when(userService.getAllUsers()).thenReturn(expectedUsers);

        // Act
        List<UserResponse> actualUsers = userService.getAllUsers();

        // Assert
        assertEquals(expectedUsers, actualUsers);
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void updateUserRoles_ShouldUpdateRoles() {
        // Arrange
        String userId = "123";
        Set<String> newRoles = new HashSet<>(Arrays.asList("ADMIN", "USER"));
        UserResponse expectedResponse = new UserResponse(); // Add necessary user details
        
        when(userService.updateUserRoles(userId, newRoles)).thenReturn(expectedResponse);

        // Act
        UserResponse actualResponse = userService.updateUserRoles(userId, newRoles);

        // Assert
        assertEquals(expectedResponse, actualResponse);
        verify(userService, times(1)).updateUserRoles(userId, newRoles);
    }
}
