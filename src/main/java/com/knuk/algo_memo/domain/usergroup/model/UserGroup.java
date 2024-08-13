package com.knuk.algo_memo.domain.usergroup.model;

import com.knuk.algo_memo.domain.group.model.Group;
import com.knuk.algo_memo.domain.user.model.User;
import com.knuk.algo_memo.global.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "user_group")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserGroup extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "group_id")
    Group group;

}
