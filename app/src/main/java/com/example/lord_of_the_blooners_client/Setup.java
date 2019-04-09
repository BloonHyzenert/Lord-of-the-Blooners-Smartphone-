package com.example.lord_of_the_blooners_client;

import java.util.ArrayList;
import java.util.List;

public class Setup {
    private static Player mainPlayer ;
	private static Team krok = new Team("Krok", "yellow");
	private static Team grounch = new Team("Grounch", "red");
	private static Team blurp = new Team("Blurp", "blue");
	private static Team item = new Team("Item", "white");
	private static List<Player> playerList = new ArrayList<Player>();

    public static Player getMainPlayer() {
        return mainPlayer;
    }

    public static void setMainPlayer(Player mainPlayer) {
        Setup.mainPlayer = mainPlayer;
    }


	public static Team getKrok() {
		return krok;
	}

	public static Team getGrounch() {
		return grounch;
	}

	public static Team getBlurp() {
		return blurp;
	}

	public static Team getItem() {
		return item;
	}

	public static List<Player> getPlayerList() {
		return playerList;
	}
}
