package com.gfa.foxbook.foxbook.integrationTests;

import com.gfa.foxbook.foxbook.controllers.UserController;
import com.gfa.foxbook.foxbook.models.User;
import com.gfa.foxbook.foxbook.models.dtos.UserProfileDTO;
import com.gfa.foxbook.foxbook.repositories.LikeRepository;
import com.gfa.foxbook.foxbook.security.jwt.JwtUtils;
import com.gfa.foxbook.foxbook.services.interfaces.PostService;
import com.gfa.foxbook.foxbook.services.interfaces.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private JwtUtils jwtUtils;
    @Mock
    private LikeRepository likeRepository;
    @Mock
    private UserService userService;
    @Mock
    private PostService postService;

    private UserController userController;

    @BeforeEach
    public void setUp() {
        userController = new UserController(userService, jwtUtils, postService, null, null, null, likeRepository);
    }

    @Test
    public void testGetUser_ValidUser_ReturnsUserProfileDTO() {
        // Mocking
        HttpServletRequest request = new MockHttpServletRequest();
        User user = new User();
        when(jwtUtils.getUserFromRequest(request)).thenReturn(user);
        UserProfileDTO expected = new UserProfileDTO(user);

        // Test
        ResponseEntity<?> response = userController.getUser(request);

        // Assertion
        assertEquals(expected, response.getBody());
    }

    // Other tests...

    @Configuration
    static class TestConfig {
        @Bean
        public UserService userService() {
            return mock(UserService.class);
        }
    }
}
