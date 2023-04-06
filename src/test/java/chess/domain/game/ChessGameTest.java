package chess.domain.game;

import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.piece.Queen;
import chess.domain.piece.castle.King;
import chess.domain.piece.castle.Rook;
import chess.domain.player.Color;
import chess.domain.player.Player;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ChessGameTest {

    private final boolean canCastle = true;

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
        final var whitePieces = new Pieces(List.of(new Rook(new Position(File.A, Rank.ONE), canCastle)));
        final var white = new Player(whitePieces, Color.WHITE);

        final var blackPieces = new Pieces(List.of(new King(new Position(File.A, Rank.TWO), canCastle)));
        final var black = new Player(blackPieces, Color.BLACK);

        final var chessGame = new ChessGame(white, black, black, white);

        boolean check = chessGame.isCheck();

        assertThat(check).isTrue();
    }

    @Test
    void isCheck_e4f5h4_true() {
        final var chessGame = ChessGame.ofInitialPieces();
        chessGame.move(new Position(File.E, Rank.TWO), new Position(File.E, Rank.FOUR));
        chessGame.move(new Position(File.F, Rank.SEVEN), new Position(File.F, Rank.FIVE));
        chessGame.move(new Position(File.D, Rank.ONE), new Position(File.H, Rank.FIVE));

        boolean check = chessGame.isCheck();
        boolean checkMate = chessGame.isCheckMate();
        Assertions.assertAll(
                () -> assertThat(check).isTrue(),
                () -> assertThat(checkMate).isFalse()
        );
    }

    @Test
    void isCheck_false() {
        final var whitePieces = new Pieces(List.of(new Rook(new Position(File.B, Rank.ONE), canCastle)));
        final var white = new Player(whitePieces, Color.WHITE);

        final var blackPieces = new Pieces(List.of(new King(new Position(File.A, Rank.THREE), canCastle)));
        final var black = new Player(blackPieces, Color.BLACK);

        final var chessGame = new ChessGame(white, black, black, white);

        boolean check = chessGame.isCheck();

        assertThat(check).isFalse();
    }

    @Test
    void isCheckMate_true() {
        List<Piece> whitePieces = new ArrayList<>();
        whitePieces.add(new Queen(new Position(File.A, Rank.TWO)));
        whitePieces.add(new Rook(new Position(File.A, Rank.ONE), canCastle));
        final var white = new Player(new Pieces(whitePieces), Color.WHITE);

        List<Piece> blackPieces = new ArrayList<>();
        blackPieces.add(new King(new Position(File.C, Rank.ONE), canCastle));
        final var black = new Player(new Pieces(blackPieces), Color.BLACK);

        final var chessGame = new ChessGame(white, black, black, white);

        boolean checkMate = chessGame.isCheckMate();

        assertThat(checkMate).isTrue();
    }

    @Test
    void isCheckMate_false() {
        List<Piece> whitePieces = new ArrayList<>();
        whitePieces.add(new Queen(new Position(File.A, Rank.TWO)));
        whitePieces.add(new Rook(new Position(File.A, Rank.ONE), canCastle));
        final var white = new Player(new Pieces(whitePieces), Color.WHITE);

        List<Piece> blackPieces = new ArrayList<>();
        blackPieces.add(new King(new Position(File.H, Rank.TWO), canCastle));
        final var black = new Player(new Pieces(blackPieces), Color.BLACK);

        final var chessGame = new ChessGame(white, black, black, white);

        boolean checkMate = chessGame.isCheckMate();

        assertThat(checkMate).isFalse();
    }

    @Test
    void isCheckMate_foolsMate_true() {
        ChessGame chessGame = ChessGame.ofInitialPieces();
        chessGame.move(new Position(File.F, Rank.TWO), new Position(File.F, Rank.THREE));
        chessGame.move(new Position(File.E, Rank.SEVEN), new Position(File.E, Rank.FIVE));
        chessGame.move(new Position(File.G, Rank.TWO), new Position(File.G, Rank.FOUR));
        chessGame.move(new Position(File.D, Rank.EIGHT), new Position(File.H, Rank.FOUR));

        boolean checkMate = chessGame.isCheckMate();

        assertThat(checkMate).isTrue();
    }

    @Test
    void isCheckMate_foolsMate_true2() {
        final var whitePieces = Pieces.ofInitialWhitePieces();
        whitePieces.move(new Position(File.F, Rank.TWO), new Position(File.F, Rank.THREE));
        whitePieces.move(new Position(File.G, Rank.TWO), new Position(File.G, Rank.FOUR));
        final var white = new Player(whitePieces, Color.WHITE);

        final var blackPieces = Pieces.ofInitialBlackPieces();
        blackPieces.move(new Position(File.E, Rank.SEVEN), new Position(File.E, Rank.FIVE));
        blackPieces.move(new Position(File.D, Rank.EIGHT), new Position(File.H, Rank.FOUR));
        final var black = new Player(blackPieces, Color.BLACK);

        final var chessGame = new ChessGame(white, black);

        boolean checkMate = chessGame.isCheckMate();

        assertThat(checkMate).isTrue();
    }

    @Test
    void isCheckMate_false2() {
        List<Piece> whitePieces = new ArrayList<>();
        whitePieces.add(new Queen(new Position(File.A, Rank.TWO)));
        whitePieces.add(new Rook(new Position(File.A, Rank.ONE), canCastle));
        final var white = new Player(new Pieces(whitePieces), Color.WHITE);

        List<Piece> blackPieces = new ArrayList<>();
        blackPieces.add(new King(new Position(File.H, Rank.ONE), canCastle));
        blackPieces.add(new Rook(new Position(File.G, Rank.EIGHT), canCastle));
        blackPieces.add(new Knight(new Position(File.G, Rank.TWO)));
        final var black = new Player(new Pieces(blackPieces), Color.BLACK);

        final var chessGame = new ChessGame(white, black, black, white);

        boolean checkMate = chessGame.isCheckMate();

        assertThat(checkMate).isFalse();
    }

    @Test
    void isCheckMate_capture_false() {
        List<Piece> whitePieces = new ArrayList<>();
        whitePieces.add(new Rook(new Position(File.B, Rank.TWO), canCastle));
        whitePieces.add(new Rook(new Position(File.A, Rank.ONE), canCastle));
        final var white = new Player(new Pieces(whitePieces), Color.WHITE);

        List<Piece> blackPieces = new ArrayList<>();
        blackPieces.add(new King(new Position(File.B, Rank.ONE), canCastle));
        final var black = new Player(new Pieces(blackPieces), Color.BLACK);

        final var chessGame = new ChessGame(white, black, black, white);

        boolean checkMate = chessGame.isCheckMate();

        assertThat(checkMate).isFalse();
    }

    @Test
    void isCheckMate_scholarMate_true() {
        ChessGame chessGame = ChessGame.ofInitialPieces();
        chessGame.move(new Position(File.E, Rank.TWO), new Position(File.E, Rank.FOUR));
        chessGame.move(new Position(File.E, Rank.SEVEN), new Position(File.E, Rank.FIVE));
        chessGame.move(new Position(File.D, Rank.ONE), new Position(File.H, Rank.FIVE));
        chessGame.move(new Position(File.F, Rank.EIGHT), new Position(File.C, Rank.FIVE));
        chessGame.move(new Position(File.F, Rank.ONE), new Position(File.C, Rank.FOUR));
        chessGame.move(new Position(File.G, Rank.EIGHT), new Position(File.F, Rank.SIX));
        chessGame.move(new Position(File.H, Rank.FIVE), new Position(File.F, Rank.SEVEN));

        boolean checkMate = chessGame.isCheckMate();

        assertThat(checkMate).isTrue();
    }
}
