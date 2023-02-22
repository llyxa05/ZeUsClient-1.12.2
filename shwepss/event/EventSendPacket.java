package shwepss.event;

import net.minecraft.network.Packet;
import shwepss.eventapi.events.Event;
import shwepss.eventapi.events.callables.EventCancellable;

public class EventSendPacket extends EventCancellable {
	   public Packet packet;

	   public EventSendPacket(Packet packet) {
	      this.packet = packet;
	   }
	   
	   public Packet getPacket() {
	        return this.packet;
	    }
}
