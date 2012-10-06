/*
 * TheWalls2: The Walls 2 plugin. Copyright (C) 2012 Andrew Stevanus (Hoot215)
 * <hoot893@gmail.com>
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package me.Hoot215.TheWalls2.util;

import me.Hoot215.TheWalls2.TheWalls2;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Teleport
  {
    public static void teleportPlayerToLocation (Player player, Location loc)
      {
        World world = Bukkit.getServer().getWorld(TheWalls2.worldName);
        if (world == null)
          {
            System.out.println("[TheWalls2] ERROR: World is null while "
                + "attempting to teleport player " + player.getName());
            return;
          }
        
        Chunk chunk = world.getChunkAt(loc);
        if ( !chunk.isLoaded())
          {
            chunk.load();
          }
        
        player.teleport(loc);
      }
  }