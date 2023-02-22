package my.ZeUs.clickgui;

import java.awt.Color;
import java.util.ArrayList;

import net.minecraft.client.gui.Gui;
import my.ZeUs.Utils.Client;
import my.ZeUs.clickgui.elements.ModuleButton;
import my.ZeUs.clickgui.util.ColorUtil;
import my.ZeUs.clickgui.util.FontUtil;
import my.ZeUs.font.Fonts;


public class Panel {
	public String title;
	public double x;
	public double y;
	private double x2;
	private double y2;
	public double width;
	public double height;
	public boolean dragging;
	public boolean extended;
	public boolean visible;
	public ArrayList<ModuleButton> Elements = new ArrayList<>();
	public ClickGUI clickgui;


	public Panel(String ititle, double ix, double iy, double iwidth, double iheight, boolean iextended, ClickGUI parent) {
		this.title = ititle;
		this.x = ix;
		this.y = iy;
		this.width = iwidth;
		this.height = iheight;
		this.extended = iextended;
		this.dragging = false;
		this.visible = true;
		this.clickgui = parent;
		setup();
	}


	public void setup() {}


	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		if (!this.visible)
			return;

		if (this.dragging) {
			x = x2 + mouseX;
			y = y2 + mouseY;
		}
		
		Color temp = ColorUtil.getClickGUIColor().darker();
		int outlineColor = 0xff9901ff;
		
		Gui.drawRect(x, y, x + width, y + height, 0xff121212);
		
		//render poloski vozle nazvaniya vkladki
		Gui.drawRect(x - 1.5, y, x, y + height, outlineColor); //levaya polosa
		Gui.drawRect(x + 81.5, y, x + 80, y + height, outlineColor); //pravaya polosa
		//Gui.drawRect(x - 2, y, x + 80, y + height, outlineColor); //zamaz chornogo
		Gui.drawRect(x - 1.5, y + 13, x + 80, y + height, outlineColor); //nuznyaya polosa
		Gui.drawRect(x - 1.5, y - 2, x + 81.5, y, outlineColor); //verhnyaya polosa
		
		Fonts.elegant_14.drawStringWithShadow(title, x + 2, y + height / 2.5 - Fonts.elegant_14.getHeight()/2, 0xffefefef); //render texta vozle verha
		
		
		if (this.extended && !Elements.isEmpty()) {
			double startY = y + height;
			int epanelcolor = 0xff232323;
			for (ModuleButton et : Elements) {
				
				//render poloski tam gde funkcii
				Gui.drawRect(x - 1.5, startY, x + width, startY + et.height + 1, outlineColor);
				Gui.drawRect(x + 81.5, startY, x + width, startY + et.height + 1, outlineColor);
				Gui.drawRect(x - 1.5, startY, x + 81.5, startY + 14 , outlineColor); //nuznyaya polosa
				
				Gui.drawRect(x, startY, x + width, startY + et.height + 1, epanelcolor);
				et.x = x + 2;
				et.y = startY;
				et.width = width - 4;
				et.drawScreen(mouseX, mouseY, partialTicks);
				startY += et.height + 1;
			}
			Gui.drawRect(x, startY + 1, x + width, startY + 1, epanelcolor);
		
		}
	}


	public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
		if (!this.visible) {
			return false;
		}
		if (mouseButton == 0 && isHovered(mouseX, mouseY)) {
			x2 = this.x - mouseX;
			y2 = this.y - mouseY;
			dragging = true;
			return true;
		} else if (mouseButton == 1 && isHovered(mouseX, mouseY)) {
			extended = !extended;
			return true;
		} else if (extended) {
			for (ModuleButton et : Elements) {
				if (et.mouseClicked(mouseX, mouseY, mouseButton)) {
					return true;
				}
			}
		}
		return false;
	}


	public void mouseReleased(int mouseX, int mouseY, int state) {
		if (!this.visible) {
			return;
		}
		if (state == 0) {
			this.dragging = false;
		}
	}


	public boolean isHovered(int mouseX, int mouseY) {
		return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
	}
}
