package chess.domain.game;

import chess.domain.piece.Empty;
import chess.domain.player.Player;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

    public ChessGame(
            final Player whitePlayer,
            final Player blackPlayer,
            final Player currentTurnPlayer,
            final Player waitingPlayer
    ) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.currentTurnPlayer = currentTurnPlayer;
        this.waitingPlayer = waitingPlayer;
    }

    public static ChessGame ofInitialPieces() {
        return new ChessGame(Player.ofInitialWhitePieces(), Player.ofInitialBlackPieces());
    }

    public void move(final Position source, final Position target) {
        validateOwnPiece(target);
        final var piece = currentTurnPlayer.findPiece(source);
        final List<Position> movablePositions = piece.computeMovablePositions(target);
        validateIsMovable(movablePositions);
        validatePath(movablePositions);
        boolean isTargetEmpty = waitingPlayer.isEmpty(target);
        if (piece.confirmMove(isTargetEmpty, target)) {
            currentTurnPlayer.move(source, target);
            removeIfHasPiece(target, waitingPlayer);
            changeTurn();
        }
    }

    public void defenseMove(final Position source, final Position target) {
        final var piece = currentTurnPlayer.findPiece(source);
        final List<Position> movablePositions = piece.computeMovablePositions(target);
        if (currentTurnPlayer.hasPieceOnPath(movablePositions)) {
            return;
        }
        boolean isTargetEmpty = waitingPlayer.isEmpty(target);
        if (piece.confirmMove(isTargetEmpty, target)) {
            currentTurnPlayer.move(source, target);
            removeIfHasPiece(target, waitingPlayer);
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

    private void removeIfHasPiece(final Position target, final Player player) {
        if (player.findPiece(target) != Empty.getInstance()) {
            player.remove(target);
        }
    }

    public void changeTurn() {
        final Player temp = currentTurnPlayer;
        currentTurnPlayer = waitingPlayer;
        waitingPlayer = temp;
    }

    public boolean isCheck() {
        final var kingPosition = currentTurnPlayer.findKingPosition();
        final List<List<Position>> allPath = waitingPlayer.findAllPath(kingPosition);

        boolean isPathExist = allPath.stream()
                .anyMatch(path -> path.contains(kingPosition));
        boolean isNotBlockedOwnPiece = allPath.stream()
                .noneMatch(path -> waitingPlayer.hasPieceOnPath(path));
        boolean isNotBlockedOpponentPiece = allPath.stream()
                .peek(path -> path.remove(kingPosition))
                .noneMatch(path -> currentTurnPlayer.hasPieceOnPath(path));

        return isPathExist && isNotBlockedOwnPiece && isNotBlockedOpponentPiece;
    }

    public boolean isCheckMate() {
        Map<Position, Set<Position>> allMoveWithoutTarget = currentTurnPlayer.findAllMoveWithoutTarget();

        loop:
        for (Map.Entry<Position, Set<Position>> entry : allMoveWithoutTarget.entrySet()) {
            for (Position destination : entry.getValue()) {
                final var gameCopied = copyGame();
                final var currentTurnPlayerCopied = currentTurnPlayer.copyPlayer();
                if (currentTurnPlayerCopied.findPiece(destination) != Empty.getInstance()) {
                    continue;
                }
                gameCopied.defenseMove(entry.getKey(), destination);
                boolean check = gameCopied.isCheck();
                if (!check) {
                    return false;
                }
            }
        }
        return true;
    }

    private ChessGame copyGame() {
        final var whitePlayerCopy = this.whitePlayer.copyPlayer();
        final var blackPlayerCopy = this.blackPlayer.copyPlayer();
        final var currentTurnPlayerCopy = this.currentTurnPlayer.copyPlayer();
        final var waitingPlayerCopy = this.waitingPlayer.copyPlayer();
        final var chessGame = new ChessGame(whitePlayerCopy, blackPlayerCopy, currentTurnPlayerCopy, waitingPlayerCopy);
        return chessGame;
    }

    public Player getCurrentTurnPlayer() {
        return currentTurnPlayer;
    }

    public Player getWaitingPlayer() {
        return waitingPlayer;
    }
}
