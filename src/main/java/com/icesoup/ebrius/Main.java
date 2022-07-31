package com.icesoup.ebrius;

import com.icesoup.ebrius.commands.Playtime;
//import com.icesoup.ebrius.utils.onJoinListener;
//import com.icesoup.ebrius.utils.onQuitListener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getLogger().info("==========Ebrius Playtime Setup==========");
        getCommand("playtime").setExecutor(new Playtime(this));
//        getServer().getPluginManager().registerEvents(new onJoinListener(), this);
//        getServer().getPluginManager().registerEvents(new onQuitListener(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable is called!");
    }
}
