package my.ZeUs.modules.hack.Render;

import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class GlowESP extends Module
{
    public GlowESP() {
    	super("GlowESP", Keyboard.KEY_NONE, Category.Render);
    }
    
    public void onEnable() {
    	WSoundEvents.playButtonClick();
    }
    
    @Override
    public void onTick() {
    	Minecraft mc = Minecraft.getMinecraft();
    	for(Entity ent : mc.world.loadedEntityList) {
    		if(ent instanceof EntityPlayer) {
    		ent.setGlowing(true);
    	}}
    }
    
    @Override
    public void onDisable() {
    	Minecraft mc = Minecraft.getMinecraft();
    	for(Entity ent : mc.world.loadedEntityList) {
    		if(ent instanceof EntityPlayer) {
    		ent.setGlowing(false);
    	}}
		WSoundEvents.grass();
    }
    
}
