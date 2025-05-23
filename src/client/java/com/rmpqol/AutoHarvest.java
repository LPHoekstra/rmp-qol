package com.rmpqol;

import net.fabricmc.fabric.api.event.client.player.ClientPlayerBlockBreakEvents;
import net.minecraft.client.MinecraftClient;

public class AutoHarvest {
    private MinecraftClient client;

    public AutoHarvest(MinecraftClient client) {
        this.client = client;
    }

    public void harvest() {
        client.options.attackKey.setPressed(true);

        ClientPlayerBlockBreakEvents.AFTER.register((world, player, pos, state) -> {
            client.options.attackKey.setPressed(false);
        });
    }
}
