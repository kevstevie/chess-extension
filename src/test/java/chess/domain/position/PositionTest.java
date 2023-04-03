package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void move() {
        final var source = new Position(File.A, Rank.ONE);

        Position move = source.move(1, 3);

        assertThat(move).isEqualTo(new Position(File.D, Rank.TWO));

    }

    @Test
    void move2() {
        final var source = new Position(File.A, Rank.ONE);

        assertThatThrownBy(() -> source.move(1, -1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void move_exception() {
        final var source = new Position(File.A, Rank.ONE);

        assertThatThrownBy(() -> source.move(1, 9))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
