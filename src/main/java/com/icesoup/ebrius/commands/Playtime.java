package com.icesoup.ebrius.commands;

import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

public class Playtime implements CommandExecutor {

    private String getTimeString(Integer seconds) {
        int days = (int) TimeUnit.SECONDS.toDays(seconds);
        long hours = (TimeUnit.SECONDS.toHours(seconds) - (days * 24L));
        long minutes = (TimeUnit.SECONDS.toMinutes(seconds) -
                (TimeUnit.SECONDS.toHours(seconds) * 60));
        long secs = (TimeUnit.SECONDS.toSeconds(seconds) -
                (TimeUnit.SECONDS.toMinutes(seconds) * 60));
        if (days > 0){
            return days + "d" + " " + hours + "h" + " " + minutes + "m" + " " + secs + "s";
        }
        else {
            return hours + "h" + " " + minutes + "m" + " " + secs + "s";
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            int secondsPlayed = player.getStatistic(Statistic.PLAY_ONE_MINUTE) / 20;
            System.out.println(secondsPlayed);
            String timePlayed = ChatColor.AQUA + "Time Played: " + ChatColor.RESET + getTimeString(secondsPlayed);
            player.sendMessage(timePlayed);
            return true;
        }
        return false;
    }

}
