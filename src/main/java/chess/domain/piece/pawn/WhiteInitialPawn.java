package chess.domain.piece.pawn;

import chess.domain.position.Position;
import chess.domain.position.UnitDirection;

import java.util.*;

public final class WhiteInitialPawn implements PawnStatus {
    @Override
    public List<Position> computeMovablePositions(final Position position, final Position target) {
        final var unitDirection = UnitDirection.of(position, target);
        if (Direction.isMovable(unitDirection) && position.computeDistance(target) <= 2) {
            return unitDirection.computePath(position, target);
        }
        return new ArrayList<>();
    }

    @Override
    public boolean isPromotable(final Position position) {
        return false;
    }

    @Override
    public Set<Position> computeAllPath(final Position position) {
        Set<Position> allPath = new HashSet<>();
        for (Direction value : Direction.values()) {
            if (position.isInBoardAfterMove(value.x, value.y)) {
                allPath.add(position.move(value.x, value.y));
            }
        }
        if (position.isInBoardAfterMove(2, 0)) {
            allPath.add(position.move(2, 0));
        }
        return allPath;
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
