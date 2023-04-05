package chess.domain.piece;

import chess.domain.position.Position;

public abstract class NormalPiece implements Piece {

    @Override
    public boolean confirmMove(final boolean isTargetEmpty, final Position target) {
        return true;
    }
}
