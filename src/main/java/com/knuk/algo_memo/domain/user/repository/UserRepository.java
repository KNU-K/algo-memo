package com.knuk.algo_memo.domain.user.repository;

import com.knuk.algo_memo.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
