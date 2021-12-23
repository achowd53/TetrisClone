public class tetrisPiece {

    enum tetromino {
        O, I, S, Z, L, J, T;
    }

    public tetrisPiece(tetromino piece, int color) {
        this.piece = piece;
        this.c = color;
        this.rot = 0;
        this.piecePosition = getInitPosition(piece);
    }

    private tetromino piece;
    private int rot;
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

    public boolean checkValidBounds(int[][] board) { // Check if piece is in valid position
        for (int[] part : piecePosition[0]) {
            if (part[0] < 0 || part[0] > 360 || part[1] > 760)
                return false;
            for (int[] boardBlock : board) {
                if (part[0] == boardBlock[0] && part[1] == boardBlock[1])
                    return false;
            }
        }
        return true;
    }

    public boolean checkCollision(int[][] board) { // Check if piece collides with board
        for (int[] part : piecePosition[0]) {
            if (part[1] == 760)
                return true;
            for (int[] boardBlock : board) {
                if (part[0] == boardBlock[0] && part[1] + 40 == boardBlock[1])
                    return true;
            }
        }
        return false;
    }

    public void upOne() { // Bring piece up a square
        for (int i = 0; i < 4; i++)
            piecePosition[0][i][1] -= 40;
    }

    public void downOne() { // Bring piece down a square
        for (int i = 0; i < 4; i++)
            piecePosition[0][i][1] += 40;
    }

    public void shiftLeft() { // Bring piece left a square
        for (int i = 0; i < 4; i++)
            piecePosition[0][i][0] -= 40;
    }

    public void shiftRight() { // Bring piece right a square
        for (int i = 0; i < 4; i++)
            piecePosition[0][i][0] += 40;
    }

    public void rotateLeft() { // Rotate piece left
        for (int i = 0; i < 3; i++)
            rotateRight();
    }

    public void rotateRight() { // Rotate piece right
        if (piece == tetromino.I) { //
            if (rot == 0) {
                rot = 1;
                piecePosition[0][0][0] += 40;
                piecePosition[0][0][1] += 80;
                piecePosition[0][1][1] += 40;
                piecePosition[0][2][0] -= 40;
                piecePosition[0][3][0] -= 80;
                piecePosition[0][3][1] -= 40;
            } else if (rot == 1) {
                rot = 2;
                piecePosition[0][0][0] -= 80;
                piecePosition[0][0][1] -= 80;
                piecePosition[0][1][0] -= 40;
                piecePosition[0][1][1] -= 40;
                piecePosition[0][3][0] += 40;
                piecePosition[0][3][1] += 40;
            } else if (rot == 2) {
                rot = 3;
                piecePosition[0][0][0] -= 40;
                piecePosition[0][0][1] += 40;
                piecePosition[0][2][0] += 40;
                piecePosition[0][2][1] -= 40;
                piecePosition[0][3][0] += 80;
                piecePosition[0][3][1] -= 80;
            } else {
                rot = 0;
                piecePosition[0][0][0] += 80;
                piecePosition[0][0][1] -= 40;
                piecePosition[0][1][0] += 40;
                piecePosition[0][2][1] += 40;
                piecePosition[0][3][0] -= 40;
                piecePosition[0][3][1] += 80;
            }
        } else if (piece == tetromino.S) { //
            if (rot == 0) {
                rot = 1;
                piecePosition[0][0][0] -= 40;
                piecePosition[0][0][1] += 40;
                piecePosition[0][2][0] -= 40;
                piecePosition[0][2][1] -= 40;
                piecePosition[0][3][1] -= 80;
            } else if (rot == 1) {
                rot = 2;
                piecePosition[0][0][0] -= 40;
                piecePosition[0][0][1] -= 40;
                piecePosition[0][2][0] += 40;
                piecePosition[0][2][1] -= 40;
                piecePosition[0][3][0] += 80;
            } else if (rot == 2) {
                rot = 3;
                piecePosition[0][0][0] += 40;
                piecePosition[0][0][1] -= 40;
                piecePosition[0][2][0] += 40;
                piecePosition[0][2][1] = +40;
                piecePosition[0][3][1] += 80;
            } else {
                rot = 0;
                piecePosition[0][0][0] += 40;
                piecePosition[0][0][1] += 40;
                piecePosition[0][2][0] -= 40;
                piecePosition[0][2][1] += 40;
                piecePosition[0][3][0] -= 80;
            }
        } else if (piece == tetromino.Z) { //
            if (rot == 0) {
                rot = 1;
                piecePosition[0][0][0] += 40;
                piecePosition[0][0][1] -= 40;
                piecePosition[0][2][0] -= 40;
                piecePosition[0][2][1] -= 40;
                piecePosition[0][3][0] -= 80;
            } else if (rot == 1) {
                rot = 2;
                piecePosition[0][0][0] += 40;
                piecePosition[0][0][1] += 40;
                piecePosition[0][2][0] += 40;
                piecePosition[0][2][1] -= 40;
                piecePosition[0][3][1] -= 80;
            } else if (rot == 2) {
                rot = 3;
                piecePosition[0][0][0] -= 40;
                piecePosition[0][0][1] += 40;
                piecePosition[0][2][0] += 40;
                piecePosition[0][2][1] += 40;
                piecePosition[0][3][0] += 80;
            } else {
                rot = 0;
                piecePosition[0][0][0] -= 40;
                piecePosition[0][0][1] -= 40;
                piecePosition[0][2][0] -= 40;
                piecePosition[0][2][1] += 40;
                piecePosition[0][3][1] += 80;
            }
        } else if (piece == tetromino.L) { //
            if (rot == 0) {
                rot = 1;
                piecePosition[0][0][0] += 40;
                piecePosition[0][0][1] += 40;
                piecePosition[0][2][0] -= 40;
                piecePosition[0][2][1] -= 40;
                piecePosition[0][3][0] -= 80;
            } else if (rot == 1) {
                rot = 2;
                piecePosition[0][0][0] -= 40;
                piecePosition[0][0][1] += 40;
                piecePosition[0][2][0] += 40;
                piecePosition[0][2][1] -= 40;
                piecePosition[0][3][1] -= 80;
            } else if (rot == 2) {
                rot = 3;
                piecePosition[0][0][0] -= 40;
                piecePosition[0][0][1] -= 40;
                piecePosition[0][2][0] += 40;
                piecePosition[0][2][1] += 40;
                piecePosition[0][3][0] += 80;
            } else {
                rot = 0;
                piecePosition[0][0][0] += 40;
                piecePosition[0][0][1] -= 40;
                piecePosition[0][2][0] -= 40;
                piecePosition[0][2][1] += 40;
                piecePosition[0][3][1] += 80;
            }
        } else if (piece == tetromino.J) { //
            if (rot == 0) {
                rot = 1;
                piecePosition[0][0][0] += 40;
                piecePosition[0][0][1] += 40;
                piecePosition[0][2][0] -= 40;
                piecePosition[0][2][1] -= 40;
                piecePosition[0][3][1] -= 80;
            } else if (rot == 1) {
                rot = 2;
                piecePosition[0][0][0] -= 40;
                piecePosition[0][0][1] += 40;
                piecePosition[0][2][0] += 40;
                piecePosition[0][2][1] -= 40;
                piecePosition[0][3][0] += 80;
            } else if (rot == 2) {
                rot = 3;
                piecePosition[0][0][0] -= 40;
                piecePosition[0][0][1] -= 40;
                piecePosition[0][2][0] += 40;
                piecePosition[0][2][1] += 40;
                piecePosition[0][3][1] += 80;
            } else {
                rot = 0;
                piecePosition[0][0][0] += 40;
                piecePosition[0][0][1] -= 40;
                piecePosition[0][2][0] -= 40;
                piecePosition[0][2][1] += 40;
                piecePosition[0][3][0] -= 80;
            }
        } else if (piece == tetromino.T) {
            if (rot == 0) {
                rot = 1;
                piecePosition[0][0][0] += 40;
                piecePosition[0][0][1] -= 40;
                piecePosition[0][2][0] -= 40;
                piecePosition[0][2][1] += 40;
                piecePosition[0][3][0] -= 40;
                piecePosition[0][3][1] -= 40;
            } else if (rot == 1) {
                rot = 2;
                piecePosition[0][0][0] += 40;
                piecePosition[0][0][1] += 40;
                piecePosition[0][2][0] -= 40;
                piecePosition[0][2][1] -= 40;
                piecePosition[0][3][0] += 40;
                piecePosition[0][3][1] -= 40;
            } else if (rot == 2) {
                rot = 3;
                piecePosition[0][0][0] -= 40;
                piecePosition[0][0][1] += 40;
                piecePosition[0][2][0] += 40;
                piecePosition[0][2][1] -= 40;
                piecePosition[0][3][0] += 40;
                piecePosition[0][3][1] += 40;
            } else {
                rot = 0;
                piecePosition[0][0][0] -= 40;
                piecePosition[0][0][1] -= 40;
                piecePosition[0][2][0] += 40;
                piecePosition[0][2][1] += 40;
                piecePosition[0][3][0] -= 40;
                piecePosition[0][3][1] += 40;
            }
        }
    }

    public int[][] getBoard() { // Return board positions of piece
        return this.piecePosition[0];
    }

    public int[][] getNext() { // Return next display of piece
        return this.piecePosition[1];
    }
}
