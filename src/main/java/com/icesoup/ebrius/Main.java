package com.icesoup.ebrius;

import com.icesoup.ebrius.commands.Playtime;
import com.icesoup.ebrius.utils.onJoinListener;
import com.icesoup.ebrius.utils.onQuitListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("onEnable is called!");
        this.getCommand("playtime").setExecutor(new Playtime());
//        getServer().getPluginManager().registerEvents(new onJoinListener(), this);
//        getServer().getPluginManager().registerEvents(new onQuitListener(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable is called!");
    }
}
