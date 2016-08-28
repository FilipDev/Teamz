/*
 *  Created by Filip P. on 8/27/16 5:36 PM.
 */

/*
 *  Created by Filip P. on 8/27/16 5:35 PM.
 */

package me.pauzen.teamz.request;

import me.pauzen.teamz.team.Team;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Request {
    private Team team;
    private Player recipient;
    private long sentTimestamp;

    public Request(Team team, Player recipient) {
        this.team = team;
        this.recipient = recipient;
        this.sentTimestamp = System.currentTimeMillis();
    }

    public long getTimestamp() {
        return sentTimestamp;
    }

    void decline() {
        recipient.sendMessage(ChatColor.GOLD + "You declined the team invite.");
        team.getOwner().sendMessage(ChatColor.GOLD + "Team invite to " + recipient.getName() + " was declined.");
    }

    void timeout() {
        recipient.sendMessage(ChatColor.RED + "Your request timed out.");
        team.getOwner().sendMessage(ChatColor.RED + "Team invite to " + recipient.getName() + " timed out.");
    }

    public Team getTeam() {
        return team;
    }
}
