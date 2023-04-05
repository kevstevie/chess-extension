package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.UnitDirection;

import java.util.List;

public final class Bishop implements Piece {

    private final Position position;

    public Bishop(final Position position) {
        this.position = position;
    }

    @Override
    public List<Position> computeMovablePositions(final Position target) {
        UnitDirection unitDirection = UnitDirection.of(position, target);
        if (unitDirection.isDiagonal()) {
            return unitDirection.computePath(position, target);
        }
        throw new IllegalArgumentException("Bishop은 대각선이 아니면 이동할 수 없습니다.");
    }

    @Override
    public boolean isSamePosition(final Position source) {
        return position.equals(source);
    }

    @Override
    public Piece move(final Position target) {
        return new Bishop(target);
    }
}
