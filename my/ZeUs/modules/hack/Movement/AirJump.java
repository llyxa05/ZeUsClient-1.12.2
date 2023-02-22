package my.ZeUs.modules.hack.Movement;

import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.modules.Module;
import net.minecraft.client.Minecraft;
import shwepss.event.*;
import shwepss.eventapi.EventManager;
import shwepss.eventapi.EventTarget;

public class AirJump extends Module {
	
	public AirJump() {
		super("AirJump", Keyboard.KEY_NONE, Category.Movement);
    }
	
	public void onEnable() {
		EventManager.register(this);
		WSoundEvents.playButtonClick();
	}
	
	 @EventTarget
	    public void onUpdate(EventPreUpdate event) {
	        if (getMC().gameSettings.keyBindJump.isPressed()) {
	        	getMC().player.motionX *= 1.5;
	        	getMC().player.motionY = 0.4;
	        	getMC().player.motionZ *= 1.5;
	        	getMC().player.onGround = true;
	        }

	    }
	
	 public void onDisable() {
		 EventManager.unregister(this);
		 WSoundEvents.grass();
	 }
}
