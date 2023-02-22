package shwepss.event;
import net.minecraft.client.gui.ScaledResolution;
import shwepss.eventapi.events.Event;
public class EventRenderGui implements Event {
    private ScaledResolution resolution;

    public void fire(ScaledResolution resolution) {
        this.resolution = resolution;
        
    }

    public ScaledResolution getResolution() {
        return this.resolution;
    }
}

