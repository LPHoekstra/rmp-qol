package com.rmpqol;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ModKeyBindings {
    public static KeyBinding autoForward;
    public static KeyBinding setSprintDuringAutoForward;

    public static void register() {
        autoForward = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.rmpqol.forward",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_EQUAL,
                "category.rmpqol.rmpqol"));

        setSprintDuringAutoForward = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.rmpqol.setSprint",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_L,
                "category.rmpqol.rmpqol"));
    }
}
