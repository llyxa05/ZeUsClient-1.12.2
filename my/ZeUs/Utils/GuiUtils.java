package my.ZeUs.Utils;

import net.minecraft.client.gui.*;
import org.lwjgl.opengl.*;

public class GuiUtils
{
    public static void drawSexyRect(final double x, final double y, final double x1, final double y1, final int color, final int color2) {
        Gui.drawRect((int)x, (int)y, (int)x1, (int)y1, color);
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        Gui.drawRect((int)x * 2 - 1, (int)y * 2, (int)x * 2, (int)y1 * 2 - 1, color2);
        Gui.drawRect((int)x * 2, (int)y * 2 - 1, (int)x1 * 2, (int)y * 2, color2);
        Gui.drawRect((int)x1 * 2, (int)y * 2, (int)x1 * 2 + 1, (int)y1 * 2 - 1, color2);
        Gui.drawRect((int)x * 2, (int)y1 * 2 - 1, (int)x1 * 2, (int)y1 * 2, color2);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
    }
    
    public static void drawGG(final double x, final double y, final double x1, final double y1, final int color) {
        GL11.glScalef(2.0f, 2.0f, 2.0f);
    }
    
    public static void drawBorderedCircle(int x, int y, float radius, final int outsideC, final int insideC) {
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        final float scale = 0.1f;
        GL11.glScalef(0.1f, 0.1f, 0.1f);
        x *= 10;
        y *= 10;
        radius *= 10.0f;
        drawCircle(x, y, radius, insideC);
        drawUnfilledCircle(x, y, radius, 1.0f, outsideC);
        GL11.glScalef(10.0f, 10.0f, 10.0f);
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }
    
    public static void drawUnfilledCircle(final int x, final int y, final float radius, final float lineWidth, final int color) {
        final float alpha = (color >> 24 & 0xFF) / 255.0f;
        final float red = (color >> 16 & 0xFF) / 255.0f;
        final float green = (color >> 8 & 0xFF) / 255.0f;
        final float blue = (color & 0xFF) / 255.0f;
        GL11.glColor4f(red, green, blue, alpha);
        GL11.glLineWidth(lineWidth);
        GL11.glEnable(2848);
        GL11.glBegin(2);
        for (int i = 0; i <= 360; ++i) {
            GL11.glVertex2d(x + Math.sin(i * 3.141526 / 180.0) * radius, y + Math.cos(i * 3.141526 / 180.0) * radius);
        }
        GL11.glEnd();
        GL11.glDisable(2848);
    }
    
    public static void drawCircle(final int x, final int y, final float radius, final int color) {
        final float alpha = (color >> 24 & 0xFF) / 255.0f;
        final float red = (color >> 16 & 0xFF) / 255.0f;
        final float green = (color >> 8 & 0xFF) / 255.0f;
        final float blue = (color & 0xFF) / 255.0f;
        GL11.glColor4f(red, green, blue, alpha);
        GL11.glBegin(9);
        for (int i = 0; i <= 360; ++i) {
            GL11.glVertex2d(x + Math.sin(i * 3.141526 / 180.0) * radius, y + Math.cos(i * 3.141526 / 180.0) * radius);
        }
        GL11.glEnd();
    }
    
}
