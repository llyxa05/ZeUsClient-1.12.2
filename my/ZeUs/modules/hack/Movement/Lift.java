package my.ZeUs.modules.hack.Movement;

import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.Utils.Wrapper;
import my.ZeUs.modules.*;

public class Lift extends Module
{
    public Lift() {
    	super("Lift", Keyboard.KEY_F, Category.Movement);
    }
    
    @Override
    public void onEnable() {
    	WSoundEvents.playButtonClick();
    }
    
    @Override
    public void onTick() {
    	Wrapper.getPlayer().jump();
    }
    
    @Override
    public void onDisable() {
        WSoundEvents.grass();
    }
}
