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

import java.util.HashMap;
import java.util.Map;

import me.Hoot215.TheWalls2.util.EntireInventory;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class TheWalls2Inventory
  {
    private Map<String, EntireInventory> inventoryMap;
    
    public TheWalls2Inventory()
      {
        inventoryMap = new HashMap<String, EntireInventory>();
      }
    
    public ItemStack[] getInventoryContents (String playerName)
      {
        return inventoryMap.get(playerName).getContents();
      }
    
    public ItemStack[] getArmourContents (String playerName)
      {
        return inventoryMap.get(playerName).getArmourContents();
      }
    
    public boolean hasInventory (String playerName)
      {
        return inventoryMap.containsKey(playerName);
      }
    
    public void addInventory (String playerName, PlayerInventory inv)
      {
        inventoryMap.put(playerName, new EntireInventory(inv));
      }
    
    public void removeInventory (String playerName)
      {
        inventoryMap.remove(playerName);
      }
    
    public void clearInventory (Player player)
      {
        player.getInventory().clear();
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
      }
  }