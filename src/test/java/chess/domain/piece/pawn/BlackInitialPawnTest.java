package chess.domain.piece.pawn;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class BlackInitialPawnTest {

    @Test
    void computeMovablePositions_diagonal() {
        final var source = new Position(File.A, Rank.TWO);
        final var target = new Position(File.B, Rank.ONE);

        final var pawn = new BlackInitialPawn();

        List<Position> positions = pawn.computeMovablePositions(source, target);

        assertThat(positions).containsExactlyInAnyOrder(
                new Position(File.B, Rank.ONE)
        );
    }

    @Test
    void computeMovablePositions_forwardTwo() {
        final var source = new Position(File.A, Rank.FOUR);
        final var target = new Position(File.A, Rank.TWO);

        final var pawn = new BlackInitialPawn();

        List<Position> positions = pawn.computeMovablePositions(source, target);

        assertThat(positions).containsExactlyInAnyOrder(
                new Position(File.A, Rank.TWO),
                new Position(File.A, Rank.THREE)
        );
    }

    @Test
    void computeMovablePositions_forward() {
        final var source = new Position(File.A, Rank.FOUR);
        final var target = new Position(File.A, Rank.THREE);

        final var pawn = new BlackInitialPawn();

        List<Position> positions = pawn.computeMovablePositions(source, target);

        assertThat(positions).containsExactlyInAnyOrder(
                new Position(File.A, Rank.THREE)
        );
    }

    @Test
    void computeMovablePositions_exception() {
        final var source = new Position(File.A, Rank.FOUR);
        final var target = new Position(File.A, Rank.FIVE);

        final var pawn = new BlackInitialPawn();

        assertThatThrownBy(() -> pawn.computeMovablePositions(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void computeMovablePositions_exception2() {
        final var source = new Position(File.A, Rank.FOUR);
        final var target = new Position(File.B, Rank.FIVE);

        final var pawn = new BlackInitialPawn();

        assertThatThrownBy(() -> pawn.computeMovablePositions(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
