package chess.domain.position;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UnitDirectionTest {

    @Test
    void of() {
        final var source = new Position(File.A, Rank.ONE);
        final var target = new Position(File.H, Rank.EIGHT);

        final var path = UnitDirection.of(source, target);

        assertEquals(1, path.getRankDirection());
        assertEquals(1, path.getFileDirection());
    }

    @Test
    void of2() {
        final var source = new Position(File.A, Rank.ONE);
        final var target = new Position(File.H, Rank.ONE);

        final var path = UnitDirection.of(source, target);

        assertEquals(0, path.getRankDirection());
        assertEquals(1, path.getFileDirection());
    }

    @Test
    void of3() {
        final var source = new Position(File.A, Rank.ONE);
        final var target = new Position(File.A, Rank.EIGHT);

        final var path = UnitDirection.of(source, target);

        assertEquals(1, path.getRankDirection());
        assertEquals(0, path.getFileDirection());
    }

    @Test
    void of4() {
        final var source = new Position(File.A, Rank.ONE);
        final var target = new Position(File.A, Rank.ONE);

        assertThatThrownBy(() -> UnitDirection.of(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void of5() {
        final var source = new Position(File.A, Rank.ONE);
        final var target = new Position(File.H, Rank.TWO);

        final var path = UnitDirection.of(source, target);

        assertThat(path.getFileDirection()).isEqualTo(7);
        assertThat(path.getRankDirection()).isEqualTo(1);
    }

    @Test
    void of6() {
        final var source = new Position(File.A, Rank.ONE);
        final var target = new Position(File.H, Rank.THREE);

        final var path = UnitDirection.of(source, target);

        assertThat(path.getFileDirection()).isEqualTo(7);
        assertThat(path.getRankDirection()).isEqualTo(2);
    }

    @Test
    void isDiagonal() {
        final var source = new Position(File.A, Rank.ONE);
        final var target = new Position(File.H, Rank.EIGHT);

        final var path = UnitDirection.of(source, target);

        assertThat(path.isDiagonal()).isTrue();
    }

    @Test
    void isDiagonal2() {
        final var source = new Position(File.A, Rank.ONE);
        final var target = new Position(File.H, Rank.ONE);

        final var path = UnitDirection.of(source, target);

        assertThat(path.isDiagonal()).isFalse();
    }

    @Test
    void isDiagonal_false() {
        final var source = new Position(File.A, Rank.ONE);
        final var target = new Position(File.H, Rank.TWO);

        final var path = UnitDirection.of(source, target);

        assertThat(path.isDiagonal()).isFalse();
    }

    @Test
    void isStraight_true() {
        final var source = new Position(File.A, Rank.ONE);
        final var target = new Position(File.H, Rank.ONE);

        final var path = UnitDirection.of(source, target);

        assertThat(path.isStraight()).isTrue();
    }

    @Test
    void isStraight_false() {
        final var source = new Position(File.A, Rank.ONE);
        final var target = new Position(File.H, Rank.TWO);

        final var path = UnitDirection.of(source, target);

        assertThat(path.isStraight()).isFalse();
    }

    @Test
    void computePath() {
        final var source = new Position(File.A, Rank.ONE);
        final var target = new Position(File.H, Rank.EIGHT);

        final var path = UnitDirection.of(source, target);

        assertThat(path.computePath(source, target)).containsExactlyInAnyOrder(
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
    void computePath2() {
        final var source = new Position(File.A, Rank.THREE);
        final var target = new Position(File.H, Rank.SEVEN);

        final var path = UnitDirection.of(source, target);

        assertThat(path.computePath(source, target)).containsExactlyInAnyOrder(
                new Position(File.H, Rank.SEVEN)
        );
    }

    @Test
    void computePath3() {
        final var source = new Position(File.A, Rank.THREE);
        final var target = new Position(File.C, Rank.SEVEN);

        final var path = UnitDirection.of(source, target);

        assertThat(path.computePath(source, target)).containsExactlyInAnyOrder(
                new Position(File.B, Rank.FIVE),
                new Position(File.C, Rank.SEVEN)
        );
    }
}
