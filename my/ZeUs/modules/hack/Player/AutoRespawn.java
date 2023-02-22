package my.ZeUs.modules.hack.Player;

import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGameOver;

public class AutoRespawn extends Module	{
	
	public AutoRespawn() {
		super("AutoRespawn", Keyboard.KEY_NONE, Category.Player);
    }
	
	public void onEnable() {
		WSoundEvents.playButtonClick();
	}
	
	public void onDisable() {
	    WSoundEvents.grass();
	}
	
	public void onTick() {
		if (this.getMC().currentScreen instanceof GuiGameOver) {
			this.getMC().player.respawnPlayer();
			this.getMC().displayGuiScreen(null);
			}
	}
}
