/*
 *  Created by Filip P. on 8/27/16 7:06 PM.
 */

package me.pauzen.teamz.command;

import me.pauzen.teamz.Teamz;
import me.pauzen.teamz.team.Team;
import me.pauzen.teamz.team.TeamManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class LeaveCommand extends TeamCommandExecutor {
    @Override
    public void onCommand(Player player, String[] args) {
        Team team = Teamz.getInstance().getTeam(player);
        if (team == null) {
            player.sendMessage(ChatColor.RED + "Must be on a team to run this command.");
        }
        else {
            TeamManager.getInstance().unregisterTeam(player.getUniqueId());
        }
    }
}
