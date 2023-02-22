package my.ZeUs.Utils;

public class TimeUtil {

	    private long previousTime;
		private long lastMS;
	    
		public TimeUtil() {
	        this.lastMS = 0L;
	        this.reset();
	    }
	    
	    public boolean check(final float milliseconds) {
	        return this.getCurrentMS() - this.previousTime >= milliseconds;
	    }
	    
	    public short convert(final float perSecond) {
	        return (short)(1000.0f / perSecond);
	    }
	    
	    public long get() {
	        return this.getCurrentMS() - this.previousTime;
	    }
	    
	    private long getCurrentMS() {
	        return System.nanoTime() / 1000000L;
	    }
	    
	    public boolean hasReached(final double milliseconds) {
	        return this.getCurrentMS() - this.lastMS >= milliseconds;
	    }
	    
	    public void reset() {
	        this.lastMS = this.getCurrentMS();
	    }
	    
	    public boolean delay(final float milliSec) {
	        return this.getTime() - this.lastMS >= milliSec;
	    }
	    
	    public long getTime() {
	        return System.nanoTime() / 1000000L;
	    }
}

