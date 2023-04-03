package chess.domain.position;

public final class Position {

    private final File file;
    private final Rank rank;

    public Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public int computeRankDirection(final Position target) {
        return this.rank.computeDistance(target.rank);
    }

    public int computeFileDirection(final Position target) {
        return this.file.computeDistance(target.file);
    }
}
