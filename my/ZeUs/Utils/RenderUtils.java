package my.ZeUs.Utils;

import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glScissor;
import static org.lwjgl.opengl.GL11.glVertex3d;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;

public class RenderUtils
{
	static Minecraft mc = Minecraft.getMinecraft();
	
	private static final AxisAlignedBB DEFAULT_AABB =
		new AxisAlignedBB(0, 0, 0, 1, 1, 1);
	
	public static void scissorBox(int x, int y, int xend, int yend)
	{
		int width = xend - x;
		int height = yend - y;
		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
		int factor = sr.getScaleFactor();
		int bottomY = Minecraft.getMinecraft().currentScreen.height - yend;
		glScissor(x * factor, bottomY * factor, width * factor,
			height * factor);
	}
	
	public static void setColor(Color c)
	{
		glColor4f(c.getRed() / 255f, c.getGreen() / 255f, c.getBlue() / 255f,
			c.getAlpha() / 255f);
	}
	
	public static void drawSolidBox()
	{
		drawSolidBox(DEFAULT_AABB);
	}
	
	public static void drawSolidBox(AxisAlignedBB bb)
	{
		glBegin(GL_QUADS);
		{
			glVertex3d(bb.minX, bb.minY, bb.minZ);
			glVertex3d(bb.maxX, bb.minY, bb.minZ);
			glVertex3d(bb.maxX, bb.minY, bb.maxZ);
			glVertex3d(bb.minX, bb.minY, bb.maxZ);
			
			glVertex3d(bb.minX, bb.maxY, bb.minZ);
			glVertex3d(bb.minX, bb.maxY, bb.maxZ);
			glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
			glVertex3d(bb.maxX, bb.maxY, bb.minZ);
			
			glVertex3d(bb.minX, bb.minY, bb.minZ);
			glVertex3d(bb.minX, bb.maxY, bb.minZ);
			glVertex3d(bb.maxX, bb.maxY, bb.minZ);
			glVertex3d(bb.maxX, bb.minY, bb.minZ);
			
			glVertex3d(bb.maxX, bb.minY, bb.minZ);
			glVertex3d(bb.maxX, bb.maxY, bb.minZ);
			glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
			glVertex3d(bb.maxX, bb.minY, bb.maxZ);
			
			glVertex3d(bb.minX, bb.minY, bb.maxZ);
			glVertex3d(bb.maxX, bb.minY, bb.maxZ);
			glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
			glVertex3d(bb.minX, bb.maxY, bb.maxZ);
			
			glVertex3d(bb.minX, bb.minY, bb.minZ);
			glVertex3d(bb.minX, bb.minY, bb.maxZ);
			glVertex3d(bb.minX, bb.maxY, bb.maxZ);
			glVertex3d(bb.minX, bb.maxY, bb.minZ);
		}
		glEnd();
	}
	
	public static void drawOutlinedBox()
	{
		drawOutlinedBox(DEFAULT_AABB);
	}
	
	public static void drawOutlinedBox(AxisAlignedBB bb)
	{
		glBegin(GL_LINES);
		{
			glVertex3d(bb.minX, bb.minY, bb.minZ);
			glVertex3d(bb.maxX, bb.minY, bb.minZ);
			
			glVertex3d(bb.maxX, bb.minY, bb.minZ);
			glVertex3d(bb.maxX, bb.minY, bb.maxZ);
			
			glVertex3d(bb.maxX, bb.minY, bb.maxZ);
			glVertex3d(bb.minX, bb.minY, bb.maxZ);
			
			glVertex3d(bb.minX, bb.minY, bb.maxZ);
			glVertex3d(bb.minX, bb.minY, bb.minZ);
			
			glVertex3d(bb.minX, bb.minY, bb.minZ);
			glVertex3d(bb.minX, bb.maxY, bb.minZ);
			
			glVertex3d(bb.maxX, bb.minY, bb.minZ);
			glVertex3d(bb.maxX, bb.maxY, bb.minZ);
			
			glVertex3d(bb.maxX, bb.minY, bb.maxZ);
			glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
			
			glVertex3d(bb.minX, bb.minY, bb.maxZ);
			glVertex3d(bb.minX, bb.maxY, bb.maxZ);
			
			glVertex3d(bb.minX, bb.maxY, bb.minZ);
			glVertex3d(bb.maxX, bb.maxY, bb.minZ);
			
			glVertex3d(bb.maxX, bb.maxY, bb.minZ);
			glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
			
			glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
			glVertex3d(bb.minX, bb.maxY, bb.maxZ);
			
			glVertex3d(bb.minX, bb.maxY, bb.maxZ);
			glVertex3d(bb.minX, bb.maxY, bb.minZ);
		}
		glEnd();
	}
	
	public static void drawCrossBox()
	{
		drawCrossBox(DEFAULT_AABB);
	}
	
	public static void drawCrossBox(AxisAlignedBB bb)
	{
		glBegin(GL_LINES);
		{
			glVertex3d(bb.minX, bb.minY, bb.minZ);
			glVertex3d(bb.maxX, bb.maxY, bb.minZ);
			
			glVertex3d(bb.maxX, bb.minY, bb.minZ);
			glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
			
			glVertex3d(bb.maxX, bb.minY, bb.maxZ);
			glVertex3d(bb.minX, bb.maxY, bb.maxZ);
			
			glVertex3d(bb.minX, bb.minY, bb.maxZ);
			glVertex3d(bb.minX, bb.maxY, bb.minZ);
			
			glVertex3d(bb.maxX, bb.minY, bb.minZ);
			glVertex3d(bb.minX, bb.maxY, bb.minZ);
			
			glVertex3d(bb.maxX, bb.minY, bb.maxZ);
			glVertex3d(bb.maxX, bb.maxY, bb.minZ);
			
			glVertex3d(bb.minX, bb.minY, bb.maxZ);
			glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
			
			glVertex3d(bb.minX, bb.minY, bb.minZ);
			glVertex3d(bb.minX, bb.maxY, bb.maxZ);
			
			glVertex3d(bb.minX, bb.maxY, bb.minZ);
			glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
			
			glVertex3d(bb.maxX, bb.maxY, bb.minZ);
			glVertex3d(bb.minX, bb.maxY, bb.maxZ);
			
			glVertex3d(bb.maxX, bb.minY, bb.minZ);
			glVertex3d(bb.minX, bb.minY, bb.maxZ);
			
			glVertex3d(bb.maxX, bb.minY, bb.maxZ);
			glVertex3d(bb.minX, bb.minY, bb.minZ);
		}
		glEnd();
	}
	
	public static void drawNode(AxisAlignedBB bb)
	{
		double midX = (bb.minX + bb.maxX) / 2;
		double midY = (bb.minY + bb.maxY) / 2;
		double midZ = (bb.minZ + bb.maxZ) / 2;
		
		GL11.glVertex3d(midX, midY, bb.maxZ);
		GL11.glVertex3d(bb.minX, midY, midZ);
		
		GL11.glVertex3d(bb.minX, midY, midZ);
		GL11.glVertex3d(midX, midY, bb.minZ);
		
		GL11.glVertex3d(midX, midY, bb.minZ);
		GL11.glVertex3d(bb.maxX, midY, midZ);
		
		GL11.glVertex3d(bb.maxX, midY, midZ);
		GL11.glVertex3d(midX, midY, bb.maxZ);
		
		GL11.glVertex3d(midX, bb.maxY, midZ);
		GL11.glVertex3d(bb.maxX, midY, midZ);
		
		GL11.glVertex3d(midX, bb.maxY, midZ);
		GL11.glVertex3d(bb.minX, midY, midZ);
		
		GL11.glVertex3d(midX, bb.maxY, midZ);
		GL11.glVertex3d(midX, midY, bb.minZ);
		
		GL11.glVertex3d(midX, bb.maxY, midZ);
		GL11.glVertex3d(midX, midY, bb.maxZ);
		
		GL11.glVertex3d(midX, bb.minY, midZ);
		GL11.glVertex3d(bb.maxX, midY, midZ);
		
		GL11.glVertex3d(midX, bb.minY, midZ);
		GL11.glVertex3d(bb.minX, midY, midZ);
		
		GL11.glVertex3d(midX, bb.minY, midZ);
		GL11.glVertex3d(midX, midY, bb.minZ);
		
		GL11.glVertex3d(midX, bb.minY, midZ);
		GL11.glVertex3d(midX, midY, bb.maxZ);
	}
	
	public static void drawBoundingBoxESP(AxisAlignedBB axisalignedbb, float width, int color) {
        GL11.glPushMatrix();
        float red = (float)(color >> 24 & 255) / 255.0F;
        float green = (float)(color >> 16 & 255) / 255.0F;
        float blue = (float)(color >> 8 & 255) / 255.0F;
        float alpha = (float)(color & 255) / 255.0F;
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(2896);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glLineWidth(width);
        GL11.glColor4f(red, green, blue, alpha);
        drawOutlinedBox(axisalignedbb);
        GL11.glLineWidth(1.0F);
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2896);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
	
	public static void glColor(int hex)
    {
        float f = (float)(hex >> 24 & 255) / 255.0F;
        float f1 = (float)(hex >> 16 & 255) / 255.0F;
        float f2 = (float)(hex >> 8 & 255) / 255.0F;
        float f3 = (float)(hex & 255) / 255.0F;
        GL11.glColor4f(f1, f2, f3, f);
    }
	
	public static void drawRect(float x2, float y2, float x1, float y1, int color)
    {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        glColor(color);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(x2, y1);
        GL11.glVertex2f(x1, y1);
        GL11.glVertex2f(x1, y2);
        GL11.glVertex2f(x2, y2);
        GL11.glEnd();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
    }

    public static void drawBorderedRect2(float x, float y, float x2, float y2, float l1, int col1, int col2)
    {
        drawRect(x, y, x2, y2, col2);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glLineWidth(l1);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        glColor(col1);
        GL11.glBegin(GL11.GL_LINE_STRIP);
        GL11.glVertex2f(x, y - 0.5F);
        GL11.glVertex2f(x, y2);
        GL11.glVertex2f(x2, y2);
        GL11.glVertex2f(x2, y);
        GL11.glVertex2f(x, y);
        GL11.glEnd();
        GL11.glShadeModel(GL11.GL_FLAT);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
    }

    public static void drawFilledBBESP(AxisAlignedBB axisalignedbb, int color) {
        GL11.glPushMatrix();
        float red = (float)(color >> 24 & 255) / 255.0F;
        float green = (float)(color >> 16 & 255) / 255.0F;
        float blue = (float)(color >> 8 & 255) / 255.0F;
        float alpha = (float)(color & 255) / 255.0F;
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(2896);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, alpha);
        drawFilledBox(axisalignedbb);
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2896);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    
    public static void drawFilledBox(AxisAlignedBB boundingBox) {
        if (boundingBox != null) {
            GL11.glBegin(7);
            GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
            GL11.glEnd();
        }
    }
    
    
    
    public static void renderItem(ItemStack itemStack, int x, int y) {
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.enableDepth();
        net.minecraft.client.renderer.RenderHelper.enableGUIStandardItemLighting();
        mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, x, y);
        mc.getRenderItem().renderItemOverlays(mc.fontRendererObj, itemStack, x, y);
        net.minecraft.client.renderer.RenderHelper.disableStandardItemLighting();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.disableDepth();
    }
    
    public static void enableGL2D() {
		GL11.glDisable(2929);
		GL11.glDisable(3553);
		GL11.glBlendFunc(770, 771);
		GL11.glDepthMask(true);
		GL11.glEnable(2848);
		GL11.glHint(3154, 4354);
		GL11.glHint(3155, 4354);
	}
    public static void disableGL2D() {
		GL11.glEnable(3553);
		GL11.glEnable(2929);
		GL11.glDisable(2848);
		GL11.glHint(3154, 4352);
		GL11.glHint(3155, 4352);
	}
}
