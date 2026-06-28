package com.example.colorchat;

import net.fabricmc.api.ClientModInitializer;

public class ColorChatClient implements ClientModInitializer {

    public static boolean ENABLED = true;

    @Override
    public void onInitializeClient() {
        ChatHandler.register();
        InventoryButtonOverlay.register();
    }
}