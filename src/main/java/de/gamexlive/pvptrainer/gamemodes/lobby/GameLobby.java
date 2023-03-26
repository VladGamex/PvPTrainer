package de.gamexlive.pvptrainer.gamemodes.lobby;

import de.gamexlive.pvptrainer.PvPTrainer;
import de.gamexlive.pvptrainer.gamemodes.util.Countdown;
import de.gamexlive.pvptrainer.stats.PlayerStats;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class GameLobby {

    private Countdown countdown;

    private ArrayList<PlayerStats> players;

    public GameLobby() {
        players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(new PlayerStats(player.getUniqueId()));
    }

    public PlayerStats getPlayerStats(Player player) {
        for(int i = 0; i < players.size(); i++) {
            if(players.get(i).getUUID().equals(player.getUniqueId())) {
                return players.get(i);
            }
        }
        return null;
    }

    public ArrayList<PlayerStats> getPlayers() { return players; }

    public void removePlayer(Player player) {
        players.removeIf(n -> (n.equals(player.getUniqueId())));
    }

    public void startCountdown() {
        countdown = new Countdown(10, PvPTrainer.plugin) {
            @Override
            public void count(int current) {

            }
        };
        countdown.start();
    }

    public void stopCountdown() { countdown.stop(); }

}
