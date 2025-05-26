package com.rmpqol.Items;

import com.rmpqol.Entities.MagicStickEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ProjectileItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import net.minecraft.world.World.ExplosionSourceType;

public class MagicStickItem extends Item implements ProjectileItem {
    public MagicStickItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        world.createExplosion(null, player.getX(),
                player.getY(),
                player.getZ(),
                5f,
                ExplosionSourceType.TNT);

        return ActionResult.SUCCESS;
    }

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        return new MagicStickEntity(null, world);
    }

}
