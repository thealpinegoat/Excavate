package com.thealpinegoat.excavatemod;

import net.minecraft.src.*;

public interface IOreExcavatable extends IExcavatable{
    default boolean IsPlayerToolValid(EntityPlayer player) {
        if (player == null) {
            return false;
        }
        ItemStack currentEquippedItemStack = player.getCurrentEquippedItem();
        if (currentEquippedItemStack == null) {
            return false;
        }
        Item heldItem = currentEquippedItemStack.getItem();
        return (heldItem instanceof  ItemToolPickaxe);
    }

    default void TryExcavate(EntityPlayer entityPlayer, World world, int x, int y, int z) {
        if (world.isMultiplayerAndNotHost) {
            return;
        }
        if (entityPlayer == null) {
            entityPlayer = world.getClosestPlayer(x, y, z, 10);
        }
        if (IsPlayerToolValid(entityPlayer)) {
            ItemStack heldItem = entityPlayer.getCurrentEquippedItem();
            if (heldItem.getItem().isDamagable() && Block.blocksList[getBlockId()].getHardness() > 0.0f) {
                int blocksBroken = Excavate(world, x, y, z);
                heldItem.damageItem(blocksBroken, null);
            } else {
                Excavate(world, x, y, z);
            }
        }
    }
}
