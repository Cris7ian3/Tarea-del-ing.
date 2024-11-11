import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private TicTacToeGame game;

    public TicTacToeGUI() {
        game = new TicTacToeGame();
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[row][col].setFocusPainted(false);
                buttons[row][col].addActionListener(this);
                add(buttons[row][col]);
            }
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        int row = -1, col = -1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (clickedButton == buttons[i][j]) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        if (row != -1 && col != -1 && game.makeMove(row, col)) {
            clickedButton.setText(String.valueOf(game.getCurrentPlayer()));

            if (game.checkForWin()) {
                JOptionPane.showMessageDialog(this, "Player " + game.getCurrentPlayer() + " wins!");
                game.resetBoard();
                resetGUI();
            } else if (game.isBoardFull()) {
                JOptionPane.showMessageDialog(this, "It's a tie!");
                game.resetBoard();
                resetGUI();
            } else {
                game.switchPlayer();
            }
        }
    }

    private void resetGUI() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
    }
}
