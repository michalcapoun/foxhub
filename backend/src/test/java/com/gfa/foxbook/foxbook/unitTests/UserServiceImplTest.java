package com.gfa.foxbook.foxbook.unitTests;

import com.gfa.foxbook.foxbook.models.User;
import com.gfa.foxbook.foxbook.repositories.LanguageRepository;
import com.gfa.foxbook.foxbook.repositories.TechnologyRepository;
import com.gfa.foxbook.foxbook.repositories.UserRepository;
import com.gfa.foxbook.foxbook.security.jwt.JwtUtils;
import com.gfa.foxbook.foxbook.services.UserServiceImpl;
import com.gfa.foxbook.foxbook.services.interfaces.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class UserServiceImplTest {

    @Mock
    UserRepository userRepository;
    TechnologyRepository technologyRepository;
    LanguageRepository languageRepository;
    JwtUtils jwtUtils;
    UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository,
                technologyRepository,
                languageRepository,
                jwtUtils);
    }

    @Test
    public void SaveUser() {
        //arrange
        User user = new User();
        //act
        userService.saveUser(user);
        //assert
        verify(userRepository).save(user);
    }

}
