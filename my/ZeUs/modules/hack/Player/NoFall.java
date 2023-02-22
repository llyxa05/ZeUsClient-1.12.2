package my.ZeUs.modules.hack.Player;

import net.minecraft.network.play.client.*;
import my.ZeUs.Utils.Category;
import my.ZeUs.modules.Module;
import net.minecraft.network.*;

public class NoFall extends Module
{
    public NoFall() {
        super("NoFall", 0, Category.Player);
    }
    
    public void onTick() {
        if (this.getMC().player.fallDistance > 2.0f) {
            this.getMC().player.connection.sendPacket(new CPacketPlayer(true));
        }
    }
}
