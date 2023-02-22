package my.ZeUs.modules.hack.Render;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.ChatUtils;
import my.ZeUs.Utils.Client;
import my.ZeUs.Utils.GuiUtils;
import my.ZeUs.Utils.RenderUtils;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.modules.Module;
import my.ZeUs.settings.Setting;
import my.ZeUs.Utils.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import shwepss.event.Event3DRender;
import shwepss.eventapi.EventManager;
import shwepss.eventapi.EventTarget;

public class NameTags extends Module {
	private double scale = 0.05;
	public NameTags() {
		super("NameTags", Keyboard.KEY_NONE, Category.Render);
	}
	
	@Override
    public void setup() {
		Client.settingsManager.rSetting(new Setting("NickColor", this, 16777215, 11777215, 19777215, false));
    }
	
	public void onEnable() {
		EventManager.register(this);
	}
	
	public void onDisable() {
		EventManager.unregister(this);
	}
	
	
	@EventTarget
    public void onRender(Event3DRender event) {
     	Minecraft mc = Minecraft.getMinecraft();
            Iterator var2 = mc.world.playerEntities.iterator();

            while (var2.hasNext()) {
                EntityPlayer var1 = (EntityPlayer) var2.next();
                if (var1 != null && var1.deathTime <= 0) {
                    Minecraft var10001 = mc;
                    if (var1 != Minecraft.player) {
                        double var3 = var1.lastTickPosX + (var1.posX - var1.lastTickPosX) - RenderManager.renderPosX;
                        double var5 = var1.lastTickPosY + (var1.posY - var1.lastTickPosY) - RenderManager.renderPosY;
                        double var7 = var1.lastTickPosZ + (var1.posZ - var1.lastTickPosZ) - RenderManager.renderPosZ;
                        this.a(var1, var3, var5, var7);
                    }
                }
            }

    }

    private void a(Entity var1, double var2, double var4, double var6) {
    	Minecraft mc = Minecraft.getMinecraft();
        String var8 = "";
        String var9 = var1.getName();
        if (var1 instanceof EntityLivingBase) {
            if (((EntityLivingBase) var1).getHealth() < 15.0F) {
                var8 = String.valueOf((new StringBuilder(" §6")).append((double) Math.round(((EntityLivingBase) var1).getHealth() * 100.0F / 100.0F) / 2.0D));
            } else if (((EntityLivingBase) var1).getHealth() > 15.0F) {
                var8 = String.valueOf((new StringBuilder(" §a")).append((double) Math.round(((EntityLivingBase) var1).getHealth() * 100.0F / 100.0F) / 2.0D));
            } else if (((EntityLivingBase) var1).getHealth() < 8.0F) {
                var8 = String.valueOf((new StringBuilder(" §4")).append((double) Math.round(((EntityLivingBase) var1).getHealth() * 100.0F / 100.0F) / 2.0D));
            }
        }

        float var10 = 1.3F;
        float var11;
        Tessellator var12;
        BufferBuilder var13;
        int var14;
        int var15;
            var11 = 0.016666668F * Minecraft.player.getDistanceToEntity(var1) / 2.5F;
            GL11.glPushMatrix();
            GL11.glTranslatef((float) var2, (float) var4 + var1.height + 0.5F, (float) var6);
            GL11.glNormal3f(0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-RenderManager.playerViewY, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(RenderManager.playerViewX, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(-var11, -var11, var11);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glEnable(2848);
            GL11.glLineWidth(2.0F);
            GL11.glDisable(3553);
            GL11.glDisable(2929);
            var12 = Tessellator.getInstance();
            var13 = var12.getBuffer();
            var14 = (int) (-Minecraft.player.getDistanceToEntity(var1)) / (int) var10;
            if (var1.isSneaking()) {
                ++var14;
            } else if (var14 < -14) {
                var14 = -5;
            }

            var15 = mc.fontRendererObj.getStringWidth(String.valueOf((new StringBuilder(String.valueOf(var9))).append(var8))) / 2;
            Gui.drawRect(-var15 - 2, var14 - 10, var15 + 2, var14, 542840575 * 4500);
            if (((EntityLivingBase) var1).getHealth() < 15.0F) {
                Gui.drawRect(-var15 - 2, var14 + 1, var15 + 2, var14, ColorUtil.getRainbow());
            } else if (((EntityLivingBase) var1).getHealth() > 15.0F) {
                Gui.drawRect(-var15 - 2, var14 + 1, var15 + 2, var14, ColorUtil.getRainbow());
            } else if (((EntityLivingBase) var1).getHealth() < 8.0F) {
                Gui.drawRect(-var15 - 2, var14 + 1, var15 + 2, var14, ColorUtil.getRainbow());
            }

            mc.fontRendererObj.drawStringWithShadow(String.valueOf((new StringBuilder(String.valueOf(var9))).append(var8)), (float) (-var15), (float) (var14 - 10), (int) Client.settingsManager.getSettingByName("NickColor").getValDouble());
            Minecraft.getMinecraft().entityRenderer.disableLightmap();
            GL11.glLineWidth(1.0F);
            GL11.glEnable(3553);
            GL11.glDisable(3042);
            GL11.glDisable(2896);
            GL11.glBlendFunc(770, 771);
            GL11.glPopMatrix();
            GL11.glEnable(2929);
            GL11.glEnable(3553);
            GL11.glDisable(3042);
            GL11.glDisable(2848);

    }
}