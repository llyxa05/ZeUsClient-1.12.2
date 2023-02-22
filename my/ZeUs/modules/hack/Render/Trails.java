package my.ZeUs.modules.hack.Render;

import java.awt.Color;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.Client;
import my.ZeUs.Utils.PaletteHelper;
import my.ZeUs.modules.Module;
import my.ZeUs.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.EnumParticleTypes;
import shwepss.event.Event3DRender;
import shwepss.event.EventPreUpdate;
import shwepss.event.EventSendPacket;
import shwepss.eventapi.EventManager;
import shwepss.eventapi.EventTarget;

public class Trails extends Module {
	
	public ArrayList<Point> points = new ArrayList<>();
    public float pointCount = 0;
	
    public Trails() {
        super("Trails", Keyboard.KEY_NONE, Category.Render);
    }
    
    Minecraft mc = Minecraft.getMinecraft();
    
    public void onEnable() {
    	EventManager.register(this);
    	points.clear();
        pointCount = 0;
    }
    
    public void onDisable() {
    	EventManager.unregister(this);
    	points.clear();
        pointCount = 0;
    }
    
    public static class Point {

        public double x;
        public double y;
        public double z;
        public long time;

        public Point(double x, double y, double z, long time) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.time = time;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public double getZ() {
            return z;
        }

        public long getTime() {
            return time;
        }
    }
    
    @EventTarget
    public void sendPacket(EventSendPacket event) {
    		if (event.packet instanceof CPacketPlayer) {
                CPacketPlayer packet = (CPacketPlayer) event.packet;
                points.add(new Point(packet.getX(mc.player.posX), packet.getY(mc.player.posY) < 0 ? mc.player.posY : packet.getY(mc.player.posY), packet.getZ(mc.player.posZ), System.currentTimeMillis()));
            }
    }
    
    
    @EventTarget
    public void onkek(Event3DRender ev) {
    		double x = mc.getRenderManager().renderPosX;
            double y = mc.getRenderManager().renderPosY;
            double z = mc.getRenderManager().renderPosZ;

            GL11.glPushMatrix();
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glEnable(GL11.GL_LINE_SMOOTH);
            GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_NICEST);
            GL11.glLineWidth((float)2);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

            GL11.glBegin(GL11.GL_LINE_STRIP);

            float size = 200 * 10;
            long fade = System.currentTimeMillis() - (long) size;
            for (Point point : points) {
                float alpha;
                float offset = (point.getTime() - fade) / size;
                if (offset < 0 || offset > 1) {
                    pointCount = points.indexOf(point);
                    continue;
                }
                alpha = offset;
                Color customColorValue = PaletteHelper.rainbow((int) x * 300, 1, 1);
                setColor(customColorValue, alpha);
                GL11.glVertex3d(point.getX() - x, point.getY() - y + 1, point.getZ() - z);
                GL11.glVertex3d(point.getX() - x, point.getY() - y + 0.01, point.getZ() - z);
            }
            GL11.glEnd();
            //GL11.glDisable(GL11.GL_DEPTH);
            GL11.glDepthMask(true);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_LINE_SMOOTH);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GlStateManager.resetColor();
            GL11.glPopMatrix();
    	}
    
    public static void setColor(Color color, float alpha) {
        float red = color.getRed() / 255F;
        float green = color.getGreen() / 255F;
        float blue = color.getBlue() / 255F;
        GlStateManager.color(red, green, blue, alpha);
    }
}
