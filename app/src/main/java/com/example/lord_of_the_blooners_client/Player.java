package com.example.lord_of_the_blooners_client;

public class Player {

    private int playerID;
    private String pseudo;
    private Team team;
    private ClientConnexion dialog;
    private Position position;
    private Position deltaPosition;
    private int etat;

    public Player(ClientConnexion dialog, int tplayerID,String tPseudo, String team, double x, double y) {
        setDialog(dialog);
        etat = 0;
        playerID=tplayerID;
        position = new Position(x, y);
        deltaPosition = new Position();
        switch(team){
            case "Krok":
                Setup.getKrok().addPlayer(this);
                break;
            case "Blurp":
                Setup.getBlurp().addPlayer(this);
                break;
            case "Grounch":
                Setup.getGrounch().addPlayer(this);
                break;
                default:
                    setTeam(null);
                    break;
        }
        setPseudo(tPseudo);
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
