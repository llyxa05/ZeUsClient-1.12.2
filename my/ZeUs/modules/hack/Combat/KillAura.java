package my.ZeUs.modules.hack.Combat;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Objects;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import my.ZeUs.Utils.AnimationHelper;
import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.Client;
import my.ZeUs.Utils.ColorUtil;
import my.ZeUs.Utils.RenderUtils;
import my.ZeUs.Utils.TimeUtil;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.modules.Module;
import my.ZeUs.modules.ModuleManager;
import my.ZeUs.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class KillAura extends Module {

   public static EntityLivingBase target; 
   
   public KillAura() {
       super("KillAura", Keyboard.KEY_R, Category.Combat);
   }
   
   @Override
   public void setup() {
	   ArrayList<String> options = new ArrayList<>();
	   options.add("DefaultPvP");
   	   options.add("SpeedAura");
   	   Client.getInstance().settingsManager.rSetting(new Setting("SpeedSelection", this, "DefaultPvP", options));
	   Client.settingsManager.rSetting(new Setting("SpeedAura", this, 7, -12, 8, false));
       Client.settingsManager.rSetting(new Setting("Distance", this, 4, 3, 6, false));
       Client.settingsManager.rSetting(new Setting("Client RotationsV1 ", this, false));
       Client.settingsManager.rSetting(new Setting("Client RotationsV2 ", this, false));
       Client.settingsManager.rSetting(new Setting("Packet Rotations ", this, false));
       Client.settingsManager.rSetting(new Setting("Invisible ", this, true));
       Client.settingsManager.rSetting(new Setting("Players ", this, true));
       Client.settingsManager.rSetting(new Setting("Mobs ", this, true));
  }
   
   
  @Override
  public void onEnable() {
      super.onEnable();
      WSoundEvents.playButtonClick();
  }

  @Override
  public void onDisable() {
      super.onDisable();
      WSoundEvents.grass();
  }

  @Override
  public void onTick() {

      if(!this.isEnabled()){
          return;
      }
      if(Client.settingsManager.getSettingByName("Client RotationsV2 ").getValBoolean()) {
    	  this.rotationsV2(this.getNearestPlayer());	
      }
      super.onTick();
      target = getClosest(Client.settingsManager.getSettingByName("Distance").getValDouble());
      Minecraft mc = Minecraft.getMinecraft();
      for (Entity ent : mc.world.loadedEntityList) {
          if (checks(ent)) {
              EntityLivingBase en = (EntityLivingBase)ent;
              if (mc.player.getDistanceToEntity(target) <= Client.settingsManager.getSettingByName("Distance").getValDouble()) {
    	          if(Client.settingsManager.getSettingByName("Packet Rotations ").getValBoolean()) {
    	              this.faceEntity(ent);	
    	          }
    	          if(Client.settingsManager.getSettingByName("Client RotationsV1 ").getValBoolean()) {
    	              this.faceEntity2(ent);	
    	          }
                  mc.playerController.attackEntity(mc.player, en);
                  mc.player.swingArm(EnumHand.MAIN_HAND);
                  }
              }
          }
      }
	  
  @Override
  public void onRender() {
	  if (ModuleManager.getModuleByName("KillAura").isEnabled()) {
        if(ModuleManager.getModuleByName("TargetHUD").isEnabled()) {
        	if(target == null)
    			return;
		    targetHud();
		}
	}	   
  }
    private double healthBarWidth;
	private double hudHeight;
	TimeUtil time = new TimeUtil();
	public void targetHud() {
	ScaledResolution scaledResolution = new ScaledResolution(getMC());
        ScaledResolution sr1 = new ScaledResolution(getMC());
                final float scaledWidth = sr1.getScaledWidth();
                final float scaledHeight = sr1.getScaledHeight();
                final float x = scaledWidth / 2.0f - (float)Client.settingsManager
                        .getSettingByName("TargetHudX").getValDouble();
                final float y = scaledHeight / 2.0f - (float)Client.settingsManager
                        .getSettingByName("TargetHudY").getValDouble();
                final float health = KillAura.target.getHealth();
                double hpPercentage = health / KillAura.target.getMaxHealth();
                hpPercentage = MathHelper.clamp(hpPercentage, 0.0, 1.0);
                final double hpWidth = 97.0 * hpPercentage;
                final String healthStr = String.valueOf((int) KillAura.target.getHealth() / 2.0f);
                if (time.check(15L)) {
                    this.healthBarWidth = AnimationHelper.animate(hpWidth, this.healthBarWidth, 0.1029999852180481);
                    this.hudHeight = AnimationHelper.animate(40.0, this.hudHeight, 0.10000000149011612);
                    time.reset();
                }
                Gui.drawRect(x + 125.5, y - 9.5, x + 265, y + 30.5f, new Color(31, 31, 31, 255).getRGB());
                Gui.drawRect(x + 166.0f, y + 6.0f, x + 263.0f, y + 15.0f, new Color(40, 40, 40, 255).getRGB());
                Gui.drawRect(x + 166.0f, y + 6.0f, x + 166.0f + this.healthBarWidth, y + 15.0f,
                		ColorUtil.getRainbow());
                getMC().fontRendererObj.drawStringWithShadow(healthStr,
                        x + 128.0f + 46.0f - getMC().fontRendererObj.getStringWidth(healthStr) / 2.0f, y + 19.5f, -1);
                getMC().fontRendererObj.drawStringWithShadow("\u2764",
                        x + 128.0f + 46.0f + getMC().fontRendererObj.getStringWidth(healthStr), y + 19.5f,
                       new Color(Color.RED.getRGB()).getRGB());
                getMC().fontRendererObj.drawStringWithShadow(KillAura.target.getName(), x + 167, y - 5.0f, -1);
                if(Client.settingsManager.getSettingByName("HeadRener ").getValBoolean()) {
                	if(target instanceof EntityPlayer) {
                    	drawHead(Objects.requireNonNull(getMC().getConnection()).getPlayerInfo(KillAura.target.getUniqueID())
        						.getLocationSkin(), (int) x + 127, (int) y - 8);
                    	//RenderUtils.renderItem(KillAura.target.getHeldItem(EnumHand.OFF_HAND), (int) x + 211,
        						//(int) (y) - (int) (2 / 6.0D) - 8);
                    } 
                }                               
       }
	
	public void drawHead(ResourceLocation skin, int width, int height) {
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		getMC().getTextureManager().bindTexture(skin);
		Gui.drawScaledCustomSizeModalRect(width, height, 8.0f, 8.0f, 8, 8, 37, 37, 64.0f, 64.0f);
	}
        
  

  public static synchronized void faceEntity(Entity entity) {
	  
      final float[] rotations = getRotationsNeeded(entity);
      if(target != null) {
    	  if (rotations != null) {
              Minecraft mc = Minecraft.getMinecraft();
              mc.player.connection.sendPacket(new CPacketPlayer.Rotation(rotations[0], rotations[1], mc.player.onGround));    
          }
      }
  }

  public static synchronized void faceEntity2(Entity entity) {
	  
      final float[] rotations = getRotationsNeeded(entity);

      if (rotations != null) {
          Minecraft mc = Minecraft.getMinecraft();
          mc.getMinecraft().player.rotationYaw = rotations[0];
          mc.getMinecraft().player.rotationPitch = rotations[1] + 10;// 14
      } 
  }
  

  public static float[] getRotationsNeeded(Entity entity) {
	  
       if (entity == null) {
           return null;
       }

       final double diffX = entity.posX - getMC().getMinecraft().player.posX;
       final double diffZ = entity.posZ - getMC().getMinecraft().player.posZ;
       double diffY;

       if (entity instanceof EntityPlayer) {
           final EntityPlayer entityLivingBase = (EntityPlayer) entity;
           diffY = entityLivingBase.posY + entityLivingBase.getEyeHeight() - (getMC().getMinecraft().player.posY + getMC().getMinecraft().player.getEyeHeight());
       } else if(entity instanceof EntityMob){
           final EntityMob entityLivingBase = (EntityMob) entity;
           diffY = (entity.boundingBox.minY + entity.boundingBox.maxY) / 1.0D - (getMC().getMinecraft().player.posY + getMC().getMinecraft().player.getEyeHeight());
       }else{
           final EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
           diffY = (entity.boundingBox.minY + entity.boundingBox.maxY) / 1.0D - (getMC().getMinecraft().player.posY + getMC().getMinecraft().player.getEyeHeight());
      }

       final double dist = MathHelper.sqrt(diffX * diffX + diffZ * diffZ);
       final float yaw = (float) (Math.atan2(diffZ, diffX) * 180.0D / Math.PI) -95.0F;
       final float pitch = (float) -(Math.atan2(diffY, dist) * 180.0D / Math.PI);
       return new float[] {
    		    getMC().getMinecraft().player.rotationYaw + MathHelper.wrapDegrees(yaw - getMC().getMinecraft().player.rotationYaw), getMC().getMinecraft().player.rotationPitch + MathHelper.wrapDegrees(pitch - getMC().getMinecraft().player.rotationPitch)
       };
  }

  final boolean invis = Client.settingsManager.getSettingByName("Invisible ").getValBoolean();
  static boolean checks(Entity en) {
	  String mode = Client.settingsManager.getSettingByName("SpeedSelection").getValString();
	  final boolean invis = Client.settingsManager.getSettingByName("Invisible ").getValBoolean();
	
      if(!(en instanceof EntityLivingBase)) {
          return false;
      }
      
      Minecraft mc = Minecraft.getMinecraft();
      if (en == mc.player) {
          return false;
      }
      if (en instanceof EntityPlayer && !Client.settingsManager.getSettingByName("Players ").getValBoolean())
          return false;
      if (en instanceof EntityAnimal && !Client.settingsManager.getSettingByName("Mobs ").getValBoolean())
          return false;
      if (en instanceof EntityMob && !Client.settingsManager.getSettingByName("Mobs ").getValBoolean())
          return false;
      if (en instanceof EntityVillager && !Client.settingsManager.getSettingByName("Mobs ").getValBoolean())
          return false; 
      if (en.isDead) {
          return false;
      }
      if (en.getDistanceToEntity(mc.player) > 5) {
         return false;
      }
      
      //mode.equalsIgnoreCase("Animate")
      if(mode.equalsIgnoreCase("DefaultPvP")) {
    	  if (mc.player.getCooledAttackStrength(1) < 1f) {
    	         return false;
    	      }
      }
      if(mode.equalsIgnoreCase("SpeedAura")) {
    	  if (mc.player.getCooledAttackStrength((float) Client.settingsManager.getSettingByName("SpeedAura").getValDouble()) < 1f) {
    	         return false;
    	    }
      }
      if (invis) {
          if (en.isInvisibleToPlayer(mc.player)) {
              return true;
           }
       if (!invis && en.isInvisibleToPlayer(mc.player)) {
          return false;
       }
     }
       return true;
  }
  
  private void rotationsV2(final Entity e) {
      double angle = 0.0;
      final float distance = Minecraft.getMinecraft().player.getDistanceToEntity(e);
      final double xDif = e.posX - Minecraft.getMinecraft().player.posX;
      final double zDif = e.posZ - Minecraft.getMinecraft().player.posZ;
      final double yDif = e.posY - Minecraft.getMinecraft().player.posY + distance;
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
      Minecraft.getMinecraft().player.rotationPitch = (float)d2 + 55.0f;
      Minecraft.getMinecraft().player.rotationYaw = (float)angle;
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
  
  private static EntityLivingBase getClosest(double range) {
      EntityLivingBase target = null;
      for (Entity ent : getMC().world.loadedEntityList) {
	      Entity entity = (Entity) ent;
	      if (entity instanceof EntityLivingBase) {
	          EntityLivingBase player = (EntityLivingBase) entity;
	          if (checks(player)) {
		          target = player;
	          }
           }
		}
		return target;
	}
  }