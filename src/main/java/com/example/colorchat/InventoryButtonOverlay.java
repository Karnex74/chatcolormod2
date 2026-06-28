package com.example.colorchat;

import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.text.Text;

public class InventoryButtonOverlay {

    public static void register() {

        ScreenEvents.AFTER_INIT.register((client, screen, w, h) -> {

            if (!(screen instanceof HandledScreen<?>)) return;

            ScreenEvents.afterRender(screen).register((s, ctx, mx, my, delta) -> {
                int x = w - 70;
                int y = 6;

                String text = ColorChatClient.ENABLED ? "Chat ON" : "Chat OFF";

                ctx.fill(x, y, x + 60, y + 20, 0xAA000000);
                ctx.drawText(client.textRenderer, Text.literal(text), x + 8, y + 6, 0xFFFFFF, true);
            });

            ScreenEvents.afterMouseClick(screen).register((s, mx, my, button) -> {
                int x = w - 70;
                int y = 6;

                if (mx >= x && mx <= x + 60 && my >= y && my <= y + 20) {
                    ColorChatClient.ENABLED = !ColorChatClient.ENABLED;

                    MinecraftClient mc = MinecraftClient.getInstance();
                    if (mc.player != null) {
                        mc.player.sendMessage(
                                Text.literal("Color Chat: " + (ColorChatClient.ENABLED ? "ON" : "OFF")),
                                true
                        );
                    }
                }
            });
        });
    }
}