package com.knuk.algo_memo.api.dto;


import lombok.Data;

@Data
public class AuthRequestDTO {
    private String email;
    private String password;

    // Getters and Setters
}
