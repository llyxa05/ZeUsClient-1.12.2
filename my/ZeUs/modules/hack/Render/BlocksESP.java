package my.ZeUs.modules.hack.Render;

import java.util.ArrayList;

import org.lwjgl.util.Timer;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.Client;
import my.ZeUs.Utils.ColorUtil;
import my.ZeUs.Utils.RenderUtils;
import my.ZeUs.modules.Module;
import my.ZeUs.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBed;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityDropper;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.tileentity.TileEntityShulkerBox;
import net.minecraft.util.math.AxisAlignedBB;
import shwepss.event.Event3DRender;
import shwepss.eventapi.EventManager;
import shwepss.eventapi.EventTarget;

public class BlocksESP extends Module
{
    private final ArrayList<Entity> players;
    private final ArrayList<Blocks> CHESTs;
    int playerBox;
    
    public BlocksESP() {
        super("BlocksESP", 0, Category.Render);
        this.players = new ArrayList<Entity>();
        this.CHESTs = new ArrayList<Blocks>();
    }
    
    @Override
    public void setup() {
    	Client.settingsManager.rSetting(new Setting("MobSpawner ", this, true));
    	Client.settingsManager.rSetting(new Setting("Dispenser ", this, true));
    	Client.settingsManager.rSetting(new Setting("Shulker ", this, true));
    	Client.settingsManager.rSetting(new Setting("Dropper ", this, true));
    	Client.settingsManager.rSetting(new Setting("Chests ", this, true));
    	Client.settingsManager.rSetting(new Setting("Hopper ", this, true));
    	Client.settingsManager.rSetting(new Setting("Bed ", this, true));
    }
    
    @Override
    public void onEnable() {
        EventManager.register(this);
    }
    
    @Override
    public void onDisable() {
        EventManager.unregister(this);
    }
    
    
    @EventTarget
    public void on3D(final Event3DRender e) {
    	
        final Minecraft mc = Minecraft.getMinecraft();
        for (final Object o : mc.world.loadedTileEntityList) {
            final TileEntity tileEntity = (TileEntity)o;
            final double n = tileEntity.getPos().getX();
            mc.getRenderManager();
            final double x = n - RenderManager.renderPosX;
            final double n2 = tileEntity.getPos().getY();
            mc.getRenderManager();
            final double y = n2 - RenderManager.renderPosY;
            final double n3 = tileEntity.getPos().getZ();
            mc.getRenderManager();
            final double z = n3 - RenderManager.renderPosZ;
            if (tileEntity instanceof TileEntityFurnace) {
                RenderUtils.drawBoundingBoxESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), 1.5f, 1717987071);
                RenderUtils.drawFilledBBESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), 1717986848);
            }
            if (tileEntity instanceof TileEntityHopper) {
            	if(Client.settingsManager.getSettingByName("Hopper ").getValBoolean()) {
            		RenderUtils.drawBoundingBoxESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), 1.5f, -2004317953);
                    RenderUtils.drawFilledBBESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), -2004318176);
            	}
            }
            if (tileEntity instanceof TileEntityDropper) {
            	if(Client.settingsManager.getSettingByName("Dropper ").getValBoolean()) {
            		RenderUtils.drawBoundingBoxESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), 1.5f, -2004317953);
                    RenderUtils.drawFilledBBESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), -2004318176);
            	}
            }
            if (tileEntity instanceof TileEntityDispenser) {
            	if(Client.settingsManager.getSettingByName("Dispenser ").getValBoolean()) {
            		RenderUtils.drawBoundingBoxESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), 1.5f, -2004317953);
                    RenderUtils.drawFilledBBESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), -2004318176);
            	}
            }
            if (tileEntity instanceof TileEntityEnderChest) {
            	if(Client.settingsManager.getSettingByName("Chests ").getValBoolean()) {
                    RenderUtils.drawBoundingBoxESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), 1.5f, 294134527);
                    RenderUtils.drawFilledBBESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), 294134304);
            	}
            }
            if (tileEntity instanceof TileEntityBrewingStand) {
                RenderUtils.drawBoundingBoxESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), 1.5f, -649338639);
                RenderUtils.drawFilledBBESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), 288585504);
            }
            if (tileEntity instanceof TileEntityShulkerBox) {
            	if(Client.settingsManager.getSettingByName("Shulker ").getValBoolean()) {
            		RenderUtils.drawBoundingBoxESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), 1.5f, 368171744);
            	}
            }
            if (tileEntity instanceof TileEntityMobSpawner) {
            	if(Client.settingsManager.getSettingByName("MobSpawner ").getValBoolean()) {
                    RenderUtils.drawBoundingBoxESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), 1.5f, ColorUtil.rainbow(20000000L, 1.0f).getRGB());
            	}
            }
            
            if (tileEntity instanceof TileEntityBed) {
            	if(Client.settingsManager.getSettingByName("Bed ").getValBoolean()) {
                    RenderUtils.drawBoundingBoxESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 0.6, z + 1.0), 3.5f, 1163488753);
                    RenderUtils.drawFilledBBESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 0.6, z + 1.0), -227217056);
            	}
            }
            if (tileEntity instanceof TileEntityChest) {
            	if(Client.settingsManager.getSettingByName("Chests ").getValBoolean()) {
            		RenderUtils.drawBoundingBoxESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), 2.2f, 99999999);
                    RenderUtils.drawFilledBBESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), 999990);
                    Timer.tick();
            	}
            }
        }
    }
}
