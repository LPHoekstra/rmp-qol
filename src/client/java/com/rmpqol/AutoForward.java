package com.rmpqol;

import net.minecraft.client.MinecraftClient;

public class AutoForward {
    private static MinecraftClient client = MinecraftClient.getInstance();
    private static AutoForward autoForwardInstance = new AutoForward();

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
            autoForward.setForward(!autoForward.isAutoForwardActive());
        }

        // if the backKey or forwardKey is pressed during an autoForward, he's cancel.
        else if ((client.options.backKey.wasPressed() || client.options.forwardKey.wasPressed())
                && autoForward.isAutoForwardActive()) {
            autoForward.setForward(false);
        }
    }

    private void setForward(boolean isForward) {
        setAutoForwardActive(isForward);
        client.options.sprintKey.setPressed(isForward);
        client.options.forwardKey.setPressed(isForward);
    }

    private boolean isAutoForwardActive() {
        return isAutoForwardActive;
    }

    private void setAutoForwardActive(boolean setAutoForwardActive) {
        this.isAutoForwardActive = setAutoForwardActive;
    }

    private static AutoForward getInstance() {
        return autoForwardInstance;
    }
}
