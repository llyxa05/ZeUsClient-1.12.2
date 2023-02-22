package my.ZeUs.modules.hack.Movement;

import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.modules.Module;

public class LongJump extends Module {
	
	boolean shifted = false;
	
    public LongJump() {
    	super("LongJump", Keyboard.KEY_NONE, Category.Movement);
    }
    
    public void onEnable() {
    	WSoundEvents.playButtonClick();
    }
    
    public void onDisable() {
        WSoundEvents.grass();
    }
    
    @Override
	public void onTick() {
		if(shifted) {
			if(getMC().player.onGround) {
				shifted = false;
				
			}
		}
		else {
			if(getMC().gameSettings.keyBindSneak.pressed) {
				shifted = true;
			}
			else {
				if (getMC().world.getCollisionBoxes(getMC().player,
						getMC().player.boundingBox.offset(getMC().player.motionX, getMC().player.motionY, getMC().player.motionZ))
						.isEmpty()) {
					getMC().player.jump();
					shifted = true;
				}
			}
		}
		super.onTick();
	}
}