package shwepss.event;

import shwepss.eventapi.events.Event;

public class EventKeyPress implements Event{
	int key;
	public EventKeyPress(int key) {
		this.key = key;
	}
	public int getKey() {
		return key;
	}
}
