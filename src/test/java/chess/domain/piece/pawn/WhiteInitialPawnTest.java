package chess.domain.piece.pawn;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class WhiteInitialPawnTest {

    @Test
    void computeMovablePositions_forwardTwo() {
        final var source = new Position(File.A, Rank.TWO);
        final var target = new Position(File.A, Rank.FOUR);

        final var pawn = new WhiteInitialPawn();

        List<Position> positions = pawn.computeMovablePositions(source, target);

        assertThat(positions).containsExactlyInAnyOrder(
                new Position(File.A, Rank.THREE),
                new Position(File.A, Rank.FOUR)
        );
    }

    @Test
    void computeMovablePositions_forward() {
        final var source = new Position(File.A, Rank.TWO);
        final var target = new Position(File.A, Rank.THREE);

        final var pawn = new WhiteInitialPawn();

        List<Position> positions = pawn.computeMovablePositions(source, target);

        assertThat(positions).containsExactlyInAnyOrder(
                new Position(File.A, Rank.THREE)
        );
    }

    @Test
    void computeMovablePositions_diagonal() {
        final var source = new Position(File.A, Rank.TWO);
        final var target = new Position(File.B, Rank.THREE);

        final var pawn = new WhiteInitialPawn();

        List<Position> positions = pawn.computeMovablePositions(source, target);

        assertThat(positions).containsExactlyInAnyOrder(
                new Position(File.B, Rank.THREE)
        );
    }

    @Test
    void computeMovablePositions_exception() {
        final var source = new Position(File.A, Rank.TWO);
        final var target = new Position(File.A, Rank.ONE);

        final var pawn = new WhiteInitialPawn();

        assertThatThrownBy(() -> pawn.computeMovablePositions(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void computeMovablePositions_exception2() {
        final var source = new Position(File.A, Rank.TWO);
        final var target = new Position(File.C, Rank.FOUR);

        final var pawn = new WhiteInitialPawn();

        assertThatThrownBy(() -> pawn.computeMovablePositions(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }
}