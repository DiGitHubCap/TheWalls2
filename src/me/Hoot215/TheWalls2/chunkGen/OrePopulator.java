
package me.Hoot215.TheWalls2.chunkGen;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

public class OrePopulator extends BlockPopulator
  {
    public void populate (World world, Random rand, Chunk chunk)
      {
        // Coal
        for (int i = 0; i < 80; i++)
          {
            if (new Random().nextInt(10) < 8)
              {
                int x = new Random().nextInt(16);
                int z = new Random().nextInt(16);
                int y = new Random().nextInt(57) + 3;
                Block[] blocks =
                    {chunk.getBlock(x, y, z), chunk.getBlock(x, y, z + 1),
                        chunk.getBlock(x, y + 1, z + 1),
                        chunk.getBlock(x + 1, y + 1, z + 1),
                        chunk.getBlock(x + 1, y + 1, z + 2),
                        chunk.getBlock(x + 1, y + 2, z + 2),
                        chunk.getBlock(x + 2, y + 2, z + 2),
                        chunk.getBlock(x + 2, y + 2, z + 3)};
                for (int a = 0; a < blocks.length; a++)
                  {
                    Block block = blocks[a];
                    if (block.getType() == Material.STONE);
                      block.setType(Material.COAL_ORE);
                  }
              }
          }
        
        // Iron
        for (int i = 0; i < 40; i++)
          {
            if (new Random().nextInt(10) < 8)
              {
                int x = new Random().nextInt(16);
                int z = new Random().nextInt(16);
                int y = new Random().nextInt(57) + 3;
                Block[] blocks =
                    {chunk.getBlock(x, y, z), chunk.getBlock(x, y, z + 1),
                        chunk.getBlock(x, y + 1, z + 1),
                        chunk.getBlock(x + 1, y + 1, z + 1)};
                for (int a = 0; a < blocks.length; a++)
                  {
                    Block block = blocks[a];
                    if (block.getType() == Material.STONE);
                      block.setType(Material.IRON_ORE);
                  }
              }
          }
        
        // Gold
        for (int i = 0; i < 20; i++)
          {
            if (new Random().nextInt(10) < 8)
              {
                int x = new Random().nextInt(16);
                int z = new Random().nextInt(16);
                int y = new Random().nextInt(29) + 3;
                Block[] blocks =
                    {chunk.getBlock(x, y, z), chunk.getBlock(x, y, z + 1),
                        chunk.getBlock(x, y + 1, z + 1),
                        chunk.getBlock(x + 1, y + 1, z + 1)};
                for (int a = 0; a < blocks.length; a++)
                  {
                    Block block = blocks[a];
                    if (block.getType() == Material.STONE);
                      block.setType(Material.GOLD_ORE);
                  }
              }
          }
      }
    
  }
