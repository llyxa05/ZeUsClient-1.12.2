package my.ZeUs.modules.hack.Render;

import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.Utils.Wrapper;
import my.ZeUs.modules.Module;

public class NoWeather extends Module
{
    public NoWeather() {
    	super("NoWeather", Keyboard.KEY_NONE, Category.Render);
    }
    
    public void onEnable() {
    	WSoundEvents.playButtonClick();
    }
    
    public void onDisable() {
		WSoundEvents.grass();
    }
    @Override
    public void onTick() {
        Wrapper.getWorld().setRainStrength(0.0f);
    }
}
