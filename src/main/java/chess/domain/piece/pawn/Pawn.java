package chess.domain.piece.pawn;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.List;
import java.util.Set;

public final class Pawn implements Piece {

    private final PawnStatus status;

    public Pawn(final PawnStatus status) {
        this.status = status;
    }

    @Override
    public List<Position> computeMovablePositions(final Position target) {
        return status.computeMovablePositions(target);
    }

    @Override
    public boolean isSamePosition(final Position source) {
        return getPosition().equals(source);
    }

    @Override
    public Piece move(final Position target) {
        return new Pawn(status.move(target));
    }

    @Override
    public boolean confirmMove(final boolean isTargetEmpty, final Position target) {
        if (getPosition().isSameFile(target)) {
            return isTargetEmpty;
        }
        return !isTargetEmpty;
    }

    @Override
    public Set<Position> computeAllPath() {
        return status.computeAllPath();
    }

    public boolean isPromotable() {
        return status.isPromotable();
    }

    public Piece promote(final Piece piece) {
        return piece;
    }

    @Override
    public boolean canCastle() {
        return false;
    }

    @Override
    public Position getPosition() {
        return status.getPosition();
    }
}
