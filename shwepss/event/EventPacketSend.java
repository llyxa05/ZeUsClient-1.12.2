package shwepss.event;
import net.minecraft.network.Packet;
import shwepss.eventapi.events.callables.EventCancellable;

public class EventPacketSend extends EventCancellable {
   public Packet packet;

   public EventPacketSend(Packet packet) {
      this.packet = packet;
   }
}
