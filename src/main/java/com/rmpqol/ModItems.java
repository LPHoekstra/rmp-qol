package com.rmpqol;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {
    public static Item SUSPICIOUS_SUBSTANCE;

    public static Item register(String path, Function<Item.Settings, Item> factory, Item.Settings settings) {
        final RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Rmpqol.MOD_ID, path));
        return Items.register(registryKey, factory, settings);
    }

    public static void init() {
        SUSPICIOUS_SUBSTANCE = register("suspicious", Item::new, new Item.Settings());
    }
}
