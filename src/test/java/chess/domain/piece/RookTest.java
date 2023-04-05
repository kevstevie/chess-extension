package chess.domain.piece;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {

    private static final Position B1 = new Position(File.B, Rank.ONE);
    private static final Position C1 = new Position(File.C, Rank.ONE);
    private static final Position D1 = new Position(File.D, Rank.ONE);
    private static final Position E1 = new Position(File.E, Rank.ONE);
    private static final Position F1 = new Position(File.F, Rank.ONE);
    private static final Position G1 = new Position(File.G, Rank.ONE);
    private static final Position H1 = new Position(File.H, Rank.ONE);

    @Test
    void computeMovablePositions_illegalMove_empty() {
        final var source = new Position(File.A, Rank.ONE);
        final var target = new Position(File.H, Rank.EIGHT);

        final var rook = new Rook(source);

        List<Position> positions = rook.computeMovablePositions(target);

        assertThat(positions).isEmpty();
    }

    @Test
    void computeMovablePositions_straight() {
        final var source = new Position(File.A, Rank.ONE);
        final var target = new Position(File.H, Rank.ONE);

        final var rook = new Rook(source);

        List<Position> positions =
                rook.computeMovablePositions(target);

        assertThat(positions).containsExactlyInAnyOrder(
                B1,
                C1,
                D1,
                E1,
                F1,
                G1,
                H1);
    }

    @Test
    void computeMovablePositions_straight2() {
        final var source = new Position(File.A, Rank.ONE);
        final var target = new Position(File.A, Rank.EIGHT);

        final var rook = new Rook(source);

        List<Position> positions =
                rook.computeMovablePositions(target);

        assertThat(positions).containsExactlyInAnyOrder(
                new Position(File.A, Rank.TWO),
                new Position(File.A, Rank.THREE),
                new Position(File.A, Rank.FOUR),
                new Position(File.A, Rank.FIVE),
                new Position(File.A, Rank.SIX),
                new Position(File.A, Rank.SEVEN),
                new Position(File.A, Rank.EIGHT));
    }
}
