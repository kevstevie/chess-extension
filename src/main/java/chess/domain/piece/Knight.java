package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.UnitDirection;

import java.util.*;

public final class Knight extends NormalPiece {

    private static final int KNIGHT_MOVE_DISTANCE = 3;

    private final Position position;

    public Knight(final Position position) {
        this.position = position;
    }

    @Override
    public List<Position> computeMovablePositions(final Position target) {
        final var unitDirection = UnitDirection.of(position, target);
        if (Direction.isKnightMovable(unitDirection) && position.computeDistance(target) == KNIGHT_MOVE_DISTANCE) {
            return unitDirection.computePath(position, target);
        }
        return new ArrayList<>();
    }

    @Override
    public boolean isSamePosition(final Position source) {
        return position.equals(source);
    }

    @Override
    public Piece move(final Position target) {
        return new Knight(target);
    }

    @Override
    public Set<Position> computeAllPath() {
        Set<Position> allPath = new HashSet<>();
        for (Direction value : Direction.values()) {
            if (position.isInBoardAfterMove(value.x, value.y)) {
                allPath.add(position.move(value.x, value.y));
            }
        }
        return allPath;
    }

    @Override
    public boolean canCastle() {
        return false;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    enum Direction {
        NORTH_EAST(1, 2),
        NORTH_WEST(-1, 2),
        SOUTH_EAST(1, -2),
        SOUTH_WEST(-1, -2),
        EAST_NORTH(2, 1),
        EAST_SOUTH(2, -1),
        WEST_NORTH(-2, 1),
        WEST_SOUTH(-2, -1);

        private final int x;
        private final int y;

        Direction(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        public static boolean isKnightMovable(final UnitDirection unitDirection) {
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
