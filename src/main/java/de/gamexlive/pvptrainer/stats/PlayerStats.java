package de.gamexlive.pvptrainer.stats;

import de.gamexlive.pvptrainer.PvPTrainer;
import de.gamexlive.pvptrainer.gamemodes.aimtrainer.enemytypes.Enemy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.UUID;

public class PlayerStats {

    private int kills, shots;
    private double accuracy;
    private final UUID uuid;

    public PlayerStats(UUID uuid) {
        this.uuid = uuid;
        kills = 0;
        shots = 0;
        accuracy = 100;
    }

    private PlayerStats(int kills, int shots, double accuracy) {
        uuid = null;
        this.kills = kills;
        this.shots = shots;
        this.accuracy = accuracy;
    }

    public void addKill() {
        kills++;
    }

    public int getKills() { return kills; }
    public int getShots() { return shots; }
    public double getAccuracy() { return accuracy; }

    public UUID getUUID() { return uuid; }

    public void addShot() {
        shots++;
    }

    public void updateAccuracy() {
        accuracy = (double) kills/ (double) shots;
    }

    // Stats werden nur ueberschrieben zurzeit
    public void uploadStats() {
        PlayerStats oldStats = getStats(uuid);
        if(oldStats != null) {
            kills += oldStats.kills;
            shots += oldStats.shots;
            updateAccuracy();
        }

        NumberFormat n = NumberFormat.getInstance();
        n.setMaximumFractionDigits(2);
        try {
            accuracy = (double) n.parse(n.format(accuracy));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        PvPTrainer.getter.changeEntry(uuid, kills, shots, String.valueOf(accuracy));
    }

    public PlayerStats getStats(UUID uuid) {
        ResultSet set = PvPTrainer.getter.getPlayer(uuid);
        PlayerStats stats = null;
        try {
            if(set.next()) {
                int kills = (int) set.getObject("kills");
                int shots = (int) set.getObject("shots");
                double accuracy = parse(set.getObject("accuracy").toString());
                stats = new PlayerStats(kills, shots, accuracy);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return stats;
    }

    private double parse(String accuracy) {
        StringBuilder newString = new StringBuilder();
        for(int i = 0; i < accuracy.length(); i++) {
            if(accuracy.charAt(i) == ',') {
                newString.append(".");
            } else {
                newString.append(accuracy.charAt(i));
            }
        }
        return Double.parseDouble(newString.toString());
    }


}
