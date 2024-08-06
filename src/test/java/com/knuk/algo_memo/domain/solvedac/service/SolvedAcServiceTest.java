package com.knuk.algo_memo.domain.solvedac.service;

import com.knuk.algo_memo.domain.solvedac.dto.ProblemDTO;
import com.knuk.algo_memo.domain.solvedac.dto.enums.Tier;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@DisplayName("SolvedAcService에 대한 테스트")
@SpringBootTest
public class SolvedAcServiceTest {

    @Autowired
    private SolvedAcService solvedAcService;
    private static final List<ProblemDTO> PROBLEM_INFO_LIST = List.of(
            new ProblemDTO(1000, "A+B", 1, Tier.BRONZE_5),
            new ProblemDTO(2751, "수 정렬하기 2", 6, Tier.SILVER_5),
            new ProblemDTO(1038, "감소하는 수", 11, Tier.GOLD_5),
            new ProblemDTO(1071, "소트", 16, Tier.PLATINUM_5),
            new ProblemDTO(1077,"넓이", 21, Tier.DIAMOND_5),
            new ProblemDTO(17473,"수열과 쿼리 25", 26, Tier.RUBY_5)
    );

    @DisplayName("SolvedAc에 문제를 요청 시")
    @Nested
    class WhenRequestingProblem {

        @Nested
        @DisplayName("정상 동작 : ")
        class SituationSuccess {

            @DisplayName("있는 문제인 경우")
            @Test
            void shouldGetProblemInfoIfExists() {
                PROBLEM_INFO_LIST.forEach((expectedInfo) -> {
                    ProblemDTO res = solvedAcService.searchProblemById(expectedInfo.getId());
                    log.info(res.toString());
                    Assertions.assertNotNull(res);
                    Assertions.assertEquals(expectedInfo.getId(), res.getId());
                    Assertions.assertEquals(expectedInfo.getTitle(), res.getTitle());
                    Assertions.assertEquals(expectedInfo.getLevel(), res.getLevel());
                    Assertions.assertEquals(expectedInfo.getTier(), res.getTier());
                });
            }
        }

        @Nested
        @DisplayName("오류가 발생 : ")
        class SituationFailure {

            @DisplayName("없는 문제인 경우 (예외 발생)")
            @Test
            void shouldHandleNonexistentProblem() {
                RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
                    solvedAcService.searchProblemById(1);
                });

                Assertions.assertEquals("Unexpected error occurred while retrieving problem information", exception.getMessage());
            }
        }
    }
}
