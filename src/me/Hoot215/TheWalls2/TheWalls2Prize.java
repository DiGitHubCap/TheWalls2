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

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TheWalls2Prize
  {
    public static void givePrize (TheWalls2 plugin, Player player)
      {
        String prize = plugin.getConfig().getString("general.prize");
        if (prize.equals("none"))
          {
            return;
          }
        else if (prize.equals("item"))
          {
            giveItemPrize(plugin, player);
            return;
          }
        else if (prize.equals("money"))
          {
            giveMoneyPrize(plugin, player);
            return;
          }
      }
    
    public static void giveItemPrize (TheWalls2 plugin, Player player)
      {
        String[] prize =
            plugin.getConfig().getString("general.prize-item").split(":");
        int prizeID = Integer.parseInt(prize[0]);
        int prizeAmount = Integer.parseInt(prize[2]);
        short prizeData = Short.parseShort(prize[1]);
        ItemStack item = new ItemStack(prizeID, prizeAmount, prizeData);
        player.getWorld().dropItem(player.getLocation(), item);
        player.sendMessage(ChatColor.AQUA + "[TheWalls2] " + ChatColor.GREEN
            + "Here's your prize!");
      }
    
    public static void giveMoneyPrize (TheWalls2 plugin, Player player)
      {
        double amount = plugin.getConfig().getDouble("general.prize-money");
        TheWalls2.economy.depositPlayer(player.getName(), amount);
        player.sendMessage(ChatColor.AQUA + "[TheWalls2] " + ChatColor.GREEN
            + "Here's your prize!");
        player.sendMessage(ChatColor.AQUA + "[TheWalls2] " + ChatColor.GOLD
            + TheWalls2.economy.format(amount) + ChatColor.GREEN
            + " has been deposited into your account");
      }
  }