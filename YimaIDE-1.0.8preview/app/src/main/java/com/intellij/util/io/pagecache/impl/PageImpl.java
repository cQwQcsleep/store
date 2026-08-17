package com.intellij.util.io.pagecache.impl;

import com.intellij.util.io.pagecache.PageUnsafe;
import defpackage.qwa;
import defpackage.rwa;
import java.io.Flushable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public abstract class PageImpl implements PageUnsafe, Flushable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final AtomicIntegerFieldUpdater<PageImpl> STATE_UPDATER = AtomicIntegerFieldUpdater.newUpdater(PageImpl.class, "statePacked");
    private static final AtomicIntegerFieldUpdater<PageImpl> TOKENS_UPDATER = AtomicIntegerFieldUpdater.newUpdater(PageImpl.class, "tokensOfUsefulness");
    private volatile Object auxDebugData;
    protected ByteBuffer data;
    private final transient long offsetInFile;
    private final int pageIndex;
    private final int pageSize;
    private volatile int statePacked;
    private volatile int tokensOfUsefulness;
    private int tokensOfUsefulnessLocal;

    private static int packState(int i, int i2) {
        return (i << 24) | i2;
    }

    private static int unpackState(int i) {
        return i >> 24;
    }

    private static int unpackUsageCount(int i) {
        return i & 16777215;
    }

    public int addTokensOfUsefulness(int i) {
        int i2;
        int i3;
        do {
            i2 = this.tokensOfUsefulness;
            i3 = i2 + i;
            if (i3 < 0) {
                i3 = Integer.MAX_VALUE;
            }
        } while (!TOKENS_UPDATER.compareAndSet(this, i2, i3));
        return i3;
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        release();
    }

    public int decayTokensOfUsefulness(int i, int i2) {
        int i3;
        int i4;
        do {
            i3 = this.tokensOfUsefulness;
            i4 = (i3 * i) / i2;
        } while (!TOKENS_UPDATER.compareAndSet(this, i3, i4));
        return i4;
    }

    public ByteBuffer detachTombstoneBuffer() {
        if (!isPreTombstone()) {
            s22.a("Bug: only PRE_TOMBSTONES could detach buffer. ", this);
            return null;
        }
        ByteBuffer byteBuffer = this.data;
        if (byteBuffer != null) {
            this.data = null;
            return byteBuffer;
        }
        s22.a("Bug: buffer already detached, .data is null. ", this);
        return null;
    }

    public void entomb() {
        if (isDirty()) {
            s22.a("Bug: page must be !dirty to be TOMBSTONE-ed, but: ", this);
            return;
        }
        int i = this.statePacked;
        int iUnpackState = unpackState(i);
        int iUnpackUsageCount = unpackUsageCount(i);
        if (iUnpackUsageCount > 0) {
            qwa.a("Bug: page.usageCount(=", iUnpackUsageCount, ") must be 0. page: ", this);
            return;
        }
        if (iUnpackState != 4) {
            qwa.a("Bug: page.state(=", iUnpackState, ") be PRE_TOMBSTONE. ", this);
            return;
        }
        if (STATE_UPDATER.compareAndSet(this, i, packState(5, 0))) {
            return;
        }
        s22.a("Bug: somebody interferes with PRE_TOMBSTONE->TOMBSTONE transition. ", this);
    }

    @Override // java.io.Flushable
    public abstract void flush() throws IOException;

    public boolean inState(int i) {
        return unpackState(this.statePacked) == i;
    }

    public boolean isAboutToUnmap() {
        return inState(3);
    }

    public abstract boolean isDirty();

    public boolean isPreTombstone() {
        return inState(4);
    }

    public boolean isTombstone() {
        return inState(5);
    }

    public boolean isUsable() {
        return inState(2);
    }

    public int localTokensOfUsefulness() {
        return this.tokensOfUsefulnessLocal;
    }

    public ByteBuffer pageBufferUnchecked() {
        return this.data;
    }

    public int pageIndex() {
        return this.pageIndex;
    }

    public int pageSize() {
        return this.pageSize;
    }

    public void release() {
        int i;
        int iUnpackState;
        int iUnpackUsageCount;
        do {
            i = this.statePacked;
            iUnpackState = unpackState(i);
            iUnpackUsageCount = unpackUsageCount(i);
            if (iUnpackState != 2 && iUnpackState != 3) {
                rwa.a("Bug: .release() must be called on {USABLE|ABOUT_TO_UNMAP} page only, but .state[=", iUnpackState, "]");
                return;
            } else {
                if (iUnpackUsageCount == 0) {
                    x01.a("Bug: can't .release() page with usageCount=0 -- unpaired .acquire()/.release() calls?");
                    return;
                }
            }
        } while (!STATE_UPDATER.compareAndSet(this, i, packState(iUnpackState, iUnpackUsageCount - 1)));
        addTokensOfUsefulness(iUnpackUsageCount * 8);
    }

    public String toString() {
        String str;
        int i = this.statePacked;
        StringBuilder sb = new StringBuilder("Page[#");
        sb.append(this.pageIndex);
        sb.append(", size: ");
        sb.append(this.pageSize);
        sb.append("b, offsetInFile: ");
        sb.append(this.offsetInFile);
        sb.append("b]{state: ");
        sb.append(unpackState(i));
        sb.append(", inUse: ");
        sb.append(unpackUsageCount(i));
        sb.append("}");
        if (this.auxDebugData != null) {
            str = ", aux: " + this.auxDebugData;
        } else {
            str = "";
        }
        sb.append(str);
        return sb.toString();
    }

    public int tokensOfUsefulness() {
        return this.tokensOfUsefulness;
    }

    public abstract boolean tryFlush() throws IOException;

    public boolean tryMoveTowardsPreTombstone(boolean z) {
        int i;
        do {
            i = this.statePacked;
            int iUnpackState = unpackState(i);
            int iUnpackUsageCount = unpackUsageCount(i);
            if (iUnpackState == 0) {
                if (z) {
                    if (STATE_UPDATER.compareAndSet(this, i, packState(4, 0))) {
                        return true;
                    }
                }
                return false;
            }
            if (iUnpackState == 1) {
                break;
            }
            if (iUnpackState != 2) {
                if (iUnpackState != 3) {
                    if (iUnpackState == 4 || iUnpackState == 5) {
                        return false;
                    }
                    qwa.a("Code bug: unknown state ", iUnpackState, ": ", this);
                    return false;
                }
                if (iUnpackUsageCount > 0) {
                    qwa.a("Page[ABOUT_TO_UNMAP].usageCount=", iUnpackUsageCount, " -- must be 0. ", this);
                    return false;
                }
                if (STATE_UPDATER.compareAndSet(this, i, packState(4, 0))) {
                    return true;
                }
                return false;
            }
            if (iUnpackUsageCount > 0) {
                return false;
            }
        } while (STATE_UPDATER.compareAndSet(this, i, packState(3, 0)));
        return false;
    }

    public void updateLocalTokensOfUsefulness(int i) {
        this.tokensOfUsefulnessLocal = i;
    }

    public int usageCount() {
        return unpackUsageCount(this.statePacked);
    }
}
