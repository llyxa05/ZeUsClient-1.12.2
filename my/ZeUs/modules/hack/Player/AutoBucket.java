package my.ZeUs.modules.hack.Player;

import my.ZeUs.Utils.Category;
import my.ZeUs.modules.Module;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import shwepss.event.EventPostUpdate;
import shwepss.eventapi.EventManager;
import shwepss.eventapi.EventTarget;

public class AutoBucket extends Module {

    public AutoBucket() {
        super("AutoBucket", 0, Category.Player);
    }
    
    @Override
    public void onEnable() {
    	EventManager.register(this);
        super.onDisable();
    }
    
    private long last = 0L;
    @EventTarget
    public void onUpdate(EventPostUpdate e) {
            if (this.getMC().player.fallDistance >= 6F && System.currentTimeMillis() - this.last > 90L) {
                Vec3d posVec = this.getMC().player.getPositionVector();
                RayTraceResult result = this.getMC().world.rayTraceBlocks(posVec,
                        posVec.addVector(0.0D, -8.329999923706055D, 0.0D), true, true, false);
                if (result != null && result.typeOfHit == RayTraceResult.Type.BLOCK) {
                    EnumHand hand = EnumHand.MAIN_HAND;
                    if (this.getMC().player.getHeldItemOffhand().getItem() == Items.WATER_BUCKET) {
                        hand = EnumHand.OFF_HAND;
                    } else if (this.getMC().player.getHeldItemMainhand().getItem() != Items.WATER_BUCKET) {
                        for (int i = 0; i < 9; i++) {
                            if (this.getMC().player.inventory.getStackInSlot(i).getItem() == Items.WATER_BUCKET) {
                                this.getMC().player.inventory.currentItem = i;
                                this.getMC().player.connection.sendPacket(new CPacketPlayer.Rotation(0, 90, false));
                                this.last = System.currentTimeMillis();

                                return;
                            }
                        }
                        return;
                    }

                    this.getMC().player.rotationPitch = 90.0F;
                    this.getMC().playerController.processRightClick((EntityPlayer) this.getMC().player, (World) this.getMC().world,
                            hand);
                    this.last = System.currentTimeMillis();
                }
            }
        }


    @Override
    public void onDisable() {
    	EventManager.unregister(this);
        super.onDisable();
    }
}
