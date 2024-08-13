package com.knuk.algo_memo.domain.user.dto;

import com.knuk.algo_memo.domain.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Builder
@AllArgsConstructor
@Data
public class UserDTO {
    private final Long id;
    private final String email;
    private final String name;
    private final String nickName;
    private final String password;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.password = user.getPassword();
        this.nickName = user.getNickName();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }


}
