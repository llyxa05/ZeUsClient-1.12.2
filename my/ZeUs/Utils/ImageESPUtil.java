package my.ZeUs.Utils;

import net.minecraft.client.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.entity.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import net.minecraft.util.*;
import java.util.*;

public class ImageESPUtil
{
    private static float scaleF;
    
    static {
        ImageESPUtil.scaleF = 0.5f;
    }
    
    public static void draw(final float partialTicks, final String location) {
        for (final Object o : Minecraft.getMinecraft().world.playerEntities) {
            final EntityPlayer e = (EntityPlayer)o;
            if (e.equals(Minecraft.getMinecraft().player)) {
                continue;
            }
            final double n = e.lastTickPosX + (e.posX - e.lastTickPosX) * partialTicks;
            Minecraft.getMinecraft().getRenderManager();
            final double x = n - RenderManager.renderPosX;
            final double n2 = e.lastTickPosY + (e.posY - e.lastTickPosY) * partialTicks;
            Minecraft.getMinecraft().getRenderManager();
            final double y = n2 - RenderManager.renderPosY + e.height;
            final double n3 = e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * partialTicks;
            Minecraft.getMinecraft().getRenderManager();
            final double z = n3 - RenderManager.renderPosZ;
            final float f = Minecraft.getMinecraft().player.getDistanceToEntity(e) * ImageESPUtil.scaleF * 0.007f;
            GlStateManager.pushMatrix();
            GlStateManager.disableDepth();
            GlStateManager.translate(x, y, z);
            GL11.glNormal3f(0.0f, 1.0f, 0.0f);
            Minecraft.getMinecraft().getRenderManager();
            GlStateManager.rotate(-RenderManager.playerViewY, 0.0f, 1.0f, 0.0f);
            Minecraft.getMinecraft().getRenderManager();
            GL11.glRotatef(RenderManager.playerViewX, 1.0f, 0.0f, 0.0f);
            GlStateManager.scale(-f, -f, f);
            GlStateManager.disableLighting();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.enableTexture2D();
            renderImage(new ResourceLocation(location), -24.0f, 0.0f, 48.0f, 60.0f);
            GlStateManager.enableLighting();
            GlStateManager.enableDepth();
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GlStateManager.popMatrix();
        }
    }
    
    public static void onRenderMethod(final String location) {
        if (Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().world == null) {
            return;
        }
        for (final Object o : Minecraft.getMinecraft().world.playerEntities) {
            final EntityPlayer p = (EntityPlayer)o;
            if (p != Minecraft.getMinecraft().getRenderViewEntity() && p.isEntityAlive() && p.getName() != "\u00c2§3Dealer") {
                Minecraft.getMinecraft().getRenderManager();
                final double pX = p.lastTickPosX + (p.posX - p.lastTickPosX) * Minecraft.getMinecraft().timer.field_194147_b - RenderManager.renderPosX;
                Minecraft.getMinecraft().getRenderManager();
                final double pY = p.lastTickPosY + (p.posY - p.lastTickPosY) * Minecraft.getMinecraft().timer.field_194147_b - RenderManager.renderPosY;
                Minecraft.getMinecraft().getRenderManager();
                final double pZ = p.lastTickPosZ + (p.posZ - p.lastTickPosZ) * Minecraft.getMinecraft().timer.field_194147_b - RenderManager.renderPosZ;
                draw(1.0f, location);
            }
        }
    }
    
    public static void renderImage(final ResourceLocation resourceLocation, final float posX, final float posY, final float width, final float height) {
        final float f = (width + height) / 2.0f;
        final int i = Math.round(f);
        GL11.glEnable(3042);
        Minecraft.getMinecraft().getTextureManager().bindTexture(resourceLocation);
        GL11.glBegin(7);
        GL11.glTexCoord2d(0.0, 0.0);
        GL11.glVertex2d(posX, posY);
        GL11.glTexCoord2d(0.0, f / i);
        GL11.glVertex2d(posX, posY + height);
        GL11.glTexCoord2d(1.0, f / i);
        GL11.glVertex2d(posX + width, posY + height);
        GL11.glTexCoord2d(1.0, 0.0);
        GL11.glVertex2d(posX + width, posY);
        GL11.glEnd();
        GL11.glDisable(3042);
    }
}
