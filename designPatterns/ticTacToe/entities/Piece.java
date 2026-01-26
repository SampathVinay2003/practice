package designPatterns.ticTacToe.entities;

import designPatterns.ticTacToe.enums.PlayerPieceType;

public class Piece {
    PlayerPieceType playerPieceType;
    public  Piece(PlayerPieceType playerPieceType) {
        this.playerPieceType = playerPieceType;
    }

    public PlayerPieceType getPlayerPieceType() {
        return playerPieceType;
    }

    public void setPlayerPieceType(PlayerPieceType playerPieceType) {
        this.playerPieceType = playerPieceType;
    }
}
