package my.ZeUs.modules.hack.Misc;

import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

public class MurderBA extends Module
{
    public MurderBA() {
    	super("MurderBA", Keyboard.KEY_NONE, Category.Misc);
    }
    
    
    
    
    
    public void onEnable() {
    	
    }
    
    @Override
    public void onDisable() {
    	
    }
    
   
    
    public static EntityLivingBase entityKiller;
    
    @Override
    public void onTick() {
    	
    	/*for (Entity ent : getMC().world.loadedEntityList) {
            EntityLivingBase en = (EntityLivingBase)ent;
        if(!(en instanceof EntityArmorStand)) {
            if(en.getHeldItemMainhand().getItem() == Item.getByNameOrId("iron_sword")) {
                 entityKiller = en;
                 this.faceEntityWithBow(this.getNearestPlayer());
                }
            }
        }*/

    	
        /*for (final Object players : Minecraft.getMinecraft().world.loadedEntityList) {
            Entity e = (Entity)players;
            if (e instanceof EntityPlayer && e != Minecraft.getMinecraft().player && !e.isDead && Minecraft.getMinecraft().player.canEntityBeSeen(e) && Minecraft.getMinecraft().player.getDistanceToEntity(e) < 60.0 && e instanceof EntityPlayer) {
                e = this.getNearestPlayer();
                final float distance = Minecraft.getMinecraft().player.getDistanceToEntity(e);
                if (Minecraft.getMinecraft().gameSettings.keyBindUseItem.pressed) {
                    this.faceEntityWithBow(this.getNearestPlayer());
                    break;
                }
                continue;
            }
        }*/
    }
    
    
    public EntityPlayer getNearestPlayer() {
        EntityPlayer nearest = null;
        if (Minecraft.getMinecraft().world == null) {
            return null;
        }
        for (final Object o : Minecraft.getMinecraft().world.playerEntities) {
            if (o != null && !(o instanceof EntityPlayerSP)) {
                final EntityPlayer e = (EntityPlayer)o;
                if (e.isDead) {
                    continue;
                }
                if (nearest == null) {
                    nearest = e;
                }
                else {
                    if (nearest.getDistanceToEntity(Minecraft.getMinecraft().player) <= e.getDistanceToEntity(Minecraft.getMinecraft().player)) {
                        continue;
                    }
                    nearest = e;
                }
            }
        }
        return nearest;
    }
    
    private void faceEntityWithBow(final Entity e) {
        double angle = 0.0;
        final float distance = Minecraft.getMinecraft().player.getDistanceToEntity(e);
        final double xDif = e.posX - Minecraft.getMinecraft().player.posX;
        final double zDif = e.posZ - Minecraft.getMinecraft().player.posZ;
        final double yDif = e.posY - Minecraft.getMinecraft().player.posY + distance / 4.0f / 2.0f + 1.0;
        if (zDif > 0.0 && xDif > 0.0) {
            angle = Math.toDegrees(-Math.atan(xDif / zDif));
        }
        else if (zDif > 0.0 && xDif < 0.0) {
            angle = Math.toDegrees(-Math.atan(xDif / zDif));
        }
        else if (zDif < 0.0 && xDif > 0.0) {
            angle = -90.0 + Math.toDegrees(Math.atan(zDif / xDif));
        }
        else if (zDif < 0.0 && xDif < 0.0) {
            angle = 90.0 + Math.toDegrees(Math.atan(zDif / xDif));
        }
        final double d = Math.sqrt(zDif * zDif + xDif * xDif);
        final double d2 = -Math.toDegrees(Math.atan(yDif / d));
        Minecraft.getMinecraft().player.rotationPitch = (float)d2 + 15.0f;
        Minecraft.getMinecraft().player.rotationYaw = (float)angle;
    }
    
}
