package com.knuk.algo_memo.domain.group.repository;

import com.knuk.algo_memo.domain.group.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
