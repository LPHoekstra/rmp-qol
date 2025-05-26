package com.rmpqol;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class QuartzXray {
    private static MinecraftClient client = MinecraftClient.getInstance();
    private static boolean isInit = false;

    public static void handleInit() {
        if (ModKeyBindings.quartzXray.wasPressed() && !isInit) {
            isInit = true;
            client.player.sendMessage(Text.of("X-ray activated"), false);

            isInit = false;
        }
    }
}
