package chess.domain.piece.pawn;

import chess.domain.position.UnitDirection;

import java.util.Arrays;

public enum BlackPawnDirection {
    NORTH(-1, 0),
    NORTH_EAST(-1, 1),
    NORTH_WEST(-1, -1);

    private final int x;
    private final int y;

    BlackPawnDirection(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public static boolean isMovable(final UnitDirection unitDirection) {
        return Arrays.stream(BlackPawnDirection.values())
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
