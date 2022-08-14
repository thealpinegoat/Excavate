package com.thealpinegoat.excavatemod.blocks;

import com.thealpinegoat.excavatemod.IOreExcavatable;
import net.minecraft.src.BlockOreNetherCoal;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;

public class BlockOreNetherCoalExcavatable extends BlockOreNetherCoal implements IOreExcavatable {
    public BlockOreNetherCoalExcavatable(Integer i) {
        super(i);
        this.setHardness(3.0F);
    }

    private EntityPlayer _lastInteractedPlayer;

    @Override
    public int getBlockId() {
        return this.blockID;
    }

    @Override
    public void onBlockClicked(World world, int i, int j, int k, EntityPlayer entityplayer) {
        super.onBlockClicked(world, i, j, k, entityplayer);
        _lastInteractedPlayer = entityplayer;
    }

    @Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int state) {
        super.onBlockDestroyedByPlayer(world, x, y, z, state);
        TryExcavate(_lastInteractedPlayer, world, x, y, z);
    }
}
