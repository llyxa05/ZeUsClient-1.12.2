package my.ZeUs.modules.hack.Player;

import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.modules.Module;


public class Parkour extends Module {

	boolean jumped = false;
	
	public Parkour() {
		super("Parkour", Keyboard.KEY_NONE, Category.Player);
	}
	
	public void onEnable() {
		WSoundEvents.playButtonClick();
	}
	
	public void onDisable() {
	    WSoundEvents.grass();
	}
	
	@Override
	public void onTick() {
		if(jumped) {
			if(getMC().player.onGround) {
				jumped = false;
				
			}
		}
		else {
			if(getMC().gameSettings.keyBindJump.pressed) {
				jumped = true;
			}
			else {
				if (getMC().world.getCollisionBoxes(getMC().player,
						getMC().player.boundingBox.offset(getMC().player.motionX, getMC().player.motionY, getMC().player.motionZ))
						.isEmpty()) {
					getMC().player.jump();
					jumped = true;
				}
			}
		}
		super.onTick();
	}
}