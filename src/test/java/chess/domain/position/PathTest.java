package chess.domain.position;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PathTest {

    @Test
    void of() {
        final var source = new Position(File.A, Rank.ONE);
        final var target = new Position(File.H, Rank.EIGHT);

        final var path = Path.of(source, target);

        assertEquals(1, path.getRankDirection());
        assertEquals(1, path.getFileDirection());
    }

    @Test
    void of2() {
        final var source = new Position(File.A, Rank.ONE);
        final var target = new Position(File.H, Rank.ONE);

        final var path = Path.of(source, target);

        assertEquals(0, path.getRankDirection());
        assertEquals(1, path.getFileDirection());
    }

    @Test
    void of3() {
        final var source = new Position(File.A, Rank.ONE);
        final var target = new Position(File.A, Rank.EIGHT);

        final var path = Path.of(source, target);

        assertEquals(1, path.getRankDirection());
        assertEquals(0, path.getFileDirection());
    }

    @Test
    void of4() {
        final var source = new Position(File.A, Rank.ONE);
        final var target = new Position(File.A, Rank.ONE);

        final var path = Path.of(source, target);

        assertEquals(0, path.getRankDirection());
        assertEquals(0, path.getFileDirection());
    }

    @Test
    void of5() {
        final var source = new Position(File.A, Rank.ONE);
        final var target = new Position(File.H, Rank.TWO);

        final var path = Path.of(source, target);

        assertEquals(1, path.getRankDirection());
        assertEquals(1, path.getFileDirection());
    }

    @Test
    void of6() {
        final var source = new Position(File.A, Rank.ONE);
        final var target = new Position(File.H, Rank.THREE);

        final var path = Path.of(source, target);

        assertEquals(2, path.getRankDirection());
        assertEquals(1, path.getFileDirection());
    }
}
