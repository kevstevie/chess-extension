package chess.domain.piece;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PiecesTest {

    @Test
    void blackPieces() {
        Pieces pieces = Pieces.ofInitialBlackPieces();

        List<Piece> piecesList = pieces.getPieces();

        assertThat(piecesList).hasSize(16);
    }

    @Test
    void whitePieces() {
        Pieces pieces = Pieces.ofInitialWhitePieces();

        List<Piece> piecesList = pieces.getPieces();

        assertThat(piecesList).hasSize(16);
    }
}
