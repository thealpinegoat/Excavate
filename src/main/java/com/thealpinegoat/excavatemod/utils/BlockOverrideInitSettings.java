package com.thealpinegoat.excavatemod.utils;

import net.minecraft.src.Block;

public class BlockOverrideInitSettings {
    public String name;
    public Class<? extends Block> clazz;
    public Object[] parameters;

    public <T extends Block> BlockOverrideInitSettings(String name, Class<T> clazz) {
        this.name = name;
        this.clazz = clazz;
        this.parameters = null;
    }

    public <T extends Block> BlockOverrideInitSettings(String name, Class<T> clazz, Object[] parameters) {
        this.name = name;
        this.clazz = clazz;
        this.parameters = parameters;
    }
}
