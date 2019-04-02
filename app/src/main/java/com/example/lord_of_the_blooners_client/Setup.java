package com.example.lord_of_the_blooners_client;

public class Setup {
    private static Player mainPlayer = new Player(null, 0, null, null);

    public static Player getMainPlayer() {
        return mainPlayer;
    }

    public static void setMainPlayer(Player mainPlayer) {
        Setup.mainPlayer = mainPlayer;
    }
}
