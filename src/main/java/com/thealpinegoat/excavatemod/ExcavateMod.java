package com.thealpinegoat.excavatemod;

import bta.Mod;
import com.thealpinegoat.excavatemod.blocks.*;
import com.thealpinegoat.excavatemod.utils.*;
import net.minecraft.client.Minecraft;
import net.minecraft.src.Block;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExcavateMod implements Mod {
    public static final String MOD_ID = "Excavate";
    private static final Logger log = Logger.getLogger(MOD_ID);
    public static int maxSize = 128;
    public static Map<Integer, Block> modBlocksMap = new HashMap<>();
    private static Minecraft _minecraft;

    public static Minecraft getMinecraft() {
        return _minecraft;
    }

    private void OverrideBlocks(String[] stoneTypes, BlockOverrideInitSettings[] oreOverrideSettings) {
        String blockName;
        Block  oldBlock, newBlock;
        for (BlockOverrideInitSettings oreOverrideSetting : oreOverrideSettings) {
            for (String stoneType : stoneTypes) {
                blockName = String.format("tile.ore.%s.%s", oreOverrideSetting.name, stoneType);
                if (!Block.nameToIdMap.containsKey(blockName)) {
                    log.warning(String.format("Name -> ID map does not " + "contain a value for %s, skipping.", blockName));
                    continue;
                }
                oldBlock = Block.blocksList[Block.nameToIdMap.get(blockName)];
                if (oreOverrideSetting.parameters != null) {
                    newBlock = ModBlockUtils.OverrideBlockWithParameters(oldBlock, oreOverrideSetting.clazz, oreOverrideSetting.parameters);
                } else {
                    newBlock = ModBlockUtils.OverrideBlock(oldBlock, oreOverrideSetting.clazz);
                }
                if (newBlock == null) {
                    log.severe(String.format("Failed to override block: " + "'%s'!", blockName));
                }
                modBlocksMap.put(oldBlock.blockID, newBlock);
            }
        }
    }

    private void OverrideOverworldBlocks() {
        String[] stoneTypes = {"stone", "basalt", "limestone", "granite"};
        BlockOverrideInitSettings[] oreOverrideSettings = new BlockOverrideInitSettings[]{
                new BlockOverrideInitSettings("coal", BlockOreCoalExcavatable.class),
                new BlockOverrideInitSettings("iron", BlockOreGenericExcavatable.class),
                new BlockOverrideInitSettings("gold", BlockOreGenericExcavatable.class),
                new BlockOverrideInitSettings("lapis", BlockOreLapisExcavatable.class),
                new BlockOverrideInitSettings("redstone", BlockOreRedstoneExcavatable.class, new Object[]{false}),
                new BlockOverrideInitSettings("redstone.glowing", BlockOreRedstoneExcavatable.class, new Object[]{true}),
                new BlockOverrideInitSettings("diamond", BlockOreDiamondExcavatable.class)};

        OverrideBlocks(stoneTypes, oreOverrideSettings);
    }

    private void OverrideNetherBlocks() {
        String[] stoneTypes = {"netherrack"};
        BlockOverrideInitSettings[] oreOverrideSettings = new BlockOverrideInitSettings[]{
                new BlockOverrideInitSettings("nethercoal", BlockOreNetherCoalExcavatable.class)};

        OverrideBlocks(stoneTypes, oreOverrideSettings);
    }

    @Override
    public void init(Minecraft minecraft) {
        _minecraft = minecraft;

        OverrideOverworldBlocks();
        OverrideNetherBlocks();
        log.log(Level.INFO, "Initialised.");
    }

    @Override
    public void update() {
    }

    @Override
    public void tick() {
    }
}
