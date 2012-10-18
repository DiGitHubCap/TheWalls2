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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.Hoot215.TheWalls2.TheWalls2;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

public class BasicChunkGenerator extends ChunkGenerator
  {
    private TheWalls2 plugin;
    
    public BasicChunkGenerator(TheWalls2 instance)
      {
        plugin = instance;
      }
    
    void setBlock (int x, int y, int z, byte[][] chunk, Material material)
      {
        if (chunk[y >> 4] == null)
          chunk[y >> 4] = new byte[16 * 16 * 16];
        if ( ! (y <= 256 && y >= 0 && x <= 16 && x >= 0 && z <= 16 && z >= 0))
          return;
        chunk[y >> 4][ ( (y & 0xF) << 8) | (z << 4) | x] =
            (byte) material.getId();
      }
    
    @Override
    public byte[][] generateBlockSections (World world, Random rand,
      int chunkX, int chunkZ, BiomeGrid biomes)
      {
        byte[][] chunk = new byte[world.getMaxHeight() / 16][];
        SimplexOctaveGenerator gen1 = new SimplexOctaveGenerator(world, 8);
        gen1.setScale(1 / 24.0);
        for (int x = 0; x < 16; x++)
          {
            for (int z = 0; z < 16; z++)
              {
                int realX = x + chunkX * 16;
                int realZ = z + chunkZ * 16;
                double frequency = 0.5;
                double amplitude = 0.1;
                int multitude = 2;
                int seaLevel = world.getSeaLevel();
                double maxStoneHeight =
                    gen1.noise(realX, realZ, frequency, amplitude) * multitude
                        + seaLevel;
                double maxDirtHeight = maxStoneHeight + 3;
                
                // Stone
                for (int y = 0; y < maxStoneHeight; y++)
                  {
                    this.setBlock(x, y, z, chunk, Material.STONE);
                  }
                
                // Dirt
                for (int y = (int) maxStoneHeight; y < maxDirtHeight; y++)
                  {
                    this.setBlock(x, y, z, chunk, Material.DIRT);
                  }
                
                // Grass
                this.setBlock(x, (int) maxDirtHeight, z, chunk, Material.GRASS);
                
                // Bedrock
                this.setBlock(x, 0, z, chunk, Material.BEDROCK);
              }
          }
        int seed = this.getSeed(chunkX, chunkZ);
        Random newRand = new Random(seed * TheWalls2.timeSeed);
        int randInt = newRand.nextInt(6);
        Biome biome;
        switch ( randInt )
          {
            case 0 :
              biome = Biome.FOREST;
              break;
            case 1 :
              biome = Biome.JUNGLE;
              break;
            case 2 :
              biome = Biome.MUSHROOM_ISLAND;
              break;
            case 3 :
              biome = Biome.SWAMPLAND;
              break;
            case 4 :
              biome = Biome.PLAINS;
              break;
            case 5 :
              biome = Biome.TAIGA;
              break;
            default :
              biome = Biome.OCEAN;
          }
        for (int biomeX = 0; biomeX < 16; biomeX++)
          {
            for (int biomeZ = 0; biomeZ < 16; biomeZ++)
              {
                biomes.setBiome(biomeX, biomeZ, biome);
              }
          }
        
        return chunk;
      }
    
    public int getSeed (int chunkX, int chunkZ)
      {
        int index = 1;
        for (String s1 : plugin.getConfig().getStringList("locations.chunks"))
          {
            for (String s2 : s1.split(";"))
              {
                String[] chunk = s2.split(",");
                if (chunkX == Integer.valueOf(chunk[0])
                    && chunkZ == Integer.valueOf(chunk[1]))
                  return index;
              }
            index++;
          }
        return 0;
      }
    
    @Override
    public List<BlockPopulator> getDefaultPopulators (World world)
      {
        ArrayList<BlockPopulator> blockPopulators =
            new ArrayList<BlockPopulator>();
        blockPopulators.add(new BiomePopulator());
        blockPopulators.add(new OrePopulator());
        return blockPopulators;
      }
  }