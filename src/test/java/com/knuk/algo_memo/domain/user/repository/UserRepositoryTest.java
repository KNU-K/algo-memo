package com.knuk.algo_memo.domain.user.repository;

import com.knuk.algo_memo.domain.user.model.User;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@DisplayName("UserRepository 테스트")
@Slf4j

public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Nested
    @DisplayName("User 저장 작업을 진행")
    @Transactional
    class SaveUserWithUserRepository {
        @Test
        @DisplayName("정상적으로 저장되었을 떄")
        void succeedToSaveUser() {
            //given
            User user = User.builder()
                    .email("k_anon3747")
                    .name("test")
                    .nickName("test")
                    .build();

            //when
            User foundUser = userRepository.save(user);

            //then
            Assertions.assertEquals(user.getEmail(), foundUser.getEmail());
            Assertions.assertEquals(user.getName(), foundUser.getName());
            Assertions.assertEquals(user.getNickName(), foundUser.getNickName());
        }
        @Test
        @DisplayName("비정상적으로 저장되었을 때")
        void failToSaveUser() {
            // Given
            User user = User.builder()
                    .email("k_anon3747")
                    .nickName("test")
                    .build();

            // When & Then
            Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
                userRepository.save(user);
            });
        }
    }

    @Nested
    @DisplayName("User 조회 작업을 진행")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class FindUserWithUserRepository {

        User user;

        @BeforeAll
        void beforeAll() {
            user = userRepository.save(
                    User.builder()
                            .email("k_anon3747")
                            .name("test")
                            .nickName("test")
                            .build());
            log.info("유저 엔티티 빌드 완료");
        }

        @Test
        @DisplayName("유저가 있을 떄 - findById")
        void succeedToFindById() {
            // when
            Optional<User> foundUser = userRepository.findById(user.getId());
            log.info("Found User: {}", foundUser);
            // then
            Assertions.assertTrue(foundUser.isPresent());
            Assertions.assertEquals(user, foundUser.get());
        }

        @Test
        @DisplayName("유저가 없을 때 - findById")
        void failToFindById() {
            // when
            Optional<User> foundUser = userRepository.findById(user.getId() + 1L);
            log.info("Found User: {}", foundUser);
            // then
            Assertions.assertFalse(foundUser.isPresent());
        }
    }
}
