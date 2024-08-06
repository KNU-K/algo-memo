package com.knuk.algo_memo.domain.group.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Entity(name = "groups")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;
}
