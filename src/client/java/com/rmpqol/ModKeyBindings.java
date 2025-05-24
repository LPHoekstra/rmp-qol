package com.rmpqol;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ModKeyBindings {
    public static KeyBinding autoForward;
    public static KeyBinding sprintAutoForward;

    public static void register() {
        autoForward = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.rmpqol.autoForward",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_EQUAL,
                "category.rmpqol.rmpqol"));

        sprintAutoForward = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.rmpqol.sprintAutoForward",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_L,
                "category.rmpqol.rmpqol"));
    }
}
