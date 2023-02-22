package my.ZeUs.modules.hack.Movement;

import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.Utils.Wrapper;
import my.ZeUs.modules.Module;

public class Dolphin extends Module
{
    public Dolphin() {
    	super("Dolphin", Keyboard.KEY_NONE, Category.Movement);
    }
    
    public void onEnable() {
    	WSoundEvents.playButtonClick();
    }
    
    @Override
    public void onTick() {
        if (Wrapper.getPlayer().isInWater()) {
            Wrapper.getPlayer().setSprinting(true);
            Wrapper.getPlayer().jump();
        }
    }
    
    public void onDisable() {
        WSoundEvents.grass();
    }
}
