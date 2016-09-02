/*
 *  Created by Filip P. on 8/27/16 5:36 PM.
 */

package me.pauzen.teamz.request;

import me.pauzen.teamz.Teamz;
import me.pauzen.teamz.team.Team;
import me.pauzen.teamz.updater.UpdateEvent;
import me.pauzen.teamz.updater.UpdateType;
import me.pauzen.teamz.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class RequestManager implements Listener {

    private static RequestManager instance;
    private Map<Player, Request> requestMap = new HashMap<>();

    public RequestManager() {
        instance = this;
    }

    public static RequestManager getInstance() {
        return instance;
    }

    public void register(JavaPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public void sendRequest(Player sender, Player player, Team team) {
        if (!requestMap.containsKey(player)) {
            if (Teamz.getInstance().getTeam(player) == null) {
                this.requestMap.put(player, new Request(team, player));
                sender.sendMessage(StringUtils.addColors("You have invited %s to your team.", ChatColor.GRAY, ChatColor.WHITE, player.getName()));
                player.sendMessage(ChatColor.YELLOW + "You have received a team request from: " + ChatColor.GREEN + team.toString());
                player.sendMessage(ChatColor.GOLD + "Use " + ChatColor.GREEN + "/team accept " + ChatColor.GOLD + "or " + ChatColor.RED + "/team deny" + ChatColor.GOLD + " to respond.");
            }
            else {
                sender.sendMessage(ChatColor.RED + "Player is already in a team.");
            }
        }
        else {
            sender.sendMessage(ChatColor.RED + "Player already has a team request.");
        }
    }

    @EventHandler
    public void onUpdate(UpdateEvent event) {
        if (event.getUpdateType() == UpdateType.TICK) {
            for (Map.Entry<Player, Request> request : requestMap.entrySet()) {
                long timestamp = request.getValue().getTimestamp();
                if (System.currentTimeMillis() - timestamp >= 15000) {
                    requestMap.remove(request.getKey()).timeout();
                }
            }
        }
    }

    public Request getRequest(Player player) {
        return requestMap.get(player);
    }

    public void forceRemove(Player player) {
        requestMap.remove(player);
    }

    public void declined(Player player) {
        requestMap.remove(player).decline();
    }
}