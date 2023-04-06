package chess.domain.player;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.castle.King;
import chess.domain.piece.castle.Rook;
import chess.domain.piece.pawn.Pawn;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PlayerTest {

    @Test
    void create() {
        final var player = Player.ofInitialWhitePieces();

        assertNotNull(player);
    }

    @Test
    void findPiece() {
        final var player = Player.ofInitialWhitePieces();
        final var source = new Position(File.A, Rank.TWO);

        Piece piece = player.findPiece(source);

        assertThat(piece).isInstanceOf(Pawn.class);
    }

    @Test
    void move() {
        final var player = Player.ofInitialWhitePieces();
        final var source = new Position(File.A, Rank.TWO);
        final var target = new Position(File.A, Rank.THREE);

        player.move(source, target);

        Piece piece = player.findPiece(target);

        assertThat(piece).isInstanceOf(Pawn.class);
    }

    @Test
    void remove() {
        final var player = Player.ofInitialWhitePieces();
        final var target = new Position(File.A, Rank.TWO);

        player.remove(target);

        Piece piece = player.findPiece(target);

        assertThat(piece).isSameAs(Empty.getInstance());
    }

    @Test
    void kingCastle_white() {
        final var player = Player.ofInitialWhitePieces();
        player.move(new Position(File.E, Rank.TWO), new Position(File.E, Rank.FOUR));
        player.move(new Position(File.G, Rank.ONE), new Position(File.F, Rank.THREE));
        player.move(new Position(File.F, Rank.ONE), new Position(File.E, Rank.TWO));
        player.castleKingSide();

        Piece piece = player.findPiece(new Position(File.G, Rank.ONE));
        Piece piece1 = player.findPiece(new Position(File.F, Rank.ONE));

        assertAll(
                () -> assertThat(piece).isInstanceOf(King.class),
                () -> assertThat(piece1).isInstanceOf(Rook.class)
        );
    }

    @Test
    void queenCastle_white() {
        final var player = Player.ofInitialWhitePieces();
        player.remove(new Position(File.D, Rank.ONE));
        player.remove(new Position(File.C, Rank.ONE));
        player.remove(new Position(File.B, Rank.ONE));
        player.castleQueenSide();

        Piece piece = player.findPiece(new Position(File.C, Rank.ONE));
        Piece piece1 = player.findPiece(new Position(File.D, Rank.ONE));

        assertAll(
                () -> assertThat(piece).isInstanceOf(King.class),
                () -> assertThat(piece1).isInstanceOf(Rook.class)
        );
    }
}
