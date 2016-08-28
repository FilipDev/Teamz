/*
 *  Created by Filip P. on 8/27/16 6:54 PM.
 */

package me.pauzen.teamz.command;

import me.pauzen.teamz.team.Team;
import me.pauzen.teamz.team.TeamManager;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class LeftCommand extends TeamCommandExecutor {
    @Override
    public void onCommand(Player player, String[] args) {
        Set<Team> teams = new HashSet<>();
        teams.addAll(TeamManager.getInstance().getTeams().values());
        player.sendMessage("There are " + teams.size() + " teams remaining.");
    }
}
