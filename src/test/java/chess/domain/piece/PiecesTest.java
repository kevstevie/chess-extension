package chess.domain.piece;

import chess.domain.piece.pawn.Pawn;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PiecesTest {

    @Test
    void blackPieces() {
        final var pieces = Pieces.ofInitialBlackPieces();

        final List<Piece> piecesList = pieces.getPieces();

        assertThat(piecesList).hasSize(16);
    }

    @Test
    void whitePieces() {
        final var pieces = Pieces.ofInitialWhitePieces();

        final List<Piece> piecesList = pieces.getPieces();

        assertThat(piecesList).hasSize(16);
    }

    @Test
    void findPiece() {
        final var pieces = Pieces.ofInitialWhitePieces();

        final var piece = pieces.findPiece(new Position(File.A, Rank.TWO));

        assertThat(piece).isInstanceOf(Pawn.class);
    }

    @Test
    void move() {
        final var pieces = Pieces.ofInitialWhitePieces();

        pieces.move(new Position(File.A, Rank.TWO), new Position(File.A, Rank.THREE));

        Piece piece = pieces.findPiece(new Position(File.A, Rank.THREE));

        assertThat(piece).isInstanceOf(Pawn.class);
    }

    @Test
    void remove() {
        final var pieces = Pieces.ofInitialWhitePieces();

        pieces.remove(new Position(File.A, Rank.TWO));

        final var piece = pieces.findPiece(new Position(File.A, Rank.TWO));

        assertThat(piece).isSameAs(Empty.getInstance());
    }

    @Test
    void hasPieceOnPath_true() {
        final var pieces = Pieces.ofInitialWhitePieces();

        final var hasPieceOnPath = pieces.hasPieceOnPath(
                List.of(
                        new Position(File.A, Rank.TWO), new Position(File.A, Rank.FOUR)
                )
        );

        assertThat(hasPieceOnPath).isTrue();
    }

    @Test
    void hasPieceOnPath_false() {
        final var pieces = Pieces.ofInitialWhitePieces();

        final var hasPieceOnPath = pieces.hasPieceOnPath(
                List.of(
                        new Position(File.A, Rank.SEVEN), new Position(File.A, Rank.THREE)
                )
        );

        assertThat(hasPieceOnPath).isFalse();
    }
}
