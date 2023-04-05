package chess.domain.piece;

import chess.domain.position.Position;

import java.util.List;

public abstract class NormalPiece implements Piece {

    @Override
    public abstract List<Position> computeMovablePositions(final Position target);

    @Override
    public abstract boolean isSamePosition(final Position source);

    @Override
    public abstract Piece move(final Position target);

    @Override
    public boolean confirmMove(final boolean isTargetEmpty, final Position target) {
        return isTargetEmpty;
    }
}
