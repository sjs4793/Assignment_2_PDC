package chessgame_version1;

import java.io.IOException;

/**
 * The X class is a subclass of the ChessPiece class. The objects of the X class are
 * used to instantiate X chess pieces. As inherited from the parent class ChessPiece,
 * The objects of this class are defined by a piece position, a piece type, and a game map.
 * The X chess pieces are used to instantiate empty tiles on a chess board, they
 * are not a designated piece from the original chess game and are only used for tile
 * representation purposes of this program.
 * @author Pulin Angurala
 */
public class X extends ChessPiece {

    /**
     * The constructor for this class is inherited from the super class and takes in arguments
     * of a piece position, a piece type and a game map to which the piece is belonging to.
     * @param pieceType is passed a piece type from the enum PieceType class to specify the type
     *                  of the chess piece.
     * @param gameMap is passed a GameMap object.
     */
    public X(PieceType pieceType, ChessPiece[][] gameMap){
        super(pieceType, gameMap);
    }

    /**
     * No modifications have been made as the X pieces are idle and only for
     * representation purposes.
     * @return false, always.
     */
    @Override
    public boolean possibleMoves() {
        for (int i = 0; i < getGameMap().length; i++){
            for (int j = 0; j < getGameMap()[0].length; j++){
//                if (getGameMap()[i][j].equals(this) && getGameMap()[i][j].getPieceType().equals(PieceType.PAWN)){
//                    getGameMap()[i][j] = new Pawn(getGameMap()[i][j].getPiecePosition(), PieceType.PAWN, getGameMap());
//                }
//                else if (getGameMap()[i][j].equals(this) && getGameMap()[i][j].getPieceType().equals(PieceType.KING)){
//                    getGameMap()[i][j] = new King(getGameMap()[i][j].getPiecePosition(), PieceType.KING, getGameMap());
//                }
//                else if (getGameMap()[i][j].equals(this) && getGameMap()[i][j].getPieceType().equals(PieceType.QUEEN)){
//                    getGameMap()[i][j] = new Queen(getGameMap()[i][j].getPiecePosition(), PieceType.QUEEN, getGameMap());
//                }
//                else if (getGameMap()[i][j].equals(this) && getGameMap()[i][j].getPieceType().equals(PieceType.ROOK)){
//                    getGameMap()[i][j] = new Rook(getGameMap()[i][j].getPiecePosition(), PieceType.ROOK, getGameMap());
//                }
//                else if (getGameMap()[i][j].equals(this) && getGameMap()[i][j].getPieceType().equals(PieceType.BISHOP)){
//                    getGameMap()[i][j] = new Bishop(getGameMap()[i][j].getPiecePosition(), PieceType.BISHOP, getGameMap());
//                }
//                else if (getGameMap()[i][j].equals(this) && getGameMap()[i][j].getPieceType().equals(PieceType.KNIGHT)){
//                    getGameMap()[i][j] = new Knight(getGameMap()[i][j].getPiecePosition(), PieceType.KNIGHT, getGameMap());
//                }
            }
        }
        return false;
    }

    @Override
    public void appearance() {

    }
}
