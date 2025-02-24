package com.rmpqol;

import net.fabricmc.fabric.api.event.client.player.ClientPlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public class AutoHarvest {
    private static boolean destroyCropBlock = false;
    private static MinecraftClient client = MinecraftClient.getInstance();

    private static void harvest() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            BlockPos blockPos = hitResult.getBlockPos();
            BlockState blockState = world.getBlockState(blockPos);
            Block block = blockState.getBlock();

            // if the block is a crop and has grown to max
            if (block instanceof CropBlock cropBlock && cropBlock.getAge(blockState) == cropBlock.getMaxAge()) {
                destroyCropBlock = true;
                client.options.attackKey.setPressed(true);
            }

            return ActionResult.PASS;
        });
    }

    private static void cancelHarvest() {
        ClientPlayerBlockBreakEvents.AFTER.register((world, player, pos, state) -> {
            if (destroyCropBlock) {
                destroyCropBlock = false;
                client.options.attackKey.setPressed(false);
            }
        });
    }

    public static void init() {
        harvest();
        cancelHarvest();
    }
}
