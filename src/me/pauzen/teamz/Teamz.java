/*
 *  Created by Filip P. on 8/27/16 5:26 PM.
 */

/*
 *  Created by Filip P. on 4/5/15 10:26 PM.
 */

package me.pauzen.teamz;

import me.pauzen.teamz.command.CommandManager;
import me.pauzen.teamz.listeners.KillListener;
import me.pauzen.teamz.request.RequestManager;
import me.pauzen.teamz.team.Team;
import me.pauzen.teamz.team.TeamManager;
import me.pauzen.teamz.updater.UpdateManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Teamz extends JavaPlugin {

    private static Teamz instance;
    private boolean enabled = true;

    public static Teamz getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        new UpdateManager();
        CommandManager commandManager = new CommandManager();
        getCommand("team").setExecutor(commandManager);
        getCommand("teamchat").setExecutor(commandManager);
        getCommand("sendcoords").setExecutor(commandManager);
        Bukkit.getPluginManager().registerEvents(new KillListener(), this);
        new RequestManager().register(this);
    }

    public Team getTeam(Player player) {
        return TeamManager.getInstance().getTeams().get(player.getUniqueId());
    }

    public void setTeamsDisabled() {
        this.enabled = false;
        TeamManager.getInstance().clearTeams();
    }

    public void setTeamsEnabled() {
        this.enabled = true;
    }

    public boolean isTeamsEnabled() {
        return enabled;
    }
}
