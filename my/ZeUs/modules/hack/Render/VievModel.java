package my.ZeUs.modules.hack.Render;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.Client;
import my.ZeUs.Utils.Wrapper;
import my.ZeUs.modules.Module;
import my.ZeUs.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;


public class VievModel extends Module
{
    
    public VievModel() {
        super("VievModel", 0, Category.Render);
    }
    
    @Override
    public void setup() {
        Client.settingsManager.rSetting(new Setting("Size Y", this, 110.0F, 10.0F, 1000.0F, false));
        Client.settingsManager.rSetting(new Setting("Size Z", this, 110.0F, 10.0F, 1000.0F, false));
    }
    
}
