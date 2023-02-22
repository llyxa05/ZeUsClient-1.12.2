package my.ZeUs.modules.hack.Render;

import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.Utils.Wrapper;
import my.ZeUs.modules.Module;

public class FullBright extends Module
{
    float prevGamma;
    
    public FullBright() {
    	super("FullBright", Keyboard.KEY_NONE, Category.Render);
    }
    
    public void onEnable() {
    	WSoundEvents.playButtonClick();
    }
    @Override
    public void onTick() {
        Wrapper.mc().gameSettings.gammaSetting = 100.0f;
    }
    
    @Override
    public void onDisable() {
        Wrapper.mc().gameSettings.gammaSetting = 0.0f;
		WSoundEvents.grass();
    }
}
