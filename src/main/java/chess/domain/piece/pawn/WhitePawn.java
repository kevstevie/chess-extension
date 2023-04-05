package chess.domain.piece.pawn;

import chess.domain.position.Position;
import chess.domain.position.UnitDirection;

import java.util.*;

public final class WhitePawn implements PawnStatus {

    private static final int FORWARD_ONE_SQUARE = 1;
    private static final int PROMOTION_RANK = 8;

    @Override
    public List<Position> computeMovablePositions(final Position position, final Position target) {
        final var unitDirection = UnitDirection.of(position, target);
        if (Direction.isMovable(unitDirection) && position.computeRankDistance(target) == FORWARD_ONE_SQUARE) {
            return unitDirection.computePath(position, target);
        }
        return new ArrayList<>();
    }

    @Override
    public boolean isPromotable(final Position position) {
        return position.isSameRank(PROMOTION_RANK);
    }

    @Override
    public Set<Position> computeAllPath(final Position position) {
        Set<Position> allPath = new HashSet<>();
        for (Direction value : Direction.values()) {
            if (position.isInBoardAfterMove(value.x, value.y)) {
                allPath.add(position.move(value.x, value.y));
            }
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
