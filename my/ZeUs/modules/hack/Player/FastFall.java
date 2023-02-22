package my.ZeUs.modules.hack.Player;

import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.Utils.Wrapper;
import my.ZeUs.modules.Module;

public class FastFall extends Module
{
    public FastFall() {
    	super("FastFall", Keyboard.KEY_NONE, Category.Player);
    }
    
    public void onEnable() {
    	WSoundEvents.playButtonClick();
    }
    
    public void onDisable() {
        WSoundEvents.grass();
    }
    
    @Override
    public void onTick() {
        if (!Wrapper.getPlayer().onGround) {
            Wrapper.getPlayer().moveForward = 0.0f;
            Wrapper.getPlayer().moveStrafing = 0.0f;
            Wrapper.getPlayer().motionX = 0.0;
            Wrapper.getPlayer().motionZ = 0.0;
        }
        if (!Wrapper.getPlayer().onGround && Wrapper.getPlayer().moveForward >= 0.0f && Wrapper.getPlayer().moveStrafing >= 0.0f && !Wrapper.getPlayer().isOnLadder() && !Wrapper.getPlayer().isInWater()) {
        	  Wrapper.getPlayer().motionY = -1.0;
        }
        else {
       //    Wrapper.getPlayer().motionY = 0.0;
        }
    }
}
