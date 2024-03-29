package com.icesoup.ebrius.commands;

import com.icesoup.ebrius.Main;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
//import java.util.logging.Level;

public class Playtime implements CommandExecutor {

    Main plugin;

    public Playtime(Main plugin) {
        this.plugin = plugin;
    }

    private String getTimeString(Integer seconds) {
        int days = (int) TimeUnit.SECONDS.toDays(seconds);
        long hours = (TimeUnit.SECONDS.toHours(seconds) - (days * 24L));
        long minutes = (TimeUnit.SECONDS.toMinutes(seconds) -
                (TimeUnit.SECONDS.toHours(seconds) * 60));
        long secs = (TimeUnit.SECONDS.toSeconds(seconds) -
                (TimeUnit.SECONDS.toMinutes(seconds) * 60));
        if (days > 0) {
            return days + "d" + " " + hours + "h" + " " + minutes + "m" + " " + secs + "s";
        } else {
            return hours + "h" + " " + minutes + "m" + " " + secs + "s";
        }
    }

    private void getPlaytime(Player player, Player sender) {
        int secondsPlayed = player.getStatistic(Statistic.PLAY_ONE_MINUTE) / 20;
        String startString = player.getName() + "'s Playtime: ";
        if (player.equals(sender)){
            startString = "Time Played: ";
        }
        String timePlayed = ChatColor.AQUA + startString + ChatColor.RESET + getTimeString(secondsPlayed);
        sender.sendMessage(timePlayed);
    }

    private void getPlaytime(OfflinePlayer player, Player sender) {
        int secondsPlayed = player.getStatistic(Statistic.PLAY_ONE_MINUTE) / 20;
        String startString = player.getName() + "'s Playtime: ";
        String timePlayed = ChatColor.AQUA + startString + ChatColor.RESET + getTimeString(secondsPlayed);
        sender.sendMessage(timePlayed);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        boolean foundOffline = false;
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0) {
//                player.sendMessage("Hello: " + args[0]);
                Player player1 = plugin.getServer().getPlayerExact(args[0]);
                if (player1 != null) {
//                    plugin.getLogger().info(player1.getName());
                    getPlaytime(player1, player);
                } else {
                    OfflinePlayer[] offlinePlayers = plugin.getServer().getOfflinePlayers();
                    for (OfflinePlayer offlinePlayer:offlinePlayers) {
                        if(Objects.equals(offlinePlayer.getName(), args[0])){
//                            plugin.getLogger().info(offlinePlayer.getName());
                            getPlaytime(offlinePlayer, player);
                            foundOffline = true;
                            break; // break out of the loop when player is found offline
                        }
                    }
                    if (!foundOffline){
                        player.sendMessage(ChatColor.RED + "Player not found!");
                    }
                }

            } else {
                getPlaytime(player, player);
            }
            return true;
        }
        return false;
    }

}
