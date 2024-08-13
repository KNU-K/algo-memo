package com.knuk.algo_memo.domain.group;

import com.knuk.algo_memo.domain.group.dto.GroupDTO;
import com.knuk.algo_memo.domain.group.model.Group;
import com.knuk.algo_memo.domain.group.repository.GroupRepository;
import com.knuk.algo_memo.domain.group.service.GroupServiceImpl;
import com.knuk.algo_memo.domain.user.model.User;
import com.knuk.algo_memo.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class GroupServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private GroupServiceImpl groupService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Mockito 초기화
    }

    @Nested
    @DisplayName("그룹을 만들 때,")
    class WhenToCreateGroupTest {

        @Test
        @DisplayName("유저를 찾을 수 없을 시 예외 발생")
        public void thenNotFoundUserTest() {
            // Given
            Long invalidUserId = 1L;
            when(userRepository.findById(invalidUserId)).thenReturn(Optional.empty());

            // When & Then
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                groupService.createGroup(invalidUserId, "1번방");
            });

            // Assert
            assertEquals("사용자가 존재하지 않습니다.", thrown.getMessage());
        }

        @Test
        @DisplayName("성공적으로 그룹 생성")
        public void thenSucceedToCreateGroupTest() {
            // Given
            Long validUserId = 1L;
            User user = User.builder()
                    .id(validUserId)
                    .nickName("testNick")
                    .password("testPassword")
                    .name("testName")
                    .email("email")
                    .build();
            when(userRepository.findById(validUserId)).thenReturn(Optional.of(user));

            // Mocking groupRepository.save to return a Group with an ID
            Group savedGroup = Group.builder()
                    .id(1L)
                    .name("1번방")
                    .owner(user)
                    .build();
            when(groupRepository.save(any(Group.class))).thenReturn(savedGroup);

            // When
            GroupDTO groupDTO = groupService.createGroup(validUserId, "1번방");

            // Then
            assertEquals("1번방", groupDTO.getName());
            assertEquals(validUserId, groupDTO.getOwner().getId());
            verify(groupRepository).save(any(Group.class)); // Verify save was called
        }
    }
}
