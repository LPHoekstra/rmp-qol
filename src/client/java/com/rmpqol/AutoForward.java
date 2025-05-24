package com.rmpqol;

import net.minecraft.client.MinecraftClient;

public class AutoForward {
    private static MinecraftClient client = MinecraftClient.getInstance();
    private static AutoForward autoForwardInstance;
    private boolean isAutoForwardActive = false;

    // used inside a ClientTickEvents
    public static void handleAutoForwardTick() {
        AutoForward autoForward = getInstance();
        /**
         * when the user press the autoForward key and the forwardKey and
         * then released the forwardKey is character is stopped. To prevent this,
         * on each tick, the setForward is set.
         */
        if (autoForward.isAutoForwardActive()) {
            autoForward.setForward(true);
        }

        // set/cancel by pressing the autoForward key
        if (ModKeyBindings.autoForward.wasPressed()) {
            if (!autoForward.isAutoForwardActive()) {
                setInstance().setForward(true);
            } else if (autoForward.isAutoForwardActive()) {
                autoForward.setForward(false);
            }
        }

        // if the backKey or forwardKey is pressed during an autoForward, he's cancel.
        else if (client.options.backKey.wasPressed() && autoForward.isAutoForwardActive()
                || client.options.forwardKey.wasPressed() && autoForward.isAutoForwardActive()) {
            autoForward.setForward(false);
        }
    }

    private void setForward(boolean isForward) {
        setIsAutoForwardActive(isForward);
        client.options.sprintKey.setPressed(isForward);
        client.options.forwardKey.setPressed(isForward);
    }

    private boolean isAutoForwardActive() {
        return isAutoForwardActive;
    }

    private void setIsAutoForwardActive(boolean setAutoForwardActive) {
        isAutoForwardActive = setAutoForwardActive;
    }

    private static AutoForward getInstance() {
        if (autoForwardInstance == null) {
            setInstance();
        }

        return autoForwardInstance;
    }

    private static AutoForward setInstance() {
        return autoForwardInstance = new AutoForward();
    }
}
