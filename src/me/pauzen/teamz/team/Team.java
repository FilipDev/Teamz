/*
 *  Created by Filip P. on 8/27/16 6:30 PM.
 */

/*
 *  Created by Filip P. on 8/27/16 5:27 PM.
 */

package me.pauzen.teamz.team;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Team {

    private final Set<UUID> players = new HashSet<>();
    private Player owner;
    private int    id;
    private int kills = 0;

    protected Team(Player owner, int id) {
        this.owner = owner;
        this.id = id;
    }

    public Player getOwner() {
        return owner;
    }

    public int getSize() {
        return getPlayers().size();
    }

    public Set<UUID> getPlayers() {
        return players;
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
        for (UUID uuid : players) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
            if (player != null) {
                out += " " + (player.isOnline() ? player.getPlayer().isDead() ? ChatColor.RED : ChatColor.WHITE : ChatColor.RED) + player.getName();
            }
        }
        return out;
    }

    protected void removePlayer(OfflinePlayer player) {
        this.players.remove(player.getUniqueId());
        if (player.isOnline()) {
            player.getPlayer().sendMessage(ChatColor.RED + "You have been removed from your team.");
        }
        sendMessage(ChatColor.RED + "Player " + player.getName() + " left your team.");
    }

    public void sendMessage(String message) {
        for (UUID uuid : getPlayers()) {
            Player player = Bukkit.getPlayer(uuid);
            if (player != null) {
                player.sendMessage(message);
            }
        }
    }

    protected void addPlayer(Player player) {
        this.players.add(player.getUniqueId());
        player.sendMessage(ChatColor.GREEN + "Joined team.");
        sendMessage(ChatColor.GREEN + "Player " + player.getName() + " joined your team.");
    }
}
