package com.thealpinegoat.excavatemod;

import bta.Mod;
import com.thealpinegoat.excavatemod.utils.ModBlockUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.src.Block;
import net.minecraft.src.Material;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ExcavateMod implements Mod {
    public static final String MOD_ID = "Excavate";
    private static final Logger log = Logger.getLogger(MOD_ID);
    public static int maxSize = 128;
    private static Minecraft _minecraft;

    private static BlockOreCoalExcavatable oreCoalStone;
    private static BlockOreCoalExcavatable oreCoalBasalt;
    private static BlockOreCoalExcavatable oreCoalLimestone;
    private static BlockOreCoalExcavatable oreCoalGranite;
    private static BlockOreGenericExcavatable oreIronStone;
    private static BlockOreGenericExcavatable oreIronBasalt;
    private static BlockOreGenericExcavatable oreIronLimestone;
    private static BlockOreGenericExcavatable oreIronGranite;
    private static BlockOreGenericExcavatable oreGoldStone;
    private static BlockOreGenericExcavatable oreGoldBasalt;
    private static BlockOreGenericExcavatable oreGoldLimestone;
    private static BlockOreGenericExcavatable oreGoldGranite;
    private static BlockOreLapisExcavatable oreLapisStone;
    private static BlockOreLapisExcavatable oreLapisBasalt;
    private static BlockOreLapisExcavatable oreLapisLimestone;
    private static BlockOreLapisExcavatable oreLapisGranite;
    private static BlockOreRedstoneExcavatable oreRedstoneStone;
    private static BlockOreRedstoneExcavatable oreRedstoneBasalt;
    private static BlockOreRedstoneExcavatable oreRedstoneLimestone;
    private static BlockOreRedstoneExcavatable oreRedstoneGranite;
    private static BlockOreRedstoneExcavatable oreRedstoneGlowingStone;
    private static BlockOreRedstoneExcavatable oreRedstoneGlowingBasalt;
    private static BlockOreRedstoneExcavatable oreRedstoneGlowingLimestone;
    private static BlockOreRedstoneExcavatable oreRedstoneGlowingGranite;
    private static BlockOreDiamondExcavatable oreDiamondStone;
    private static BlockOreDiamondExcavatable oreDiamondBasalt;
    private static BlockOreDiamondExcavatable oreDiamondLimestone;
    private static BlockOreDiamondExcavatable oreDiamondGranite;

    public static Logger getLogger() { return log; }

    public static Minecraft getMinecraft() {
        return _minecraft;
    }

    public static <T extends Block> T OverrideBlock(Block block, Class<T> clazz) {
        try {
            T newBlock = ModBlockUtils.TryCreateOverrideBlock(block, clazz);
            newBlock.setBlockName(block.getBlockName(0).substring(5));
            newBlock.atlasIndices = block.atlasIndices;
            newBlock.stepSound = block.stepSound;
            return newBlock;
        } catch (Exception e) {
            log.severe(String.format("Could not override block! Block: %s", block.getBlockName(0)));
        }
        return null;
    }

    public static <T extends Block> T OverrideBlock(Block block, Class<T> clazz, boolean bool) {
        try {
            T newBlock = ModBlockUtils.TryCreateOverrideBlock(block, clazz, bool);
            newBlock.setBlockName(block.getBlockName(0).substring(5));
            newBlock.atlasIndices = block.atlasIndices;
            newBlock.stepSound = block.stepSound;
            return newBlock;
        } catch (Exception e) {
            log.severe(String.format("Could not override block! Block: %s", block.getBlockName(0)));
        }
        return null;
    }

    public static void InitModBlocks() {
        oreCoalStone = OverrideBlock(Block.oreCoalStone, BlockOreCoalExcavatable.class);
        oreCoalBasalt = OverrideBlock(Block.oreCoalBasalt, BlockOreCoalExcavatable.class);
        oreCoalLimestone = OverrideBlock(Block.oreCoalLimestone, BlockOreCoalExcavatable.class);
        oreCoalGranite = OverrideBlock(Block.oreCoalGranite, BlockOreCoalExcavatable.class);
        oreIronStone = OverrideBlock(Block.oreIronStone, BlockOreGenericExcavatable.class);
        oreIronBasalt = OverrideBlock(Block.oreIronBasalt, BlockOreGenericExcavatable.class);
        oreIronLimestone = OverrideBlock(Block.oreIronLimestone, BlockOreGenericExcavatable.class);
        oreIronGranite = OverrideBlock(Block.oreIronGranite, BlockOreGenericExcavatable.class);
        oreGoldStone = OverrideBlock(Block.oreGoldStone, BlockOreGenericExcavatable.class);
        oreGoldBasalt = OverrideBlock(Block.oreGoldBasalt, BlockOreGenericExcavatable.class);
        oreGoldLimestone = OverrideBlock(Block.oreGoldLimestone, BlockOreGenericExcavatable.class);
        oreGoldGranite = OverrideBlock(Block.oreGoldGranite, BlockOreGenericExcavatable.class);
        oreLapisStone = OverrideBlock(Block.oreLapisStone, BlockOreLapisExcavatable.class);
        oreLapisBasalt = OverrideBlock(Block.oreLapisBasalt, BlockOreLapisExcavatable.class);
        oreLapisLimestone = OverrideBlock(Block.oreLapisLimestone, BlockOreLapisExcavatable.class);
        oreLapisGranite = OverrideBlock(Block.oreLapisGranite, BlockOreLapisExcavatable.class);
        oreRedstoneStone = OverrideBlock(Block.oreRedstoneStone, BlockOreRedstoneExcavatable.class, false);
        oreRedstoneBasalt = OverrideBlock(Block.oreRedstoneBasalt, BlockOreRedstoneExcavatable.class, false);
        oreRedstoneLimestone = OverrideBlock(Block.oreRedstoneLimestone, BlockOreRedstoneExcavatable.class, false);
        oreRedstoneGranite = OverrideBlock(Block.oreRedstoneGranite, BlockOreRedstoneExcavatable.class, false);
        oreRedstoneGlowingStone = OverrideBlock(Block.oreRedstoneGlowingStone, BlockOreRedstoneExcavatable.class, true);
        oreRedstoneGlowingBasalt = OverrideBlock(Block.oreRedstoneGlowingBasalt, BlockOreRedstoneExcavatable.class, true);
        oreRedstoneGlowingLimestone = OverrideBlock(Block.oreRedstoneGlowingLimestone, BlockOreRedstoneExcavatable.class, true);
        oreRedstoneGlowingGranite = OverrideBlock(Block.oreRedstoneGlowingGranite, BlockOreRedstoneExcavatable.class, true);
        oreDiamondStone = OverrideBlock(Block.oreDiamondStone, BlockOreDiamondExcavatable.class);
        oreDiamondBasalt = OverrideBlock(Block.oreDiamondBasalt, BlockOreDiamondExcavatable.class);
        oreDiamondLimestone = OverrideBlock(Block.oreDiamondLimestone, BlockOreDiamondExcavatable.class);
        oreDiamondGranite = OverrideBlock(Block.oreDiamondGranite, BlockOreDiamondExcavatable.class);

    }

    @Override
    public void init(Minecraft minecraft) {
        _minecraft = minecraft;

        InitModBlocks();
        log.log(Level.INFO, "Initialised.");
    }

    @Override
    public void update() {

    }

    @Override
    public void tick() {

    }
}
