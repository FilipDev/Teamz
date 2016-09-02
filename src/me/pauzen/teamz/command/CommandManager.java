/*
 *  Created by Filip P. on 8/27/16 7:51 PM.
 */

package me.pauzen.teamz.command;

import me.pauzen.teamz.Teamz;
import me.pauzen.teamz.team.Team;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandManager implements CommandExecutor {

    private Map<String, TeamCommandExecutor> teamCommands = new HashMap<>();

    {
        teamCommands.put("accept", new AcceptCommand());
        teamCommands.put("deny", new DenyCommand());
        teamCommands.put("size", new SizeCommand());
        teamCommands.put("invite", new InviteCommand());
        teamCommands.put("solo", new CreateCommand());
        teamCommands.put("create", new CreateCommand());
        teamCommands.put("leave", new LeaveCommand());
        teamCommands.put("list", new ListCommand());
        teamCommands.put("kills", new KillsCommand());
        teamCommands.put("last", new LastCommand());
        teamCommands.put("left", new LeftCommand());
        teamCommands.put("reset", new ResetCommand());
        teamCommands.put("clear", new ResetCommand());
        teamCommands.put("kick", new KickCommand());
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (command.getName().equals("teamchat")) {
            if (args.length > 0) {
                if (commandSender instanceof Player) {
                    Player player = (Player) commandSender;
                    Team team = Teamz.getInstance().getTeam(player);
                    if (team != null) {
                        StringBuilder messageBuilder = new StringBuilder();
                        for (int i = 0; i < args.length; i++) {
                            String arg = args[i];
                            if (i != 0) {
                                messageBuilder.append(" ");
                            }
                            messageBuilder.append(arg);
                        }
                        team.sendMessage(player.getName() + ": " + messageBuilder.toString());
                    }
                }
            }
            else {
                commandSender.sendMessage(ChatColor.RED + "Please add a message after the command.");
            }
        }
        if (command.getName().equals("sendcoords")) {
            if (commandSender instanceof Player) {
                Player player = (Player) commandSender;
                Team team = Teamz.getInstance().getTeam(player);
                if (team != null) {
                    String location = "x= " + player.getLocation().getBlockX() + " y=" + player.getLocation().getBlockY() + " z=" + player.getLocation().getBlockZ();
                    team.sendMessage(player.getName() + " sent out their coordinates: " + location);
                }
                else {
                    player.sendMessage(ChatColor.RED + "You are not in a team.");
                }
            }
        }
        if (command.getName().equals("team")) {
            if (args.length > 0) {
                TeamCommandExecutor executor = teamCommands.get(args[0]);
                if (executor == null) {
                    if (args[0].equals("true")) {
                        Teamz.getInstance().setTeamsEnabled();
                        commandSender.sendMessage("Enabled teams.");
                        return true;
                    }
                    if (args[0].equals("false")) {
                        Teamz.getInstance().setTeamsDisabled();
                        commandSender.sendMessage("Disabled teams.");
                        return true;
                    }
                    commandSender.sendMessage(ChatColor.RED + "Subcommand not found.");
                }
                else {
                    String[] newArgs = Arrays.copyOfRange(args, 1, args.length);
                    if (commandSender instanceof Player) {
                        Player sender = (Player) commandSender;
                        if (sender.getWorld().getName().equals("SPAWN")) {
                            executor.onCommand(sender, newArgs);
                        }
                        else {
                            sender.sendMessage(ChatColor.RED + "Command cannot be executed in this world.");
                        }
                    }
                    executor.onCommand(commandSender, newArgs);
                }
            }
            else {
                commandSender.sendMessage(ChatColor.RED + "Please specify a valid subcommand.");
                for (String subcommand : teamCommands.keySet()) {
                    commandSender.sendMessage("/team " + subcommand);
                }
            }
        }
        return true;
    }
}
