package designPatterns.ticTacToe;

import designPatterns.ticTacToe.entities.Board;
import designPatterns.ticTacToe.entities.Piece;
import designPatterns.ticTacToe.entities.PieceO;
import designPatterns.ticTacToe.entities.PieceX;
import designPatterns.ticTacToe.entities.Player;
import designPatterns.ticTacToe.enums.PlayerPieceType;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class TicTacToeGame {
    Scanner scanner = new Scanner(System.in);
    Deque<Player> players;
    public TicTacToeGame() {
        initializeGame();
    }

    private void initializeGame() {
        players = new LinkedList<>();
        Player player1 = new Player("Player 1", PlayerPieceType.X);
        Player player2 = new Player("Player 2", PlayerPieceType.O);
        players.add(player1);
        players.add(player2);
    }

    public String play(){
        Board board = new Board(3);
        boolean isGoingOn = true;
        
        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("Enter row and column (0-2) separated by space");
        board.printBoard();
        
        while(isGoingOn){
            Player player = players.pop();
            System.out.println(player.getName()+"'s turn (" + player.getPlayerPieceType() + "), Choose grid (row col): ");
            
            int i, j;
            try {
                i = scanner.nextInt();
                j = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter numbers only.");
                scanner.nextLine();
                players.addFirst(player);
                continue;
            }
            
            boolean addPiece = board.addPiece(player.getPlayerPieceType(), i, j);
            if(addPiece){
                board.printBoard();
                players.add(player);
            }else{
                System.out.println("Invalid move! Cell is either occupied or out of bounds. Try again.");
                players.addFirst(player);
                continue;
            }

            boolean isThereWinner = checkWinner(board);
            if(isThereWinner){
                return player.getName();
            }
            
            if(!board.isFreeCellExists()){
                return "Tie";
            }
        }
        return "Tie";
    }

    private boolean checkWinner(Board board) {
        int dimension = board.getDimension();
        
        for (int i = 0; i < dimension; i++) {
            boolean rowMatch = true;
            boolean colMatch = true;
            
            for (int j = 0; j < dimension; j++) {
                if (board.getPiece(i, j) == null || 
                    board.getPiece(i, 0) == null ||
                    board.getPiece(i, j) != board.getPiece(i, 0)) {
                    rowMatch = false;
                }
                
                if (board.getPiece(j, i) == null || 
                    board.getPiece(0, i) == null ||
                    board.getPiece(j, i) != board.getPiece(0, i)) {
                    colMatch = false;
                }
            }
            
            if (rowMatch || colMatch) {
                return true;
            }
        }
        
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;
        
        for (int i = 0; i < dimension; i++) {
            if (board.getPiece(i, i) == null || 
                board.getPiece(0, 0) == null ||
                board.getPiece(i, i) != board.getPiece(0, 0)) {
                diagonalMatch = false;
            }
            
            if (board.getPiece(i, dimension - 1 - i) == null || 
                board.getPiece(0, dimension - 1) == null ||
                board.getPiece(i, dimension - 1 - i)!= board.getPiece(0, dimension - 1)) {
                antiDiagonalMatch = false;
            }
        }
        
        return diagonalMatch || antiDiagonalMatch;
    }
}
