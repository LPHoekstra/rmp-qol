package com.rmpqol;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public class EventListener {
    private static MinecraftClient client = MinecraftClient.getInstance();

    private static void blockCallback() {
        UseBlockCallback.EVENT.register((playerEntity, world, hand, blockHitResult) -> {
            BlockPos blockPos = blockHitResult.getBlockPos();
            BlockState blockState = world.getBlockState(blockPos);
            Block block = blockState.getBlock();

            // Autoharvest
            if (block instanceof CropBlock cropBlock && cropBlock.getAge(blockState) == cropBlock.getMaxAge()) {
                AutoHarvest autoHarvest = new AutoHarvest(client);
                autoHarvest.harvest();
            }

            return ActionResult.PASS;
        });
    }

    private static void clientTickEvents() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            // AutoForwards
            if (ModKeyBindings.autoForwards.wasPressed() && !AutoForwards.getIsAutoForwardsActive()) {
                AutoForwards autoForwards = AutoForwards.getInstance();
                autoForwards.setForwards();
                // cancel the autoforwards by pressing the backwards, or forwards, or
                // autoForwards key
            } else if (client.options.backKey.wasPressed() && AutoForwards.getIsAutoForwardsActive()
                    || client.options.forwardKey.wasPressed() && AutoForwards.getIsAutoForwardsActive()
                    || ModKeyBindings.autoForwards.wasPressed()) {
                AutoForwards autoForwards = AutoForwards.getInstance();
                autoForwards.cancelForwards();
            }
        });
    }

    public static void init() {
        blockCallback();
        clientTickEvents();
    }
}
