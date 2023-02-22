package shwepss.event;

import net.minecraft.network.Packet;
import shwepss.eventapi.events.Event;

public class EventReadPacket implements Event {
    private Packet<?> packet;

    public EventReadPacket(Packet<?> packet){
        this.packet = packet;
    }

    public Packet<?> getPacket() {
        return packet;
    }

    public void setPacket(Packet<?> packet) {
        this.packet = packet;
    }
}
