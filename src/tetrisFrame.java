import javax.swing.*;
import java.awt.*;

public class tetrisFrame extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(Font.getFont("Helvetica Nueva Bold"));
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
        g.drawString("             Lines Cleared:", 420, 345);
        g.drawString("                Time Taken:", 420, 385);
        g.drawString("                 High Score:", 420, 425);
        g.drawString(" Time For High Score:", 420, 465);
    };

    public static void createAndShowGui() {
        JFrame gui = new JFrame("TetrisClone V1.0.0");
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setContentPane(new tetrisFrame());
        // gui.setLocationByPlatform(true);
        gui.setSize(600, 800);
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
