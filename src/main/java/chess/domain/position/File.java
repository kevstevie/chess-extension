package chess.domain.position;

import java.util.Arrays;

public enum File {
    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8);

    private final int value;

    File(final int value) {
        this.value = value;
    }

    public File of(final int value) {
        return Arrays.stream(File.values())
                .filter(file -> file.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 파일 값입니다."));
    }

    public int computeDistance(final File target) {
        return target.value - this.value;
    }

    public File move(final int fileDirection) {
        return of(this.value + fileDirection);
    }

    public boolean isInBoardAfterMove(final int file) {
        return 1 <= file + this.value && file + this.value <= 8;
    }
}
