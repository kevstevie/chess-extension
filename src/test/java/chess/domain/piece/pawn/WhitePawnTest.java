package chess.domain.piece.pawn;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WhitePawnTest {

    @Test
    void computeMovablePositions_forward() {
        final var source = new Position(File.A, Rank.FOUR);
        final var target = new Position(File.A, Rank.FIVE);

        final var pawn = new WhitePawn(source);

        List<Position> positions = pawn.computeMovablePositions(target);

        assertThat(positions).containsExactlyInAnyOrder(
                new Position(File.A, Rank.FIVE)
        );
    }

    @Test
    void computeMovablePosition_diagonal() {
        final var source = new Position(File.A, Rank.FOUR);
        final var target = new Position(File.B, Rank.FIVE);

        final var pawn = new WhitePawn(source);

        List<Position> positions = pawn.computeMovablePositions(target);

        assertThat(positions).containsExactlyInAnyOrder(
                new Position(File.B, Rank.FIVE)
        );
    }

    @Test
    void computeMovablePositions_forwardTwo_empty() {
        final var source = new Position(File.A, Rank.FOUR);
        final var target = new Position(File.A, Rank.SIX);

        final var pawn = new WhitePawn(source);

        List<Position> positions = pawn.computeMovablePositions(target);

        assertThat(positions).isEmpty();
    }

    @Test
    void computeMovablePositions_diagonal_empty() {
        final var source = new Position(File.A, Rank.FOUR);
        final var target = new Position(File.C, Rank.SIX);

        final var pawn = new WhitePawn(source);

        List<Position> positions = pawn.computeMovablePositions(target);

        assertThat(positions).isEmpty();
    }
}
