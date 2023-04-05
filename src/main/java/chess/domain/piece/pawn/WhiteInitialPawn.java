package chess.domain.piece.pawn;

import chess.domain.position.Position;
import chess.domain.position.UnitDirection;

import java.util.Arrays;
import java.util.List;

public final class WhiteInitialPawn implements PawnStatus {
    @Override
    public List<Position> computeMovablePositions(final Position position, final Position target) {
        final var unitDirection = UnitDirection.of(position, target);
        if (Direction.isMovable(unitDirection) && position.computeDistance(target) <= 2) {
            return unitDirection.computePath(position, target);
        }
        throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
    }

    @Override
    public boolean isPromotable(final Position position) {
        return false;
    }

    enum Direction {
        SOUTH(1, 0),
        SOUTH_EAST(1, 1),
        SOUTH_WEST(1, -1);

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
