package de.gamexlive.pvptrainer.sql;

import de.gamexlive.pvptrainer.PvPTrainer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SQLGetter {

    private PvPTrainer plugin;
    public SQLGetter(PvPTrainer plugin) {
        this.plugin = plugin;
        createTables();
    }


    public void createTables() {
        PreparedStatement ps;
        try {
            ps = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS aimtrainer " + "(UUID VARCHAR(100)," +
                    " KILLS INT, SHOTS INT, ACCURACY VARCHAR(100))");
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeEntry(UUID uuid, int kills, int shots, String accuracy) {
        if(exists(uuid)) {
            System.out.println("PLAYER EXISTS");
            updatePlayer(uuid, kills, shots, accuracy);
        } else {
            System.out.println("PLAYER EXISTS NOT");
            addNewPlayer(uuid, kills, shots, accuracy);
        }
    }

    private void addNewPlayer(UUID uuid, int kills, int shots, String accuracy) {
        PreparedStatement statement;
        try {
            statement = plugin.SQL.getConnection().prepareStatement("INSERT INTO aimtrainer(UUID, KILLS, SHOTS, ACCURACY) VALUES(?,?,?,?)");
            statement.setString(1, uuid.toString());
            statement.setString(2, ""+kills);
            statement.setString(3, ""+shots);
            statement.setString(4, accuracy);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean exists(UUID uuid) {
        PreparedStatement statement;
        try {
            statement = plugin.SQL.getConnection().prepareStatement("SELECT UUID FROM aimtrainer WHERE UUID=?");
            statement.setString(1, uuid.toString());
            ResultSet set = statement.executeQuery();
            return set.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ResultSet getPlayer(UUID uuid) {
        PreparedStatement statement;
        try {
            statement = plugin.SQL.getConnection().prepareStatement("SELECT * FROM aimtrainer WHERE UUID=?");
            statement.setString(1, uuid.toString());
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void updatePlayer(UUID uuid, int kills, int shots, String accuracy) {
        PreparedStatement statement;
        try {
            statement = plugin.SQL.getConnection().prepareStatement("UPDATE aimtrainer SET KILLS=?, SHOTS=?, ACCURACY=? WHERE UUID=?");
            statement.setString(1, ""+kills);
            statement.setString(2, ""+shots);
            statement.setString(3, accuracy);
            statement.setString(4, uuid.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
