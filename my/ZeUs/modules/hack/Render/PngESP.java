package my.ZeUs.modules.hack.Render;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.Client;
import my.ZeUs.Utils.ImageESPUtil;
import my.ZeUs.modules.Module;
import my.ZeUs.settings.Setting;
import shwepss.event.Event2DRender;
import shwepss.event.Event3DRender;
import shwepss.eventapi.EventManager;
import shwepss.eventapi.EventTarget;

public class PngESP extends Module
{
	public PngESP() {
    	super("PngESP", Keyboard.KEY_NONE, Category.Render);
    }
	
	@Override
	public void setup() {
		ArrayList<String> options = new ArrayList<>();
		options.add("Ewkaki");
	   	options.add("Marisa");
	    Client.getInstance().settingsManager.rSetting(new Setting("PNGSelector", this, "Ewakaki", options));
	}
	
	public void onEnable() {
		EventManager.register(this);
	}
	
	public void onDisable() {
		EventManager.unregister(this);
	}
	
	@EventTarget
	public void onRender(final Event3DRender render) {
		String mode = Client.settingsManager.getSettingByName("PNGSelector").getValString();
		if(mode.equalsIgnoreCase("Ewkaki")) {
			ImageESPUtil.onRenderMethod("ewkakizwalk1.png");
		}
		if(mode.equalsIgnoreCase("Marisa")) {
			ImageESPUtil.onRenderMethod("marisa1.png");
		}
    }
}
