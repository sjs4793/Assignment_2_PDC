package chessgame.version.pkg2;

import java.util.ArrayList;

public class Player {
    private ArrayList<ChessPiece> chessPieces;

    public Player(Boolean isPLayerOne, ChessPiece[][] gameBoard){
        chessPieces = new ArrayList<>();
        assignPieces(isPLayerOne, gameBoard);
    }

    public void assignPieces(Boolean isPLayerOne, ChessPiece[][] gameBoard){
        int pawnRow;
        int firstRow;

        if (isPLayerOne) {
            firstRow = 0;
            pawnRow = 1;
        }
        else{
            firstRow = 7;
            pawnRow = 6;
        }
//
//        for (int i = 0; i < 8; i++){
//            chessPieces.add(new Pawn(new PiecePosition(pawnRow, i), PieceType.PAWN, gameBoard));
//        }

        chessPieces.add(new King(new PiecePosition(firstRow, 4), PieceType.KING, gameBoard));
        chessPieces.add(new Queen(new PiecePosition(firstRow, 3), PieceType.QUEEN, gameBoard));
        chessPieces.add(new Bishop(new PiecePosition(firstRow, 2), PieceType.BISHOP, gameBoard));
        chessPieces.add(new Bishop(new PiecePosition(firstRow, 6), PieceType.BISHOP, gameBoard));
        chessPieces.add(new Knight(new PiecePosition(firstRow, 1), PieceType.KNIGHT, gameBoard));
        chessPieces.add(new Knight(new PiecePosition(firstRow, 5), PieceType.KNIGHT, gameBoard));
        chessPieces.add(new Rook(new PiecePosition(firstRow, 0), PieceType.ROOK, gameBoard));
        chessPieces.add(new Rook(new PiecePosition(firstRow, 7), PieceType.ROOK, gameBoard));

        setPiecesPlayer(isPLayerOne);
    }

    public void setPiecesPlayer(boolean isPLayerOne){
        for (int i = 0; i <chessPieces.size(); i++){
            chessPieces.get(i).setPlayerOne(isPLayerOne);
        }
    }

    public ArrayList<ChessPiece> getChessPieces() {
        return chessPieces;
    }
}
