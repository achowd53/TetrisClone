import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
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
    private static int highScore = 0;
    private static String highTime;
    private static File highScoreFile;
    private static JButton newGame;

    public static class tetrisListener implements KeyListener { // Keyboard Press Listeners for Actions

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (gameStarted) {
                if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) { // Shift Left
                    curPiece.shiftLeft();
                    if (!curPiece.checkValidBounds(board))
                        curPiece.shiftRight();
                    else
                        updateContent();
                } else if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) { // Shift Right
                    curPiece.shiftRight();
                    if (!curPiece.checkValidBounds(board))
                        curPiece.shiftLeft();
                    else
                        updateContent();
                } else if (e.getKeyCode() == KeyEvent.VK_J) { // Rotate Left
                    curPiece.rotateLeft();
                    if (!curPiece.checkValidBounds(board))
                        curPiece.rotateRight();
                    else
                        updateContent();
                } else if (e.getKeyCode() == KeyEvent.VK_K) { // Rotate Right
                    curPiece.rotateRight();
                    if (!curPiece.checkValidBounds(board))
                        curPiece.rotateLeft();
                    else
                        updateContent();
                } else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) { // Go Down
                    curPiece.downOne();
                    if (!curPiece.checkCollision(board)) {
                        updateContent();
                        curPiece.downOne();
                    }
                    updateContent();
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    private static boolean clearLines() { // Finds and clears all new filled lines on the board
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
        if (linesCleared > highScore) {
            highScore = linesCleared;
            highTime = getTime();
        }
        return cleared;
    }

    public static String getTime() { // Gets Elapsed Time from when game started
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

    public static void updateContent() { // Updates JPanel Content with new data
        if (curPiece.checkCollision(board))
            connectPiece();
        content.updateFrame(board, curPiece, nextPiece, linesCleared, getTime(), highScore, highTime);
        if (clearLines())
            content.updateFrame(board, curPiece, nextPiece, linesCleared, getTime(), highScore, highTime);
    }

    public static void loseGame() { // What happens when you lose the game
        gameStarted = false;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(highScoreFile))) {
            writer.write(String.valueOf(highScore) + "\n" + highTime);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void startGame() { // Initialize new game
        board = new int[][] {};
        linesCleared = 0;
        startTime = System.nanoTime();
        gameStarted = true;
        curPiece = randomTetrisPiece();
        nextPiece = randomTetrisPiece();
        content.updateFrame(board, curPiece, nextPiece, linesCleared, getTime(), highScore, highTime);
    }

    public static void connectPiece() { // Connects piece to board and generates new piece
        board = curPiece.addToBoard(board);
        curPiece = nextPiece;
        nextPiece = randomTetrisPiece();
        if (!curPiece.checkValidBounds(board)) { // Lose Game, instead freeze board until new game clicked
            loseGame();
        }
    }

    public static tetrisPiece randomTetrisPiece() { // Generates a new random tetris piece with random color
        tetrisPiece.tetromino[] intToPiece = { tetrisPiece.tetromino.O, tetrisPiece.tetromino.I,
                tetrisPiece.tetromino.S, tetrisPiece.tetromino.Z, tetrisPiece.tetromino.L,
                tetrisPiece.tetromino.J, tetrisPiece.tetromino.T };
        Random rand = new Random();
        return new tetrisPiece(intToPiece[rand.nextInt(7)], rand.nextInt(9));
    }

    public static void gameLogic() { // Main Game Logic Loop for lowering piece by 1 row and updating frame
        curPiece.downOne();
        updateContent();
    }

    public static void createAndShowGui() { // Initializes and Displays the GUI
        // Set Up JFrame
        JFrame gui = new JFrame("TetrisClone V1.0.0");
        // Add Tetris GUI TO JFrame
        content = new tetrisFrame();
        content.setLayout(null);
        // Set Up JFrame Defaults
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setContentPane(content);
        // Add Keybind Listener to JFrame
        gui.addKeyListener(new tetrisListener());
        // Add New Game Button
        newGame = new JButton("New Game");
        newGame.setFocusable(false);
        newGame.setBounds(450, 540, 100, 30);
        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                startGame();
            }
        });
        gui.add(newGame);
        // Show JFrame
        gui.setSize(600, 840);
        gui.setVisible(true);
        // Read High Score File
        URL path = tetrisGame.class.getResource("tetrisHighScore.txt");
        highScoreFile = new File(path.getFile().replace("%20", " "));
        try (BufferedReader reader = new BufferedReader(new FileReader(highScoreFile))) {
            highScore = Integer.parseInt(reader.readLine());
            highTime = new String(reader.readLine());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Set Up Game Timer
        int delay = 300;
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (gameStarted)
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
