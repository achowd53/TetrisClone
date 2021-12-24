import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Random;

public class tetrisGame {
    private static int linesCleared = 0;
    private static int[][] board = {};
    private static boolean gameStarted = false;
    private static tetrisPiece curPiece;
    private static tetrisPiece nextPiece;
    private static tetrisFrame content;
    private static long startTime;

    public static class tetrisListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
                curPiece.shiftLeft();
                if (!curPiece.checkValidBounds(board))
                    curPiece.shiftRight();
                else
                    updateContent();
            } else if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                curPiece.shiftRight();
                if (!curPiece.checkValidBounds(board))
                    curPiece.shiftLeft();
                else
                    updateContent();
            } else if (e.getKeyCode() == KeyEvent.VK_J) {
                curPiece.rotateLeft();
                if (!curPiece.checkValidBounds(board))
                    curPiece.rotateRight();
                else
                    updateContent();
            } else if (e.getKeyCode() == KeyEvent.VK_K) {
                curPiece.rotateRight();
                if (!curPiece.checkValidBounds(board))
                    curPiece.rotateLeft();
                else
                    updateContent();
            } else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
                curPiece.downOne();
                if (!curPiece.checkCollision(board)) {
                    updateContent();
                    curPiece.downOne();
                }
                updateContent();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    private static boolean clearLines() {
        int[] fullLines = new int[20];
        boolean cleared = false;
        for (int[] box : board)
            fullLines[box[1] / 40] += 1;
        for (int i = 0; i < 20; i++) {
            if (fullLines[i] == 10) {
                final int y = i;
                linesCleared += 1;
                cleared = true;
                board = Arrays.stream(board).filter(x -> x[1] != y * 40).toArray(int[][]::new);
                for (int j = 0; j < board.length; j++) {
                    if (board[j][1] < y * 40)
                        board[j][1] += 40;
                }
            }
        }
        return cleared;
    }

    public static String getTime() {
        long timeElapsed = (System.nanoTime() - startTime) / ((long) 1e9);
        int seconds = (int) timeElapsed % 60;
        String second_part = String.valueOf(seconds);
        if (seconds < 10)
            second_part = "0" + second_part;
        int minutes = (int) timeElapsed / 60;
        String minute_part = String.valueOf(minutes);
        if (minutes < 10)
            minute_part = "0" + minute_part;
        return minute_part + ":" + second_part;
    }

    public static void updateContent() {
        if (curPiece.checkCollision(board))
            connectPiece();
        content.updateFrame(!gameStarted, board, curPiece, nextPiece, linesCleared, getTime());
        if (clearLines())
            content.updateFrame(!gameStarted, board, curPiece, nextPiece, linesCleared, getTime());
    }

    public static void connectPiece() {
        board = curPiece.addToBoard(board);
        curPiece = nextPiece;
        nextPiece = randomTetrisPiece();
        if (!curPiece.checkValidBounds(board)) { // Lose Game, instead freeze board until new game clicked
            board = new int[][] {};
            startTime = System.nanoTime();
            linesCleared = 0;
        }
    }

    public static tetrisPiece randomTetrisPiece() {
        tetrisPiece.tetromino[] intToPiece = { tetrisPiece.tetromino.O, tetrisPiece.tetromino.I,
                tetrisPiece.tetromino.S, tetrisPiece.tetromino.Z, tetrisPiece.tetromino.L,
                tetrisPiece.tetromino.J, tetrisPiece.tetromino.T };
        Random rand = new Random();
        return new tetrisPiece(intToPiece[rand.nextInt(7)], rand.nextInt(9));
    }

    public static void gameLogic() {
        curPiece.downOne();
        updateContent();
    }

    public static void createAndShowGui() {
        JFrame gui = new JFrame("TetrisClone V1.0.0");
        content = new tetrisFrame();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setContentPane(content);
        gui.addKeyListener(new tetrisListener());
        gui.setSize(600, 840);
        gui.setVisible(true);
        curPiece = new tetrisPiece(tetrisPiece.tetromino.O, 0);
        nextPiece = randomTetrisPiece();
        startTime = System.nanoTime();
        int delay = 500;
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                gameLogic();
            }
        };
        new Timer(delay, taskPerformer).start();

    };

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        createAndShowGui();
                    }
                });
    }
}
