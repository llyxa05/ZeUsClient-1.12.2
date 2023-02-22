package my.ZeUs.Utils;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import my.ZeUs.modules.Module;
import my.ZeUs.settings.Setting;
import net.minecraft.client.Minecraft;

public class ClickGui extends Module 
{
    public ClickGui() {
        super("ClickGui", Keyboard.KEY_RSHIFT, Category.Render);
    }
    
    @Override
    public void setup() {
    	ArrayList<String> options = new ArrayList<>();
    	options.add("Marisa1");
    	options.add("Marisa2");
    	options.add("Ewkaki1");
    	options.add("Ewkaki2");
    	options.add("Genric");
    	options.add("Reimu");
    	options.add("Cirno");
    	options.add("None");
    	Client.getInstance().settingsManager.rSetting(new Setting("PNG Selector", this, "None", options));
    	Client.settingsManager.rSetting(new Setting("PNG Y ", this, 117, 5, 150, false));
    	Client.settingsManager.rSetting(new Setting("PNG Z ", this, 441, 1, 500, false));
    	Client.settingsManager.rSetting(new Setting("Blur ", this, true));
    }
    
    @Override 
    public void onTick() {
        super.onTick(); 
        Minecraft.getMinecraft().displayGuiScreen(Client.clickGUI); 
        toggle();
    }
    
    public void onEnable() {
   // 	Client.getInstance().moduleManager.getModuleByName("Configs").toggle();
    }
    
   @Override
    public void onDisable() {
        super.onDisable(); 
        Minecraft.getMinecraft().displayGuiScreen(Client.clickGUI); 
    }
}
