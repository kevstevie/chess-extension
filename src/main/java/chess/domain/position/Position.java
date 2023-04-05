package chess.domain.position;

import java.util.Objects;

public final class Position {

    private final File file;
    private final Rank rank;

    public Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public int computeRankDistance(final Position target) {
        return this.rank.computeDistance(target.rank);
    }

    public int computeFileDistance(final Position target) {
        return this.file.computeDistance(target.file);
    }

    public int computeDistance(final Position target) {
        return Math.abs(computeFileDistance(target)) + Math.abs(computeRankDistance(target));
    }

    public Position move(final int rankDirection, final int fileDirection) {
        Rank rankMoved = this.rank.move(rankDirection);
        File fileMoved = this.file.move(fileDirection);

        return new Position(fileMoved, rankMoved);
    }

    public boolean isNearSquare(final Position target) {
        return Math.abs(computeRankDistance(target)) <= 1 && Math.abs(computeFileDistance(target)) <= 1;
    }

    public boolean isSameFile(final Position target) {
        return this.file == target.file;
    }

    public boolean isSameRank(final int number) {
        return this.rank == Rank.of(number);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    @Override
    public String toString() {
        return "Position{" +
                "file=" + file +
                ", rank=" + rank +
                '}';
    }
}
