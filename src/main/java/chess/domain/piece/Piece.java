package chess.domain.piece;

import chess.domain.position.Position;

import java.util.List;

public interface Piece {

    List<Position> computeMovablePositions(final Position target);

    boolean isSamePosition(Position source);

    Piece move(Position target);

    boolean confirmMove(boolean isTargetEmpty, Position target);
}
