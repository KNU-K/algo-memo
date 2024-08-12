package com.knuk.algo_memo.api;

import com.knuk.algo_memo.domain.user.dto.CreateUserCommand;
import com.knuk.algo_memo.domain.user.dto.UserDTO;
import com.knuk.algo_memo.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping()
    public UserDTO createUser(@RequestBody CreateUserCommand userCommand){
        return userService.createUser(userCommand);
    }

}
