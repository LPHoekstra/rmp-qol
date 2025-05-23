package com.rmpqol;

import net.minecraft.client.MinecraftClient;

public class AutoForwards {
    private static MinecraftClient client = MinecraftClient.getInstance();
    private static AutoForwards autoForwardsInstance;
    private static boolean isAutoForwardsActive = false;

    public void setForwards() {
        setInstance(new AutoForwards());
        setIsAutoForwardsActive(true);
        client.options.sprintKey.setPressed(true);
        client.options.forwardKey.setPressed(true);
    }

    public void cancelForwards() {
        setIsAutoForwardsActive(false);
        client.options.forwardKey.setPressed(false);
        client.options.sprintKey.setPressed(false);
    }

    public static AutoForwards getInstance() {
        if (autoForwardsInstance == null) {
            setInstance(new AutoForwards());
        }

        return autoForwardsInstance;
    }

    private static void setInstance(AutoForwards newAutoForwardsInstance) {
        autoForwardsInstance = newAutoForwardsInstance;
    }

    private void setIsAutoForwardsActive(boolean setAutoForwardsActive) {
        isAutoForwardsActive = setAutoForwardsActive;
    }

    public static boolean getIsAutoForwardsActive() {
        return isAutoForwardsActive;
    }
}
