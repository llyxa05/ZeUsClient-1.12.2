package my.ZeUs.modules.hack.Player;

import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.Client;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.modules.Module;
import my.ZeUs.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import shwepss.event.EventPreUpdate;
import shwepss.eventapi.EventManager;
import shwepss.eventapi.EventTarget;

public class BedFucker extends Module {
	
    public BedFucker() {
    	super("BedFucker", Keyboard.KEY_N, Category.Player);
    }
    
    @Override
    public void onEnable(){
        EventManager.register(this);
        WSoundEvents.playButtonClick();
    }
    
    @Override
    public void setup() {
        Client.settingsManager.rSetting(new Setting("BreakRange", this, 5, 1, 6, false));
    }
    
    @EventTarget
    public void onPreUpdate(EventPreUpdate e) {
        Minecraft mc = Minecraft.getMinecraft();
        int playerX = (int) mc.player.posX;  
        int playerZ = (int) mc.player.posZ;
        int playerY = (int) mc.player.posY;
        //get players position
        for (double y = playerY -Client.settingsManager.getSettingByName("BreakRange").getValDouble(); y <= playerY + Client.settingsManager.getSettingByName("BreakRange").getValDouble(); y++) {         //loop through xyz
            for (double x = playerX -Client.settingsManager.getSettingByName("BreakRange").getValDouble(); x <= playerX + Client.settingsManager.getSettingByName("BreakRange").getValDouble(); x++) {        //loop through xyz
                for (double z = playerZ -Client.settingsManager.getSettingByName("BreakRange").getValDouble(); z <= playerZ + Client.settingsManager.getSettingByName("BreakRange").getValDouble(); z++) {      //loop through xyz
                    BlockPos pos = new BlockPos(x, y, z);  // create Blockpos to check the blocks material
                    if (mc.world.getBlockState(pos).getBlock() == Blocks.BED) {  //if blockmaterial equals bed then ---> destroy block
                          //reducing autobans, because your mining the block very fast
                        
                        destroyBlock(pos);  //destroy Block
                        
                        
                    }
                }
            }
        }
    }
    private void destroyBlock(BlockPos blockPos) {  //hand over the blockpos which you want to destroy
        Minecraft mc = Minecraft.getMinecraft();
        mc.player.swingArm(EnumHand.MAIN_HAND); //prevent a noswing flag, serversided swing, if you want to swing the hand clientsided just do mc.player.swingItem();
        mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, blockPos, EnumFacing.UP)); //start destroying the block at the specific blockpos
        mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockPos, EnumFacing.UP)); //stop destroying the block at the specific blockpos
    }
    
    @Override
    public void onDisable() {
    	EventManager.unregister(this);
        WSoundEvents.grass();
    }
}
