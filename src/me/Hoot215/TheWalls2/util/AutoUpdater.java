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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import me.Hoot215.TheWalls2.TheWalls2;

import org.bukkit.ChatColor;

public class AutoUpdater implements Runnable
  {
    private TheWalls2 plugin;
    private Object lock;
    private static boolean isUpToDate = true;
    private String currentVersion;
    
    public AutoUpdater(TheWalls2 plugin, Object lock, String currentVersion)
      {
       	this.plugin = plugin;
        this.lock = lock;
        this.currentVersion = currentVersion;
      }
    
    public Object getLock ()
      {
        return lock;
      }
    
    public static boolean getIsUpToDate ()
      {
        return isUpToDate;
      }
    
    public void run ()
      {
        while (true)
          {
            try
              {
                URL url =
                    new URL(
                        "http://dl.dropbox.com/u/56151340/BukkitPlugins/TheWalls2/latest");
                Scanner s = new Scanner(url.openStream());
                if ( !checkUpdated(s.nextLine()))
                  {
                    synchronized (lock)
                      {
                        isUpToDate = false;
                      }
                    plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin,
                        new Runnable()
                          {
                            public void run ()
                              {
                                plugin.getServer().getConsoleSender().sendMessage(
                                    ChatColor.GREEN
                                        + "[TheWalls2] An updated version of "
                                        + "TheWalls2 is available!");
                              }
                          });
                  }
                s.close();
                Thread.sleep(3600000L);
              }
            catch (MalformedURLException e)
              {
                plugin.getLogger().severe("Error with AutoUpdater: " + e.getMessage());
              }
            catch (IOException e)
              {
            	plugin.getLogger().severe("Error with AutoUpdater: " + e.getMessage());
              }
            catch (InterruptedException e)
              {
            	plugin.getLogger().severe("Error with AutoUpdater: " + e.getMessage());
              }
          }
      }
    
    private boolean checkUpdated (String newestVersion)
      {
        String[] currentVersionArray = currentVersion.split("\\.");
        String[] newestVersionArray = newestVersion.split("\\.");
        int[] c = new int[3];
        int[] n = new int[3];
        c[0] = Integer.parseInt(currentVersionArray[0]);
        c[1] = Integer.parseInt(currentVersionArray[1]);
        n[0] = Integer.parseInt(newestVersionArray[0]);
        n[1] = Integer.parseInt(newestVersionArray[1]);
        if (currentVersionArray.length > 3)
          {
            c[2] = 0;
          }
        if (newestVersionArray.length > 3)
          {
            n[2] = 0;
          }
        
        if (n[0] > c[0])
          {
            return false;
          }
        else if (n[1] > c[1])
          {
            return false;
          }
        else if (n[2] > c[2])
          {
            return false;
          }
        return true;
      }
  }