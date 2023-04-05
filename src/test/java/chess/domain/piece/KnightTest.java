package chess.domain.piece;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {

    @Test
    void computeMovablePositions() {
        final var source = new Position(File.A, Rank.ONE);
        final var target = new Position(File.C, Rank.TWO);

        final var knight = new Knight(source);

        List<Position> positions = knight.computeMovablePositions(target);

        assertThat(positions).containsExactlyInAnyOrder(
                new Position(File.C, Rank.TWO)
        );
    }

    @Test
    void computeMovablePositions2() {
        final var source = new Position(File.C, Rank.THREE);
        final var target = new Position(File.B, Rank.FIVE);

        final var knight = new Knight(source);

        List<Position> positions = knight.computeMovablePositions(target);

        assertThat(positions).containsExactlyInAnyOrder(
                new Position(File.B, Rank.FIVE)
        );
    }

    @Test
    void computeMovablePositions3() {
        final var source = new Position(File.D, Rank.THREE);
        final var target = new Position(File.B, Rank.TWO);

        final var knight = new Knight(source);

        List<Position> positions = knight.computeMovablePositions(target);

        assertThat(positions).containsExactlyInAnyOrder(
                new Position(File.B, Rank.TWO)
        );
    }

    @Test
    void computeMovablePositions_illegalMove_empty() {
        final var source = new Position(File.A, Rank.ONE);
        final var target = new Position(File.C, Rank.THREE);

        final var knight = new Knight(source);

        List<Position> positions = knight.computeMovablePositions(target);

        assertThat(positions).isEmpty();
    }

    @Test
    void computeMovablePositions_illegalMove_empty2() {
        final var source = new Position(File.A, Rank.ONE);
        final var target = new Position(File.C, Rank.FIVE);

        final var knight = new Knight(source);

        List<Position> positions = knight.computeMovablePositions(target);

        assertThat(positions).isEmpty();
    }
}
