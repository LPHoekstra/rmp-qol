package com.rmpqol.Entities;

import com.rmpqol.ModItems;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.minecraft.world.World.ExplosionSourceType;

public class MagicStickEntity extends ThrownItemEntity {
    // to register the entity
    public MagicStickEntity(EntityType<? extends MagicStickEntity> entityType, World world) {
        super(entityType, world);
    }

    // spawn with velocity
    public MagicStickEntity(World world, LivingEntity owner, ItemStack stack) {
        // don't use MagicStick ?
        super(EntityType.SNOWBALL, owner, world, stack);
    }

    // create the entity at the location
    public MagicStickEntity(World world, double x, double y, double z, ItemStack stack) {
        // don't use MagicStick ?
        super(EntityType.SNOWBALL, x, y, z, world, stack);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.MAGIC_STICK;
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        System.out.println("Collision détectée avec : " + hitResult.getType());
        if (!this.getWorld().isClient) {
            this.getWorld().createExplosion(null,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    5f,
                    ExplosionSourceType.TNT);

            this.discard();
        }
    }
}
