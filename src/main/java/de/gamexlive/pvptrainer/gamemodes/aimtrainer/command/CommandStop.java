package de.gamexlive.pvptrainer.gamemodes.aimtrainer.command;

import de.gamexlive.pvptrainer.PvPTrainer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandStop implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        PvPTrainer.manager.updatePlayerTest((Player) commandSender);

        return true;
    }
}
