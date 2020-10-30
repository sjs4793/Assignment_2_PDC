package chessgame.version.pkg2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class View extends JFrame implements Observer {
   public JButton startButton;
    public ChessBoard chessBoardPanel;
    public Player playerOne, playerTwo;
    public ChessPiece[][] tiles = new ChessPiece[8][8];
    public ChessPiece currentPiece = null;

    public View() throws IOException {
        this.setSize(new Dimension(750, 750));
        chessBoardPanel = new ChessBoard();
        add(chessBoardPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    public class ChessBoard extends JPanel {
        public ChessBoard() throws IOException {
            setLayout(new GridLayout(8, 8));
            super.setPreferredSize(new Dimension(600, 600));
        }
    }


    public void addActionListener(ActionListener listener) {
        if (tiles != null) {
            for (int i = 0; i < tiles.length; i++) {
                for (int j = 0; j < tiles[0].length; j++) {
                    tiles[i][j].addActionListener(listener);
                }
            }
        }
    }
}