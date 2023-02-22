package my.ZeUs.modules.hack.Render;

import java.awt.Color;

import my.ZeUs.Utils.AnimationHelper;
import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.Client;
import my.ZeUs.Utils.RenderUtils;
import my.ZeUs.Utils.TimeUtil;
import my.ZeUs.modules.Module;
import my.ZeUs.modules.ModuleManager;
import my.ZeUs.modules.hack.Combat.KillAura;
import my.ZeUs.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;

public class TargetHUD extends Module {

public TargetHUD() {
	super("TargetHUD", 0, Category.Render);
}

@Override
public void setup() {
    Client.settingsManager.rSetting(new Setting("TargetHudX", this, 0, -100, 800, true));
	Client.settingsManager.rSetting(new Setting("TargetHudY", this, 0, -100, 500, true));
	Client.settingsManager.rSetting(new Setting("HeadRener ", this, false));
}

}


