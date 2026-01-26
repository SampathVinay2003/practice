package designPatterns.ticTacToe.entities;

import com.javac.Util.Pair;
import designPatterns.ticTacToe.enums.PlayerPieceType;

public class Board {
    int dimension;
    PlayerPieceType[][] board;

    public Board(int dimension) {
        this.dimension = dimension;
        this.board = new PlayerPieceType[dimension][dimension];
    }

    public boolean addPiece(PlayerPieceType piece, int i, int j) {
        if (i < 0 || i >= dimension || j < 0 || j >= dimension) {
            return false;
        }
        if (board[i][j] != null) {
            return false;
        }
        board[i][j] = piece;
        return true;
    }

    public PlayerPieceType getPiece(int i, int j) {
        return board[i][j];
    }

    public PlayerPieceType getPlayerPieceType(int i, int j) {
        return board[i][j];
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public PlayerPieceType[][] getBoard() {
        return board;
    }

    public void setBoard(PlayerPieceType[][] board) {
        this.board = board;
    }

    public boolean isFreeCellExists() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (board[i][j] == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public void printBoard() {
        System.out.println("\nCurrent Board:");
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (board[i][j] != null) {
                    System.out.print(" " + board[i][j] + " ");
                } else {
                    System.out.print(" - ");
                }
                if (j < dimension - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < dimension - 1) {
                for (int k = 0; k < dimension; k++) {
                    System.out.print("---");
                    if (k < dimension - 1) {
                        System.out.print("+");
                    }
                }
                System.out.println();
            }
        }
        System.out.println();
    }

}
