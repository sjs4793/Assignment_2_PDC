package chessgame.version.pkg2;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * The Bishop class is a subclass of the ChessPiece class. The objects of the Bishop class are
 * used to instantiate Bishop chess pieces.As inherited from the parent class ChessPiece,
 * The objects of this class are defined by a piece position, a piece type, and a game map.
 * @author Pulin Angurala
 */
public class Bishop extends ChessPiece {


    /**
     * The constructor for this class is inherited from the super class and takes in arguments
     * of a piece position, a piece type and a game map to which the piece is belonging to.
     * @param piecePosition is passed a position located in the game map
     * @param pieceType is passed a piece type from the enum PieceType class to specify the type
     *                  of the chess piece.
     * @param gameMap is passed a GameMap object.
     */
    public Bishop(PiecePosition piecePosition, PieceType pieceType, ChessPiece[][] gameMap) {
        super(piecePosition, pieceType, gameMap);
    }

    /**
     * This method is an abstract method inherited from the super class and is overridden to
     * actively determine and highlight the possible moves a pawn chess piece may make, when
     * on the game map.
     * @return true, if the king is within the possible moves spectrum of this chess piece
     */
    @Override
    public boolean possibleMoves(){
        boolean samePlayer = true;
        boolean bool =  false;
        for (int i = 0; i < getGameMap().length; i++) {
            for (int j = 0; j < getGameMap()[0].length; j++) {

                if (getGameMap()[i][j].equals(this) && getGameMap()[i][j].getPieceType().equals(PieceType.X)){
                    getGameMap()[i][j] = new X(PieceType.X, getGameMap());
                }

                if (getGameMap()[i][j].equals(this) && getPieceType().equals(PieceType.BISHOP)) {
                    if (!getGameMap()[i][j].isPlayerOne()){
                        samePlayer = false;
                    }
                    int a = 0;
                    while (j < 7 || i < 7 || getGameMap()[i+1][j+1].isPlayerOne() != samePlayer) {
                        a++;
                        if ((i+a) > 7 || (j+a) > 7){
                            break;
                        }
                        if (!getGameMap()[i+a][j+a].getPieceType().equals(PieceType.X)){
                            if (getGameMap()[i+a][j+a].isPlayerOne() != samePlayer) {
//                                getGameMap()[i + a][j+a].setInRange(true);
                                setAvailable(getGameMap()[i+a][j+a]);
                                if (getGameMap()[i+a][j+a].getPieceType().equals(PieceType.KING)){
                                    bool = true;
                                }
                            }
                            break;
                        }
//                        getGameMap()[i + a][j + a].setInRange(true);
                        setAvailable(getGameMap()[i+a][j+a]);
                    }
                    a = 0;

                    while(i > 0 && j > 0 ||  getGameMap()[i-a][j-a].isPlayerOne() != samePlayer){
                        a++;
                        if ((i-a) < 0 || (j-a) < 0){
                            break;
                        }
                        if (!getGameMap()[i-a][j-a].getPieceType().equals(PieceType.X)){
                            if (getGameMap()[i-a][j-a].isPlayerOne() != samePlayer) {
//                                getGameMap()[i-a][j-a].setInRange(true);
                                setAvailable(getGameMap()[i-a][j-a]);
                                if (getGameMap()[i-a][j-a].getPieceType().equals(PieceType.KING)){
                                    bool = true;
                                }
                            }
                            break;
                        }
                        setAvailable(getGameMap()[i-a][j-a]);
//                        getGameMap()[i-a][j-a].setInRange(true);
                    }

                    a = 0;

                    while(i < 7 && j > 0  || getGameMap()[i+a][j-a].isPlayerOne() != samePlayer){
                        a++;
                        if ((i+a) > 7 || (j-a) < 0){
                            break;
                        }
                        if (!getGameMap()[i+a][j-a].getPieceType().equals(PieceType.X)){
                            if (getGameMap()[i+a][j-a].isPlayerOne() != samePlayer) {
//                                getGameMap()[i + a][j-a].setInRange(true);
                                setAvailable(getGameMap()[i+a][j-a]);
                                if (getGameMap()[i+a][j-a].getPieceType().equals(PieceType.KING)){
                                    bool = true;
                                }
                            }
                            break;
                        }
//                        getGameMap()[i+a][j-a].setInRange(true);
                        setAvailable(getGameMap()[i+a][j-a]);
                    }


                    a = 0;

                    while (i > 0 && j!= 7 || getGameMap()[i-a][j+a].isPlayerOne() != samePlayer) {
                        a++;
                        if ((i - a) < 0 || (j + a) > getGameMap()[0].length - 1) {
                            break;
                        }
                        if (!getGameMap()[i-a][j+a].getPieceType().equals(PieceType.X)){
                            if (getGameMap()[i-a][j+a].isPlayerOne() != samePlayer) {
//                                getGameMap()[i-a][j+a].setInRange(true);
                                setAvailable(getGameMap()[i-a][j+a]);
                                if (getGameMap()[i-a][j+a].getPieceType().equals(PieceType.KING)){
                                    bool =  true;
                                }
                            }
                            break;
                        }
//                        getGameMap()[i - a][j + a].setInRange(true);
                        setAvailable(getGameMap()[i-a][j+a]);
                    }
                }
            }
        }
        return bool;
    }

    @Override
    public void appearance() {
        if (isPlayerOne()){
            setIcon(new ImageIcon("/Users/pulinangurala/IdeaProjects/PDC - Chess Game 2/fancy/BB.gif"));
        }
        else{
            setIcon(new ImageIcon("/Users/pulinangurala/IdeaProjects/PDC - Chess Game 2/fancy/WB.gif"));
        }
    }


    public void setAvailable(ChessPiece chessPiece){
        chessPiece.setBackground(Color.decode("#faf3c0"));
        chessPiece.setBorder(new LineBorder(Color.BLACK, 2));
    }
}
