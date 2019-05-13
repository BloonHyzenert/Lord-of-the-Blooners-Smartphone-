package com.example.lord_of_the_blooners_client;

import android.graphics.Color;

public class Player {

    private int playerID;
    private String pseudo;
    private Team team;
    private ClientConnexion dialog;
    private Position position;
    private Position deltaPosition;
    private int etat;
    private int score;

    public Player(ClientConnexion dialog, int tplayerID,String tPseudo, String team, double x, double y) {
        setDialog(dialog);
        etat = 0;
        playerID=tplayerID;
        position = new Position(x, y);
        this.score = 0;
        deltaPosition = new Position();

        int color = Color.BLACK;
        switch(team){
            case "Krok":
                Setup.getKrok().addPlayer(this);
                color = Color.YELLOW;
                break;
            case "Blurp":
                Setup.getBlurp().addPlayer(this);
                color = Color.BLUE;
                break;
            case "Grounch":
                Setup.getGrounch().addPlayer(this);
                color = Color.RED;
                break;
            default:
                setTeam(null);
                break;
        }
        if (GameActivity.game!=null)
            GameActivity.game.setBackgroundColor(color);

        setPseudo(tPseudo);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        if (GameActivity.game!=null)
            GameActivity.game.setTextScore(score);
    }

    public void swap(String team){
        if (this.team.getName().equals(team))
            return;
        int color = Color.BLACK;
        switch(team){
            case "Krok":
                if (this.getTeam()!=null)
                    Setup.getKrok().addPlayer(this.getTeam().removePlayer(this));
                else
                    Setup.getKrok().addPlayer(this);
                color = Color.YELLOW;
                break;
            case "Blurp":
                if(this.getTeam()!=null)
                    Setup.getBlurp().addPlayer(this.getTeam().removePlayer(this));
                else
                    Setup.getBlurp().addPlayer(this);
                color = Color.BLUE;
                break;
            case "Grounch":
                if (this.getTeam()!=null)
                    Setup.getGrounch().addPlayer(this.getTeam().removePlayer(this));
                else
                    Setup.getGrounch().addPlayer(this);
                color = Color.RED;
                break;
            default:
                setTeam(null);
                break;
        }
        if (GameActivity.game!=null)
            GameActivity.game.setBackgroundColor(color);
    }

    public void setPosition(double dx, double dy) {
        setPosition(new Position(position.getX()+dx,position.getY()+dy));
    }

    public int getEtat(){
        return etat;
    }

    public void setEtat(int etat){
        this.etat = etat;
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

    public Position getDeltaPosition() {
        return deltaPosition;
    }

    public void setDeltaPosition(Position deltaPosition) {
        this.deltaPosition = deltaPosition;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setPseudo(String tPseudo) {
        pseudo=tPseudo;

    }

    public String getPseudo() {
        return pseudo;
    }
}
