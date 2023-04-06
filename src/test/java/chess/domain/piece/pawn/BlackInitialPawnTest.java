package chess.domain.piece.pawn;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BlackInitialPawnTest {

    @Test
    void computeMovablePositions_diagonal() {
        final var source = new Position(File.A, Rank.TWO);
        final var target = new Position(File.B, Rank.ONE);

        final var pawn = new BlackInitialPawn(source);

        List<Position> positions = pawn.computeMovablePositions(target);

        assertThat(positions).containsExactlyInAnyOrder(
                new Position(File.B, Rank.ONE)
        );
    }

    @Test
    void computeMovablePositions_forwardTwo() {
        final var source = new Position(File.A, Rank.FOUR);
        final var target = new Position(File.A, Rank.TWO);

        final var pawn = new BlackInitialPawn(source);

        List<Position> positions = pawn.computeMovablePositions(target);

        assertThat(positions).containsExactlyInAnyOrder(
                new Position(File.A, Rank.TWO),
                new Position(File.A, Rank.THREE)
        );
    }

    @Test
    void computeMovablePositions_forward() {
        final var source = new Position(File.A, Rank.FOUR);
        final var target = new Position(File.A, Rank.THREE);

        final var pawn = new BlackInitialPawn(source);

        List<Position> positions = pawn.computeMovablePositions(target);

        assertThat(positions).containsExactlyInAnyOrder(
                new Position(File.A, Rank.THREE)
        );
    }

    @Test
    void computeMovablePositions_illegalMove_empty() {
        final var source = new Position(File.A, Rank.FOUR);
        final var target = new Position(File.A, Rank.FIVE);

        final var pawn = new BlackInitialPawn(source);

        List<Position> positions = pawn.computeMovablePositions(target);

        assertThat(positions).isEmpty();
    }

    @Test
    void computeMovablePositions_illegalMove_empty2() {
        final var source = new Position(File.A, Rank.FOUR);
        final var target = new Position(File.B, Rank.FIVE);

        final var pawn = new BlackInitialPawn(source);

        List<Position> positions = pawn.computeMovablePositions(target);

        assertThat(positions).isEmpty();
    }

    @Test
    void move_forwardTwo_enPassant() {
        final var source = new Position(File.A, Rank.FOUR);
        final var target = new Position(File.A, Rank.TWO);

        final var pawn = new BlackInitialPawn(source);

        PawnStatus move = pawn.move(target);

        assertThat(move).isInstanceOf(EnPassantBlackPawn.class);
    }

    @Test
    void move_forwardOne_blackPawn() {
        final var source = new Position(File.A, Rank.FOUR);
        final var target = new Position(File.A, Rank.THREE);

        final var pawn = new BlackInitialPawn(source);

        PawnStatus move = pawn.move(target);

        assertThat(move).isInstanceOf(BlackPawn.class);
    }
}
