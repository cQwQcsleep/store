package com.intellij.util.io.pagecache;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class FilePageCacheStatistics {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private volatile int totalClosedStoragesReclaimed;
    private final AtomicLong totalNativeBytesAllocated = new AtomicLong();
    private final AtomicLong totalNativeBytesReclaimed = new AtomicLong();
    private final AtomicLong totalHeapBytesAllocated = new AtomicLong();
    private final AtomicLong totalHeapBytesReclaimed = new AtomicLong();
    private volatile long nativeBytesCurrentlyUsed = 0;
    private volatile long heapBytesCurrentlyUsed = 0;
    private final AtomicInteger totalPagesAllocated = new AtomicInteger();
    private final AtomicInteger totalPagesReclaimed = new AtomicInteger();
    private final AtomicInteger totalPagesHandedOver = new AtomicInteger();
    private final AtomicLong totalBytesRead = new AtomicLong();
    private final AtomicLong totalBytesWritten = new AtomicLong();
    private final AtomicLong totalPagesWritten = new AtomicLong();
    private final AtomicLong totalPagesRequested = new AtomicLong();
    private final AtomicLong totalBytesRequested = new AtomicLong();
    private final AtomicLong totalPagesRequestsNs = new AtomicLong();
    private final AtomicLong totalPagesReadNs = new AtomicLong();
    private final AtomicLong totalPagesWriteNs = new AtomicLong();
    private final AtomicInteger pageAllocationsWaited = new AtomicInteger();
    private volatile long housekeeperTurnsDone = 0;
    private volatile long housekeeperTurnsSkipped = 0;
    private volatile long housekeeperTotalTimeNs = 0;

    public void cacheMaintenanceTurnDone(long j) {
        this.housekeeperTurnsDone++;
        this.housekeeperTotalTimeNs += j;
    }

    public void cacheMaintenanceTurnSkipped(long j) {
        this.housekeeperTurnsSkipped++;
        this.housekeeperTotalTimeNs += j;
    }

    public void closedStoragesReclaimed(int i) {
        this.totalClosedStoragesReclaimed += i;
    }

    public void pageReclaimedHeap(int i) {
        this.totalHeapBytesReclaimed.addAndGet(i);
        this.totalPagesReclaimed.incrementAndGet();
    }

    public void pageReclaimedNative(int i) {
        this.totalNativeBytesReclaimed.addAndGet(i);
        this.totalPagesReclaimed.incrementAndGet();
    }

    public String toString() {
        return "Statistics[pages: {requested: " + this.totalPagesRequested + ", allocated: " + this.totalPagesAllocated + ", flushed: " + this.totalPagesWritten + ", reclaimed: " + this.totalPagesReclaimed + "}, nativeBytes: {allocated: " + this.totalNativeBytesAllocated + ", reclaimed: " + this.totalNativeBytesReclaimed + ", current: " + this.nativeBytesCurrentlyUsed + "}, heapBytes: {allocated: " + this.totalHeapBytesAllocated + ", reclaimed: " + this.totalHeapBytesReclaimed + ", current: " + this.heapBytesCurrentlyUsed + "}, pages handed over: " + this.totalPagesHandedOver + ", pages allocation waited: " + this.pageAllocationsWaited + ", bytes: {requested: " + this.totalBytesRequested + ", read: " + this.totalBytesRead + ", written=" + this.totalBytesWritten + "}, housekeeperTurns: {done: " + this.housekeeperTurnsDone + ", skipped: " + this.housekeeperTurnsSkipped + "}, closedStoragesReclaimed: " + this.totalClosedStoragesReclaimed + ']';
    }

    public int totalPageAllocationsWaited() {
        return this.pageAllocationsWaited.get();
    }

    public int totalPagesAllocated() {
        return this.totalPagesAllocated.get();
    }

    public int totalPagesReclaimed() {
        return this.totalPagesReclaimed.get();
    }
}
