package chess.domain.piece.pawn;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.List;

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
}
