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

package me.Hoot215.TheWalls2.chunkGen;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

public class BiomePopulator extends BlockPopulator
  {
    public void populate (World world, Random rand, Chunk chunk)
      {
        Biome biome = chunk.getBlock(0, 0, 0).getBiome();
        int realChunkX = chunk.getX() * 16;
        int realChunkZ = chunk.getZ() * 16;
        
        switch ( biome )
          {
            case FOREST :
              world.generateTree(getRandomLocation(world, chunk),
                  TreeType.BIG_TREE);
              world.generateTree(getRandomLocation(world, chunk),
                  TreeType.BIRCH);
              break;
            case JUNGLE :
              world.generateTree(getRandomLocation(world, chunk),
                  TreeType.SMALL_JUNGLE);
              world.generateTree(getRandomLocation(world, chunk),
                  TreeType.SMALL_JUNGLE);
              world.generateTree(getRandomLocation(world, chunk),
                  TreeType.JUNGLE);
              world.generateTree(getRandomLocation(world, chunk),
                  TreeType.JUNGLE_BUSH);
              world.generateTree(getRandomLocation(world, chunk),
                  TreeType.JUNGLE_BUSH);
              world.generateTree(getRandomLocation(world, chunk),
                  TreeType.JUNGLE_BUSH);
              world.generateTree(getRandomLocation(world, chunk),
                  TreeType.JUNGLE_BUSH);
              world.generateTree(getRandomLocation(world, chunk),
                  TreeType.JUNGLE_BUSH);
              world.generateTree(getRandomLocation(world, chunk),
                  TreeType.JUNGLE_BUSH);
              break;
            case MUSHROOM_ISLAND :
              world.generateTree(getRandomLocation(world, chunk),
                  TreeType.BIRCH);
              world.generateTree(getRandomLocation(world, chunk),
                  TreeType.BIRCH);
              break;
            case SWAMPLAND :
              world.generateTree(getRandomLocation(world, chunk),
                  TreeType.BROWN_MUSHROOM);
              world.generateTree(getRandomLocation(world, chunk),
                  TreeType.BROWN_MUSHROOM);
              world.generateTree(getRandomLocation(world, chunk),
                  TreeType.RED_MUSHROOM);
              world.generateTree(getRandomLocation(world, chunk),
                  TreeType.SWAMP);
              world.generateTree(getRandomLocation(world, chunk),
                  TreeType.SWAMP);
              break;
            case PLAINS :
              for (int x = 0; x < 16; x++)
                {
                  for (int z = 0; z < 16; z++)
                    {
                      Random newRand = new Random();
                      if (newRand.nextInt(10) < 3)
                        {
                          int realX = realChunkX + x;
                          int realZ = realChunkZ + z;
                          int realY =
                              world.getHighestBlockYAt(realX, realZ);
                          Block block = chunk.getBlock(realX, realY, realZ);
                          block.setType(Material.LONG_GRASS);
                          block.setData((byte) 1);
                        }
                    }
                }
              world
                  .generateTree(getRandomLocation(world, chunk), TreeType.TREE);
              world
                  .generateTree(getRandomLocation(world, chunk), TreeType.TREE);
              break;
            case TAIGA :
              world.generateTree(getRandomLocation(world, chunk),
                  TreeType.REDWOOD);
              world.generateTree(getRandomLocation(world, chunk),
                  TreeType.TALL_REDWOOD);
              for (int x = 0; x < 16; x++)
                {
                  for (int z = 0; z < 16; z++)
                    {
                      int realX = realChunkX + x;
                      int realZ = realChunkZ + z;
                      int realY = world.getHighestBlockYAt(realX, realZ);
                      Block block = chunk.getBlock(realX, realY, realZ);
                      block.setType(Material.SNOW);
                    }
                }
              break;
          }
      }
    
    private static Location getRandomLocation (World world, Chunk chunk)
      {
        Random rand1 = new Random();
        Random rand2 = new Random();
        int realX = chunk.getX() * 16;
        int realZ = chunk.getZ() * 16;
        int randX = rand1.nextInt(16);
        int randZ = rand2.nextInt(16);
        int x = realX + randX;
        int z = realZ + randZ;
        return new Location(world, x, world.getHighestBlockYAt(x, z), z);
      }
  }