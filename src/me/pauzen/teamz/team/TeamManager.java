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
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TeamManager implements Listener {

    private static TeamManager       instance = new TeamManager();
    private final  Map<Player, Team> teams    = new HashMap<>();
    private        int               maxSize  = 3;
    private int currentTeams = 0;

    public static TeamManager getInstance() {
        return instance;
    }

    public void register(JavaPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public void clearTeams() {
        for (Player player : teams.keySet()) {
            unregisterTeam(player);
        }
        currentTeams = 0;
    }

    public void unregisterTeam(Player player) {
        getTeams().get(player).removePlayer(player);
        teams.remove(player);
    }

    public Map<Player, Team> getTeams() {
        return teams;
    }

    public void autoPlace(Collection<Player> players) {
        for (Player player : players) {
            Team team = teams.get(player);
            if (team == null) {
                createTeam(player);
            }
        }
    }

    public void createTeam(Player owner) {
        currentTeams++;
        Team currentTeam = teams.get(owner);
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
        teams.put(player, team);
    }

    public void disbandTeam(Team team) {
        for (Player player : team.getPlayers()) {
            unregisterTeam(player);
        }
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
}