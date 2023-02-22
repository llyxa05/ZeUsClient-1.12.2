package my.ZeUs.modules.hack.Combat;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.Client;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.modules.Module;
import my.ZeUs.settings.Setting;
import shwepss.event.EventPostUpdate;
import shwepss.eventapi.EventManager;
import shwepss.eventapi.EventTarget;

public class Velocity extends Module
{
    public Velocity() {
    	super("Velocity", Keyboard.KEY_I, Category.Combat);
    }
    
    public static boolean lvan = false;
	
	public void onEnable() {
			this.lvan = true;
			WSoundEvents.playButtonClick();
	}
	
	
	public void onDisable() {
			this.lvan = false;
		    WSoundEvents.grass();
	}
}
