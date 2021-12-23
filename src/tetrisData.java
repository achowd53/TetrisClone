import javax.swing.*;
import java.awt.event.*;

public class tetrisData {

    public static void createAndShowGui() {
        JFrame gui = new JFrame("TetrisClone V1.0.0");
        tetrisFrame content = new tetrisFrame();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setContentPane(content);
        gui.setSize(600, 800);
        gui.setVisible(true);
        int[][] a = {};
        tetrisPiece curPiece = new tetrisPiece(tetrisPiece.tetromino.I, 0);
        tetrisPiece nextPiece = new tetrisPiece(tetrisPiece.tetromino.Z, 2);
        int delay = 1000; // milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                curPiece.rotateRight();
                content.updateFrame(true, a, curPiece, nextPiece);
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
