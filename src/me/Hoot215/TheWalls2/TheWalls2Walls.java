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

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import me.Hoot215.TheWalls2.util.Cuboid;

public class TheWalls2Walls implements Runnable
  {
    private TheWalls2 plugin;
    private Cuboid wall;
    private int sleepTime;
    
    public TheWalls2Walls(TheWalls2 instance, Cuboid wall, int sleepTime)
      {
        plugin = instance;
        this.wall = wall;
        this.sleepTime = sleepTime;
      }
    
    public void run ()
      {
        for (int x = wall.getXMin(); x <= wall.getXMax(); x++)
          {
            for (int z = wall.getZMin(); z <= wall.getZMax(); z++)
              {
                for (int y = wall.getYMin(); y <= wall.getYMax(); y++)
                  {
                    this.doWork(x, y, z);
                  }
              }
          }
      }
    
    public void doWork (final int x, final int y, final int z)
      {
        plugin.getServer().getScheduler()
            .scheduleSyncDelayedTask(plugin, new Runnable()
              {
                public void run ()
                  {
                    World world =
                        plugin.getServer().getWorld(TheWalls2.worldName);
                    Block block = world.getBlockAt(x, y, z);
                    Material material = block.getType();
                    Byte data = block.getData();
                    block.setType(Material.AIR);
                    world.spawnFallingBlock(new Location(world, x, y, z),
                        material, data);
                  }
              });
        try
          {
            Thread.sleep(sleepTime);
          }
        catch (InterruptedException e)
          {
            e.printStackTrace();
          }
      }
  }