package designPatterns.ticTacToe.entities;

import designPatterns.ticTacToe.enums.PlayerPieceType;

public class Player {
    private PlayerPieceType pieceType;
    private String name;

    public PlayerPieceType getPlayerPieceType() {
        return pieceType;
    }

    public void setPlayerPieceType(PlayerPieceType pieceType) {
        this.pieceType = pieceType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player(String name, PlayerPieceType playerPieceType) {
        this.name = name;
        this.pieceType = playerPieceType;
    }
}
