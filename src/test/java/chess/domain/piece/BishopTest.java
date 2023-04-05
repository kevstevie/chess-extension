package chess.domain.piece;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.Test;

import java.util.List;

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
}
