package chess.domain.piece.pawn;

import chess.domain.position.UnitDirection;

import java.util.Arrays;

public enum WhitePawnDirection {
    SOUTH(1, 0),
    SOUTH_EAST(1, 1),
    SOUTH_WEST(1, -1);

    private final int x;
    private final int y;

    WhitePawnDirection(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public static boolean isMovable(final UnitDirection unitDirection) {
        return Arrays.stream(WhitePawnDirection.values())
                .anyMatch(
                        direction -> (
                                unitDirection.isSameRankValue(direction.x) &&
                                        unitDirection.isSameFileValue(direction.y)
                        )
                );
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }
}
