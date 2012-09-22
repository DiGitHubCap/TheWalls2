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
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TheWalls2GameList {
	private Set<String> gameList;
	
	public TheWalls2GameList(Set<String> queue) {
		gameList = new HashSet<String>();
		
		for (String s : queue) {
			gameList.add(s.split(":")[0]);
		}
	}
	
	public void notifyAll(String message) {
		for (String playerName : gameList) {
			Player player = Bukkit.getServer().getPlayer(playerName);
			String prefix = ChatColor.AQUA + "[TheWalls2] " + ChatColor.YELLOW;
			player.sendMessage(prefix + message);
		}
	}
	
	public void removeFromGame(String playerName) {
		gameList.remove(playerName);
	}
	
	public boolean isInGame(String playerName) {
		if (gameList.contains(playerName))
			return true;
		return false;
	}
	
	public int getPlayerCount() {
		return gameList.size();
	}
	
	public String getLastPlayer() {
		String ret = null;
		for (String s : gameList) {
			ret = s;
		}
		return ret;
	}
	
	public Set<String> getList() {
		return gameList;
	}
}