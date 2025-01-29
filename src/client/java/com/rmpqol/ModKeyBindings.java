package com.rmpqol;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ModKeyBindings {
    public static KeyBinding autoForwards;
    public static KeyBinding setSprintDuringAutoForwards;

    public static void register() {
        autoForwards = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.rmpqol.forwards",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_EQUAL,
				"category.rmpqol.rmpqol"
        ));

        setSprintDuringAutoForwards = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.rmpqol.setSprint",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_L,
                "category.rmpqol.rmpqol"
        ));
    }
}
