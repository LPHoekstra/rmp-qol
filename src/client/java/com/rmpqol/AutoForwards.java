package com.rmpqol;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.text.Text;

public class AutoForwards {
    private static boolean isSprintingDuringAutoForwards = true;
    private static boolean isAutoForwards = false;

    public static void setForward() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            // activate the auto-forwards by pressing the key
            if (ModKeyBindings.autoForwards.wasPressed() && !isAutoForwards) {
                isAutoForwards = true;
                client.options.forwardKey.setPressed(true);
                client.options.sprintKey.setPressed(isSprintingDuringAutoForwards);
            }

            // cancel the auto-forwards by pressing the backwards, or forwards, or
            // autoForwards key
            else if (client.options.backKey.wasPressed() && isAutoForwards
                    || client.options.forwardKey.wasPressed() && isAutoForwards
                    || ModKeyBindings.autoForwards.wasPressed() && isAutoForwards) {
                isAutoForwards = false;
                client.options.forwardKey.setPressed(false);
                client.options.sprintKey.setPressed(false);
            }
        });
    }

    public static void setSprintDuringAutoForward() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (ModKeyBindings.setSprintDuringAutoForwards.wasPressed()) {
                isSprintingDuringAutoForwards = !isSprintingDuringAutoForwards;
                String msgToUser = "Sprint during auto forwards: " + (isSprintingDuringAutoForwards ? "on" : "off");

                client.player.sendMessage(Text.literal(
                        msgToUser),
                        true);
            }
        });
    }

    public static void init() {
        setForward();
        setSprintDuringAutoForward();
    }
}
