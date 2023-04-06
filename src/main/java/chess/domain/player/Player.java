package chess.domain.player;

import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.List;
import java.util.Map;
import java.util.Set;

public final class Player {

    private final List<Position> WHITE_KING_CASTLE_PATH = List.of(
            new Position(File.F, Rank.ONE),
            new Position(File.G, Rank.ONE)
    );
    private final List<Position> BLACK_KING_CASTLE_PATH = List.of(
            new Position(File.F, Rank.EIGHT),
            new Position(File.G, Rank.EIGHT)
    );
    private final List<Position> WHITE_QUEEN_CASTLE_PATH = List.of(
            new Position(File.D, Rank.ONE),
            new Position(File.C, Rank.ONE),
            new Position(File.B, Rank.ONE)
    );
    private final List<Position> BLACK_QUEEN_CASTLE_PATH = List.of(
            new Position(File.D, Rank.EIGHT),
            new Position(File.C, Rank.EIGHT),
            new Position(File.B, Rank.EIGHT)
    );

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

    public Player copyPlayer() {
        return new Player(pieces.copyPieces(), color);
    }

    public List<List<Position>> findAllPath(final Position position) {
        return pieces.findAllPath(position);
    }

    public Map<Position, Set<Position>> findAllMoveWithoutTarget() {
        return pieces.findAllMoveWithoutTarget();
    }

    public boolean checkKingCastlePath(final Player waitingPlayer) {
        if (color == Color.WHITE) {
            return !this.hasPieceOnPath(WHITE_KING_CASTLE_PATH) &&
                    !waitingPlayer.hasPieceOnPath(WHITE_KING_CASTLE_PATH);
        }
        return !this.hasPieceOnPath(BLACK_KING_CASTLE_PATH) &&
                !waitingPlayer.hasPieceOnPath(BLACK_KING_CASTLE_PATH);
    }

    public boolean checkQueenCastlePath(final Player waitingPlayer) {
        if (color == Color.WHITE) {
            return !this.hasPieceOnPath(WHITE_QUEEN_CASTLE_PATH) &&
                    !waitingPlayer.hasPieceOnPath(WHITE_QUEEN_CASTLE_PATH);
        }
        return !this.hasPieceOnPath(BLACK_QUEEN_CASTLE_PATH) &&
                !waitingPlayer.hasPieceOnPath(BLACK_QUEEN_CASTLE_PATH);
    }

    public void castleKingSide() {
        if (color == Color.WHITE) {
            if (pieces.findPiece(new Position(File.E, Rank.ONE)).canCastle() &&
                    pieces.findPiece(new Position(File.H, Rank.ONE)).canCastle()) {
                pieces.move(new Position(File.E, Rank.ONE), new Position(File.G, Rank.ONE));
                pieces.move(new Position(File.H, Rank.ONE), new Position(File.F, Rank.ONE));
                return;
            }
        }
        if (pieces.findPiece(new Position(File.E, Rank.EIGHT)).canCastle() &&
                pieces.findPiece(new Position(File.H, Rank.EIGHT)).canCastle()) {
            pieces.move(new Position(File.E, Rank.EIGHT), new Position(File.G, Rank.EIGHT));
            pieces.move(new Position(File.H, Rank.EIGHT), new Position(File.F, Rank.EIGHT));
        }
    }

    public void castleQueenSide() {
        if (color == Color.WHITE) {
            if (pieces.findPiece(new Position(File.E, Rank.ONE)).canCastle() &&
                    pieces.findPiece(new Position(File.A, Rank.ONE)).canCastle()) {
                pieces.move(new Position(File.E, Rank.ONE), new Position(File.C, Rank.ONE));
                pieces.move(new Position(File.A, Rank.ONE), new Position(File.D, Rank.ONE));
                return;
            }
        }
        if (pieces.findPiece(new Position(File.E, Rank.EIGHT)).canCastle() &&
                pieces.findPiece(new Position(File.A, Rank.EIGHT)).canCastle()) {
            pieces.move(new Position(File.E, Rank.EIGHT), new Position(File.C, Rank.EIGHT));
            pieces.move(new Position(File.A, Rank.EIGHT), new Position(File.D, Rank.EIGHT));
        }
    }
}
