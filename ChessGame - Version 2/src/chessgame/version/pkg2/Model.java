package chessgame.version.pkg2;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;

public class Model extends Observable{
    public Database db;
    public final ChessPiece[][] tiles = new ChessPiece[8][8];
    public Player playerOne, playerTwo;
    public ChessPiece currentPiece;
    public Data data;

    public Model(){
        currentPiece = null;
        db = new Database();
        playerOne = new Player(true, tiles);
        playerTwo = new Player(false, tiles);
    }

    public void initialiseMap() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                for (int a = 0; a < playerOne.getChessPieces().size(); a++) {
                    if (playerOne.getChessPieces().get(a).getPiecePosition().getRow() == i &&
                            playerOne.getChessPieces().get(a).getPiecePosition().getCol() == j) {
                        tiles[i][j] = playerOne.getChessPieces().get(a);
                        break;
                    } else if (playerTwo.getChessPieces().get(a).getPiecePosition().getRow() == i &&
                            playerTwo.getChessPieces().get(a).getPiecePosition().getCol() == j) {
                        tiles[i][j] = playerTwo.getChessPieces().get(a);
                        break;
                    } else {
                        tiles[i][j] = new X(PieceType.X, tiles);
                    }
                }

                if ((i + j) % 2 == 0) {
                    tiles[i][j].setBackground(Color.decode("#3c777d"));
                } else {
                    tiles[i][j].setBackground(Color.WHITE);
                }

                tiles[i][j].appearance();
                tiles[i][j].setOpaque(true);
                tiles[i][j].setBorder(BorderFactory.createEmptyBorder());
            }
        }
    }

    public void reset(){
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                if (tiles[i][j].getBackground().equals(Color.decode("#faf3c0"))) {

                    if ((i + j) % 2 == 0) {
                        tiles[i][j].setBackground(Color.decode("#3c777d"));
                    } else {
                        tiles[i][j].setBackground(Color.WHITE);
                    }
                    tiles[i][j].setBorder(null);
                }
            }
        }
    }
}