package chessgame.version.pkg2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    public View view;
    public Model model;

    public Controller(View view, Model model){
        this.view = view;
        this.model = model;
        view.playerOne = model.playerOne;
        view.playerTwo = model.playerTwo;
        view.tiles = model.tiles;
        view.currentPiece = model.currentPiece;
        model.initialiseMap();
        this.view.addActionListener(this);

        for (int i = 0; i < view.tiles.length; i++){
            for (int j = 0; j < view.tiles[0].length; j++){
                view.chessBoardPanel.add(view.tiles[i][j]);
            }
        }
        view.revalidate();
        view.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (model.currentPiece == null) {
            for (int i = 0; i < model.tiles.length; i++) {
                for (int j = 0; j < model.tiles[0].length; j++) {
                    if (source == model.tiles[i][j]) {
                        model.reset();
                        model.currentPiece = model.tiles[i][j];
                        model.tiles[i][j].possibleMoves();
                    }
                }
            }
        }
        else {
            for (int i = 0; i < model.tiles.length; i++) {
                for (int j = 0; j < model.tiles[0].length; j++) {
                    if (source == model.tiles[i][j]
                            && model.tiles[i][j] != model.currentPiece) {
                        if (model.tiles[i][j].getBackground().equals(Color.decode("#faf3c0"))) {
                            if (!model.tiles[i][j].getPieceType().equals(PieceType.X)) {

                                Player aPlayer = model.playerOne;
                                Boolean isPlayerOne =  true;

                                if (!model.tiles[i][j].isPlayerOne()){
                                    aPlayer = model.playerTwo;
                                    isPlayerOne = false;
                                }

                                for (int a = 0; a < aPlayer.getChessPieces().size(); a++){
                                    if (aPlayer.getChessPieces().get(a).equals(model.tiles[i][j])){
                                        aPlayer.getChessPieces().remove(model.tiles[i][j]);
                                    }
                                }

                                remap(model.currentPiece, i, j);
                            }
                            else {
                                remap(model.currentPiece, i, j);
                            }
                            model.currentPiece = null;
                        }
                        else {
                            model.reset();
                            model.currentPiece = model.tiles[i][j];
                            model.currentPiece.possibleMoves();
                        }
                    }
                }
            }
        }
    }

    public void remap(ChessPiece chessPiece, int row, int col) {
        Player aPlayer = model.playerOne;

        if (!chessPiece.isPlayerOne()){
            aPlayer = model.playerTwo;
        }

        for (int i = 0; i < aPlayer.getChessPieces().size(); i++){
            if (aPlayer.getChessPieces().get(i).getPiecePosition().equals(chessPiece.getPiecePosition())){
                aPlayer.getChessPieces().get(i).setPiecePosition(new PiecePosition(row, col));
                break;
            }
        }

        for (int i = 0; i < view.tiles.length; i++){
            for (int j = 0; j < view.tiles[0].length; j++){
                view.chessBoardPanel.remove(view.tiles[i][j]);
            }
        }

        model.initialiseMap();

        for (int i = 0; i < view.tiles.length; i++){
            for (int j = 0; j < view.tiles[0].length; j++){
                view.chessBoardPanel.add(view.tiles[i][j]);
                view.addActionListener(this);
            }
        }

        view.revalidate();
        view.repaint();
    }
}