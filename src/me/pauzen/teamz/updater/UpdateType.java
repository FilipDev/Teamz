package me.pauzen.teamz.updater;

public enum UpdateType {

    TICK(1L),
    SECOND(20L),
    MINUTE(1200L),
    TEM_MINUTES(12000),;

    private long ticks;
    private long lastTime;

    UpdateType(long ticks) {
        this.ticks = ticks * 50L;
        this.lastTime = System.currentTimeMillis();
    }

    public long getTicks() {
        return ticks;
    }

    protected boolean elapsed() {
        if (elapsed(this.lastTime, this.ticks)) {
            this.lastTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }

    public static boolean elapsed(long time, long required) {
        return System.currentTimeMillis() - time >= required;
    }
}
