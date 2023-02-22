package shwepss.event;

import shwepss.eventapi.events.Event;

public final class EventKeyPressed
implements Event {
    private final int eventKey;

    public EventKeyPressed(int eventKey) {
        this.eventKey = eventKey;
    }

    public int getEventKey() {
        return this.eventKey;
    }
}

