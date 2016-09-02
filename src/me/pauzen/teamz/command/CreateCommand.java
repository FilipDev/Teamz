/*
 *  Created by Filip P. on 8/27/16 7:08 PM.
 */

package me.pauzen.teamz.command;

import me.pauzen.teamz.Teamz;
import me.pauzen.teamz.team.TeamManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CreateCommand extends TeamCommandExecutor {
    @Override
    public void onCommand(Player player, String[] args) {
        if (Teamz.getInstance().isTeamsEnabled()) {
            TeamManager.getInstance().createTeam(player);
        }
        else {
            player.sendMessage(ChatColor.RED + "Teams are disabled at this time.");
        }
    }
}
