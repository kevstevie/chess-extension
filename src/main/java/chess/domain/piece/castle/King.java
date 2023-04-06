package chess.domain.piece.castle;

import chess.domain.piece.NormalPiece;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.UnitDirection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class King extends NormalPiece {

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
        return new ArrayList<>();
    }

    @Override
    public boolean isSamePosition(final Position source) {
        return position.equals(source);
    }

    @Override
    public Piece move(final Position target) {
        return new King(target);
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public Set<Position> computeAllPath() {
        Set<Position> allPath = new HashSet<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (position.isInBoardAfterMove(i, j)) {
                    allPath.add(position.move(i, j));
                }
            }
        }
        allPath.remove(position);
        return allPath;
    }
}
