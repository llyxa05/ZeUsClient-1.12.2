package my.ZeUs.modules.hack.Render;

import net.minecraft.client.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;

import java.util.*;

import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.modules.*;
import net.minecraft.entity.player.*;

public class MurderHack extends Module
{
	public MurderHack() {
		super("MurderHack", Keyboard.KEY_NONE, Category.Render);
    }
    
	public void onEnable() {
		WSoundEvents.playButtonClick();
	}
	
    @Override
    public void onTick() {
        final Minecraft mc = Minecraft.getMinecraft();
        for (final Entity ent : mc.world.loadedEntityList) {
            if (this.checks(ent)) {
                final EntityLivingBase en = (EntityLivingBase)ent;
                for (final Object o : mc.world.loadedEntityList) {
                    final EntityLivingBase e1;
                    if (o instanceof EntityLivingBase && (e1 = (EntityLivingBase)o) != mc.player && en.getHeldItemMainhand().getItem() == Item.getByNameOrId("iron_sword")) {
                        en.setGlowing(true);
                    }
                }
            }
        }
    }
    
    @Override
    public void onDisable() {
        final Minecraft mc = Minecraft.getMinecraft();
        for (final Entity ent : mc.world.loadedEntityList) {
            if (this.checks(ent)) {
                final EntityLivingBase en = (EntityLivingBase)ent;
                en.setGlowing(false);
            }
        }
		WSoundEvents.grass();
    }
    
    boolean checks(final Entity en) {
        return en instanceof EntityLivingBase && !en.isDead && en instanceof EntityPlayer;
    }
}

