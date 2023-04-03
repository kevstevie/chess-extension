package chess.domain.piece.pawn;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class WhitePawnTest {

    @Test
    void computeMovablePositions_forward() {
        final var source = new Position(File.A, Rank.FOUR);
        final var target = new Position(File.A, Rank.FIVE);

        final var pawn = new WhitePawn();

        List<Position> positions = pawn.computeMovablePositions(source, target);

        assertThat(positions).containsExactlyInAnyOrder(
                new Position(File.A, Rank.FIVE)
        );
    }

    @Test
    void computeMovablePositin_diagonal() {
        final var source = new Position(File.A, Rank.FOUR);
        final var target = new Position(File.B, Rank.FIVE);

        final var pawn = new WhitePawn();

        List<Position> positions = pawn.computeMovablePositions(source, target);

        assertThat(positions).containsExactlyInAnyOrder(
                new Position(File.B, Rank.FIVE)
        );
    }

    @Test
    void computeMovablePositions_fowardTwo_exception() {
        final var source = new Position(File.A, Rank.FOUR);
        final var target = new Position(File.A, Rank.SIX);

        final var pawn = new WhitePawn();

        assertThatThrownBy(() -> pawn.computeMovablePositions(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void computeMovablePositions_digonal_exception() {
        final var source = new Position(File.A, Rank.FOUR);
        final var target = new Position(File.C, Rank.SIX);

        final var pawn = new WhitePawn();

        assertThatThrownBy(() -> pawn.computeMovablePositions(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
