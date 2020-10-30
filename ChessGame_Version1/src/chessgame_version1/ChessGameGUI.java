package chessgame_version1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;;
import java.io.IOException;

public class ChessGameGUI extends JPanel implements ActionListener{
    ChessBoard chessBoardPanel;
    JButton startGamebutton;

    public ChessGameGUI() {
        setLayout(null);
        super.setPreferredSize(new Dimension(750, 750));
        startGamebutton = new JButton("Start Game");
        startGamebutton.setLocation(330, 300);
        startGamebutton.setSize(100, 100);
        startGamebutton.addActionListener(this);
        add(startGamebutton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startGamebutton){
            remove(startGamebutton);
            try {
                chessBoardPanel = new ChessBoard();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            chessBoardPanel.initialiseMap();
            chessBoardPanel.setLocation(75, 75);
            chessBoardPanel.setSize(600, 600);
            setBackground(Color.decode("#2a575c"));
            add(chessBoardPanel);
        }
        revalidate();
        repaint();
    }


    private class ChessBoard extends JPanel implements ActionListener {
        Player playerOne, playerTwo;

        private final ChessPiece[][] tiles = new ChessPiece[8][8];
        public ChessPiece currentPiece = null;

        public ChessBoard() throws IOException {
            setLayout(new GridLayout(8, 8));
            super.setPreferredSize(new Dimension(600, 600));
            playerOne = new Player(true, tiles);
            playerTwo = new Player(false, tiles);
        }

        public void initialiseMap() {
            for (int i = 0; i < tiles.length; i++) {
                for (int j = 0; j < tiles[0].length; j++) {
                    for (int a = 0; a < playerOne.getChessPieces().size(); a++) {
                        if (playerOne.getChessPieces().get(a).getPiecePosition().getRow() == i &&
                                playerOne.getChessPieces().get(a).getPiecePosition().getCol() == j){
                            tiles[i][j] = playerOne.getChessPieces().get(a);
                            break;
                        }
                        else if (playerTwo.getChessPieces().get(a).getPiecePosition().getRow() == i &&
                                playerTwo.getChessPieces().get(a).getPiecePosition().getCol() == j){
                            tiles[i][j] = playerTwo.getChessPieces().get(a);
                            break;
                        }
                        else {
                            tiles[i][j] = new X(PieceType.X, tiles);
                        }
                    }

                    if ((i + j) % 2 == 0) {
                        tiles[i][j].setBackground(Color.decode("#3c777d"));
                    } else {
                        tiles[i][j].setBackground(Color.WHITE);
                    }

                    tiles[i][j].appearance();
                    tiles[i][j].addActionListener(this);
                    tiles[i][j].setOpaque(true);
                    tiles[i][j].setBorder(BorderFactory.createEmptyBorder());
                    add(tiles[i][j]);
                }
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();

            if (currentPiece == null) {
                for (int i = 0; i < tiles.length; i++) {
                    for (int j = 0; j < tiles[0].length; j++) {
                        if (source == tiles[i][j]) {
                            reset();
                            currentPiece = tiles[i][j];
                            tiles[i][j].possibleMoves();
                        }
                    }
                }
            }
            else {
                for (int i = 0; i < tiles.length; i++) {
                    for (int j = 0; j < tiles[0].length; j++) {
                        if (source == tiles[i][j]
                                && tiles[i][j] != currentPiece) {
                            if (tiles[i][j].getBackground().equals(Color.decode("#faf3c0"))) {
                                if (!tiles[i][j].getPieceType().equals(PieceType.X)) {

                                    Player aPlayer = playerOne;
                                    Boolean isPlayerOne =  true;

                                    if (!tiles[i][j].isPlayerOne()){
                                        aPlayer = playerTwo;
                                        isPlayerOne = false;
                                    }

                                    for (int a = 0; a < aPlayer.getChessPieces().size(); a++){
                                        if (aPlayer.getChessPieces().get(a).equals(tiles[i][j])){
                                            aPlayer.getChessPieces().remove(tiles[i][j]);
                                        }
                                    }
                                    
                                    remap(currentPiece, i, j);
                                    currentPiece = null;

                                }
                                else{
                                    remap(currentPiece, i, j);
                                    currentPiece = null;
                                }
                            }
                            else {
                                reset();
                                currentPiece = tiles[i][j];
                                currentPiece.possibleMoves();
                            }
                        }
                    }
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

        public void remap(ChessPiece chessPiece, int row, int col){
            Player aPlayer = playerOne;
            Boolean isPlayerOne =  true;

            if (!chessPiece.isPlayerOne()){
                aPlayer = playerTwo;
                isPlayerOne = false;
            }

            for (int i = 0; i < aPlayer.getChessPieces().size(); i++){
                if (aPlayer.getChessPieces().get(i).getPiecePosition().equals(chessPiece.getPiecePosition())){
                    aPlayer.getChessPieces().get(i).setPiecePosition(new PiecePosition(row, col));
                    break;
                }
            }

            for (int i = 0; i < tiles.length; i++) {
                for (int j = 0; j < tiles[0].length; j++) {
                    remove(tiles[i][j]);
                }
            }

            revalidate();
            repaint();
            initialiseMap();
        }
    }


    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("Chess GUI");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(new ChessGameGUI());
        frame.pack();
        frame.setVisible(true);
    }
}