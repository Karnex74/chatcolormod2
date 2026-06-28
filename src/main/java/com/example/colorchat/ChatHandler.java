package com.example.colorchat;

import net.fabricmc.fabric.api.client.message.v1.ClientSendMessageEvents;
import net.minecraft.client.MinecraftClient;

import java.util.List;
import java.util.regex.Pattern;

public class ChatHandler {

    public static void register() {

        ClientSendMessageEvents.ALLOW_CHAT.register(message -> {
            if (!ColorChatClient.ENABLED) return true;
            if (message.startsWith("/")) return true;

            MinecraftClient client = MinecraftClient.getInstance();
            if (client == null || client.getNetworkHandler() == null) return true;

            String modified = "&f" + colorize(message, client);

            client.getNetworkHandler().sendChatMessage(modified);

            return false;
        });
    }

    private static String colorize(String message, MinecraftClient client) {

        List<String> names = client.getNetworkHandler()
                .getPlayerList()
                .stream()
                .map(e -> e.getProfile().getName())
                .toList();

        String result = message;

        for (String name : names) {
            Pattern p = Pattern.compile("\\b" + Pattern.quote(name) + "\\b");
            result = p.matcher(result).replaceAll("&c$0&f");
        }

        return result;
    }
}