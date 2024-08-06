package com.knuk.algo_memo.domain.solvedac.enums;

import com.knuk.algo_memo.domain.solvedac.dto.enums.Tier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@DisplayName("Tier Test")
public class TierTest {

    private Map<Integer, String> rankMap;

    @BeforeEach
    void setUp() {
        // rankMap 초기화
        rankMap = new HashMap<>() {{
            put(1, "BRONZE 5");
            put(2, "BRONZE 4");
            put(3, "BRONZE 3");
            put(4, "BRONZE 2");
            put(5, "BRONZE 1");
            put(6, "SILVER 5");
            put(7, "SILVER 4");
            put(8, "SILVER 3");
            put(9, "SILVER 2");
            put(10, "SILVER 1");
            put(11, "GOLD 5");
            put(12, "GOLD 4");
            put(13, "GOLD 3");
            put(14, "GOLD 2");
            put(15, "GOLD 1");
            put(16, "PLATINUM 5");
            put(17, "PLATINUM 4");
            put(18, "PLATINUM 3");
            put(19, "PLATINUM 2");
            put(20, "PLATINUM 1");
            put(21, "DIAMOND 5");
            put(22, "DIAMOND 4");
            put(23, "DIAMOND 3");
            put(24, "DIAMOND 2");
            put(25, "DIAMOND 1");
            put(26, "RUBY 5");
            put(27, "RUBY 4");
            put(28, "RUBY 3");
            put(29, "RUBY 2");
            put(30, "RUBY 1");
        }};
    }

    @Test
    @DisplayName("Level을 Tier 로 변경하는 것이 정상동작하는지 확인")
    void shouldCorrectlyMapLevelToTier() {
        rankMap.forEach((level, expectedTier) -> {
            Assertions.assertEquals(expectedTier, Tier.fromLevel(level).toString(),
                    "Level " + level + " should map to tier " + expectedTier);
        });
    }
}
