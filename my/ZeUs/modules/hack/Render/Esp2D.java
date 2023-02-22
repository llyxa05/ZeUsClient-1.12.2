package my.ZeUs.modules.hack.Render;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import org.lwjgl.opengl.GL11;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.ColorUtil;
import my.ZeUs.Utils.RenderUtils;
import my.ZeUs.Utils.RenderUtils;
import my.ZeUs.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import shwepss.event.Event3DRender;
import shwepss.eventapi.EventManager;
import shwepss.eventapi.EventTarget;

public class Esp2D extends Module
{
    public static double red;
    public static double green;
    public static double blue;
    public static boolean rainbow;
    public static boolean mode;
    private final List<Entity> entities;
    private int ticks;
    public int index;
    public Timer time;
    private Minecraft mc;
    
    public Esp2D() {
        super("ESP2D", 0, Category.Render);
        this.entities = new ArrayList<Entity>();
        this.ticks = 0;
        this.index = 0;
        this.time = new Timer();
        this.mc = Minecraft.getMinecraft();
    }
    
    @Override
    public void onEnable() {
        final Minecraft mc = Minecraft.getMinecraft();
        EventManager.register(this);
        if (mc.world != null) {
            this.ticks = 0;
        }
    }
    
    @Override
    public void onDisable() {
        EventManager.unregister(this);
    }
    
    @EventTarget
    public void onRender(final Event3DRender render) {
        int f\u00e4rbung = -1;
        final Minecraft mc = Minecraft.getMinecraft();
        for (final Object o : mc.world.loadedEntityList) {
            if (o instanceof EntityLivingBase) {
                final Object o2 = o;
                if (o2 == mc.player) {
                    continue;
                }
                final EntityLivingBase entity = (EntityLivingBase)o;
                if (entity == null) {
                    continue;
                }
                final Entity entity2 = entity;
                if (entity2 == mc.player) {
                    continue;
                }
                if (!(entity instanceof EntityPlayer)) {
                    continue;
                }
                if (((EntityPlayer)entity).isInvisible()) {
                    continue;
                }
                if (entity instanceof EntityLivingBase && entity.hurtTime > 0) {
                    f\u00e4rbung = ColorUtil.getRainbow();
                }
                else if (!Esp2D.rainbow) {
                    f\u00e4rbung = -1083115228;
                }
                final float posX = (float)((float)entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * this.mc.timer.field_194147_b);
                final float posY = (float)((float)entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * this.mc.timer.field_194147_b);
                final float posZ = (float)((float)entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * this.mc.timer.field_194147_b);
                this.draw2DCorner(entity, posX - RenderManager.renderPosX, posY - RenderManager.renderPosY, posZ - RenderManager.renderPosZ, f\u00e4rbung);
            }
        }
    }
    
    public static void renderTwoDimensionalESP(final Entity e, final int color, final Event3DRender render) {
        final Minecraft mc = Minecraft.getMinecraft();
        GL11.glPushMatrix();
        GL11.glTranslated(e.lastTickPosX + (e.posX - e.lastTickPosX) * render.pticks - RenderManager.renderPosX, e.lastTickPosY + (e.posY - e.lastTickPosY) * render.pticks - RenderManager.renderPosY + 1.0, e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * render.pticks - RenderManager.renderPosZ);
        GL11.glNormal3f(0.0f, 1.0f, 0.0f);
        Minecraft.getMinecraft();
        Minecraft.getMinecraft();
        GL11.glRotatef(-mc.player.rotationYaw, 0.0f, 1.0f, 0.0f);
        GL11.glDisable(2929);
        final double extraDist = 0.2;
        if (Esp2D.mode) {
            RenderUtils.drawBorderedRect2(-e.width, (float)(-e.height / 2.0f - 0.2), e.width, (float)(e.height - 0.55), 4.0f, color, 16777215);
        }
        GL11.glEnable(2929);
        GL11.glPopMatrix();
    }
    
    public void draw2DCorner(final Entity e, final double posX, final double posY, final double posZ, final int color) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(posX, posY, posZ);
        GL11.glNormal3f(0.0f, 0.0f, 0.0f);
        GlStateManager.rotate(-RenderManager.playerViewY, 0.0f, 1.0f, 0.0f);
        GlStateManager.scale(-0.1, -0.1, 0.1);
        GL11.glDisable(2896);
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GlStateManager.enableTexture2D();
        GlStateManager.depthMask(true);
        RenderUtils.drawRect(4.0f, -20.0f, 7.0f, -19.0f, color);
        RenderUtils.drawRect(-7.0f, -20.0f, -4.0f, -19.0f, color);
        RenderUtils.drawRect(6.0f, -20.0f, 7.0f, -17.5f, color);
        RenderUtils.drawRect(-7.0f, -20.0f, -6.0f, -17.5f, color);
        RenderUtils.drawRect(-7.0f, 2.0f, -4.0f, 3.0f, color);
        RenderUtils.drawRect(4.0f, 2.0f, 7.0f, 3.0f, color);
        RenderUtils.drawRect(-7.0f, 0.5f, -6.0f, 3.0f, color);
        RenderUtils.drawRect(6.0f, 0.5f, 7.0f, 3.0f, color);
        RenderUtils.drawRect(7.0f, -20.0f, 7.3f, -17.5f, -16777216);
        RenderUtils.drawRect(-7.3f, -20.0f, -7.0f, -17.5f, -16777216);
        RenderUtils.drawRect(4.0f, -20.3f, 7.3f, -20.0f, -16777216);
        RenderUtils.drawRect(-7.3f, -20.3f, -4.0f, -20.0f, -16777216);
        RenderUtils.drawRect(-7.0f, 3.0f, -4.0f, 3.3f, -16777216);
        RenderUtils.drawRect(4.0f, 3.0f, 7.0f, 3.3f, -16777216);
        RenderUtils.drawRect(-7.3f, 0.5f, -7.0f, 3.3f, -16777216);
        RenderUtils.drawRect(7.0f, 0.5f, 7.3f, 3.3f, -16777216);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glEnable(2896);
        GlStateManager.popMatrix();
    }
    
    public static void color(final int color, final float alpha) {
        final float red = (color >> 16 & 0xFF) / 255.0f;
        final float green = (color >> 8 & 0xFF) / 255.0f;
        final float blue = (color & 0xFF) / 255.0f;
        GL11.glColor4f(red, green, blue, alpha);
    }
    
    public static void drawRectum(final double g, final double h, final double i, final double j, final int col1) {
        final float f = (col1 >> 24 & 0xFF) / 255.0f;
        final float f2 = (col1 >> 16 & 0xFF) / 255.0f;
        final float f3 = (col1 >> 8 & 0xFF) / 255.0f;
        final float f4 = (col1 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glBegin(7);
        GL11.glVertex2d(i, h);
        GL11.glVertex2d(g, h);
        GL11.glVertex2d(g, j);
        GL11.glVertex2d(i, j);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }
    
    public static void drawBorderedRectum(final double x, final double y, final double x2, final double y2, final float l1, final int col1, final int col2) {
        drawRectum(x, y, x2, y2, col2);
        final float f = (col1 >> 24 & 0xFF) / 255.0f;
        final float f2 = (col1 >> 16 & 0xFF) / 255.0f;
        final float f3 = (col1 >> 8 & 0xFF) / 255.0f;
        final float f4 = (col1 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glLineWidth(l1);
        GL11.glBegin(1);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x, y2);
        GL11.glVertex2d(x2, y2);
        GL11.glVertex2d(x2, y);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x2, y);
        GL11.glVertex2d(x, y2);
        GL11.glVertex2d(x2, y2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }
}
