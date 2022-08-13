package com.thealpinegoat.excavatemod;

import net.minecraft.src.*;

import java.util.Random;

public interface IExcavatable<C extends Block> {
    default int Excavate(World world, int x, int y, int z) {
        return Excavate(world, x, y, z, 0);
    }
    default int Excavate(World world, int x, int y, int z, int excavationSize) {
        if (excavationSize <= ExcavateMod.maxSize) {
            int xo, yo, zo;
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    for (int k = -1; k <= 1; k++) {
                        if ((i | j | k) == 0) {
                            continue;
                        }
                        xo = x + i;
                        yo = y + j;
                        zo = z + k;
                        if (world.getBlockId(xo, yo, zo) == this.getBlockId()) {
                            world.setBlockWithNotify(xo, yo, zo, 0);
                            world.dropItem(xo, yo, zo, new ItemStack(Item.itemsList[this.idDropped()]));
                            excavationSize++;
                            excavationSize = Excavate(world, xo, yo, zo, excavationSize);
                        }
                    }
                }
            }
        }
        return excavationSize;
    }
    boolean IsPlayerToolValid(EntityPlayer player);
    int getBlockId();
    default int idDropped() {
        return idDropped(0, new Random());
    }
    int idDropped(int i, Random random);
}
