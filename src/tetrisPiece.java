package tetrisGame;

import java.awt.Color;

public class tetrisPiece {

    enum tetromino {
        O, I, S, Z, L, J, T;
    }

    public tetrisPiece(tetromino piece, Color color) {
        this.piece = piece;
        this.pieceColor = color;
        this.piecePosition = getInitPosition(piece);
    }

    private tetromino piece;
    private Color pieceColor;
    private int[][][] piecePosition; // {boardPositions, nextPositions}

    private int[][][] getInitPosition(tetromino piece) { // Initial board and next positions of piece
        if (piece == tetromino.O) {
            int[][][] oPiecePosition = { { { 160, 0 }, { 200, 0 }, { 160, 40 }, { 200, 40 } },
                    { { 440, 120 }, { 480, 120 }, { 440, 160 }, { 480, 160 } } };
            return oPiecePosition;
        } else if (piece == tetromino.I) {
            int[][][] iPiecePosition = { { { 200, 0 }, { 200, 40 }, { 200, 80 }, { 200, 120 } },
                    { { 480, 80 }, { 480, 120 }, { 480, 160 }, { 480, 200 } } };
            return iPiecePosition;
        } else if (piece == tetromino.S) {
            int[][][] sPiecePosition = { { { 240, 0 }, { 200, 0 }, { 200, 40 }, { 160, 40 } },
                    { { 520, 120 }, { 480, 120 }, { 480, 160 }, { 440, 160 } } };
            return sPiecePosition;
        } else if (piece == tetromino.Z) {
            int[][][] zPiecePosition = { { { 160, 0 }, { 200, 0 }, { 200, 40 }, { 240, 40 } },
                    { { 440, 120 }, { 480, 120 }, { 480, 160 }, { 520, 160 } } };
            return zPiecePosition;
        } else if (piece == tetromino.L) {
            int[][][] lPiecePosition = { { { 160, 0 }, { 160, 40 }, { 160, 80 }, { 200, 80 } },
                    { { 480, 120 }, { 480, 160 }, { 480, 200 }, { 520, 200 } } };
            return lPiecePosition;
        } else if (piece == tetromino.J) {
            int[][][] jPiecePosition = { { { 200, 0 }, { 200, 40 }, { 200, 80 }, { 160, 80 } },
                    { { 480, 120 }, { 480, 160 }, { 480, 200 }, { 440, 200 } } };
            return jPiecePosition;
        } else {
            int[][][] tPiecePosition = { { { 160, 0 }, { 200, 0 }, { 240, 0 }, { 200, 40 } },
                    { { 440, 120 }, { 480, 120 }, { 520, 120 }, { 480, 160 } } };
            return tPiecePosition;
        }
    }

    public boolean checkCollision(int[][] board) { // Check if any part of piece will collide with board in next state
        return true;
    }

    public void downOne() { // Bring piece down a square

    }

    public void shiftLeft() { // Bring piece left a square

    }

    public void shiftRight() { // Bring piece right a square

    }

    public void rotateLeft() { // Rotate piece left

    }

    public void rotateRight() { // Rotate piece right

    }
}
