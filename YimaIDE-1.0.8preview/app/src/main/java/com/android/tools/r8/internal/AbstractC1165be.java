package com.android.tools.r8.internal;

import java.util.Comparator;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

/* JADX INFO: renamed from: com.android.tools.r8.internal.be, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1165be {
    public static C1079ae a(Spliterator spliterator, Function function, int i, long j) {
        if (!((i & 16384) == 0)) {
            w01.a("flatMap does not support SUBSIZED characteristic");
            return null;
        }
        if (!((i & 4) == 0)) {
            w01.a("flatMap does not support SORTED characteristic");
            return null;
        }
        spliterator.getClass();
        function.getClass();
        return new C1079ae(null, spliterator, function, i, j);
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [java.util.Spliterator$OfInt] */
    public static C0949Xd a(int i, int i2, IntFunction intFunction, Comparator comparator) {
        if (comparator != null && (i2 & 4) == 0) {
            j2d.a();
            return null;
        }
        return new C0949Xd(IntStream.range(0, i).spliterator(), intFunction, i2, comparator);
    }

    public static C0897Vd a(Spliterator spliterator, Function function) {
        spliterator.getClass();
        function.getClass();
        return new C0897Vd(spliterator, function);
    }
}
