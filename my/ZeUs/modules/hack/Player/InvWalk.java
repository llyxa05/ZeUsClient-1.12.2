package my.ZeUs.modules.hack.Player;

import net.minecraft.client.settings.*;
import net.minecraft.client.gui.*;
import org.lwjgl.input.*;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.modules.Module;

public class InvWalk extends Module {
	
	public InvWalk() {
		super("InvWalk", Keyboard.KEY_NONE, Category.Player);
    }
	
	public void onEnable() {
		WSoundEvents.playButtonClick();
	}
	
	public void onDisable() {
	    WSoundEvents.grass();
	}
	@Override
    public void onTick() {
        final KeyBinding[] keys;
        final KeyBinding[] arrkeyBinding = keys = new KeyBinding[] { this.getMC().gameSettings.keyBindForward, this.getMC().gameSettings.keyBindBack, this.getMC().gameSettings.keyBindLeft, this.getMC().gameSettings.keyBindRight, this.getMC().gameSettings.keyBindJump, this.getMC().gameSettings.keyBindSprint };
        if (this.getMC().currentScreen != null && !(this.getMC().currentScreen instanceof GuiChat)) {
            final KeyBinding[] arrayOfKeyBinding = keys;
            for (int i = keys.length, b = 0; b < i; b = (byte)(b + 1)) {
                final KeyBinding bind = arrayOfKeyBinding[b];
                bind.pressed = Keyboard.isKeyDown(bind.getKeyCode());
            }
        }
    }
}
