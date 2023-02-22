package my.ZeUs.modules.hack.Hud;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.Client;
import my.ZeUs.modules.Module;
import my.ZeUs.settings.Setting;

public class ArrayList1 extends Module
{
    public ArrayList1() {
    	super("ArrayList", 0, Category.Hud);
    }
    
    @Override
    public void setup() {
    	Client.settingsManager.rSetting(new Setting("BackgroundAlpha", this, 35, 1, 255, true));
    }
    
    public void onEnable() {
    	this.toggle();
    }
}
