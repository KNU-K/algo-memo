package com.knuk.algo_memo.domain.user.dto;

import com.knuk.algo_memo.domain.user.model.User;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CreateUserCommand {
    private String name;
    private String email;
    private String nickName;
    private String password;

    public User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .nickName(nickName)
                .password(password)
                .build();
    }

}
