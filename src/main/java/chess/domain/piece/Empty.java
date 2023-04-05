package chess.domain.piece;

import chess.domain.position.Position;

import java.util.List;

public final class Empty implements Piece {

    private static final Empty EMPTY = new Empty();

    private Empty() {
    }

    public static Empty getInstance() {
        return EMPTY;
    }

    @Override
    public List<Position> computeMovablePositions(final Position target) {
        throw new IllegalArgumentException("빈칸은 이동할 수 없습니다.");
    }

    @Override
    public boolean isSamePosition(final Position source) {
        throw new IllegalArgumentException("빈칸은 이동할 수 없습니다.");
    }

    @Override
    public Piece move(final Position target) {
        throw new IllegalArgumentException("빈칸은 이동할 수 없습니다.");
    }

    @Override
    public boolean confirmMove(final boolean isTargetEmpty, final Position target) {
        throw new IllegalArgumentException("빈칸은 이동할 수 없습니다.");
    }

    @Override
    public Position getPosition() {
        throw new IllegalArgumentException("빈칸은 이동할 수 없습니다.");
    }
}
