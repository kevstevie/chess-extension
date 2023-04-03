package chess.domain.piece.pawn;

import chess.domain.position.Position;

import java.util.List;

public interface PawnStatus {
    List<Position> computeMovablePositions(Position position, Position target);
}
