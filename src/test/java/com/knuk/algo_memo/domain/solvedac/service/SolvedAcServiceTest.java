package com.knuk.algo_memo.domain.solvedac.service;

import com.knuk.algo_memo.domain.solvedac.dto.ProblemDTO;
import com.knuk.algo_memo.domain.solvedac.SolvedAcService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@Slf4j
@SpringBootTest
public class SolvedAcServiceTest {

    @Autowired
    SolvedAcService solvedAcService;

    @DisplayName("SolvedAc API 가지고 오기")
    @Test
    void shouldToGetTheProblemInfo(){
        ProblemDTO res = solvedAcService.getProblemInfo(1000);

        Assertions.assertEquals(res.getId(), 1000);
        Assertions.assertEquals(res.getTitle(), "A+B");
        Assertions.assertEquals(res.getLevel(), 1);
    }

}
