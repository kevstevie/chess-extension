package chess.domain.piece.pawn;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {


    @Test
    void confirmMove_forward_true() {
        final var source = new Position(File.A, Rank.THREE);
        final var target = new Position(File.A, Rank.FOUR);

        final var pawn = new Pawn(source, new WhitePawn());
        pawn.computeMovablePositions(target);

        boolean result = pawn.confirmMove(true, target);

        assertThat(result).isTrue();
    }

    @Test
    void confirmMove_forwardTwo_true() {
        final var source = new Position(File.A, Rank.FOUR);
        final var target = new Position(File.A, Rank.SIX);

        final var pawn = new Pawn(source, new WhiteInitialPawn());
        pawn.computeMovablePositions(target);

        boolean result = pawn.confirmMove(true, target);

        assertThat(result).isTrue();
    }

    @Test
    void confirmMove_diagonal_true() {
        final var source = new Position(File.A, Rank.FOUR);
        final var target = new Position(File.B, Rank.FIVE);

        final var pawn = new Pawn(source, new WhitePawn());
        pawn.computeMovablePositions(target);

        boolean result = pawn.confirmMove(false, target);

        assertThat(result).isTrue();
    }

    @Test
    void confirmMove_forward_false() {
        final var source = new Position(File.A, Rank.FOUR);
        final var target = new Position(File.A, Rank.FIVE);

        final var pawn = new Pawn(source, new WhitePawn());
        pawn.computeMovablePositions(target);

        boolean result = pawn.confirmMove(false, target);

        assertThat(result).isFalse();
    }

    @Test
    void isPromotable_black_false() {
        final var source = new Position(File.A, Rank.ONE);

        final var pawn = new Pawn(source, new BlackInitialPawn());

        boolean result = pawn.isPromotable();

        assertThat(result).isFalse();
    }

    @Test
    void isPromotable_black_true() {
        final var source = new Position(File.A, Rank.ONE);

        final var pawn = new Pawn(source, new BlackPawn());

        boolean result = pawn.isPromotable();

        assertThat(result).isTrue();
    }

    @Test
    void isPromotable_white_true() {
        final var source = new Position(File.A, Rank.EIGHT);

        final var pawn = new Pawn(source, new WhitePawn());

        boolean result = pawn.isPromotable();

        assertThat(result).isTrue();
    }

    @Test
    void isPromotable_white_false() {
        final var source = new Position(File.A, Rank.SEVEN);

        final var pawn = new Pawn(source, new WhitePawn());

        boolean result = pawn.isPromotable();

        assertThat(result).isFalse();
    }
}
