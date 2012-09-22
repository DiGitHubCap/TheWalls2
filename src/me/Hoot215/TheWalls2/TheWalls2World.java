/** TheWalls2: The Walls 2 plugin.
  * Copyright (C) 2012  Andrew Stevanus (Hoot215) <hoot893@gmail.com>
  * 
  * This program is free software: you can redistribute it and/or modify
  * it under the terms of the GNU Affero General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  * 
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU Affero General Public License for more details.
  * 
  * You should have received a copy of the GNU Affero General Public License
  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
  */

package me.Hoot215.TheWalls2;

import java.io.File;
import java.io.IOException;

import me.Hoot215.TheWalls2.util.FileUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

public class TheWalls2World {
	public static boolean isRestoring = false;
	
	public static void createBackup() {
		File world = new File(TheWalls2.worldName);
		File worldBackup = new File(TheWalls2.worldName + "_backup");
		if (!worldBackup.exists()) {
			System.out.println("[TheWalls2] World backup is being created...");
			try {
				FileUtils.copyFolder(world, worldBackup);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("[TheWalls2] World backup found");
		}
	}
	
	public static void restoreBackup(TheWalls2 plugin, boolean delay) {
		plugin.getServer().broadcastMessage(ChatColor.AQUA + "[TheWalls2] "
				+ ChatColor.YELLOW + "World is being unloaded...");
		isRestoring = true;
		for (Player player : plugin.getServer()
				.getWorld("thewalls2").getPlayers()) {
			if (player == null)
				break;
			
			player.kickPlayer("[TheWalls2] You can't be in the world when " +
					"it unloads! Please re-join in a few seconds.");
		}
		for (Player player : plugin.getRespawnQueue().getPlayerList()) {
			if (player == null)
				break;
			
			player.kickPlayer("[TheWalls2] You can't be in the world when " +
					"it unloads! Please re-join in a few seconds.");
		}
		plugin.getServer().unloadWorld(TheWalls2.worldName, false);
		
		if (delay) {
			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin,
					new Runnable() {
				public void run() {
					Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[TheWalls2] "
							+ ChatColor.YELLOW + "World backup is being restored...");
					File world = new File(TheWalls2.worldName);
					File worldBackup = new File(TheWalls2.worldName + "_backup");
					FileUtils.deleteFolder(world);
					try {
						FileUtils.copyFolder(worldBackup, world);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[TheWalls2] "
							+ ChatColor.YELLOW + "World is being loaded...");
					WorldCreator wc = new WorldCreator(TheWalls2.worldName);
					Bukkit.getServer().createWorld(wc);
					isRestoring = false;
				}
			}, 20L);
		}
		else {
			plugin.getServer().broadcastMessage(ChatColor.AQUA + "[TheWalls2] "
					+ ChatColor.YELLOW + "World backup is being restored...");
			File world = new File(TheWalls2.worldName);
			File worldBackup = new File(TheWalls2.worldName + "_backup");
			FileUtils.deleteFolder(world);
			try {
				FileUtils.copyFolder(worldBackup, world);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			plugin.getServer().broadcastMessage(ChatColor.AQUA + "[TheWalls2] "
					+ ChatColor.YELLOW + "World is being loaded...");
			WorldCreator wc = new WorldCreator(TheWalls2.worldName);
			plugin.getServer().createWorld(wc);
			isRestoring = false;
		}
	}
}