package chess.domain.piece;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class QueenTest {

    @Test
    void computeMovablePositions_diagonal() {
        final var source = new Position(File.A, Rank.ONE);
        final var target = new Position(File.H, Rank.EIGHT);

        final var queen = new Queen(source);

        List<Position> positions = queen.computeMovablePositions(target);

        assertThat(positions).containsExactlyInAnyOrder(
                new Position(File.B, Rank.TWO),
                new Position(File.C, Rank.THREE),
                new Position(File.D, Rank.FOUR),
                new Position(File.E, Rank.FIVE),
                new Position(File.F, Rank.SIX),
                new Position(File.G, Rank.SEVEN),
                new Position(File.H, Rank.EIGHT)
        );
    }

    @Test
    void computeMovablePositions_straight() {
        final var source = new Position(File.A, Rank.ONE);
        final var target = new Position(File.A, Rank.EIGHT);

        final var queen = new Queen(source);

        List<Position> positions = queen.computeMovablePositions(target);

        assertThat(positions).containsExactlyInAnyOrder(
                new Position(File.A, Rank.TWO),
                new Position(File.A, Rank.THREE),
                new Position(File.A, Rank.FOUR),
                new Position(File.A, Rank.FIVE),
                new Position(File.A, Rank.SIX),
                new Position(File.A, Rank.SEVEN),
                new Position(File.A, Rank.EIGHT)
        );
    }

    @Test
    void computeMovablePositions2() {
        final var source = new Position(File.C, Rank.THREE);
        final var target = new Position(File.E, Rank.ONE);

        final var queen = new Queen(source);

        List<Position> positions = queen.computeMovablePositions(target);

        assertThat(positions).containsExactlyInAnyOrder(
                new Position(File.D, Rank.TWO),
                new Position(File.E, Rank.ONE)
        );
    }

    @Test
    void computeMovablePositions_exception() {
        final var source = new Position(File.A, Rank.ONE);
        final var target = new Position(File.B, Rank.FOUR);

        final var queen = new Queen(source);

        assertThatThrownBy(() -> queen.computeMovablePositions(target))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
