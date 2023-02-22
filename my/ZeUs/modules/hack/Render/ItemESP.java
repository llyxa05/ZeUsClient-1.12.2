package my.ZeUs.modules.hack.Render;

import my.ZeUs.Utils.Category;
import my.ZeUs.modules.Module;
import shwepss.event.*;
import net.minecraft.client.*;
import net.minecraft.entity.item.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.gui.*;
import java.util.*;
import net.minecraft.entity.*;
import shwepss.eventapi.*;

public class ItemESP extends Module
{
    public static boolean isEnabled;
    
    public ItemESP() {
        super("ItemESP", 0, Category.Render);
    }
    
    @Override
    public void onEnable() {
        EventManager.register(this);
        ItemESP.isEnabled = true;
    }
    
    @Override
    public void onDisable() {
        ItemESP.isEnabled = false;
        EventManager.unregister(this);
    }
    
    @EventTarget
    public void onRender(final Event3DRender e) {
        final Minecraft mc = Minecraft.getMinecraft();
        for (final Object o : mc.world.loadedEntityList) {
            if (!(o instanceof EntityItem)) {
                continue;
            }
            final EntityItem item = (EntityItem)o;
            GlStateManager.pushMatrix();
            GlStateManager.translate((float)item.lastTickPosX + (item.posX - item.lastTickPosX) * Minecraft.getMinecraft().timer.field_194147_b - RenderManager.renderPosX, item.lastTickPosY + (item.posY - item.lastTickPosY) * Minecraft.getMinecraft().timer.field_194147_b - RenderManager.renderPosY, item.lastTickPosZ + (item.posZ - item.lastTickPosZ) * Minecraft.getMinecraft().timer.field_194147_b - RenderManager.renderPosZ);
            final RenderManager renderManager = Minecraft.getMinecraft().renderManager;
            GlStateManager.rotate(-RenderManager.playerViewY, 0.0f, 1.0f, 0.0f);
            GlStateManager.scale(-0.02666667f, -0.02666667f, 0.02666667f);
            GlStateManager.disableDepth();
            GlStateManager.disableLighting();
            Gui.drawRect(9.0, -25.0, 13.0, -17.0, -16777216);
            Gui.drawRect(5.0, -21.0, 12.0, -25.0, -16777216);
            Gui.drawRect(10.0, -22.0, 12.0, -18.0, -16711697);
            Gui.drawRect(6.0, -22.0, 12.0, -24.0, -16711697);
            Gui.drawRect(-9.0, -25.0, -13.0, -17.0, -16777216);
            Gui.drawRect(-5.0, -21.0, -12.0, -25.0, -16777216);
            Gui.drawRect(-10.0, -22.0, -12.0, -18.0, -16711697);
            Gui.drawRect(-6.0, -22.0, -12.0, -24.0, -16711697);
            Gui.drawRect(9.0, -7.0, 13.0, 1.0, -16777216);
            Gui.drawRect(9.0, -3.0, 5.0, 1.0, -16777216);
            Gui.drawRect(10.0, -6.0, 12.0, 0.0, -16711697);
            Gui.drawRect(10.0, -2.0, 6.0, 0.0, -16711697);
            Gui.drawRect(-9.0, -7.0, -13.0, 1.0, -16777216);
            Gui.drawRect(-10.0, -3.0, -5.0, 1.0, -16777216);
            Gui.drawRect(-10.0, -6.0, -12.0, 0.0, -16711697);
            Gui.drawRect(-10.0, -2.0, -6.0, 0.0, -16711697);
            GlStateManager.enableDepth();
            GlStateManager.color(1.0f, 1.0f, 0.0f, 1.0f);
            GlStateManager.popMatrix();
        }
    }
}