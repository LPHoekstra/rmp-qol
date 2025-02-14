package com.rmpqol;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public class AutoHarvest {

    private static void harvest() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            BlockPos blockPos = hitResult.getBlockPos();
            BlockState blockState = world.getBlockState(blockPos);
            Block block = blockState.getBlock();

            // if the block is a crop and has grown to max
            if (block instanceof CropBlock cropBlock && cropBlock.getAge(blockState) == cropBlock.getMaxAge()) {
                world.breakBlock(blockPos, true);
            }

            return ActionResult.PASS;
        });
    }

    public static void init() {
        harvest();
    }
}
