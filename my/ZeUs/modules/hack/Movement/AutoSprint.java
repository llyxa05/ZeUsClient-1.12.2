package my.ZeUs.modules.hack.Movement;

import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.Utils.Wrapper;
import my.ZeUs.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.world.storage.WorldInfo;

public class AutoSprint extends Module
{
   
	public AutoSprint() {
		super("AutoSprint", Keyboard.KEY_V, Category.Movement);
    }
    
    @Override
    public void onEnable() {
    	WSoundEvents.playButtonClick();
    }
    
    
    @Override
    public void onDisable() {
        WSoundEvents.grass();
    }
    
    public void onTick() {
    	Minecraft mc = Minecraft.getMinecraft();
    	if (mc.gameSettings.keyBindForward.isKeyDown()) {
    	    mc.player.setSprinting(true);
    	  }
    	}
}
