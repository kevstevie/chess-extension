package chess.domain.piece.pawn;

import chess.domain.position.Position;
import chess.domain.position.UnitDirection;

import java.util.Arrays;
import java.util.List;

public final class BlackPawn implements PawnStatus {

    private static final int FORWARD_ONE_SQUARE = -1;

    @Override
    public List<Position> computeMovablePositions(final Position position, final Position target) {
        final var unitDirection = UnitDirection.of(position, target);
        if (Direction.isMovable(unitDirection) && position.computeRankDistance(target) == FORWARD_ONE_SQUARE) {
            return unitDirection.computePath(position, target);
        }
        throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
    }

    enum Direction {
        NORTH(-1, 0),
        NORTH_EAST(-1, 1),
        NORTH_WEST(-1, -1);

        private final int x;
        private final int y;

        Direction(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        public static boolean isMovable(final UnitDirection unitDirection) {
            return Arrays.stream(Direction.values())
                    .anyMatch(
                            direction -> (
                                    unitDirection.isSameRankValue(direction.x) &&
                                            unitDirection.isSameFileValue(direction.y)
                            )
                    );
        }
    }
}
