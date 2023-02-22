package my.ZeUs.modules.hack.Render;

import java.awt.Color;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Cylinder;
import org.lwjgl.util.glu.GLU;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.Client;
import my.ZeUs.modules.Module;
import my.ZeUs.settings.Setting;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import shwepss.event.Event3DRender;
import shwepss.eventapi.EventManager;
import shwepss.eventapi.EventTarget;

public class ChinaHat extends Module {
    public ChinaHat() {
        super("ChinaHat", Keyboard.KEY_NONE, Category.Render);
    }
    
    @Override
    public void setup() {
		ArrayList<String> hat = new ArrayList<String>();
		hat.add("StrawHexagon");
		hat.add("StrawPolygon");
		hat.add("StrawCircle");
		hat.add("Hexagon");
		hat.add("Polygon");
		hat.add("Circle");
		Client.getInstance().settingsManager.rSetting(new Setting("ChinaHat Mode", this, "StrawCircle", hat));
		Client.getInstance().settingsManager.rSetting(new Setting("UnRenderFirstPerson", this, true));
    }
    
    public void onEnable() {
    	EventManager.register(this);
    }
    
    
    @EventTarget
    public void onRender3D(Event3DRender event) {
    	String mode = Client.settingsManager.getSettingByName("ChinaHat Mode").getValString();
		final double height = getMC().player.isSneaking() ? -0.18 : getMC().player.isSneaking() ? -0.30 : -0.08;
		if ((getMC().gameSettings.thirdPersonView == 1 || getMC().gameSettings.thirdPersonView == 2) && Client.settingsManager
				.getSettingByName("UnRenderFirstPerson").getValBoolean()) {
			GL11.glPushMatrix();
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glDepthMask(false);
			Color col = Color.magenta;
            GL11.glColor4f(col.getRed() / 255f, col.getGreen() / 255f, col.getBlue() / 255f, 2f);
            if(Client.moduleManager.getModuleByName("WallHack").isEnabled()) {
            	GL11.glDisable(GL11.GL_DEPTH_TEST);
            }
			GL11.glTranslatef(0f, (float) ((float) (getMC().player.height + 0.36) + height), 0f); 
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glRotatef(90f, 1f, 0f, 0f);
			Cylinder c = new Cylinder();
			c.setDrawStyle(GLU.GLU_SMOOTH);
			c.setDrawStyle(GLU.GLU_LINE);
			
			if (mode.equalsIgnoreCase("StrawCircle")) {
				c.draw(0f, 0.55f, 0.3f, 30, 5);
				
			}

			if (mode.equalsIgnoreCase("StrawHexagon")) {
				c.draw(0f, 0.55f, 0.3f, 5, 10);
			}

			if (mode.equalsIgnoreCase("Circle")) {
				c.draw(0f, 0.55f, 0.3f, 150, 100);
			}

			if (mode.equalsIgnoreCase("Hexagon")) {
				c.draw(0f, 0.55f, 0.3f, 6, 100);
			}

			if (mode.equalsIgnoreCase("StrawPolygon")) {
				c.draw(0f, 0.55f, 0.3f, 9, 10);
			}

			if (mode.equalsIgnoreCase("Polygon")) {
				c.draw(0f, 0.55f, 0.3f, 9, 100);
			}

			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glDepthMask(true);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glPopMatrix();
		} else if(!Client.settingsManager
				.getSettingByName("UnRenderFirstPerson").getValBoolean()){
			GL11.glPushMatrix();
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glDepthMask(false);
			Color col = Color.magenta;
            GL11.glColor4f(col.getRed() / 255f, col.getGreen() / 255f, col.getBlue() / 255f, 2f);
            if(Client.moduleManager.getModuleByName("WallHack").isEnabled()) {
            	GL11.glDisable(GL11.GL_DEPTH_TEST);
            }
			GL11.glTranslatef(0f, (float) ((float) (getMC().player.height + 0.36) + height), 0f); 
			GL11.glRotatef(90f, 1f, 0f, 0f);
			Cylinder c = new Cylinder();
			c.setDrawStyle(GLU.GLU_SMOOTH);
			c.setDrawStyle(GLU.GLU_LINE);
			if (mode.equalsIgnoreCase("StrawCircle")) {
				c.draw(0f, 0.55f, 0.3f, 30, 5);
			}

			if (mode.equalsIgnoreCase("StrawHexagon")) {
				c.draw(0f, 0.55f, 0.3f, 5, 10);
			}

			if (mode.equalsIgnoreCase("Circle")) {
				c.draw(0f, 0.55f, 0.3f, 150, 100);
			}

			if (mode.equalsIgnoreCase("Hexagon")) {
				c.draw(0f, 0.55f, 0.3f, 6, 100);
			}

			if (mode.equalsIgnoreCase("StrawPolygon")) {
				c.draw(0f, 0.55f, 0.3f, 9, 10);
			}

			if (mode.equalsIgnoreCase("Polygon")) {
				c.draw(0f, 0.55f, 0.3f, 9, 100);
			}

			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glDepthMask(true);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glPopMatrix();
		}
    }
    
      
      public void onDisable() {
    	  EventManager.unregister(this);
      }
}