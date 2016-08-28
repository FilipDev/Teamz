/*
 *  Created by Filip P. on 8/27/16 6:30 PM.
 */

/*
 *  Created by Filip P. on 8/27/16 5:27 PM.
 */

package me.pauzen.teamz.team;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class Team {

    private final Set<Player>       players = new HashSet<>();
    private Player owner;
    private int id;
    private int kills = 0;

    protected Team(Player owner, int id) {
        this.owner = owner;
        this.id = id;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public Player getOwner() {
        return owner;
    }

    public int getSize() {
        return getPlayers().size();
    }

    public int getId() {
        return id;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    @Override
    public String toString() {
        String out = "[Team<" + id + ">]:";
        for (Player player : players) {
            out += " " + player.getName();
        }
        return out;
    }

    protected void removePlayer(Player player) {
        this.players.remove(player);
        player.sendMessage(ChatColor.RED + "You have been removed from your team.");
        if (player == owner) {
            TeamManager.getInstance().disbandTeam(this);
        }
        sendMessage(ChatColor.RED + "Player " + player.getName() + " left your team.");
    }

    protected void addPlayer(Player player) {
        sendMessage(ChatColor.GREEN + "Player " + player.getName() + " joined your team.");
        this.players.add(player);
        player.sendMessage(ChatColor.GREEN + "Joined team.");
    }

    public void sendMessage(String message) {
        for (Player player : getPlayers()) {
            player.sendMessage(message);
        }
    }
}
