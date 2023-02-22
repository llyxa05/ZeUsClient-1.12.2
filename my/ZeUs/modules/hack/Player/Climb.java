package my.ZeUs.modules.hack.Player;

import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.Utils.Wrapper;
import my.ZeUs.modules.Module;

public class Climb extends Module
{
    public Climb() {
    	super("Spider", Keyboard.KEY_NONE, Category.Player);
    }
    
    public void onEnable() {
    	WSoundEvents.playButtonClick();
    }
    
    public void onDisable() {
        WSoundEvents.grass();
    }
    
    @Override
    public void onTick() {
        if (Wrapper.getMinecraft().player.isCollidedHorizontally) {
            Wrapper.getMinecraft().player.motionY = 0.2;
        }
    }
}
