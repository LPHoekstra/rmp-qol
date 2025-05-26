package com.rmpqol;

import com.rmpqol.Entities.MagicStickEntity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static final EntityType<MagicStickEntity> MAGIC_STICK = register(
            "magic_stick",
            EntityType.Builder.<MagicStickEntity>create(
                    MagicStickEntity::new,
                    SpawnGroup.MISC)
                    .dropsNothing()
                    .dimensions(0.2F, 0.2F)
                    .maxTrackingRange(6)
                    .trackingTickInterval(10));

    private static RegistryKey<EntityType<?>> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.ofVanilla(id));
    }

    private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> type) {
        return register(keyOf(id), type);
    }

    private static <T extends Entity> EntityType<T> register(RegistryKey<EntityType<?>> key,
            EntityType.Builder<T> type) {
        return Registry.register(Registries.ENTITY_TYPE, key, type.build(key));
    }

    public static void init() {
    }
}
