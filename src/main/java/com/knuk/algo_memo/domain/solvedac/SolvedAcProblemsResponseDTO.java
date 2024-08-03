package com.knuk.algo_memo.domain.solvedac;

import lombok.Data;

import java.util.List;

@Data
public class SolvedAcProblemsResponseDTO {
    String count;
    List<ProblemDTO> items;
}
