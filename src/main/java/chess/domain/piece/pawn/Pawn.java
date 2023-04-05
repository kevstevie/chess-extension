package chess.domain.piece.pawn;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.List;
import java.util.Set;

public final class Pawn implements Piece {

    private final Position position;
    private final PawnStatus status;

    public Pawn(final Position position, final PawnStatus status) {
        this.position = position;
        this.status = status;
    }

    @Override
    public List<Position> computeMovablePositions(final Position target) {
        return status.computeMovablePositions(position, target);
    }

    @Override
    public boolean isSamePosition(final Position source) {
        return position.equals(source);
    }

    @Override
    public Piece move(final Position target) {
        return new Pawn(target, status);
    }

    @Override
    public boolean confirmMove(final boolean isTargetEmpty, final Position target) {
        if (position.isSameFile(target)) {
            return isTargetEmpty;
        }
        return !isTargetEmpty;
    }

    @Override
    public Set<Position> computeAllPath() {
        return status.computeAllPath(position);
    }

    public boolean isPromotable() {
        return status.isPromotable(position);
    }

    public Piece promote(final Piece piece) {
        return piece;
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
