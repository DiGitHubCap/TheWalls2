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

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.bukkit.Location;

public class TheWalls2PlayerQueue {
	private TheWalls2 plugin;
	private Set<String> queue;
	
	public TheWalls2PlayerQueue(TheWalls2 instance) {
		plugin = instance;
		queue = new HashSet<String>();
	}
	
	public void addPlayer(String playerName, Location loc) {
		queue.add(playerName + ":" + loc.getWorld().getName()
				+ ":" + loc.getBlockX()
				+ ":" + loc.getBlockY()
				+ ":" + loc.getBlockZ());
	}
	
	public void removePlayer(String playerName, boolean teleport) {
		if (teleport)
			plugin.getServer().getPlayer(playerName)
			.teleport(getLastPlayerLocation(playerName));
		
		Iterator<String> iter = queue.iterator();
		while(iter.hasNext()) {
			String s = iter.next();
			
			if (s.contains(playerName)) {
				iter.remove();
			}
		}
	}
	
	public Location getLastPlayerLocation(String playerName) {
		Location loc = null;
		
		for (String s : queue) {
			String[] a = s.split(":");
			if (a[0].equals(playerName)) {
				loc = new Location(plugin.getServer().getWorld(a[1]),
						Double.parseDouble(a[2]),
						Double.parseDouble(a[3]),
						Double.parseDouble(a[4]));
			}
		}
		
		return loc;
	}
	
	public boolean isInQueue(String playerName) {
		for (String s : queue) {
			if (s.contains(playerName))
				return true;
		}
		return false;
	}
	
	public int getSize() {
		return queue.size();
	}
	
	public Set<String> getList() {
		return queue;
	}
	
	public void reset(boolean teleport) {
		if (!teleport) {
			queue = new HashSet<String>();
			return;
		}
		
		for (String s : queue) {
			String playerName = s.split(":")[0];
			plugin.getServer().getPlayer(playerName)
			.teleport(getLastPlayerLocation(playerName));
		}
		queue = new HashSet<String>();
	}
}