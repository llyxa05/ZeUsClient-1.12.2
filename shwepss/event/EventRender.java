package shwepss.event;

import shwepss.eventapi.events.Event;

public class EventRender implements Event
{
    public int particalTicks;
    
    public EventRender(final int particalTicks) {
        this.particalTicks = particalTicks;
    }
}
