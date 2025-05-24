package com.rmpqol;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class AutoForward {
    private static MinecraftClient client = MinecraftClient.getInstance();
    private static AutoForward autoForwardInstance = new AutoForward();

    private boolean isAutoForwardActive = false;
    private boolean isSprintingDuringAutoForward = true;

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

        if (ModKeyBindings.sprintAutoForward.wasPressed()) {
            autoForward.setSprintingDuringAutoForward(!autoForward.isSprintingDuringAutoForward());
            // show a message to the user about the state
            String msgToUser = "Sprint during auto forwards: "
                    + (autoForward.isSprintingDuringAutoForward() ? "on" : "off");
            client.player.sendMessage(Text.of(msgToUser), true);
        }
    }

    private void setForward(boolean isForward) {
        setAutoForwardActive(isForward);
        boolean isSprinting = isForward ? isSprintingDuringAutoForward() : false;
        client.options.sprintKey.setPressed(isSprinting);
        client.options.forwardKey.setPressed(isForward);
    }

    private boolean isAutoForwardActive() {
        return isAutoForwardActive;
    }

    private void setAutoForwardActive(boolean setAutoForwardActive) {
        this.isAutoForwardActive = setAutoForwardActive;
    }

    private boolean isSprintingDuringAutoForward() {
        return isSprintingDuringAutoForward;
    }

    private void setSprintingDuringAutoForward(boolean isSprintingDuringAutoForward) {
        this.isSprintingDuringAutoForward = isSprintingDuringAutoForward;
    }

    private static AutoForward getInstance() {
        return autoForwardInstance;
    }
}
