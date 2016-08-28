/*
 *  Created by Filip P. on 8/27/16 6:59 PM.
 */

package me.pauzen.teamz.command;

import me.pauzen.teamz.team.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class LastCommand extends TeamCommandExecutor {
    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (sender.hasPermission("teamz.op")) {
            TeamManager.getInstance().autoPlace(Bukkit.getWorld("SPAWN").getPlayers());
            sender.sendMessage(ChatColor.GREEN + "Autoplaced all non-teamed players in their own teams.");
        } else {
            sender.sendMessage(ChatColor.RED + "You don't have permission to run this command.");
        }
    }
}
