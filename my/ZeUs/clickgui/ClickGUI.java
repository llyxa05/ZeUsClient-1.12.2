package my.ZeUs.clickgui;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.ChatUtils;
import my.ZeUs.Utils.Client;
import my.ZeUs.clickgui.elements.Element;
import my.ZeUs.clickgui.elements.ModuleButton;
import my.ZeUs.clickgui.elements.menu.ElementSlider;
import my.ZeUs.clickgui.util.ColorUtil;
import my.ZeUs.clickgui.util.FontUtil;
import my.ZeUs.modules.Module;
import my.ZeUs.settings.Setting;
import my.ZeUs.settings.SettingsManager;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;


public class ClickGUI extends GuiScreen {
	public static ArrayList<Panel> panels;
	public static ArrayList<Panel> rpanels;
	private ModuleButton mb = null;
	public SettingsManager setmgr;
	
	public ClickGUI() {
		setmgr = Client.getInstance().settingsManager;
		FontUtil.setupFontUtils();
		panels = new ArrayList<Panel>();
		double pwidth = 80;
		double pheight = 15;
		double px = 10;
		double py = 10;
		double pyplus = pheight + 10;
		

		for (Category c : Category.values()) {
			String title = Character.toUpperCase(c.name().toLowerCase().charAt(0)) + c.name().toLowerCase().substring(1);
			ClickGUI.panels.add(new Panel(title, px, py, pwidth, pheight, false, this) {
						@Override
						public void setup() {
							for (Module m : Client.getInstance().moduleManager.getModules()) {
								if (!m.getCategory().equals(c))continue;
								this.Elements.add(new ModuleButton(m, this));
							}
						}
			});
			py += pyplus;
		}
		

		rpanels = new ArrayList<Panel>();
		for (Panel p : panels) {
			rpanels.add(p);
		}
		Collections.reverse(rpanels);

	}

	
	public void loadGui() {
		try {
			File file1 = new File((mc).mcDataDir + File.separator + "ZeUs/cfg");
			File file = new File(file1, "clickgui.cfg");
			if (!file1.exists()) {
				file1.mkdirs();
			}
			FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = br.readLine()) != null) {
				String readString = line.trim();
				String[] split = readString.split(":");
				for (Setting s : SettingsManager.getSettings()) {
					if (s.getName().equals(split[0])) {
						s.setValString(split[1]);
						s.setValBoolean(Boolean.valueOf(split[2]).booleanValue());
						s.setValDouble(Float.valueOf(split[3]));
					}
				}
			}
			br.close();
		} catch (Exception e) {

		}

	}
	
	public void saveGui() {
		try {
			File file1 = new File((mc).mcDataDir + File.separator + "ZeUs/cfg");
			File file = new File(file1, "clickgui.cfg");
			if (!file1.exists()) {
				file1.mkdirs();
			}
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
			String line;

			out.write("Config:Default");
			out.write("\r\n");
			for (Setting s : SettingsManager.getSettings()) {
				if (!s.getName().equals("Configs")) {
					out.write(s.getName() + ":" + s.getValString() + ":" + s.getValBoolean() + ":" + s.getValDouble());
					out.write("\r\n");
				}
			}

			out.close();
		} catch (Exception e) {
			ChatUtils.message("Failed to save cfg!");
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
				
		String mode = Client.settingsManager.getSettingByName("PNG Selector").getValString();
		
		if(mode.equalsIgnoreCase("Marisa1")) {
        	this.mc.getTextureManager().bindTexture(new ResourceLocation("marisa1.png"));
        	Gui.drawModalRectWithCustomSizedTexture(Client.settingsManager.getSettingByName("PNG Z ").getValDouble(), Client.settingsManager.getSettingByName("PNG Y ").getValDouble(), 0.0F, 0.0F, 240, 240, 240, 240);
        }
        if(mode.equalsIgnoreCase("Marisa2")) {
        	this.mc.getTextureManager().bindTexture(new ResourceLocation("marisa2.png"));
        	Gui.drawModalRectWithCustomSizedTexture(Client.settingsManager.getSettingByName("PNG Z ").getValDouble(), Client.settingsManager.getSettingByName("PNG Y ").getValDouble(), 0.0F, 0.0F, 240, 240, 240, 240);
        }
        if(mode.equalsIgnoreCase("Reimu")) {
        	this.mc.getTextureManager().bindTexture(new ResourceLocation("reimu.png"));
        	Gui.drawModalRectWithCustomSizedTexture(Client.settingsManager.getSettingByName("PNG Z ").getValDouble(), Client.settingsManager.getSettingByName("PNG Y ").getValDouble(), 0.0F, 0.0F, 240, 240, 240, 240);
        }
        if(mode.equalsIgnoreCase("Cirno")) {
        	this.mc.getTextureManager().bindTexture(new ResourceLocation("cirno.png"));
        	Gui.drawModalRectWithCustomSizedTexture(Client.settingsManager.getSettingByName("PNG Z ").getValDouble(), Client.settingsManager.getSettingByName("PNG Y ").getValDouble(), 0.0F, 0.0F, 240, 240, 240, 240);
        }
        if(mode.equalsIgnoreCase("Genric")) {
        	this.mc.getTextureManager().bindTexture(new ResourceLocation("generic.png"));
        	Gui.drawModalRectWithCustomSizedTexture(Client.settingsManager.getSettingByName("PNG Z ").getValDouble(), Client.settingsManager.getSettingByName("PNG Y ").getValDouble(), 0.0F, 0.0F, 240, 240, 240, 240);
        }
        if(mode.equalsIgnoreCase("Ewkaki1")) {
        	this.mc.getTextureManager().bindTexture(new ResourceLocation("ewkakizwalk1.png"));
            Gui.drawModalRectWithCustomSizedTexture(Client.settingsManager.getSettingByName("PNG Z ").getValDouble(), Client.settingsManager.getSettingByName("PNG Y ").getValDouble(), 0.0F, 0.0F, 240, 240, 240, 240);
        }
        if(mode.equalsIgnoreCase("Ewkaki2")) {
        	this.mc.getTextureManager().bindTexture(new ResourceLocation("ewkakizswim.png"));
        	Gui.drawModalRectWithCustomSizedTexture(Client.settingsManager.getSettingByName("PNG Z ").getValDouble(), Client.settingsManager.getSettingByName("PNG Y ").getValDouble(), 0.0F, 0.0F, 240, 240, 240, 240);
        }
        if(mode.equalsIgnoreCase("None")) {
        	
        }
        
        this.drawGradientRect(0, this.height / 2 + 40, this.width, this.height, 111111111, 123342543 * 1000000);
        
		for (Panel p : panels) {
			p.drawScreen(mouseX, mouseY, partialTicks);
		}
		
        ScaledResolution s = new ScaledResolution(mc);
        
        
        GL11.glPushMatrix();
        GL11.glTranslated(s.getScaledWidth(), s.getScaledHeight(), 0);GL11.glScaled(0.5, 0.5, 0.5);
        GL11.glPopMatrix();
		
    
        
		mb = null;
		
		
		
		listen:
		for (Panel p : panels) {
			if (p != null && p.visible && p.extended && p.Elements != null
					&& p.Elements.size() > 0) {
				for (ModuleButton e : p.Elements) {
					if (e.listening) {
						mb = e;
						break listen;
					}
				}
			}
		}
	

		for (Panel panel : panels) {
			if (panel.extended && panel.visible && panel.Elements != null) {
				for (ModuleButton b : panel.Elements) {
					if (b.extended && b.menuelements != null && !b.menuelements.isEmpty()) {
						double off = 0;
						Color temp = ColorUtil.getClickGUIColor().darker();
						int outlineColor = new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), 170).getRGB();
						
						for (Element e : b.menuelements) {
							e.offset = off;
							e.update();
							e.drawScreen(mouseX, mouseY, partialTicks);
							off += e.height;
						}
					}
				}
			}

		}
		
		if(mb != null){
			drawRect(0, 0, this.width, this.height, 0x88101010);
			GL11.glPushMatrix();
			GL11.glTranslatef(s.getScaledWidth() / 2, s.getScaledHeight() / 2, 0.0F);
			GL11.glScalef(4.0F, 4.0F, 0F);
			FontUtil.drawTotalCenteredStringWithShadow("Ожидаю...", 0, -10, 0xffffffff);
			GL11.glScalef(0.5F, 0.5F, 0F);
			FontUtil.drawTotalCenteredStringWithShadow("Нажмите клавишу 'ESCAPE' чтобы унбинднуть " + mb.mod.getName() + (mb.mod.getKey() > -1 ? " (" + Keyboard.getKeyName(mb.mod.getKey())+ ")" : ""), 0, 0, 0xffffffff);
			GL11.glPopMatrix();
		}
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) {

		if(mb != null)return;
		
		for (Panel panel : rpanels) {
			if (panel.extended && panel.visible && panel.Elements != null) {
				for (ModuleButton b : panel.Elements) {
					if (b.extended) {
						for (Element e : b.menuelements) {
							if (e.mouseClicked(mouseX, mouseY, mouseButton))
								return;
						}
					}
				}
			}
		}


		for (Panel p : rpanels) {
			if (p.mouseClicked(mouseX, mouseY, mouseButton))
				return;
		}
		

		try {
			super.mouseClicked(mouseX, mouseY, mouseButton);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void mouseReleased(int mouseX, int mouseY, int state) {

		if(mb != null)return;
		
		for (Panel panel : rpanels) {
			if (panel.extended && panel.visible && panel.Elements != null) {
				for (ModuleButton b : panel.Elements) {
					if (b.extended) {
						for (Element e : b.menuelements) {
							e.mouseReleased(mouseX, mouseY, state);
						}
					}
				}
			}
		}
		
		for (Panel p : rpanels) {
			p.mouseReleased(mouseX, mouseY, state);
		}
		
		super.mouseReleased(mouseX, mouseY, state);
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) {
		for (Panel p : rpanels) {
			if (p != null && p.visible && p.extended && p.Elements != null && p.Elements.size() > 0) {
				for (ModuleButton e : p.Elements) {
					try {
						if (e.keyTyped(typedChar, keyCode))return;
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}

		try {
			super.keyTyped(typedChar, keyCode);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}
	
	
	
	/*
	 * Start blur
	 */
	
	@Override
	public void initGui() {
		loadGui();
		if(Client.settingsManager.getSettingByName("Blur ").getValBoolean()){
			 if (!this.mc.gameSettings.ofFastRender && !this.mc.entityRenderer.isShaderActive()) {
					if (mc.entityRenderer.theShaderGroup != null) {
						mc.entityRenderer.theShaderGroup.deleteShaderGroup();
					}
					mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
				} 
		 }
	}

	@Override
	public void onGuiClosed() {
		/*
		 * End blur 
		 */
		if (mc.entityRenderer.theShaderGroup != null) {
			mc.entityRenderer.theShaderGroup.deleteShaderGroup();
			mc.entityRenderer.theShaderGroup = null;
		}
		for (Panel panel : ClickGUI.rpanels) {
			if (panel.extended && panel.visible && panel.Elements != null) {
				for (ModuleButton b : panel.Elements) {
					if (b.extended) {
						for (Element e : b.menuelements) {
							if(e instanceof ElementSlider){
								((ElementSlider)e).dragging = false;
							}
						}
					}
				}
			}
		}
		saveGui();
	}

	public void closeAllSettings() {
		for (Panel p : rpanels) {
			if (p != null && p.visible && p.extended && p.Elements != null
					&& p.Elements.size() > 0) {
				for (ModuleButton e : p.Elements) {
				}
			}
		}
	}
}
