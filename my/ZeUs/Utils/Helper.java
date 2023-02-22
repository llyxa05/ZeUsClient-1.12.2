package my.ZeUs.Utils;

import net.minecraft.network.*;
import net.minecraft.client.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.*;
import java.awt.*;
import java.net.*;
import java.io.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.client.gui.*;

public class Helper
{
    public static String currentIP;
    public static int currentPort;
    public static String savedIP;
    public static String savedPort;
    
    public static void sendPacket(final Packet packet) {
        if (!Wrapper.getMinecraft().isSingleplayer()) {
            Wrapper.getMinecraft().getConnection().sendPacket(packet);
        }
    }
    
    
    public static void addMessage(String message) {
    	ChatUtils.message(ChatUtils.white + "[" + ChatUtils.blue +  "ZeUs" + ChatUtils.white + "] " +ChatUtils.cyan + message);
    }
    
    
    public static void sendMessage(final String message) throws UnknownHostException, IOException {
        Minecraft.getMinecraft().player.sendChatMessage(message);
    }
  
    
    public static void addConsole(final String message) {
        System.out.println("[Dance]: " + message);
    }
    
    public static void openLink(final String url) {
        final Desktop desktop = Desktop.getDesktop();
        try {
            final URI uri = new URI(url);
            desktop.browse(uri);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
   
    
}
