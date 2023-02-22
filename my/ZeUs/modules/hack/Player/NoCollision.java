package my.ZeUs.modules.hack.Player;

import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.modules.Module;

public class NoCollision extends Module {
	
	public static boolean cola = false;
	
    public NoCollision() {
        super("NoCollision", Keyboard.KEY_NONE, Category.Player);
    }
    
    public void onEnable() {
		this.cola = true;
		WSoundEvents.playButtonClick();
    }


    public void onDisable() {
		this.cola = false;
	    WSoundEvents.grass();
    }
}
