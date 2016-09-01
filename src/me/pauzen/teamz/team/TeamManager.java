/*
 *  Created by Filip P. on 8/27/16 6:30 PM.
 */

/*
 *  Created by Filip P. on 8/27/16 5:49 PM.
 */

package me.pauzen.teamz.team;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.*;

public class TeamManager implements Listener {

    private static TeamManager     instance = new TeamManager();
    private final  Map<UUID, Team> teams    = new HashMap<>();
    private        int               maxSize  = 3;
    private int currentTeams = 0;

    public static TeamManager getInstance() {
        return instance;
    }

    public void clearTeams() {
        for (UUID uuid : teams.keySet()) {
            unregisterTeam(uuid);
        }
        currentTeams = 0;
    }

    public void unregisterTeam(UUID uuid) {
        teams.get(uuid).removePlayer(Bukkit.getOfflinePlayer(uuid));
        teams.remove(uuid);
    }

    public Map<UUID, Team> getTeams() {
        return teams;
    }

    public Set<Team> getTeamSet() {
        Set<Team> teams = new HashSet<>();
        teams.addAll(this.teams.values());
        return teams;
    }

    public void autoPlace(Collection<Player> players) {
        for (Player player : players) {
            Team team = teams.get(player.getUniqueId());
            if (team == null) {
                createTeam(player);
            }
        }
    }

    public void createTeam(Player owner) {
        currentTeams++;
        Team currentTeam = teams.get(owner.getUniqueId());
        if (currentTeam != null) {
            currentTeam.removePlayer(owner);
        }
        registerTeam(owner, new Team(owner, currentTeams));
    }

    public void registerTeam(Player player, Team team) {
        if (team.getSize() == getMaxSize()) {
            player.sendMessage(ChatColor.RED + "Cannot join team because it's already at its maximum size.");
            return;
        }
        team.addPlayer(player);
        teams.put(player.getUniqueId(), team);
    }

    public void disbandTeam(Team team) {
        for (UUID uuid : team.getPlayers()) {
            unregisterTeam(uuid);
        }
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
}