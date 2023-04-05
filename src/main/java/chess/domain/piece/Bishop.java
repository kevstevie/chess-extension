package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.UnitDirection;

import java.util.ArrayList;
import java.util.List;

public final class Bishop extends NormalPiece {

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
        return new ArrayList<>();
    }

    @Override
    public boolean isSamePosition(final Position source) {
        return position.equals(source);
    }

    @Override
    public Piece move(final Position target) {
        return new Bishop(target);
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
