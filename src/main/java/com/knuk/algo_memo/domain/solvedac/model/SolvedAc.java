package com.knuk.algo_memo.domain.solvedac.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity(name = "solved_ac")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SolvedAc {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    String count;
//    List<ProblemDTO> items;

}
