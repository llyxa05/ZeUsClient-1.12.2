package my.ZeUs.clickgui.elements;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.Client;
import my.ZeUs.modules.Module;
import my.ZeUs.settings.Setting;
import my.ZeUs.Utils.Wrapper;
import my.ZeUs.clickgui.Panel;
import my.ZeUs.clickgui.elements.menu.ElementCheckBox;
import my.ZeUs.clickgui.elements.menu.ElementComboBox;
import my.ZeUs.clickgui.elements.menu.ElementSlider;
import my.ZeUs.clickgui.util.ColorUtil;
import my.ZeUs.clickgui.util.FontUtil;
import my.ZeUs.font.Fonts;



public class ModuleButton {
	protected final List<Element> elements = new ArrayList<>();
	public Module mod;
	public ArrayList<Element> menuelements;
	public Panel parent;
	public double x;
	public double y;
	public double width;
	public double height;
	public boolean extended = false;
	public boolean listening = false;


	public ModuleButton(Module imod, Panel pl) {
		mod = imod;
		height = Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 2;
		parent = pl;
		menuelements = new ArrayList<Element>();

		if (Client.getInstance().settingsManager.getSettingsByMod(imod) != null)
			for (Setting s : Client.getInstance().settingsManager.getSettingsByMod(imod)) {
				if (s.isCheck()) {
					menuelements.add(new ElementCheckBox(this, s));
				} else if (s.isSlider()) {
					menuelements.add(new ElementSlider(this, s));
				} else if (s.isCombo()) {
					menuelements.add(new ElementComboBox(this, s));
				}
			}

	}


	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		Color temp = ColorUtil.getClickGUIColor();
		int color = new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), 150).getRGB();
		

		int textcolor = 0xffafafaf;
		if (mod.isEnabled()) {
			Gui.drawRect(x - 2, y, x + width + 2, y + height + 1, color);
			textcolor = 0xffefefef;
		}
		

		if (isHovered(mouseX, mouseY)) {
			Gui.drawRect(x - 2, y, x + width + 2, y + height + 1, 0x55111111);
		}
		
		if (Client.settingsManager.getSettingsByMod(mod) != null)
	        for (Setting s : Client.settingsManager.getSettingsByMod(mod)) {

	           if (!s.isCheck()) {
	        	   FontUtil.drawString("-", x + width - 10, y + height / 2F - 4, -1);
	           }
	           if (!s.isSlider()) {
	        	   FontUtil.drawString("-", x + width - 10, y + height / 2F - 4, -1);
	           }
	           if (!s.isCombo()) {
	        	   FontUtil.drawString("-", x + width - 10, y + height / 2F - 4, -1);
	           }
	        }
		
		if (elements.size() > 1) {
           FontUtil.drawString("-", x + width - 10, y + height / 2F - 5, -1);
        }

		Fonts.roboto_14.drawTotalCenteredStringWithShadow(mod.getName(), x + width / 2, y + 1 + height / 2, textcolor);
	}


	public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
		if (!isHovered(mouseX, mouseY))
			return false;
		


		if (mouseButton == 0) {
			mod.toggle();
			
		} else if (mouseButton == 1) {

			if (menuelements != null && menuelements.size() > 0) {
				boolean b = !this.extended;
				Client.getInstance().clickGUI.closeAllSettings();
				this.extended = b;
				
			}
		} else if (mouseButton == 2) {

			listening = true;
		}
		return true;
	}

	public boolean keyTyped(char typedChar, int keyCode) throws IOException {

		if (listening) {
			if (keyCode != Keyboard.KEY_ESCAPE) {
				//Client.sendChatMessage("Bound '" + mod.getName() + "'" + " to '" + Keyboard.getKeyName(keyCode) + "'");
				mod.setKey(keyCode);
			} else {
				//Client.sendChatMessage("Unbound '" + mod.getName() + "'");
				mod.setKey(Keyboard.KEY_NONE);
			}
			listening = false;
			return true;
		}
		return false;
	}

	public boolean isHovered(int mouseX, int mouseY) {
		return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
	}

}
