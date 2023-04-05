package chess.domain.piece;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {

    static Stream<Arguments> kingParameters() {
        return Stream.of(
                Arguments.arguments(new Position(File.A, Rank.FOUR), new Position(File.A, Rank.FIVE)),
                Arguments.arguments(new Position(File.A, Rank.FOUR), new Position(File.B, Rank.FIVE)),
                Arguments.arguments(new Position(File.A, Rank.FOUR), new Position(File.B, Rank.FOUR)),
                Arguments.arguments(new Position(File.A, Rank.FOUR), new Position(File.B, Rank.THREE)),
                Arguments.arguments(new Position(File.A, Rank.FOUR), new Position(File.A, Rank.THREE)),
                Arguments.arguments(new Position(File.B, Rank.FOUR), new Position(File.C, Rank.THREE)),
                Arguments.arguments(new Position(File.B, Rank.FOUR), new Position(File.C, Rank.FOUR)),
                Arguments.arguments(new Position(File.B, Rank.FOUR), new Position(File.C, Rank.FIVE))
        );
    }

    @ParameterizedTest
    @MethodSource("kingParameters")
    void computeMovablePositions(final Position source, final Position target) {
        final var king = new King(source);

        List<Position> positions = king.computeMovablePositions(target);

        assertThat(positions).containsExactly(target);
    }

    static Stream<Arguments> kingNotMovable() {
        return Stream.of(
                Arguments.arguments(new Position(File.A, Rank.FOUR), new Position(File.A, Rank.SIX)),
                Arguments.arguments(new Position(File.A, Rank.FOUR), new Position(File.C, Rank.SIX)),
                Arguments.arguments(new Position(File.A, Rank.FOUR), new Position(File.C, Rank.FOUR)),
                Arguments.arguments(new Position(File.A, Rank.FOUR), new Position(File.C, Rank.TWO)),
                Arguments.arguments(new Position(File.A, Rank.FOUR), new Position(File.A, Rank.TWO)),
                Arguments.arguments(new Position(File.B, Rank.FOUR), new Position(File.D, Rank.TWO)),
                Arguments.arguments(new Position(File.B, Rank.FOUR), new Position(File.D, Rank.FOUR)),
                Arguments.arguments(new Position(File.B, Rank.FOUR), new Position(File.D, Rank.SIX))
        );
    }

    @ParameterizedTest
    @MethodSource("kingNotMovable")
    void computeMovablePositions_illegal_empty(final Position source, final Position target) {
        final var king = new King(source);

        List<Position> positions = king.computeMovablePositions(target);

        assertThat(positions).isEmpty();
    }
}
