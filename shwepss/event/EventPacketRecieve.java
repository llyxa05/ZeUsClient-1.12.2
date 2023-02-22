package shwepss.event;

import net.minecraft.network.Packet;
import shwepss.eventapi.events.Event;
import shwepss.eventapi.events.callables.EventCancellable;

public class EventPacketRecieve extends EventCancellable implements Event{
    public Packet packet;
    
    public EventPacketRecieve(final Packet packet) {
        this.packet = packet;
    }
    
    public Packet getPacket() {
        return this.packet;
    }
}
