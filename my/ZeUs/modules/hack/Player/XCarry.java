package my.ZeUs.modules.hack.Player;

import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.TimeUtil;
import my.ZeUs.modules.Module;
import net.minecraft.network.play.client.CPacketCloseWindow;
import shwepss.event.EventPacketRecieve;
import shwepss.event.EventSendPacket;
import shwepss.eventapi.EventManager;
import shwepss.eventapi.EventTarget;

public class XCarry extends Module{
    
    public XCarry() {
        super("XCarry", Keyboard.KEY_NONE, Category.Player);
    }
    
    @EventTarget
    public void onPacket(EventPacketRecieve event) {
        if (event.getPacket() instanceof CPacketCloseWindow) {
            CPacketCloseWindow cPacketCloseWindow = (CPacketCloseWindow)event.getPacket();
            if (cPacketCloseWindow.windowId == 0) {
                event.setCancelled(true);
            }
        }
    }
    
    public void onEnable() {
        EventManager.register(this);
    }

    public void onDisable() {
        EventManager.unregister(this);
    }
}
