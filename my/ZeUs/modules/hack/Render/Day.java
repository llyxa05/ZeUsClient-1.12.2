package my.ZeUs.modules.hack.Render;

import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.Utils.Wrapper;
import my.ZeUs.modules.Module;
import net.minecraft.world.storage.WorldInfo;

public class Day extends Module
{
    public Day() {
    	super("Day", Keyboard.KEY_NONE, Category.Render);
    }
    
    @Override
    public void onEnable() {
    	WSoundEvents.playButtonClick();
    }
    
    @Override
    public void onTick() {
        Wrapper.getWorld().getWorldInfo().setWorldTime(5000L);
    }
    
    @Override
    public void onDisable() {
        Wrapper.getWorld().getWorldInfo().setWorldTime(WorldInfo.worldTime);
		WSoundEvents.grass();
    }
}
