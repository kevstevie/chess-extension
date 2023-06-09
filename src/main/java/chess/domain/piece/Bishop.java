package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.UnitDirection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public Set<Position> computeAllPath() {
        Set<Position> allPath = new HashSet<>();
        for (int i = -7; i < 8; i++) {
            for (int j = -7; j < 8; j++) {
                if (Math.abs(i) == Math.abs(j) && position.isInBoardAfterMove(i, j)) {
                    allPath.add(position.move(i, j));
                }
            }
        }
        allPath.remove(position);
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
}
