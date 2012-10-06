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

public class TheWalls2ConfigSetter
  {
    public static boolean isLobbyDifferent (TheWalls2 plugin, String locString)
      {
        if (locString.isEmpty())
          return false;
        String[] locStringArray = locString.split(";");
        double x = Double.parseDouble(locStringArray[0]);
        double y = Double.parseDouble(locStringArray[1]);
        double z = Double.parseDouble(locStringArray[2]);
        
        Location lobby = plugin.getLocationData().getLobby();
        if (lobby.getX() != x || lobby.getY() != y || lobby.getZ() != z)
          {
            return true;
          }
        return false;
      }
    
    public static void updateLobbyLocation (TheWalls2 plugin, String locString)
      {
        String[] locStringArray = locString.split(";");
        double x = Double.parseDouble(locStringArray[0]);
        double y = Double.parseDouble(locStringArray[1]);
        double z = Double.parseDouble(locStringArray[2]);
        Location lobby =
            new Location(plugin.getServer().getWorld(TheWalls2.worldName), x,
                y, z);
        plugin.getLocationData().setLobby(lobby);
      }
  }