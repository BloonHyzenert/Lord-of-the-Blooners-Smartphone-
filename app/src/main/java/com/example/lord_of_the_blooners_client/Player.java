package com.example.lord_of_the_blooners_client;
import java.net.Socket;

public class Player {

    private int playerID;

    private String pseudo;

    private String team;

    private ClientConnexion dialog;

    private Position position;

    public Player(ClientConnexion dialog, int tplayerID,String tPseudo, String team) {
        setDialog(dialog);
        playerID=tplayerID;
        position = new Position();
        setTeam(team);
        setPseudo(tPseudo);
    }

    public void move(int dx, int dy) {
        setPosition(new Position(position.getX()+dx,position.getY()+dy));
    }

    public int getPlayerID() {
        return playerID;
    }

    public ClientConnexion getDialog() {
        return dialog;
    }

    public void setDialog(ClientConnexion socket) {
        this.dialog = socket;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setPseudo(String tPseudo) {
        pseudo=tPseudo;

    }

    public String getPseudo() {
        return pseudo;
    }
}
