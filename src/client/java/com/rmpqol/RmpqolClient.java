package com.rmpqol;

import net.fabricmc.api.ClientModInitializer;

public class RmpqolClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as
		// rendering.
		ModKeyBindings.register();
		AutoForwards.init();
		EventListener.init();
	}
}