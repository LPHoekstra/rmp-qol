package com.rmpqol.Entities;

import com.rmpqol.ModItems;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class MagicStickEntity extends ThrownItemEntity {

    public MagicStickEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.MAGIC_STICK;
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        System.out.println("Collision détectée avec : " + hitResult.getType());

        this.discard();
    }

}
