package com.rmpqol;

import net.fabricmc.fabric.api.event.client.player.ClientPlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.client.MinecraftClient;

public class AutoHarvest {
    private static MinecraftClient client = MinecraftClient.getInstance();
    private static boolean eventRegistered = false;
    private static boolean isHarvesting = false;

    private static void harvest() {
        isHarvesting = true;
        client.options.attackKey.setPressed(true);

        if (!eventRegistered) {
            ClientPlayerBlockBreakEvents.AFTER.register((world, player, pos, state) -> {
                client.options.attackKey.setPressed(false);
                isHarvesting = false;
            });
            eventRegistered = true;
        }
    }

    public static void handleAutoHarvestBlockCallback(Block block, BlockState blockState) {
        if (block instanceof CropBlock cropBlock && cropBlock.getAge(blockState) == cropBlock.getMaxAge()) {
            if (!isHarvesting) {
                harvest();
            }
        }
    }
}
