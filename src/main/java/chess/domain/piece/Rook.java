package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.UnitDirection;

import java.util.ArrayList;
import java.util.List;

public final class Rook extends NormalPiece {

    private final Position position;

    public Rook(final Position position) {
        this.position = position;
    }

    @Override
    public List<Position> computeMovablePositions(final Position target) {
        UnitDirection unitDirection = UnitDirection.of(position, target);
        if (unitDirection.isStraight()) {
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
        return new Rook(target);
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
