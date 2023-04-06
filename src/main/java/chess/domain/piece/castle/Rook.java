package chess.domain.piece.castle;

import chess.domain.piece.NormalPiece;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.UnitDirection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Rook extends NormalPiece {

    private final Position position;
    private final boolean canCastle;

    public Rook(final Position position, final boolean canCastle) {
        this.position = position;
        this.canCastle = canCastle;
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
        return new Rook(target, false);
    }

    @Override
    public Set<Position> computeAllPath() {
        Set<Position> allPath = new HashSet<>();
        for (int i = -7; i < 8; i++) {
            if (position.isInBoardAfterMove(0, i)) {
                allPath.add(position.move(0, i));
            }
            if (position.isInBoardAfterMove(i, 0)) {
                allPath.add(position.move(i, 0));
            }
        }
        allPath.remove(position);
        return allPath;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public boolean canCastle() {
        return canCastle;
    }
}
