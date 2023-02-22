package my.ZeUs.modules.hack.Render;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import my.ZeUs.Utils.Category;
import my.ZeUs.modules.Module;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import shwepss.event.Event3DRender;
import shwepss.eventapi.EventManager;
import shwepss.eventapi.EventTarget;

public class BlockOverlay extends Module {
	
	public BlockOverlay() {
        super("BlockOverlay", 0, Category.Render);
    }
	
    public void onEnable()
    {
    	EventManager.register(this);
    }

    public void onDisable()
    {
    	EventManager.unregister(this);
    }

    @EventTarget
    public void onkek(Event3DRender ev) {
    	 if (getMC().world.getBlockState(getMC().objectMouseOver.getBlockPos()).getBlock() != Blocks.AIR && getMC().world.getBlockState(getMC().objectMouseOver.getBlockPos()).getBlock().isFullBlock(getMC().world.getBlockState(getMC().objectMouseOver.getBlockPos()))) {
             final double[] array = new double[3];
             final int n = 0;
             final double n2 = getMC().objectMouseOver.getBlockPos().getX();
             getMC().getRenderManager();
             array[n] = n2 - RenderManager.renderPosX;
             final int n3 = 1;
             final double n4 = getMC().objectMouseOver.getBlockPos().getY();
             getMC().getRenderManager();
             array[n3] = n4 - RenderManager.renderPosY;
             final int n5 = 2;
             final double n6 = getMC().objectMouseOver.getBlockPos().getZ();
             getMC().getRenderManager();
             array[n5] = n6 - RenderManager.renderPosZ;
             final double[] lllllllllllllIIIlIIlIIllllllIIlI = array;
             drawBlockOutline(new AxisAlignedBB(lllllllllllllIIIlIIlIIllllllIIlI[0], lllllllllllllIIIlIIlIIllllllIIlI[1], lllllllllllllIIIlIIlIIllllllIIlI[2], lllllllllllllIIIlIIlIIllllllIIlI[0] + 1.0, lllllllllllllIIIlIIlIIllllllIIlI[1] + 1.0, lllllllllllllIIIlIIlIIllllllIIlI[2] + 1.0), Color.WHITE, 1.7f);
    	 
    	 }
    }
    
    public static void drawBlockOutline(final AxisAlignedBB bb, final Color color, final float linewidth) {
        final float red = color.getRed() / 255.0f;
        final float green = color.getGreen() / 255.0f;
        final float blue = color.getBlue() / 255.0f;
        final float alpha = color.getAlpha() / 255.0f;
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(linewidth);
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

     
}
