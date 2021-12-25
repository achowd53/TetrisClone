import javax.swing.*;
import java.awt.*;

public class tetrisFrame extends JPanel {
    private int linesCleared;
    private String timeElapsed;
    private int highScore;
    private String highTime;
    private int[][] board = {};
    private tetrisPiece curPiece = null;
    private tetrisPiece nextPiece = null;

    public void updateFrame(int[][] board, tetrisPiece curPiece, tetrisPiece nextPiece,
            int linesCleared, String timeElapsed, int highScore, String highTime) { // Repaints Panel with new data
        this.linesCleared = linesCleared;
        this.timeElapsed = timeElapsed;
        this.highScore = highScore;
        this.highTime = highTime;
        this.board = board;
        this.curPiece = curPiece;
        this.nextPiece = nextPiece;
        repaint();
    }

    // Red, Orange, Yellow, Lime, Cyan, Cerulean, Magenta, Rose, Gray, Black
    private Color[] intColor = { new Color(255, 0, 0), new Color(255, 165, 0), new Color(255, 255, 0),
            new Color(0, 255, 0), new Color(0, 255, 255), new Color(42, 82, 190), new Color(255, 0, 255),
            new Color(255, 0, 127), new Color(128, 128, 128), Color.BLACK };

    @Override
    protected void paintComponent(Graphics g) { // Paints Panel with data
        super.paintComponent(g);
        g.setFont(Font.getFont("Helvetica Nueva Bold"));
        g.setColor(Color.BLACK);
        // Seperate Tetris Frame from Side Bar
        for (int x = 400; x <= 420; x += 20)
            g.drawLine(x, 0, x, 800);
        // Create Grid in Tetris Frame
        for (int y = 0; y < 800; y += 40)
            g.drawLine(0, y, 400, y);
        for (int x = 0; x < 400; x += 40)
            g.drawLine(x, 0, x, 800);
        // Draw Next Box in Side Bar
        for (int x = 440; x < 560; x += 40) {
            for (int y = 80; y < 240; y += 40) {
                g.drawRect(x, y, 40, 40);
            }
        }
        g.drawString("Next:", 440, 75);
        // Draw Score Box in Side Bar
        for (int y = 280; y <= 320; y += 20)
            g.drawLine(420, y, 600, y);
        for (int y = 360; y < 480; y += 40)
            g.drawLine(420, y, 600, y);
        for (int y = 480; y <= 520; y += 20)
            g.drawLine(420, y, 600, y);
        g.drawString("             Lines Cleared: " + String.valueOf(linesCleared), 420, 345);
        g.drawString("                Time Taken: " + timeElapsed, 420, 385);
        g.drawString("                 High Score: " + String.valueOf(highScore), 420, 425);
        g.drawString(" Time For High Score: " + highTime, 420, 465);
        // If game has started, display board, next, and tetris piece
        for (int[] box : board) {
            g.setColor(intColor[box[2]]);
            g.fillRect(box[0], box[1], 40, 40);
            g.setColor(Color.BLACK);
            g.drawRect(box[0], box[1], 40, 40);
        }
        if (curPiece != null) {
            for (int[] box : curPiece.getBoard()) {
                g.setColor(intColor[box[2]]);
                g.fillRect(box[0], box[1], 40, 40);
                g.setColor(Color.BLACK);
                g.drawRect(box[0], box[1], 40, 40);
            }
        }
        if (nextPiece != null) {
            for (int[] box : nextPiece.getNext()) {
                g.setColor(intColor[box[2]]);
                g.fillRect(box[0], box[1], 40, 40);
                g.setColor(Color.BLACK);
                g.drawRect(box[0], box[1], 40, 40);
            }
        }
    };

    public static void createAndShowGui() { // Intializes and Displays GUI
        JFrame gui = new JFrame("TetrisClone V1.0.0");
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setContentPane(new tetrisFrame());
        // gui.setLocationByPlatform(true);
        gui.setSize(600, 840);
        gui.setVisible(true);
    };

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        createAndShowGui();
                    }
                });
    };
}
