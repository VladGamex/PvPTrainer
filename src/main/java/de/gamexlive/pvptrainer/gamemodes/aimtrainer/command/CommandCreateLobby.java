package de.gamexlive.pvptrainer.gamemodes.aimtrainer.command;

import de.gamexlive.pvptrainer.PvPTrainer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandCreateLobby implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player)) { return false; }
        Player p = (Player) sender;
        PvPTrainer.manager.createLobby(p);
        return true;
    }
}
