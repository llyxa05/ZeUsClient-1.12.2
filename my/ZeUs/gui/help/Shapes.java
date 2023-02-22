/*
 * Decompiled with CFR 0_128.
 */
package my.ZeUs.gui.help;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;


import org.lwjgl.opengl.GL11;

public class Shapes {
    private static Minecraft mc = Minecraft.getMinecraft();
   // private static RenderItem itemRenderer = new RenderItem();

    public static void drawRoundedRect(float x2, float y2, float x1, float y1, int borderC, int insideC) {
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        Shapes.drawVLine(x2 *= 2.0f, (y2 *= 2.0f) + 1.0f, (y1 *= 2.0f) - 2.0f, borderC);
        Shapes.drawVLine((x1 *= 2.0f) - 1.0f, y2 + 1.0f, y1 - 2.0f, borderC);
        Shapes.drawHLine(x2 + 2.0f, x1 - 3.0f, y2, borderC);
        Shapes.drawHLine(x2 + 2.0f, x1 - 3.0f, y1 - 1.0f, borderC);
        Shapes.drawHLine(x2 + 1.0f, x2 + 1.0f, y2 + 1.0f, borderC);
        Shapes.drawHLine(x1 - 2.0f, x1 - 2.0f, y2 + 1.0f, borderC);
        Shapes.drawHLine(x1 - 2.0f, x1 - 2.0f, y1 - 2.0f, borderC);
        Shapes.drawHLine(x2 + 1.0f, x2 + 1.0f, y1 - 2.0f, borderC);
        Shapes.drawRect(x2 + 1.0f, y2 + 1.0f, x1 - 1.0f, y1 - 1.0f, insideC);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
    }

    public static void drawBorderedRect(float x2, float y2, float x1, float y1, int borderC, int insideC) {
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        Shapes.drawVLine(x2 *= 2.0f, y2 *= 2.0f, (y1 *= 2.0f) - 1.0f, borderC);
        Shapes.drawVLine((x1 *= 2.0f) - 1.0f, y2, y1, borderC);
        Shapes.drawHLine(x2, x1 - 1.0f, y2, borderC);
        Shapes.drawHLine(x2, x1 - 2.0f, y1 - 1.0f, borderC);
        Shapes.drawRect(x2 + 1.0f, y2 + 1.0f, x1 - 1.0f, y1 - 1.0f, insideC);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
    }



    public static boolean stringListContains(List<String> list, String needle) {
        for (String s2 : list) {
            if (!s2.trim().equalsIgnoreCase(needle.trim())) continue;
            return true;
        }
        return false;
    }

    public static void drawBorderedRect(double x2, double y2, double x22, double y22, float l1, int col1, int col2) {
        Shapes.drawRect((float)x2, (float)y2, (float)x22, (float)y22, col2);
        float f2 = (float)(col1 >> 24 & 255) / 255.0f;
        float f1 = (float)(col1 >> 16 & 255) / 255.0f;
        float f22 = (float)(col1 >> 8 & 255) / 255.0f;
        float f3 = (float)(col1 & 255) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        GL11.glColor4f(f1, f22, f3, f2);
        GL11.glLineWidth(l1);
        GL11.glBegin(1);
        GL11.glVertex2d(x2, y2);
        GL11.glVertex2d(x2, y22);
        GL11.glVertex2d(x22, y22);
        GL11.glVertex2d(x22, y2);
        GL11.glVertex2d(x2, y2);
        GL11.glVertex2d(x22, y2);
        GL11.glVertex2d(x2, y22);
        GL11.glVertex2d(x22, y22);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }

    public static void drawHLine(float par1, float par2, float par3, int par4) {
        if (par2 < par1) {
            float var5 = par1;
            par1 = par2;
            par2 = var5;
        }
        Shapes.drawRect(par1, par3, par2 + 1.0f, par3 + 1.0f, par4);
    }

    public static void drawVLine(float par1, float par2, float par3, int par4) {
        if (par3 < par2) {
            float var5 = par2;
            par2 = par3;
            par3 = var5;
        }
        Shapes.drawRect(par1, par2 + 1.0f, par1 + 1.0f, par3, par4);
    }

    public static void drawRect(float paramXStart, float paramYStart, float paramXEnd, float paramYEnd, int paramColor) {
        float alpha = (float)(paramColor >> 24 & 255) / 255.0f;
        float red = (float)(paramColor >> 16 & 255) / 255.0f;
        float green = (float)(paramColor >> 8 & 255) / 255.0f;
        float blue = (float)(paramColor & 255) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        GL11.glColor4f(red, green, blue, alpha);
        GL11.glBegin(7);
        GL11.glVertex2d(paramXEnd, paramYStart);
        GL11.glVertex2d(paramXStart, paramYStart);
        GL11.glVertex2d(paramXStart, paramYEnd);
        GL11.glVertex2d(paramXEnd, paramYEnd);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }

    public static void drawGradientRect(double x2, double y2, double x22, double y22, int col1, int col2) {
        float f2 = (float)(col1 >> 24 & 255) / 255.0f;
        float f1 = (float)(col1 >> 16 & 255) / 255.0f;
        float f22 = (float)(col1 >> 8 & 255) / 255.0f;
        float f3 = (float)(col1 & 255) / 255.0f;
        float f4 = (float)(col2 >> 24 & 255) / 255.0f;
        float f5 = (float)(col2 >> 16 & 255) / 255.0f;
        float f6 = (float)(col2 >> 8 & 255) / 255.0f;
        float f7 = (float)(col2 & 255) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);
        GL11.glPushMatrix();
        GL11.glBegin(7);
        GL11.glColor4f(f1, f22, f3, f2);
        GL11.glVertex2d(x22, y2);
        GL11.glVertex2d(x2, y2);
        GL11.glColor4f(f5, f6, f7, f4);
        GL11.glVertex2d(x2, y22);
        GL11.glVertex2d(x22, y22);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glShadeModel(7424);
    }

    public static void drawGradientBorderedRect(int x2, int y2, int x22, int y22, float l1, int col1, int col2, int col3) {
        float f2 = (float)(col1 >> 24 & 255) / 255.0f;
        float f1 = (float)(col1 >> 16 & 255) / 255.0f;
        float f22 = (float)(col1 >> 8 & 255) / 255.0f;
        float f3 = (float)(col1 & 255) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glDisable(3042);
        GL11.glPushMatrix();
        GL11.glColor4f(f1, f22, f3, f2);
        GL11.glLineWidth(1.0f);
        GL11.glBegin(1);
        GL11.glVertex2d(x2, y2);
        GL11.glVertex2d(x2, y22);
        GL11.glVertex2d(x22, y22);
        GL11.glVertex2d(x22, y2);
        GL11.glVertex2d(x2, y2);
        GL11.glVertex2d(x22, y2);
        GL11.glVertex2d(x2, y22);
        GL11.glVertex2d(x22, y22);
        GL11.glEnd();
        GL11.glPopMatrix();
        Gui.drawGradientRect(x2, y2, x22, y22, col2, col3);
        GL11.glEnable(3042);
        GL11.glEnable(3553);
        GL11.glDisable(2848);
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    public static void drawItem(int var0, int var1, ItemStack var2) {
    	Minecraft mc = Minecraft.getMinecraft();
       // RenderItem.(mc.fontRendererObj, mc.renderEngine, var2, var0, var1);
       // RenderItem.renderItemAndEffectIntoGUI(mc.fontRendererObj, mc.renderEngine, var2, var0, var1);
        GL11.glDisable(2884);
        GL11.glEnable(3008);
        GL11.glDisable(3042);
        GL11.glDisable(2896);
        GL11.glDisable(2884);
        GL11.glClear(256);
    }
}

