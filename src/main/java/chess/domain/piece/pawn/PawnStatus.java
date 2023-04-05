package chess.domain.piece.pawn;

import chess.domain.position.Position;

import java.util.List;
import java.util.Set;

public interface PawnStatus {
    List<Position> computeMovablePositions(Position position, Position target);

    boolean isPromotable(Position position);

    Set<Position> computeAllPath(Position position);
}
