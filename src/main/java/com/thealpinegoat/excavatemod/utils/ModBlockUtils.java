package com.thealpinegoat.excavatemod.utils;

import net.minecraft.src.Block;

import java.lang.reflect.Constructor;

public class ModBlockUtils {
    public static <T extends Block> T OverrideBlock(Block oldBlock, Class<T> newBlockClazz) {
        return OverrideBlockWithParameters(oldBlock, newBlockClazz, new Object[]{});
    }

    public static <T extends Block> T OverrideBlockWithParameters(Block oldBlock, Class<T> newBlockClazz, Object[] parameters) {
        Object[] newParams = new Object[parameters.length + 1];
        newParams[0] = oldBlock.blockID;
        System.arraycopy(parameters, 0, newParams, 1, parameters.length);

        Class<?>[] parameterTypes = new Class[parameters.length + 1];
        parameterTypes[0] = Integer.class;
        for (int i = 0; i < parameters.length; i++) {
            parameterTypes[i+1] = parameters[i].getClass();
        }

        if (Block.blocksList[oldBlock.blockID] != null) {
            Block.blocksList[oldBlock.blockID] = null;
        }
        try {
            Constructor<T> constructor = newBlockClazz.getDeclaredConstructor(parameterTypes);
            T newBlock = constructor.newInstance(newParams);
            CopyOldBlockToNew(oldBlock, newBlock);
            return newBlock;
        } catch (Exception e) {
            return null;
        }
    }
    public static void CopyOldBlockToNew(Block oldBlock, Block newBlock) {
        Block.blocksList[oldBlock.blockID] = newBlock;
        newBlock.setBlockName(oldBlock.getBlockName(0).substring(5));
        newBlock.atlasIndices = oldBlock.atlasIndices;
        newBlock.stepSound = oldBlock.stepSound;
    }
}
