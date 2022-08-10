package me.pesekjak.bitshift;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Bitshift extends JavaPlugin {

    @Getter
    private static Bitshift instance;
    @Getter
    private static SkriptAddon addonInstance;

    @Override
    public void onEnable() {
        if(!Bukkit.getPluginManager().isPluginEnabled("Skript")) {
            Logger.severe("Skript plugin is not installed, disabling.");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        if(!setupAddon()) {
            Logger.severe("Bitshift failed to load, disabling.");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        Logger.info("Plugin successfully enabled.");
    }

    protected boolean setupAddon() {
        instance = this;
        addonInstance = Skript.registerAddon(getInstance());
        try {
            getAddonInstance().loadClasses("me.pesekjak.bitshift.skript");
            return true;
        } catch (IOException exception) {
            return false;
        }
    }

}
