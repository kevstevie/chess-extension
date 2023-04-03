package chess.domain.piece;

import chess.domain.piece.pawn.BlackInitialPawn;
import chess.domain.piece.pawn.Pawn;
import chess.domain.piece.pawn.WhiteInitialPawn;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.ArrayList;
import java.util.List;

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

    public List<Piece> getPieces() {
        return pieces;
    }
}
