package chess.domain.piece.pawn;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class WhiteInitialPawnTest {

    @Test
    void computeMovablePositions_forwardTwo() {
        final var source = new Position(File.A, Rank.TWO);
        final var target = new Position(File.A, Rank.FOUR);

        final var pawn = new WhiteInitialPawn(source);

        List<Position> positions = pawn.computeMovablePositions(target);

        assertThat(positions).containsExactlyInAnyOrder(
                new Position(File.A, Rank.THREE),
                new Position(File.A, Rank.FOUR)
        );
    }

    @Test
    void computeMovablePositions_forward() {
        final var source = new Position(File.A, Rank.TWO);
        final var target = new Position(File.A, Rank.THREE);

        final var pawn = new WhiteInitialPawn(source);

        List<Position> positions = pawn.computeMovablePositions(target);

        assertThat(positions).containsExactlyInAnyOrder(
                new Position(File.A, Rank.THREE)
        );
    }

    @Test
    void computeMovablePositions_diagonal() {
        final var source = new Position(File.A, Rank.TWO);
        final var target = new Position(File.B, Rank.THREE);

        final var pawn = new WhiteInitialPawn(source);

        List<Position> positions = pawn.computeMovablePositions(target);

        assertThat(positions).containsExactlyInAnyOrder(
                new Position(File.B, Rank.THREE)
        );
    }

    @Test
    void computeMovablePositions_illegalMove_empty() {
        final var source = new Position(File.A, Rank.TWO);
        final var target = new Position(File.A, Rank.ONE);

        final var pawn = new WhiteInitialPawn(source);

        List<Position> positions = pawn.computeMovablePositions(target);

        assertThat(positions).isEmpty();
    }

    @Test
    void computeMovablePositions_illegalMove_empty2() {
        final var source = new Position(File.A, Rank.TWO);
        final var target = new Position(File.C, Rank.FOUR);

        final var pawn = new WhiteInitialPawn(source);

        List<Position> positions = pawn.computeMovablePositions(target);

        assertThat(positions).isEmpty();
    }
}
