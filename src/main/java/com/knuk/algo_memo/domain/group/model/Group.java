package com.knuk.algo_memo.domain.group.model;

import com.knuk.algo_memo.domain.group.dto.GroupDTO;
import com.knuk.algo_memo.domain.user.model.User;
import com.knuk.algo_memo.global.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity(name = "groups")
@Builder
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class Group extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;  // 그룹의 주인을 나타내는 필드

    @Column(nullable = false)
    String name;

    public void addOwner(User owner){
        this.owner = owner;
    }

    public GroupDTO toDTO(){
        return GroupDTO.builder()
                .id(id)
                .name(name)
                .owner(owner.toDTO())
                .build();
    }
}
