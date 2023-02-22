package my.ZeUs.modules.hack.Hud;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.Client;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.modules.Module;
import my.ZeUs.settings.Setting;
import net.minecraft.network.play.server.SPacketTimeUpdate;
import shwepss.event.EventPacketRecieve;
import shwepss.eventapi.EventManager;
import shwepss.eventapi.EventTarget;

public class WaterMark extends Module
{
    public WaterMark() {
    	super("WaterMark", 0, Category.Hud);
    }
    
    @Override
    public void setup() {
    	ArrayList<String> options = new ArrayList<>();
    	options.add("Animate");
    	options.add("OneTap");
    	options.add("Static");
    	Client.getInstance().settingsManager.rSetting(new Setting("Style Selection", this, "OneTap", options));
    }
    
    public static boolean whater = false;
    
	public void onEnable() {
			this.whater = true;
			WSoundEvents.playButtonClick();
	}
	
	
	public void onDisable() {
			this.whater = false;		
			WSoundEvents.grass();
	}
}
