package com.knuk.algo_memo.domain.solvedac.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.knuk.algo_memo.domain.solvedac.dto.enums.Tier;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class ProblemDTO {

    private Integer id;
    private String title;
    private Integer level;
    private Tier tier;

    // JSON 필드를 매핑하는 생성자
    public ProblemDTO(@JsonProperty("problemId") Integer id,
                      @JsonProperty("titleKo") String title,
                      @JsonProperty("level") Integer level) {
        this.id = id;
        this.title = title;
        this.level = level;
        this.tier = Tier.fromLevel(level);
    }

    public ProblemDTO(Integer id, String title, Integer level, Tier tier) {
        this.id = id;
        this.title = title;
        this.level = level;
        this.tier = tier;
    }
}
