package me.lasillje.nomelonlag;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.lasillje.nomelonlag.listeners.BlockGrowListener;

public class NoMelonLag extends JavaPlugin {
		
	@Override
	public void onEnable() {
		
		getConfig().addDefault("harvester", "PISTON_HEAD");
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		
		registerListener(new BlockGrowListener(this));
	}

	private void registerListener(Listener listener) {
		Bukkit.getPluginManager().registerEvents(listener,this);
	}	
}
