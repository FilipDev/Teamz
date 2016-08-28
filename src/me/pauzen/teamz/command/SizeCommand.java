/*
 *  Created by Filip P. on 8/27/16 6:47 PM.
 */

package me.pauzen.teamz.command;

import me.pauzen.teamz.team.TeamManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SizeCommand extends TeamCommandExecutor {
    @Override
    public void onCommand(Player player, String[] args) {
        if (player.hasPermission("teams.op")) {
            if (args.length != 1) {
                player.sendMessage(ChatColor.RED + "Must input a number.");
            }
            else {
                try {
                    int size = Integer.parseInt(args[0]);
                    TeamManager.getInstance().setMaxSize(size);
                    player.sendMessage("Changed max team size to " + size);
                } catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "Must input a number.");
                }
            }
        } else {
            player.sendMessage(ChatColor.RED + "You don't have permission for this command.");
        }
    }
}
