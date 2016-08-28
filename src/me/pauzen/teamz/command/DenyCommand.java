/*
 *  Created by Filip P. on 8/27/16 7:15 PM.
 */

package me.pauzen.teamz.command;

import me.pauzen.teamz.request.Request;
import me.pauzen.teamz.request.RequestManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class DenyCommand extends TeamCommandExecutor {
    @Override
    public void onCommand(Player player, String[] args) {
        Request request = RequestManager.getInstance().getRequest(player);
        if (request != null) {
            RequestManager.getInstance().declined(player);
            player.sendMessage(ChatColor.YELLOW + "Declined team invite.");
        } else {
            player.sendMessage(ChatColor.RED + "You don't have a pending invite.");
        }
    }
}
