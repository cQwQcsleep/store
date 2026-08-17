package com.intellij.util.containers;

import com.intellij.util.containers.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class ThreadLocalRandom {
    private static final ThreadLocal<Tlr> tlr = ThreadLocal.withInitial(new Supplier() { // from class: wde
        @Override // java.util.function.Supplier
        public final Object get() {
            return ThreadLocalRandom.a();
        }
    });
    private static final AtomicInteger probeGenerator = new AtomicInteger();
    private static final AtomicLong seeder = new AtomicLong();

    public static final class Tlr {
        int threadLocalRandomProbe;
        long threadLocalRandomSeed;
    }

    public static /* synthetic */ Tlr a() {
        return new Tlr();
    }

    public static int advanceProbe(int i) {
        int i2 = i ^ (i << 13);
        int i3 = i2 ^ (i2 >>> 17);
        int i4 = i3 ^ (i3 << 5);
        tlr.get().threadLocalRandomProbe = i4;
        return i4;
    }

    public static int getProbe() {
        return tlr.get().threadLocalRandomProbe;
    }

    public static void localInit() {
        int iAddAndGet = probeGenerator.addAndGet(-1640531527);
        if (iAddAndGet == 0) {
            iAddAndGet = 1;
        }
        long jMix64 = mix64(seeder.getAndAdd(-4942790177534073029L));
        Tlr tlr2 = tlr.get();
        tlr2.threadLocalRandomProbe = iAddAndGet;
        tlr2.threadLocalRandomSeed = jMix64;
    }

    private static long mix64(long j) {
        long j2 = (j ^ (j >>> 33)) * (-49064778989728563L);
        long j3 = (j2 ^ (j2 >>> 33)) * (-4265267296055464877L);
        return j3 ^ (j3 >>> 33);
    }
}
