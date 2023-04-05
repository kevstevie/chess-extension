package chess.domain.player;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.pawn.Pawn;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
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
}
