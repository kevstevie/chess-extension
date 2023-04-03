package chess.domain.position;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public final class UnitDirection {
    private static final int DIAGONAL_INCLINATION = 1;
    private static final int ZERO_VECTOR = 0;

    private final int rankDirection;
    private final int fileDirection;

    private UnitDirection(final int rankDirection, final int fileDirection) {
        this.rankDirection = rankDirection;
        this.fileDirection = fileDirection;
    }

    public static UnitDirection of(final Position source, final Position target) {
        final int rankDirection = source.computeRankDirection(target);
        final int fileDirection = source.computeFileDirection(target);

        final var gcd = computeGcd(rankDirection, fileDirection);

        if (gcd == 0) {
            throw new IllegalArgumentException("같은 위치로는 이동할 수 없습니다.");
        }

        final var unitRank = rankDirection / gcd;
        final var unitFile = fileDirection / gcd;

        return new UnitDirection(unitRank, unitFile);
    }

    private static int computeGcd(final int rankDirection, final int fileDirection) {
        return BigInteger.valueOf(rankDirection)
                .gcd(BigInteger.valueOf(fileDirection))
                .intValue();
    }

    public List<Position> computePath(final Position source, final Position target) {
        List<Position> path = new ArrayList<>();
        while (!source.equals(target)) {
            source.move(rankDirection, fileDirection);
            path.add(source);
        }
        return path;
    }

    public boolean isDiagonal() {
        return Math.abs(rankDirection) == DIAGONAL_INCLINATION && Math.abs(fileDirection) == DIAGONAL_INCLINATION;
    }

    public boolean isStraight() {
        return rankDirection == ZERO_VECTOR || fileDirection == ZERO_VECTOR;
    }

    public int getRankDirection() {
        return rankDirection;
    }

    public int getFileDirection() {
        return fileDirection;
    }
}
