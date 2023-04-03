package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.UnitDirection;

import java.util.List;

public final class King implements Piece {

    private final Position position;

    public King(final Position position) {
        this.position = position;
    }

    @Override
    public List<Position> computeMovablePositions(final Position target) {
        final var unitDirection = UnitDirection.of(position, target);
        if (position.isNearSquare(target)) {
            return unitDirection.computePath(position, target);
        }
        throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
    }
}
