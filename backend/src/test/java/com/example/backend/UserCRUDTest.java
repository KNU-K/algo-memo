package com.example.backend;

import com.example.backend.domain.IncorrectAlgorithm;
import com.example.backend.domain.User;
import com.example.backend.repository.IncorrectAlgorithmRepository;
import com.example.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class UserCRUDTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    IncorrectAlgorithmRepository incorrectAlgorithmRepository;
    @Test
    @DisplayName("Create User Test")
    void tryCreatingUserTest(){
        User newUser = new User("asd","asd");
        userRepository.saveAndFlush(newUser);
        Optional<User> foundUser = userRepository.findById(1L);
        assertThat(foundUser).isNotEmpty();
        assertThat(foundUser.get()).isEqualTo(newUser);
    }

    @Test
    @DisplayName("Post Test")
    void tryIncorrectAlgoAndUser(){
        User newUser = new User("asd","asd");
        IncorrectAlgorithm incorrectAlgorithm = new IncorrectAlgorithm("asd","asd", newUser);

        userRepository.saveAndFlush(newUser);
        Optional<User> foundUser = userRepository.findById(1L);

        incorrectAlgorithmRepository.saveAndFlush(incorrectAlgorithm);


        assertThat(foundUser).isNotEmpty();
        assertThat(foundUser.get()).isEqualTo(newUser);
        assertThat(foundUser.get().getIncorrectAlgorithm().get(0)).isEqualTo(incorrectAlgorithm);

    }



}
