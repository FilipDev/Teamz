/*
 *  Created by Filip P. on 8/27/16 7:10 PM.
 */

package me.pauzen.teamz.command;

import me.pauzen.teamz.Teamz;
import me.pauzen.teamz.request.RequestManager;
import me.pauzen.teamz.team.Team;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class InviteCommand extends TeamCommandExecutor {
    @Override
    public void onCommand(Player player, String[] args) {
        Team team = Teamz.getInstance().getTeam(player);
        if (team != null) {
            if (team.getOwner() == player) {
                if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        if (target != player) {
                            RequestManager.getInstance().sendRequest(player, target, team);
                        } else {
                            player.sendMessage(ChatColor.RED + "You cannot send a request to yourself...");
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "Player not found.");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Please specify a player.");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Only the team owner can invite people..");
            }
        } else {
            player.sendMessage(ChatColor.RED + "You aren't in a team.");
        }
    }
}
