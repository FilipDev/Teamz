/*
 *  Created by Filip P. on 8/27/16 7:13 PM.
 */

package me.pauzen.teamz.command;

import me.pauzen.teamz.request.Request;
import me.pauzen.teamz.request.RequestManager;
import me.pauzen.teamz.team.TeamManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class AcceptCommand extends TeamCommandExecutor {
    @Override
    public void onCommand(Player player, String[] args) {
        Request request = RequestManager.getInstance().getRequest(player);
        if (request != null) {
            TeamManager.getInstance().registerTeam(player, request.getTeam());
        } else {
            player.sendMessage(ChatColor.RED + "You don't have a pending invite.");
        }
    }
}
