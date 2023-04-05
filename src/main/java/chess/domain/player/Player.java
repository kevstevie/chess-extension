package chess.domain.player;

import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.position.Position;

import java.util.List;

public final class Player {

    private final Color color;
    private final Pieces pieces;

    public Player(final Pieces pieces, final Color color) {
        this.pieces = pieces;
        this.color = color;
    }

    public static Player ofInitialWhitePieces() {
        return new Player(Pieces.ofInitialWhitePieces(), Color.WHITE);
    }

    public static Player ofInitialBlackPieces() {
        return new Player(Pieces.ofInitialBlackPieces(), Color.BLACK);
    }

    public Piece findPiece(final Position source) {
        return pieces.findPiece(source);
    }

    public void move(final Position source, final Position target) {
        pieces.move(source, target);
    }

    public void remove(final Position target) {
        pieces.remove(target);
    }

    public Color getColor() {
        return color;
    }

    public boolean hasPieceOnPath(final List<Position> movablePositions) {
        return pieces.hasPieceOnPath(movablePositions);
    }

    public boolean isEmpty(final Position target) {
        return pieces.isEmpty(target);
    }

    public Position findKingPosition() {
        return pieces.findKingPosition();
    }

    public List<List<Position>> findAllPath(final Position kingPosition) {
        return pieces.findAllPath(kingPosition);
    }
}
