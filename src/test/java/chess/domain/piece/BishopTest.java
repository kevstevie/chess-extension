package chess.domain.piece;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {

    @Test
    void computeMovablePositions() {
        final var source = new Position(File.A, Rank.ONE);
        final var target = new Position(File.H, Rank.EIGHT);

        final var bishop = new Bishop(source);

        List<Position> positions = bishop.computeMovablePositions(target);

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
    void computeMovablePositions2() {
        final var source = new Position(File.C, Rank.THREE);
        final var target = new Position(File.E, Rank.ONE);

        final var bishop = new Bishop(source);

        List<Position> positions = bishop.computeMovablePositions(target);

        assertThat(positions).containsExactlyInAnyOrder(
                new Position(File.D, Rank.TWO),
                new Position(File.E, Rank.ONE)
        );
    }

    @Test
    void computeMovablePositions_illegalMove_empty() {
        final var source = new Position(File.A, Rank.ONE);
        final var target = new Position(File.H, Rank.ONE);

        final var bishop = new Bishop(source);

        List<Position> positions = bishop.computeMovablePositions(target);

        assertThat(positions).isEmpty();
    }

    @Test
    void computeAllPath() {
        final var source = new Position(File.A, Rank.ONE);

        final var bishop = new Bishop(source);

        Set<Position> positions = bishop.computeAllPath();

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
    void computeAllPath_B3() {
        final var source = new Position(File.B, Rank.THREE);

        final var bishop = new Bishop(source);

        Set<Position> positions = bishop.computeAllPath();

        assertThat(positions).containsExactlyInAnyOrder(
                new Position(File.A, Rank.TWO),
                new Position(File.C, Rank.FOUR),
                new Position(File.D, Rank.FIVE),
                new Position(File.E, Rank.SIX),
                new Position(File.F, Rank.SEVEN),
                new Position(File.G, Rank.EIGHT),
                new Position(File.A, Rank.FOUR),
                new Position(File.C, Rank.TWO),
                new Position(File.D, Rank.ONE)
        );
    }
}
