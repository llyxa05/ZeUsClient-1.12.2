package my.ZeUs.Utils;

public class Timer2
{
    private long previousTime;
    
    public Timer2() {
        this.previousTime = -1L;
    }
    
    public boolean check(final double d) {
        return this.getCurrentMS() - this.previousTime >= d;
    }
    
    public void reset() {
        this.previousTime = this.getCurrentMS();
    }
    
    public short convert(final float perSecond) {
        return (short)(1000.0f / perSecond);
    }
    
    public long get() {
        return this.getCurrentMS() - this.previousTime;
    }
    
    protected long getCurrentMS() {
        return System.nanoTime() / 1000000L;
    }
}
