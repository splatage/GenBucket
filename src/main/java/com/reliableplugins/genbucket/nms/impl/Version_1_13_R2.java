package com.reliableplugins.genbucket.nms.impl;

import com.reliableplugins.genbucket.nms.NMSHandler;
import net.minecraft.server.v1_13_R2.*;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_13_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_13_R2.util.CraftMagicNumbers;
import org.bukkit.inventory.ItemStack;

public class Version_1_13_R2 implements NMSHandler {

    @Override
    public void setBlock(World world, int x, int y, int z, int id, byte data) {
        if (y > 255) return;

        net.minecraft.server.v1_13_R2.World w = ((CraftWorld) world).getHandle();
        net.minecraft.server.v1_13_R2.Chunk chunk = w.getChunkAt(x >> 4, z >> 4);
        BlockPosition bp = new BlockPosition(x, y, z);
        IBlockData ibd = CraftMagicNumbers.getBlock(org.bukkit.Material.values()[id], data);
        w.setTypeAndData(bp, ibd, 2);
        chunk.setType(bp, ibd, false);

    }

    @Override
    public ItemStack setGeneratorItem(ItemStack itemStack, String type) {
        net.minecraft.server.v1_13_R2.ItemStack nms = CraftItemStack.asNMSCopy(itemStack);
        if (nms.getTag() == null) {
            nms.setTag(new NBTTagCompound());
        }

        //Hook Plugins Can Check If The Item Has NBTTag: GENBUCKET
        nms.getTag().setString("GENBUCKET", "GENBUCKET");
        nms.getTag().setString(key, type);
        return CraftItemStack.asBukkitCopy(nms);
    }

    @Override
    public String getGeneratorType(ItemStack itemStack) {
        net.minecraft.server.v1_13_R2.ItemStack nms = CraftItemStack.asNMSCopy(itemStack);

        if (nms == null) return null;

        if (nms.getTag() != null && nms.getTag().hasKey(key)) {
            return nms.getTag().getString(key);
        }
        return null;
    }

    @Override
    public String getVersion() {
        return "v1_13_R2";
    }
}