package my.ZeUs.modules.hack.Misc;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.Client;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.modules.Module;
import my.ZeUs.settings.Setting;

public class ChatBypass extends Module {
	
	public ChatBypass() {
    	super("ChatBypass", Keyboard.KEY_NONE, Category.Misc);
    }
    
	@Override
	   public void setup() {
		  /*ArrayList<String> options = new ArrayList<>();
		   options.add("FontV1");
	   	   options.add("FontV2");
	   	   Client.getInstance().settingsManager.rSetting(new Setting("FontSelection", this, "FontV1", options));*/
		Client.settingsManager.rSetting(new Setting("ENG UTF Font ", this, true));
		Client.settingsManager.rSetting(new Setting("RU UTF Font ", this, true));
	  }
	
	public static boolean ivan = false;
	
    public void onEnable() {
    	this.ivan = true;
    }
    
    
    public void onDisable() {
    	this.ivan = false;
    }
}
