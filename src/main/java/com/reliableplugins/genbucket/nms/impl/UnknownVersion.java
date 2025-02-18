package com.reliableplugins.genbucket.nms.impl;

import com.reliableplugins.genbucket.nms.NMSHandler;
import net.minecraft.core.BlockPosition;
import net.minecraft.world.level.block.state.IBlockData;
import net.minecraft.world.level.chunk.Chunk;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.util.CraftMagicNumbers;

/**
 * @author : Michael
 * @since : 6/30/2021, Wednesday
 **/
public class UnknownVersion implements NMSHandler {

    @Override
    public void setBlock(World world, int x, int y, int z, int id, byte data) {
        if (y > 255) return;
        net.minecraft.server.level.WorldServer w = ((CraftWorld) world).getHandle();
        Chunk chunk = w.getChunkAt(x >> 4, z >> 4);
        BlockPosition bp = new BlockPosition(x, y, z);
        IBlockData ibd = CraftMagicNumbers.getBlock(org.bukkit.Material.values()[id], data);
        w.setTypeAndData(bp, ibd, 3);
        chunk.setType(bp, ibd, false);
    }

}