package com.knuk.algo_memo.domain.solvedac.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ResponseWrapper {
    private List<ProblemDTO> problems;

    public ResponseWrapper(@JsonProperty("items") List<ProblemDTO> problems) {
        this.problems = problems;
    }
}
