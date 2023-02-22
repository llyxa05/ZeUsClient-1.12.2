package my.ZeUs.modules.hack.Misc;

import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.Client;
import my.ZeUs.Utils.DiscordRP;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.modules.Module;
import my.ZeUs.settings.Setting;
import net.arikia.dev.drpc.DiscordRichPresence;

public class DiscorDRPC extends Module {
	
	public DiscorDRPC() {
    	super("Discord RPC", Keyboard.KEY_NONE, Category.Misc);
    }
	
	@Override
	public void setup() {
	    Client.settingsManager.rSetting(new Setting("Protect IP ", this, false));
	}
	
	public static boolean DRPC = false;
	
	@Override
	public void onEnable(){
		DiscordRP.start();
		WSoundEvents.playButtonClick();
	}
	
	@Override
	public void onDisable() {
		DiscordRP.shutdown();
	    WSoundEvents.grass();
	}
}
