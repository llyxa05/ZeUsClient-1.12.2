package my.ZeUs.Utils;

import net.minecraft.client.*;
import net.minecraft.client.entity.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.network.Packet;
import net.minecraft.client.gui.*;
import net.minecraft.util.text.*;

public class Wrapper
{
	private static HackPack hackpack;
	
    public static final Minecraft getMinecraft() {
        return Minecraft.getMinecraft();
    }
    
    public static final EntityPlayerSP getPlayer() {
        return Minecraft.getMinecraft().player;
    }
    
    public static final WorldClient getWorld() {
        return Minecraft.getMinecraft().world;
    }
    
    public static final ScaledResolution getScaledResolution() {
        return new ScaledResolution(getMinecraft());
    }
    
    public static final int getScreenWidth() {
    	
        return getScaledResolution().getScaledWidth();
    }
    
    public static final int getScreenHeight() {
        return getScaledResolution().getScaledHeight();
    }
    
    public static Minecraft mc() {
        return Minecraft.getMinecraft();
    }
    
    public static WorldClient world() {
        return mc().world;
    }
    
    
    public static EntityPlayerSP player() {
        return mc().player;
    }
    
    public static void sendPacket(final Packet p) {
        player().connection.sendPacket(p);
    }
    
    public static void sendPacketBypass(final Packet p) {
        player().connection.sendPacket(p);
    }
    
    public static Minecraft mc;
    
    public Wrapper() {
        this.mc = Minecraft.getMinecraft();
    }

    public static HackPack getHackPack() {
        return Wrapper.hackpack;
    }
    
}
