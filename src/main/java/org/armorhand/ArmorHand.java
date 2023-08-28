package org.armorhand;

import org.armorhand.listeners.PlaceListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class ArmorHand extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getConfig();

        getServer().getPluginManager().registerEvents(new PlaceListener(getConfig()), this);
    }


}
