package com.icesoup.ebrius.commands;

import com.icesoup.ebrius.Main;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Playtime implements CommandExecutor {

    Main plugin;

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

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0) {
                player.sendMessage("Hello: " + args[0]);
                Player player1 = plugin.getServer().getPlayer(args[0]);
                if (player1 != null) {
                    plugin.getLogger().log(Level.ALL, player1.getName());
                } else {
                    plugin.getLogger().log(Level.ALL, "potty");
                }

            } else {
                int secondsPlayed = player.getStatistic(Statistic.PLAY_ONE_MINUTE) / 20;
                System.out.println(secondsPlayed);
                String timePlayed = ChatColor.AQUA + "Time Played: " + ChatColor.RESET + getTimeString(secondsPlayed);
                player.sendMessage(timePlayed);
            }
            return true;
        }
        return false;
    }

}
