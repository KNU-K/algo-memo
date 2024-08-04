package com.knuk.algo_memo.domain.solvedac.service;

import com.knuk.algo_memo.domain.solvedac.dto.ProblemDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@Slf4j
@DisplayName("SolvedAcService에 대한 테스트")
@SpringBootTest
public class SolvedAcServiceTest {

    @Autowired
    SolvedAcService solvedAcService;

    @DisplayName("SolvedAc에 문제를 요청 시")
    @Nested
    class WhenRequestingProblem {

        @DisplayName("있는 문제인 경우")
        @Test
        void shouldGetProblemInfoIfExists() {

            ProblemDTO res = solvedAcService.searchProblemById(1000);

            Assertions.assertNotNull(res);
            Assertions.assertEquals(res.getId(), 1000);
            Assertions.assertEquals(res.getTitle(), "A+B");
            Assertions.assertEquals(res.getLevel(), 1);
        }

        @DisplayName("없는 문제인 경우 (예외 발생)")
        @Test
        void shouldHandleNonexistentProblem() {
            RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
                solvedAcService.searchProblemById(1);
            });

            Assertions.assertEquals("Problem with ID 1 not found", exception.getMessage());
        }
    }
}