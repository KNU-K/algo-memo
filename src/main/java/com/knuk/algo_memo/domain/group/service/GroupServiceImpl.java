package com.knuk.algo_memo.domain.group.service;

import com.knuk.algo_memo.domain.group.dto.GroupDTO;
import com.knuk.algo_memo.domain.group.model.Group;
import com.knuk.algo_memo.domain.group.repository.GroupRepository;
import com.knuk.algo_memo.domain.user.dto.UserDTO;
import com.knuk.algo_memo.domain.user.model.User;
import com.knuk.algo_memo.domain.user.repository.UserRepository;
import com.knuk.algo_memo.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService{

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    @Override
    public GroupDTO createGroup(Long id,String name) {
        /**
         * AOP를 통해서 인증 Annotation 만들고, parsing
          */
        User user = userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("사용자가 존재하지 않습니다."));
        Group group = Group.builder()
                        .owner(user)
                        .name(name)
                        .build();

        Group savedGroup = groupRepository.save(group);


        return new GroupDTO(savedGroup);
    }
}
