public class tetrisPiece {

    enum tetromino {
        O, I, S, Z, L, J, T;
    }

    public tetrisPiece(tetromino piece, int color) {
        this.piece = piece;
        this.c = color;
        this.piecePosition = getInitPosition(piece);
    }

    private tetromino piece;
    private int c; // int representing piece color ranging from 0-8
    private int[][][] piecePosition; // {boardPositions, nextPositions}

    private int[][][] getInitPosition(tetromino piece) { // Initial board and next positions of piece
        if (piece == tetromino.O) {
            int[][][] oPiecePosition = { { { 160, 0, c }, { 200, 0, c }, { 160, 40, c }, { 200, 40, c } },
                    { { 440, 120, c }, { 480, 120, c }, { 440, 160, c }, { 480, 160, c } } };
            return oPiecePosition;
        } else if (piece == tetromino.I) {
            int[][][] iPiecePosition = { { { 200, 0, c }, { 200, 40, c }, { 200, 80, c }, { 200, 120, c } },
                    { { 480, 80, c }, { 480, 120, c }, { 480, 160, c }, { 480, 200, c } } };
            return iPiecePosition;
        } else if (piece == tetromino.S) {
            int[][][] sPiecePosition = { { { 240, 0, c }, { 200, 0, c }, { 200, 40, c }, { 160, 40, c } },
                    { { 520, 120, c }, { 480, 120, c }, { 480, 160, c }, { 440, 160, c } } };
            return sPiecePosition;
        } else if (piece == tetromino.Z) {
            int[][][] zPiecePosition = { { { 160, 0, c }, { 200, 0, c }, { 200, 40, c }, { 240, 40, c } },
                    { { 440, 120, c }, { 480, 120, c }, { 480, 160, c }, { 520, 160, c } } };
            return zPiecePosition;
        } else if (piece == tetromino.L) {
            int[][][] lPiecePosition = { { { 160, 0, c }, { 160, 40, c }, { 160, 80, c }, { 200, 80, c } },
                    { { 480, 120, c }, { 480, 160, c }, { 480, 200, c }, { 520, 200, c } } };
            return lPiecePosition;
        } else if (piece == tetromino.J) {
            int[][][] jPiecePosition = { { { 200, 0, c }, { 200, 40, c }, { 200, 80, c }, { 160, 80, c } },
                    { { 480, 120, c }, { 480, 160, c }, { 480, 200, c }, { 440, 200, c } } };
            return jPiecePosition;
        } else {
            int[][][] tPiecePosition = { { { 160, 0, c }, { 200, 0, c }, { 240, 0, c }, { 200, 40, c } },
                    { { 440, 120, c }, { 480, 120, c }, { 520, 120, c }, { 480, 160, c } } };
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
