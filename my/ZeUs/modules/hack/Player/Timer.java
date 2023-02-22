package my.ZeUs.modules.hack.Player;

import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.Client;
import my.ZeUs.modules.Module;
import my.ZeUs.settings.Setting;

public class Timer extends Module
{
    private float startBright;
    private float fullBright;
    
    public Timer() {
        super("Timer", 0, Category.Player);
        this.fullBright = 1.0E9f;
    }
    
    @Override
    public void setup() {
    	Client.settingsManager.rSetting(new Setting("Speed", this, 0, 0.0, 3.0, false));
    }
    
    @Override
    public void onEnable() {
        this.getMC().timer.field_194147_b = (float) Client.settingsManager.getSettingByName("Speed").getValDouble();
    }
    
    @Override
    public void onTick() {
        if (this.getMC().timer.field_194147_b != 0.5) {
            this.getMC().timer.field_194147_b = (float) Client.settingsManager.getSettingByName("Speed").getValDouble();
        }
    }
    
    @Override
    public void onDisable() {
        this.getMC().timer.field_194147_b = 0.0f;
    }
}