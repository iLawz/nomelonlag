package me.lasillje.nomelonlag.listeners;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.inventory.ItemStack;

import me.lasillje.nomelonlag.NoMelonLag;

public class BlockGrowListener implements Listener {

	private BlockFace[] facesToCheck = {BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST};
	
	private Random r = new Random();
	
	private Material harvester;

	public BlockGrowListener(NoMelonLag plugin) {
		harvester = Material.getMaterial(plugin.getConfig().getString("harvester").toUpperCase());
	}
	
	@EventHandler
	public void onBlockGrow(BlockGrowEvent e) {
		
		Block crop = (e.getNewState().getType() == Material.MELON ||
				e.getNewState().getType() == Material.SUGAR_CANE) ? e.getNewState().getBlock() : null;
				
		if(crop != null) {
			for(BlockFace face : facesToCheck) {
				if(crop.getRelative(face).getType() == harvester) {
					
					int dropAmount = (e.getNewState().getType() == Material.MELON) ? r.nextInt(5)+3 : 1;
					Material dropType = (e.getNewState().getType() == Material.MELON) ? Material.MELON_SLICE : Material.SUGAR_CANE;
					
					crop.getWorld().dropItemNaturally(crop.getLocation(), new ItemStack(dropType, dropAmount));
					e.setCancelled(true);
					break;
				}
			}
		}
	}
}
