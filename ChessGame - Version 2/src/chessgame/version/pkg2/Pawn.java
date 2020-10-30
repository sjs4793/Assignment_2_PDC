package chessgame.version.pkg2;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Pawn extends ChessPiece{
    /**
     * The  constructor for this class takes in an argument of a piece position, piece type and a game map
     * to initialise a chess piece.
     *
     * @param piecePosition is passed piece position to store for each chess piece
     * @param pieceType     is passed a pieceType enum to define the the type of chess piece
     */
    public Pawn(PiecePosition piecePosition, PieceType pieceType, ChessPiece[][] gameMap)  {
        super(piecePosition, pieceType, gameMap);
    }


    @Override
    public boolean possibleMoves() {
        boolean opposite = true;
        int x  = 1;
        int startingRow = 0;

        if (isPlayerOne()){
            x = 1;
            startingRow = 1;
        }
        else{
            x = -1;
            startingRow = 6;
            opposite = false;
        }


        for (int i = 0; i < getGameMap().length; i++){
            for (int j = 0; j < getGameMap()[0].length; j++){

                if ((getGameMap()[i][j].equals(this)) && getPieceType().equals(PieceType.PAWN)){
                    System.out.println(getGameMap()[i][j].getPiecePosition());
                    if(i > 0 && i < 7 ) {
                        if (getGameMap()[i + (x)][j].getPieceType().equals(PieceType.X)
                                && getGameMap()[i + (2*x)][j].getPieceType().equals(PieceType.X) &&
                                getPiecePosition().getRow() == startingRow) {

                            setAvailable(getGameMap()[i + (x)][j]);
                            setAvailable(getGameMap()[i + (2*x)][j]);
                        }
                        if (getGameMap()[i + (x)][j].getPieceType().equals(PieceType.X)) {
                            setAvailable(getGameMap()[i + (x)][j]);
                        }
                        if (j < 7 && !getGameMap()[i + (x)][j + 1].getPieceType().equals(PieceType.X) &&
                                getGameMap()[i + (x)][j + 1].isPlayerOne() != opposite) {
                            setAvailable(getGameMap()[i + (x)][j + 1]);
                        }
                        if ((j > 0) && !getGameMap()[i + (x)][j - 1].getPieceType().equals(PieceType.X) &&
                                getGameMap()[i + (x)][j - 1].isPlayerOne() != opposite) {
                            setAvailable(getGameMap()[i + (x)][j - 1]);
                        }
                    }
//
//                    if (isPlayerOne()){
//                        promote(getGameMap()[i][j], 7);
//                    }
//                    else{
//                        promote(getGameMap()[i][j], 0);
//                        getGameMap()[i][j].setPlayerOne(false);
//                    }
                }
//                else if ((getGameMap()[i][j].equals(this) && getGameMap()[i][j].getPieceType().equals(PieceType.X))){
//                    getGameMap()[i][j] = new X(getGameMap()[i][j].getPiecePosition(), PieceType.X, getGameMap());
//                }
            }
        }
        return false;
    }

    @Override
    public void appearance() {
        if (isPlayerOne() && getPieceType().equals(PieceType.PAWN)){
            setIcon(new ImageIcon("/Users/pulinangurala/IdeaProjects/PDC - Chess Game 2/fancy/BP.gif"));
        }
        else{
            if(getPieceType().equals(PieceType.PAWN)) {
                setIcon(new ImageIcon("/Users/pulinangurala/IdeaProjects/PDC - Chess Game 2/fancy/WP.gif"));
            }
        }
    }



    public void setAvailable(ChessPiece chessPiece){
       chessPiece.setBackground(Color.decode("#faf3c0"));
       chessPiece.setBorder(new LineBorder(Color.BLACK, 2, true));
    }
}
