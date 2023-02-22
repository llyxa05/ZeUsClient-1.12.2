package my.ZeUs.modules.hack.Misc;

import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.modules.Module;

public class AutoPKM extends Module{
	
    public AutoPKM() {
    	super("AutoPKM", Keyboard.KEY_NONE, Category.Misc);
    }
    
    public void onEnable() {
    	WSoundEvents.playButtonClick();
    }
    
    @Override
    public void onTick() {
        this.getMC().rightClickMouse();
    }
    
    public void onDisable() {
        WSoundEvents.grass();
    }
    
}
