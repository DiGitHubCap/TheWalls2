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

import java.util.List;

import me.Hoot215.TheWalls2.util.Cuboid;

import org.bukkit.Location;
import org.bukkit.Material;

public class TheWalls2GameTimer implements Runnable
  {
    private TheWalls2 plugin;
    private boolean notify;
    private int notifyInterval;
    private int time;
    private int originalTime;
    
    public TheWalls2GameTimer(TheWalls2 instance, boolean notify,
      int notifyInterval, int time)
      {
        plugin = instance;
        this.notify = notify;
        this.notifyInterval = notifyInterval;
        this.time = time;
        originalTime = time;
      }
    
    public void run ()
      {
        while (time > 0)
          {
            if (plugin.getGameList() == null)
              return;
            
            if (notify && time != originalTime && time % notifyInterval == 0)
              {
                plugin.getServer().getScheduler()
                    .scheduleSyncDelayedTask(plugin, new Runnable()
                      {
                        public void run ()
                          {
                            plugin.getGameList().notifyAll(
                                String.valueOf(time) + " minutes remaining!");
                            time = time - 1;
                          }
                      });
              }
            else
              time = time - 1;
            try
              {
                Thread.sleep(60000);
              }
            catch (InterruptedException e)
              {
                e.printStackTrace();
              }
          }
        
        if (plugin.getGameList() == null)
          return;
        
        plugin.getServer().getScheduler()
            .scheduleSyncDelayedTask(plugin, new Runnable()
              {
                public void run ()
                  {
                    plugin.getGameList().notifyAll("The walls are dropping!");
                    @SuppressWarnings("unchecked")
                    List<String> locList =
                        (List<String>) plugin.getConfig().getList(
                            "locations.walls");
                    int iterations = 1;
                    int wallDelay =
                        plugin.getConfig().getInt("locations.wall-delay");
                    for (final String s : locList)
                      {
                        int delay = wallDelay * 20 * iterations;
                        plugin.getServer().getScheduler()
                            .scheduleSyncDelayedTask(plugin, new Runnable()
                              {
                                public void run ()
                                  {
                                    String[] locs = s.split(";");
                                    String[] loc1Array = locs[0].split(",");
                                    String[] loc2Array = locs[1].split(",");
                                    Location loc1 =
                                        new Location(plugin.getServer()
                                            .getWorld(TheWalls2.worldName),
                                            Integer.valueOf(loc1Array[0]),
                                            Integer.valueOf(loc1Array[1]),
                                            Integer.valueOf(loc1Array[2]));
                                    Location loc2 =
                                        new Location(plugin.getServer()
                                            .getWorld(TheWalls2.worldName),
                                            Integer.valueOf(loc2Array[0]),
                                            Integer.valueOf(loc2Array[1]),
                                            Integer.valueOf(loc2Array[2]));
                                    Cuboid cube = new Cuboid(loc1, loc2);
                                    int sleepTime =
                                        plugin.getConfig().getInt(
                                            "locations.sleep-interval");
                                    new Thread(new TheWalls2Walls(plugin, cube,
                                        sleepTime)).start();
                                  }
                              }, delay);
                        iterations++;
                      }
                  }
              });
        plugin.getServer().getScheduler()
            .scheduleSyncDelayedTask(plugin, new Runnable()
              {
                public void run ()
                  {
                    Location virtualWallDrop =
                        plugin.getLocationData().getVirtualWallDrop();
                    plugin.getServer().getWorld(TheWalls2.worldName)
                        .getBlockAt(virtualWallDrop).setType(Material.AIR);
                  }
              }, 2L);
      }
  }