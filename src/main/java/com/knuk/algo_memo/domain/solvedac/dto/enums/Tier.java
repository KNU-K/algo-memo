package com.knuk.algo_memo.domain.solvedac.dto.enums;

public enum Tier {
    BRONZE_5(1),
    BRONZE_4(2),
    BRONZE_3(3),
    BRONZE_2(4),
    BRONZE_1(5),
    SILVER_5(6),
    SILVER_4(7),
    SILVER_3(8),
    SILVER_2(9),
    SILVER_1(10),
    GOLD_5(11),
    GOLD_4(12),
    GOLD_3(13),
    GOLD_2(14),
    GOLD_1(15),
    PLATINUM_5(16),
    PLATINUM_4(17),
    PLATINUM_3(18),
    PLATINUM_2(19),
    PLATINUM_1(20),
    DIAMOND_5(21),
    DIAMOND_4(22),
    DIAMOND_3(23),
    DIAMOND_2(24),
    DIAMOND_1(25),
    RUBY_5(26),
    RUBY_4(27),
    RUBY_3(28),
    RUBY_2(29),
    RUBY_1(30);
    private final Integer level;

    Tier(Integer level) {
        this.level = level;
    }

    public Integer getLevel() {
        return level;
    }

    public static Tier fromLevel(Integer level) {
        for (Tier tier : values()) {
            if (tier.getLevel().equals(level)) {
                return tier;
            }
        }
        throw new IllegalArgumentException("Invalid level: " + level);
    }

    @Override
    public String toString() {
        return name().replace('_', ' ');
    }
}
