package com.knuk.algo_memo.domain.solvedac.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class ProblemDTO {

    private Integer id;
    private String title;
    private Long level;

    // JSON 필드를 매핑하는 생성자
    public ProblemDTO(@JsonProperty("problemId") Integer id,
                      @JsonProperty("titleKo") String title,
                      @JsonProperty("level") Long level) {
        this.id = id;
        this.title = title;
        this.level = level;
    }

}
