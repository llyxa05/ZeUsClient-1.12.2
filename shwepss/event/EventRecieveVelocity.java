package shwepss.event;

import net.minecraft.entity.Entity;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import shwepss.eventapi.events.callables.EventCancellable;

public class EventRecieveVelocity extends EventCancellable
{
    private Entity entity;
    private SPacketEntityVelocity packetIn;
    
    public EventRecieveVelocity(final Entity entity, final SPacketEntityVelocity packetIn) {
        this.entity = entity;
        this.packetIn = packetIn;
    }
    
    public Entity getEntity() {
        return this.entity;
    }
    
    public SPacketEntityVelocity getPacket() {
        return this.packetIn;
    }
}
