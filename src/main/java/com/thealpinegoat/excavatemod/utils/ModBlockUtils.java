package com.thealpinegoat.excavatemod.utils;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemBlock;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ModBlockUtils {
    public static <T> T TryCreateOverrideBlock(Block oldBlock, Class<T> newBlockClazz) {
        if (Block.blocksList[oldBlock.blockID] != null) {
            Block.blocksList[oldBlock.blockID] = null;
        }
        try {
            Constructor<T> constructor = newBlockClazz.getDeclaredConstructor(int.class);
            T newBlock = constructor.newInstance(oldBlock.blockID);
            Block.blocksList[oldBlock.blockID] = (Block) newBlock;
            return newBlock;
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T TryCreateOverrideBlock(Block oldBlock, Class<T> newBlockClazz, boolean bool) {
        if (Block.blocksList[oldBlock.blockID] != null) {
            Block.blocksList[oldBlock.blockID] = null;
        }
        try {
            Constructor<T> constructor = newBlockClazz.getDeclaredConstructor(int.class, boolean.class);
            T newBlock = constructor.newInstance(oldBlock.blockID, bool);
            Block.blocksList[oldBlock.blockID] = (Block) newBlock;
            return newBlock;
        } catch (Exception e) {
            return null;
        }
    }

    public static int FindNextFreeBlockId() {
        for (int id = 0; id < Block.blocksList.length; id++) {
            if (Block.blocksList[id] == null) {
                return id;
            }
        }
        // No free block IDs found.
        return -1;
    }
}
