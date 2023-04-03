package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.UnitDirection;

import java.util.List;

public final class Queen implements Piece {

    private final Position position;

    public Queen(final Position position) {
        this.position = position;
    }

    @Override
    public List<Position> computeMovablePositions(final Position target) {
        final var unitDirection = UnitDirection.of(position, target);

        if (unitDirection.isStraight() || unitDirection.isDiagonal()) {
            return unitDirection.computePath(position, target);
        }

        throw new IllegalArgumentException("Queen은 대각선 또는 직선으로만 이동할 수 있습니다.");
    }
}
