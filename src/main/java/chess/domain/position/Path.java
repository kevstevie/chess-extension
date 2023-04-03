package chess.domain.position;

import java.math.BigInteger;

public final class Path {
    private final int rankDirection;
    private final int fileDirection;

    private Path(final int rankDirection, final int fileDirection) {
        this.rankDirection = rankDirection;
        this.fileDirection = fileDirection;
    }

    public static Path of(final Position source, final Position target) {
        final int rankDirection = source.computeRankDirection(target);
        final int fileDirection = source.computeFileDirection(target);

        final var gcd = BigInteger.valueOf(rankDirection)
                .gcd(BigInteger.valueOf(fileDirection))
                .intValue();

        final var unitRank = rankDirection / gcd;
        final var unitFile = fileDirection / gcd;

        return new Path(unitRank, unitFile);
    }

    public int getRankDirection() {
        return rankDirection;
    }

    public int getFileDirection() {
        return fileDirection;
    }
}
