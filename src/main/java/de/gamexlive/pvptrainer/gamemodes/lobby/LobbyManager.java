package de.gamexlive.pvptrainer.gamemodes.lobby;

import de.gamexlive.pvptrainer.stats.PlayerStats;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class LobbyManager {

    private ArrayList<GameLobby> lobbies;

    public LobbyManager() {
        lobbies = new ArrayList<>();
    }

    public void createLobby(Player player) {
        lobbies.add(new GameLobby());
        addPlayer(player);
    }

    public void addPlayer(Player player) {
        lobbies.get(0).addPlayer(player);
    }

    public PlayerStats getPlayer(Player player) {
        for(int i = 0; i < lobbies.size(); i++) {
            GameLobby current = lobbies.get(i);
            if(current.getPlayerStats(player) != null) {
                return current.getPlayerStats(player);
            }
        }
        return null;
    }

    public void updatePlayerTest(Player player) {
        getPlayer(player).uploadStats();
    }

}
