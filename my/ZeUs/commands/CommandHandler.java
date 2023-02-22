package my.ZeUs.commands;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.viaversion.viaversion.libs.kyori.adventure.bossbar.BossBar.Color;

import java.io.*;

import my.ZeUs.Utils.ChatUtils;
import my.ZeUs.Utils.Helper;
import my.ZeUs.commands.*;
import my.ZeUs.modules.Module;
import my.ZeUs.modules.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.world.GameType;
import my.ZeUs.Utils.Wrapper;

public class CommandHandler
{
    
    public void onCommand(final String par1Str) throws UnknownHostException, IOException {
        
    	Minecraft mc = Minecraft.getMinecraft();
    	
        if (par1Str.equalsIgnoreCase(".gmc")) {
            mc.playerController.setGameType(GameType.CREATIVE);
            ChatUtils.message("Your game mode has been updated to" + ChatUtils.gray + ChatUtils.o + " " + "Creative Mode");
        }
        
        if (par1Str.equalsIgnoreCase(".gms")) {
            mc.playerController.setGameType(GameType.SURVIVAL);
            ChatUtils.message("Your game mode has been updated to" + ChatUtils.gray + ChatUtils.o + " " + "Survival Mode");
        }
        
        if (par1Str.equalsIgnoreCase(".gmsp")) {
            mc.playerController.setGameType(GameType.SPECTATOR);
            ChatUtils.message("Your game mode has been updated to" + ChatUtils.gray + ChatUtils.o + " " + "Specrator Mode");
        }
        
        String zeus = ChatUtils.cyan + "[" + ChatUtils.purple + "ZeUs" + ChatUtils.cyan + "] ";
        
        if (par1Str.equalsIgnoreCase(".help")) {
            ChatUtils.message(zeus + ".gms -> set to fake gamemode 0");
            ChatUtils.message(zeus + ".gmc -> set to fake gamemode 1");
            ChatUtils.message(zeus + ".gmsp -> set to fake gamemode 3");
            ChatUtils.message(zeus + ".getip -> copy the IP address");
            ChatUtils.message(zeus + ".vclip <arg> -> vertical clip");
        }
        
        if (par1Str.equalsIgnoreCase(".getip")) {
            final String getip1 = mc.getCurrentServerData().serverIP;
            final String getip2 = "ip = " + getip1;
            GuiScreen.setClipboardString(getip1);
            ChatUtils.message(String.valueOf(zeus) + getip2);
            ChatUtils.message(zeus + "Copied to clipboard");
        }
        
        if (par1Str.equalsIgnoreCase(".ttconnect")) {
            Wrapper.sendPacket(new CPacketChatMessage(par1Str.split(".ttconnect ")[1]));
            return;
                
        }
        if(par1Str.contains(".vclip ")) {
        	double height = Double.parseDouble(par1Str.split("vclip ")[1]);
        	mc.player.setPosition(mc.player.posX, mc.player.posY + height, mc.player.posZ);
        	ChatUtils.message("Я вклипнулся на " + height + " блоков!");
        }
    }
}
