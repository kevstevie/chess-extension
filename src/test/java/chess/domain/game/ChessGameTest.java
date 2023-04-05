package chess.domain.game;

import chess.domain.piece.King;
import chess.domain.piece.Pieces;
import chess.domain.piece.Rook;
import chess.domain.player.Color;
import chess.domain.player.Player;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ChessGameTest {

    @Test
    void move() {
        final var chessGame = ChessGame.ofInitialPieces();
        final var source = new Position(File.A, Rank.TWO);
        final var target = new Position(File.A, Rank.FOUR);

        chessGame.move(source, target);
    }

    @Test
    void move_a1a2_exception() {
        final var chessGame = ChessGame.ofInitialPieces();
        final var source = new Position(File.A, Rank.ONE);
        final var target = new Position(File.A, Rank.TWO);

        assertThatThrownBy(() -> chessGame.move(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void move_a3_a4_exception() {
        final var chessGame = ChessGame.ofInitialPieces();
        final var source = new Position(File.A, Rank.THREE);
        final var target = new Position(File.A, Rank.FOUR);

        assertThatThrownBy(() -> chessGame.move(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void changeTurn() {
        final var chessGame = ChessGame.ofInitialPieces();

        chessGame.changeTurn();

        Color color = chessGame.getCurrentTurnPlayer().getColor();
        Color color2 = chessGame.getWaitingPlayer().getColor();
        Assertions.assertAll(
                () -> assertThat(color).isEqualTo(Color.BLACK),
                () -> assertThat(color2).isEqualTo(Color.WHITE)
        );
    }

    @Test
    void move_validatePath_exception() {
        final var chessGame = ChessGame.ofInitialPieces();
        final var source = new Position(File.A, Rank.ONE);
        final var target = new Position(File.A, Rank.FOUR);

        assertThatThrownBy(() -> chessGame.move(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void isCheck_true() {
        final var whitePieces = new Pieces(List.of(new Rook(new Position(File.A, Rank.ONE))));
        final var white = new Player(whitePieces, Color.WHITE);

        final var blackPieces = new Pieces(List.of(new King(new Position(File.A, Rank.TWO))));
        final var black = new Player(blackPieces, Color.BLACK);

        final var chessGame = new ChessGame(white, black);

        boolean check = chessGame.isCheck();

        assertThat(check).isTrue();
    }

    @Test
    void isCheck_false() {
        final var whitePieces = new Pieces(List.of(new Rook(new Position(File.B, Rank.ONE))));
        final var white = new Player(whitePieces, Color.WHITE);

        final var blackPieces = new Pieces(List.of(new King(new Position(File.A, Rank.THREE))));
        final var black = new Player(blackPieces, Color.BLACK);

        final var chessGame = new ChessGame(white, black);

        boolean check = chessGame.isCheck();

        assertThat(check).isFalse();
    }
}
