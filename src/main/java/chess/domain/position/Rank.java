package chess.domain.position;

import java.util.Arrays;

public enum Rank {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private final int value;

    Rank(final int value) {
        this.value = value;
    }

    public static Rank of(final int value) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 랭크 값입니다."));
    }

    public int computeDistance(final Rank target) {
        return target.value - this.value;
    }

    public Rank move(final int rankDirection) {
        return of(this.value + rankDirection);
    }

    public boolean isInBoardAfterMove(final int rank) {
        return 1 <= rank + this.value && rank + this.value <= 8;
    }
}
