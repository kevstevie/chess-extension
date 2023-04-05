package chess.domain.game;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.player.Player;
import chess.domain.position.Position;

import java.util.List;

public final class ChessGame {

    private final Player whitePlayer;
    private final Player blackPlayer;
    private Player currentTurnPlayer;
    private Player waitingPlayer;

    public ChessGame(final Player whitePlayer, final Player blackPlayer) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.currentTurnPlayer = whitePlayer;
        this.waitingPlayer = blackPlayer;
    }

    public static ChessGame ofInitialPieces() {
        return new ChessGame(Player.ofInitialWhitePieces(), Player.ofInitialBlackPieces());
    }

    public void move(final Position source, final Position target) {
        validateOwnPiece(target);
        final Piece piece = currentTurnPlayer.findPiece(source);
        final List<Position> movablePositions = piece.computeMovablePositions(target);
        validateIsMovable(movablePositions);
        validatePath(movablePositions);
        boolean isTargetEmpty = waitingPlayer.isEmpty(target);
        if (piece.confirmMove(isTargetEmpty, target)) {
            currentTurnPlayer.move(source, target);
            removeIfOpponentHasPiece(target);
            changeTurn();
        }
    }

    private void validateIsMovable(final List<Position> movablePositions) {
        if (movablePositions.isEmpty()) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
    }

    private void validateOwnPiece(final Position target) {
        if (currentTurnPlayer.findPiece(target) != Empty.getInstance()) {
            throw new IllegalArgumentException("이미 다른 말이 있는 위치로 이동할 수 없습니다.");
        }
    }

    private void validatePath(final List<Position> movablePositions) {
        if (currentTurnPlayer.hasPieceOnPath(movablePositions)) {
            throw new IllegalArgumentException("말이 있는 경로로는 이동할 수 없습니다.");
        }
    }

    private void removeIfOpponentHasPiece(final Position target) {
        if (waitingPlayer.findPiece(target) != Empty.getInstance()) {
            waitingPlayer.remove(target);
        }
    }

    public void changeTurn() {
        final Player temp = currentTurnPlayer;
        currentTurnPlayer = waitingPlayer;
        waitingPlayer = temp;
    }

    public boolean isCheck() {
        final var kingPosition = waitingPlayer.findKingPosition();
        final List<List<Position>> allPath = currentTurnPlayer.findAllPath(kingPosition);
        boolean isPathExist = allPath.stream()
                .anyMatch(path -> path.contains(kingPosition));
        boolean isNotBlockedOwnPiece = allPath.stream()
                .noneMatch(path -> currentTurnPlayer.hasPieceOnPath(path));
        boolean isNotBlockedOpponentPiece = allPath.stream()
                .peek(path -> path.remove(kingPosition))
                .noneMatch(path -> waitingPlayer.hasPieceOnPath(path));

        return isPathExist && isNotBlockedOwnPiece && isNotBlockedOpponentPiece;
    }

    public Player getCurrentTurnPlayer() {
        return currentTurnPlayer;
    }

    public Player getWaitingPlayer() {
        return waitingPlayer;
    }
}
