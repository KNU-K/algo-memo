package com.knuk.algo_memo.domain.solvedac.service;

import com.knuk.algo_memo.domain.solvedac.dto.ProblemDTO;

public interface SolvedAcService {
    ProblemDTO searchProblemById(Integer problemId);
}
