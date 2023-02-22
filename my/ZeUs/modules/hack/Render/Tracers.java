package my.ZeUs.modules.hack.Render;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.Client;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.modules.Module;
import my.ZeUs.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import shwepss.event.Event3DRender;
import shwepss.eventapi.EventManager;
import shwepss.eventapi.EventTarget;

public class Tracers
extends Module {
    public Tracers() {
        super("Tracers", Keyboard.KEY_X, Category.Render);
    }
    
    public void onEnable(){
    	EventManager.register(this);
    	WSoundEvents.playButtonClick();
    }
    
    @Override
    public void setup() {
    	Client.settingsManager.rSetting(new Setting("Width", this, 1.0F, 0.5F, 3.5F, false));
    }
    
    
    @EventTarget
    public void onEvent3D(Event3DRender event) {
        boolean old = Tracers.getMC().gameSettings.viewBobbing;
        Tracers.getMC().gameSettings.viewBobbing = false;
        Tracers.getMC().entityRenderer.setupCameraTransform(event.pticks(), 2);
        Tracers.getMC().gameSettings.viewBobbing = old;
        GL11.glPushMatrix();
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDisable(3553);
        GL11.glDisable(2896);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth((float)Client.settingsManager.getSettingByName("Width").getValDouble());
        for (Entity entity : Tracers.getMC().world.loadedEntityList) {
            if (entity == Minecraft.player || !(entity instanceof EntityPlayer)) continue;
            assert (getMC().getRenderViewEntity() != null);
            getMC().getRenderViewEntity().getDistanceToEntity(entity);
            double d = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) - Tracers.getMC().getRenderManager().viewerPosX;
            double d2 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) - Tracers.getMC().getRenderManager().viewerPosY;
            double d3 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) - Tracers.getMC().getRenderManager().viewerPosZ;
            Vec3d vec3d = new Vec3d(0.0, 0.0, 1.0);
            vec3d = vec3d.rotatePitch(-((float)Math.toRadians(Minecraft.player.rotationPitch)));
            Vec3d vec3d2 = vec3d.rotateYaw(-((float)Math.toRadians(Minecraft.player.rotationYaw)));
            GL11.glBegin(2);
            GL11.glVertex3d(vec3d2.xCoord, (double)Minecraft.player.getEyeHeight() + vec3d2.yCoord, vec3d2.zCoord);
            GL11.glVertex3d(d, d2 + 0.1, d3);
            GL11.glEnd();
        }
        GL11.glDisable(3042);
        GL11.glDepthMask(true);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glPopMatrix();
    }
    
    public void onDisable() {
    	EventManager.unregister(this);
		WSoundEvents.grass();
    }
}