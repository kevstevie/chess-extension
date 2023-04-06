package chess.domain.piece.pawn;

import chess.domain.position.Position;
import chess.domain.position.UnitDirection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class WhiteInitialPawn implements PawnStatus {

    private final Position position;

    public WhiteInitialPawn(final Position position) {
        this.position = position;
    }

    @Override
    public List<Position> computeMovablePositions(final Position target) {
        final var unitDirection = UnitDirection.of(position, target);
        if (WhitePawnDirection.isMovable(unitDirection) && position.computeDistance(target) <= 2) {
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
        for (WhitePawnDirection value : WhitePawnDirection.values()) {
            if (position.isInBoardAfterMove(value.x(), value.y())) {
                allPath.add(position.move(value.x(), value.y()));
            }
        }
        if (position.isInBoardAfterMove(2, 0)) {
            allPath.add(position.move(2, 0));
        }
        return allPath;
    }

    @Override
    public PawnStatus move(final Position target) {
        if (position.computeRankDistance(target) == 2) {
            return new EnPassantWhitePawn(target);
        }
        return new WhitePawn(target);
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
