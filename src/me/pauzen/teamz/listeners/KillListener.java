/*
 *  Created by Filip P. on 8/27/16 8:46 PM.
 */

package me.pauzen.teamz.listeners;

import me.pauzen.teamz.Teamz;
import me.pauzen.teamz.team.Team;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class KillListener implements Listener {

    @EventHandler
    public void onKill(PlayerDeathEvent event) {
        Player killer = event.getEntity().getKiller();
        Team team = Teamz.getInstance().getTeam(killer);
        if (killer != null) {
            if (team != null) {
                team.setKills(team.getKills() + 1);
            }
        }
    }

}
