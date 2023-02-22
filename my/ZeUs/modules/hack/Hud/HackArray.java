package my.ZeUs.modules.hack.Hud;

import java.awt.Color;
import java.util.Collections;
import java.util.Comparator;

import org.lwjgl.opengl.GL11;

import my.ZeUs.Utils.Client;
import my.ZeUs.Utils.ColorUtil;
import my.ZeUs.Utils.RectHelper;
import my.ZeUs.Utils.Wrapper;
import my.ZeUs.font.CFontRenderer;
import my.ZeUs.font.Fonts;
import my.ZeUs.modules.Module;
import my.ZeUs.modules.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;


public class HackArray {
	
	public static class ModuleComparator implements Comparator<Module>{

		@Override
		public int compare(Module arg0, Module arg1) {
			if(Minecraft.getMinecraft().fontRendererObj.getStringWidth(arg0.name) > Minecraft.getMinecraft().fontRendererObj.getStringWidth(arg1.name)) {
				return -1;
			}
			if(Minecraft.getMinecraft().fontRendererObj.getStringWidth(arg0.name) < Minecraft.getMinecraft().fontRendererObj.getStringWidth(arg1.name)) {
				return 1;
			}
			return 0;
		}	
    }	
	
	public static void renderArrayListGui() {
	        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
	        CFontRenderer fr = Fonts.arial;


	        ModuleManager.getModules().sort(Comparator.comparingInt(m -> fr.getStringWidth(((Module)m).getName())).reversed());
	        int yPosition = 0;
	        int count = 0;
	        int color = -1;
	        for (final Module m : ModuleManager.getModules()) {
	            if (m.isEnabled()) {
	                final String name = m.getName();
	                final int scaledWidth = Wrapper.getScaledResolution().getScaledWidth();
	                final CFontRenderer fontRenderer = fr;
	                final int xPosition = scaledWidth - fontRenderer.getStringWidth(name) - 2;

	                double offset = count*(fr.getHeight() + 6);
	                int back = (int) Client.settingsManager.getSettingByName("BackgroundAlpha").getValDouble();
	                
	                GL11.glEnable(GL11.GL_BLEND);
	                //Gui.drawRect(sr.getScaledWidth() - fr.getStringWidth(name) -7, offset, sr.getScaledWidth() - fr.getStringWidth(name) + 60, 7 + fr.getHeight() + offset, ColorUtil.getRainbow1(4, 0.8f, 1, count * 150));
	                RectHelper.drawGlow(sr.getScaledWidth() - fr.getStringWidth(name) , offset, sr.getScaledWidth() - fr.getStringWidth(name) + 60, 7 + fr.getHeight() + offset, ColorUtil.getRainbow1(4, 0.8f, 1, count * 150));
	                //Gui.drawRect(sr.getScaledWidth() - fr.getStringWidth(name), offset, sr.getScaledWidth() - fr.getStringWidth(name), 20 + fr.getHeight() + offset, ColorUtil.getRainbow1(4, 0.8f, 1, count * 150));
	                //1RectHelper.drawGlow(sr.getScaledWidth() - fr.getStringWidth(name) , offset, sr.getScaledWidth() + fr.getStringWidth(name) - 20, 20 - fr.getHeight() + offset, ColorUtil.getRainbow1(4, 0.8f, 1, count * 150));
	                //RectHelper.drawGlow(sr.getScaledWidth() -2, offset, sr.getScaledWidth(), 12 + fr.getHeight() + offset - 6, ColorUtil.getRainbow1(4, 0.8f, 1, count * 150));
	                Gui.drawRect(sr.getScaledWidth() - fr.getStringWidth(name) -5, offset, sr.getScaledWidth(), 6 + fr.getHeight() + offset, ColorUtil.getColor(1, back));
	                //RectHelper.drawGlow(sr.getScaledWidth() - fr.getStringWidth(name) -2, offset, sr.getScaledWidth(), 6 + fr.getHeight() + offset, ColorUtil.getColor(1, back));
	                Gui.drawRect(sr.getScaledWidth() -2, offset, sr.getScaledWidth(), 12 + fr.getHeight() + offset - 6, ColorUtil.getRainbow1(4, 0.8f, 1, count * 150));
	                fr.drawStringWithShadow(name, xPosition - 1, 4 + offset, ColorUtil.getRainbow1(4, 0.8f, 1, count * 150));
	                count++;
	            }
	        }
	    }
}