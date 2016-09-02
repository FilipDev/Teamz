/*
 *  Created by Filip P. on 8/27/16 8:27 PM.
 */

/*
 *  Created by Filip P. on 8/27/16 7:30 PM.
 */

package me.pauzen.teamz.command;

import me.pauzen.teamz.Teamz;
import me.pauzen.teamz.team.Team;
import me.pauzen.teamz.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class KillsCommand extends TeamCommandExecutor {
    @Override
    public void onCommand(Player player, String[] args) {
        Player target = args.length >= 1 ? Bukkit.getPlayer(args[0]) : player;
        if (target == null) {
            player.sendMessage(ChatColor.RED + "Player not found.");
            return;
        }
        Team team = Teamz.getInstance().getTeam(target);
        if (team != null) {
            player.sendMessage(StringUtils.addColors("%s has %s kills.", ChatColor.GRAY, ChatColor.WHITE, team, team.getKills()));
        }
        else {
            if (target == player) {
                player.sendMessage(ChatColor.RED + "You don't have a team yet.");
                player.sendMessage(ChatColor.YELLOW + "You can create a team with the command " + ChatColor.GREEN + "/team create");
            }
            else {
                player.sendMessage(ChatColor.RED + target.getName() + " doesn't have a team.");
            }
        }
    }
}
