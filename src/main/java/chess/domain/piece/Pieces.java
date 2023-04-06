package chess.domain.piece;

import chess.domain.piece.castle.King;
import chess.domain.piece.castle.Rook;
import chess.domain.piece.pawn.BlackInitialPawn;
import chess.domain.piece.pawn.Pawn;
import chess.domain.piece.pawn.WhiteInitialPawn;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public final class Pieces {

    private static final List<Piece> initialWhitePieces = new ArrayList<>();
    private static final List<Piece> initialBlackPieces = new ArrayList<>();

    static {
        initializeWhitePieces();
        initializeWhitePawns();
        initializeBlackPieces();
        initializeBlackPawns();
    }

    private final List<Piece> pieces;

    public Pieces(final List<Piece> pieces) {
        this.pieces = pieces;
    }

    public static Pieces ofInitialWhitePieces() {
        return new Pieces(new ArrayList<>(initialWhitePieces));
    }

    public static Pieces ofInitialBlackPieces() {
        return new Pieces(new ArrayList<>(initialBlackPieces));
    }

    private static void initializeBlackPawns() {
        initialBlackPieces.add(new Pawn(new Position(File.A, Rank.SEVEN), new BlackInitialPawn()));
        initialBlackPieces.add(new Pawn(new Position(File.B, Rank.SEVEN), new BlackInitialPawn()));
        initialBlackPieces.add(new Pawn(new Position(File.C, Rank.SEVEN), new BlackInitialPawn()));
        initialBlackPieces.add(new Pawn(new Position(File.D, Rank.SEVEN), new BlackInitialPawn()));
        initialBlackPieces.add(new Pawn(new Position(File.E, Rank.SEVEN), new BlackInitialPawn()));
        initialBlackPieces.add(new Pawn(new Position(File.F, Rank.SEVEN), new BlackInitialPawn()));
        initialBlackPieces.add(new Pawn(new Position(File.G, Rank.SEVEN), new BlackInitialPawn()));
        initialBlackPieces.add(new Pawn(new Position(File.H, Rank.SEVEN), new BlackInitialPawn()));
    }

    private static void initializeBlackPieces() {
        initialBlackPieces.add(new Rook(new Position(File.A, Rank.EIGHT)));
        initialBlackPieces.add(new Knight(new Position(File.B, Rank.EIGHT)));
        initialBlackPieces.add(new Bishop(new Position(File.C, Rank.EIGHT)));
        initialBlackPieces.add(new Queen(new Position(File.D, Rank.EIGHT)));
        initialBlackPieces.add(new King(new Position(File.E, Rank.EIGHT)));
        initialBlackPieces.add(new Bishop(new Position(File.F, Rank.EIGHT)));
        initialBlackPieces.add(new Knight(new Position(File.G, Rank.EIGHT)));
        initialBlackPieces.add(new Rook(new Position(File.H, Rank.EIGHT)));
    }

    private static void initializeWhitePawns() {
        initialWhitePieces.add(new Pawn(new Position(File.A, Rank.TWO), new WhiteInitialPawn()));
        initialWhitePieces.add(new Pawn(new Position(File.B, Rank.TWO), new WhiteInitialPawn()));
        initialWhitePieces.add(new Pawn(new Position(File.C, Rank.TWO), new WhiteInitialPawn()));
        initialWhitePieces.add(new Pawn(new Position(File.D, Rank.TWO), new WhiteInitialPawn()));
        initialWhitePieces.add(new Pawn(new Position(File.E, Rank.TWO), new WhiteInitialPawn()));
        initialWhitePieces.add(new Pawn(new Position(File.F, Rank.TWO), new WhiteInitialPawn()));
        initialWhitePieces.add(new Pawn(new Position(File.G, Rank.TWO), new WhiteInitialPawn()));
        initialWhitePieces.add(new Pawn(new Position(File.H, Rank.TWO), new WhiteInitialPawn()));
    }

    private static void initializeWhitePieces() {
        initialWhitePieces.add(new Rook(new Position(File.A, Rank.ONE)));
        initialWhitePieces.add(new Knight(new Position(File.B, Rank.ONE)));
        initialWhitePieces.add(new Bishop(new Position(File.C, Rank.ONE)));
        initialWhitePieces.add(new Queen(new Position(File.D, Rank.ONE)));
        initialWhitePieces.add(new King(new Position(File.E, Rank.ONE)));
        initialWhitePieces.add(new Bishop(new Position(File.F, Rank.ONE)));
        initialWhitePieces.add(new Knight(new Position(File.G, Rank.ONE)));
        initialWhitePieces.add(new Rook(new Position(File.H, Rank.ONE)));
    }

    public Piece findPiece(final Position source) {
        return pieces.stream()
                .filter(piece -> piece.isSamePosition(source))
                .findFirst()
                .orElse(Empty.getInstance());
    }

    public void move(final Position source, final Position target) {
        final Piece piece = findPiece(source);
        final Piece pieceMoved = piece.move(target);
        pieces.remove(piece);
        pieces.add(pieceMoved);
    }

    public void remove(final Position target) {
        final Piece piece = findPiece(target);
        pieces.remove(piece);
    }

    public boolean hasPieceOnPath(final List<Position> movablePositions) {
        for (Position movablePosition : movablePositions) {
            boolean hasPiece = pieces.stream()
                    .anyMatch(piece -> piece.isSamePosition(movablePosition));
            if (hasPiece) {
                return true;
            }
        }
        return false;
    }

    public Position findKingPosition() {
        return pieces.stream()
                .filter(piece -> piece.getClass() == King.class)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("King이 없습니다."))
                .getPosition();
    }

    public List<List<Position>> findAllPath(final Position target) {
        return pieces.stream()
                .map(piece -> piece.computeMovablePositions(target))
                .filter(positions -> !positions.isEmpty())
                .collect(Collectors.toList());
    }

    public boolean isEmpty(final Position target) {
        return findPiece(target) == Empty.getInstance();
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public Map<Position, Set<Position>> findAllMoveWithoutTarget() {
        return pieces.stream()
                .collect(Collectors.toMap(Piece::getPosition, Piece::computeAllPath));
    }

    public Pieces copyPieces() {
        return new Pieces(new ArrayList<>(pieces));
    }
}
