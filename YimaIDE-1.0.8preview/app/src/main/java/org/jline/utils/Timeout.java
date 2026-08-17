package org.jline.utils;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public class Timeout {
    private long cur = 0;
    private long end = Long.MAX_VALUE;
    private final long timeout;

    public Timeout(long j) {
        this.timeout = j;
    }

    public boolean elapsed() {
        if (this.timeout > 0) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            this.cur = jCurrentTimeMillis;
            if (this.end == Long.MAX_VALUE) {
                this.end = this.timeout + jCurrentTimeMillis;
            }
            if (jCurrentTimeMillis >= this.end) {
                return true;
            }
        }
        return false;
    }

    public boolean isInfinite() {
        return this.timeout <= 0;
    }

    public long timeout() {
        long j = this.timeout;
        return j > 0 ? Math.max(1L, this.end - this.cur) : j;
    }
}
