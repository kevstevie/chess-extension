package chess.domain.piece.pawn;

import chess.domain.position.Position;
import chess.domain.position.UnitDirection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class EnPassantBlackPawn implements PawnStatus {

    private static final int FORWARD_ONE_SQUARE = 1;

    private final Position position;

    public EnPassantBlackPawn(final Position position) {
        this.position = position;
    }

    @Override
    public List<Position> computeMovablePositions(final Position target) {
        final var unitDirection = UnitDirection.of(position, target);
        if (BlackPawnDirection.isMovable(unitDirection) && position.computeRankDistance(target) == FORWARD_ONE_SQUARE) {
            return unitDirection.computePath(position, target);
        }
        return new ArrayList<>();
    }

    @Override
    public boolean isPromotable() {
        return false;
    }

    @Override
    public Set<Position> computeAllPath() {
        Set<Position> allPath = new HashSet<>();
        for (BlackPawnDirection value : BlackPawnDirection.values()) {
            if (position.isInBoardAfterMove(value.x(), value.y())) {
                allPath.add(position.move(value.x(), value.y()));
            }
        }
        return allPath;
    }

    @Override
    public PawnStatus move(final Position target) {
        return new BlackPawn(target);
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
