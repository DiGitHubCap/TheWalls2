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

package me.Hoot215.TheWalls2;

import java.io.File;
import java.io.IOException;

import me.Hoot215.TheWalls2.util.FileUtils;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.entity.Player;

public class TheWalls2World
  {
    public static boolean isRestoring = false;
    
    public static void reloadWorld (TheWalls2 plugin)
      {
        World world = plugin.getServer().getWorld(TheWalls2.worldName);
        plugin.getServer().broadcastMessage(
            ChatColor.AQUA + "[TheWalls2] " + ChatColor.YELLOW
                + "World is being unloaded...");
        isRestoring = true;
        for (Player player : world.getPlayers())
          {
            if (player == null)
              break;
            
            player.kickPlayer("[TheWalls2] You can't be in the world when "
                + "it unloads! Please re-join in a few seconds.");
          }
        for (Player player : plugin.getRespawnQueue().getPlayerList())
          {
            if (player == null)
              break;
            
            player.kickPlayer("[TheWalls2] You can't be in the world when "
                + "it unloads! Please re-join in a few seconds.");
          }
        
        CraftWorld cw = (CraftWorld) world;
        cw.getPlayers().clear();
        
        plugin.getServer().unloadWorld(TheWalls2.worldName, false);
        plugin.getServer().broadcastMessage(
            ChatColor.AQUA + "[TheWalls2] " + ChatColor.YELLOW
                + "World is being loaded...");
        createWorld (plugin);
        isRestoring = false;
      }
    
    public static World createWorld (TheWalls2 plugin)
      {
        FileUtils.deleteFolder(new File(TheWalls2.worldName));
        try
          {
            FileUtils.copyFolder(new File("walls_template"), new File(
                TheWalls2.worldName));
          }
        catch (IOException e)
          {
            e.printStackTrace();
          }
        WorldCreator wc = new WorldCreator(TheWalls2.worldName);
        wc.generator(plugin.getDefaultWorldGenerator(TheWalls2.worldName, null));
        World world = plugin.getServer().createWorld(wc);
        plugin.getLocationData().setWorld(world);
        prepareWorld(plugin, world);
        return world;
      }
    
    public static void prepareWorld (TheWalls2 plugin, World world)
      {
        TheWalls2.timeSeed = System.currentTimeMillis();
        for (String s1 : plugin.getConfig().getStringList("locations.chunks"))
          {
            for (String s2 : s1.split(";"))
              {
                String[] chunkCoords = s2.split(",");
                world.regenerateChunk(Integer.valueOf(chunkCoords[0]),
                    Integer.valueOf(chunkCoords[1]));
              }
          }
      }
  }