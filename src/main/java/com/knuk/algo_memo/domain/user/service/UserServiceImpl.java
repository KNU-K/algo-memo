package com.knuk.algo_memo.domain.user.service;

import com.knuk.algo_memo.domain.user.dto.CreateUserCommand;
import com.knuk.algo_memo.domain.user.dto.UserDTO;
import com.knuk.algo_memo.domain.user.model.User;
import com.knuk.algo_memo.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    final private UserRepository userRepository;

    @Override
    public UserDTO findByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        return new UserDTO(user);
    }

    @Override
    public UserDTO createUser(CreateUserCommand userCommand) {
        User user = userCommand.toEntity();
        User newUser = userRepository.save(user);
        return new UserDTO(newUser);
    }
}
