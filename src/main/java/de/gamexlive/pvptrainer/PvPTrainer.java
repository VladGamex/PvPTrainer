package de.gamexlive.pvptrainer;

import de.gamexlive.pvptrainer.gamemodes.aimtrainer.command.CommandCreateLobby;
import de.gamexlive.pvptrainer.gamemodes.aimtrainer.command.CommandStop;
import de.gamexlive.pvptrainer.gamemodes.aimtrainer.logic.EntityKillEvent;
import de.gamexlive.pvptrainer.gamemodes.aimtrainer.logic.PlayerShootBowEvent;
import de.gamexlive.pvptrainer.gamemodes.lobby.LobbyManager;
import de.gamexlive.pvptrainer.sql.MySQL;
import de.gamexlive.pvptrainer.sql.SQLGetter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class PvPTrainer extends JavaPlugin {

    public MySQL SQL;
    public static SQLGetter getter;
    public static PvPTrainer plugin;
    public static LobbyManager manager;

    @Override
    public void onEnable() {
        plugin = this;
        manager = new LobbyManager();
        this.SQL = new MySQL();
        try {
            this.SQL.connect();
        } catch (SQLException | ClassNotFoundException e) {
            Bukkit.getLogger().info("Database is not connected");
        }
        if(SQL.isConnected()) {
            getter = new SQLGetter(this);
            Bukkit.getLogger().info("Database is connected");
        }
        this.getCommand("create").setExecutor(new CommandCreateLobby());
        this.getCommand("stopLobby").setExecutor(new CommandStop());
        Bukkit.getPluginManager().registerEvents(new EntityKillEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerShootBowEvent(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
