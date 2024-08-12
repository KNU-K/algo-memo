package com.knuk.algo_memo.api;

import com.knuk.algo_memo.domain.auth.dto.AuthenticationToken;
import com.knuk.algo_memo.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    @PostMapping("/login")
    AuthenticationToken login(@RequestBody AuthRequestDTO requestDTO) {
        return authService.login(requestDTO.getEmail(), requestDTO.getPassword());
    }

}
