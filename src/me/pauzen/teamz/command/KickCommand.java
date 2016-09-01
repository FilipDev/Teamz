/*
 *  Created by Filip P. on 8/27/16 6:50 PM.
 */

package me.pauzen.teamz.command;

import me.pauzen.teamz.Teamz;
import me.pauzen.teamz.team.Team;
import me.pauzen.teamz.team.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class KickCommand extends TeamCommandExecutor {
    @Override
    public void onCommand(Player player, String[] args) {
        Team team = Teamz.getInstance().getTeam(player);
        if (team != null) {
            if (team.getOwner() == player) {
                if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        TeamManager.getInstance().unregisterTeam(target.getUniqueId());
                    } else {
                        player.sendMessage(ChatColor.RED + "You must specify a valid player to kick.");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "You must specify a player to kick.");
                }
            } else {
                player.sendMessage(ChatColor.RED + "You must be the team owner to execute this command.");
            }
        } else {
            player.sendMessage(ChatColor.RED + "You aren't on a team.");
        }
    }
}
