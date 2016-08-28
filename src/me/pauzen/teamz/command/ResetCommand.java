/*
 *  Created by Filip P. on 8/27/16 7:04 PM.
 */

package me.pauzen.teamz.command;

import me.pauzen.teamz.team.TeamManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ResetCommand extends TeamCommandExecutor {
    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (sender.hasPermission("teamz.op")) {
            TeamManager.getInstance().clearTeams();
            sender.sendMessage(ChatColor.GREEN + "Reset all teams.");
        } else {
            sender.sendMessage(ChatColor.RED + "You don't have permission to run this command.");
        }
    }
}
