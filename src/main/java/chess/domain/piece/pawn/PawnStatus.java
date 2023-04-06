package chess.domain.piece.pawn;

import chess.domain.position.Position;

import java.util.List;
import java.util.Set;

public interface PawnStatus {
    List<Position> computeMovablePositions(final Position target);

    boolean isPromotable();

    Set<Position> computeAllPath();

    PawnStatus move(final Position target);

    Position getPosition();
}
