package com.android.tools.r8.internal;

import com.android.tools.r8.internal.De0;
import defpackage.db3;
import java.util.Arrays;
import java.util.Collection;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class De0 {
    public static Stream a(final Stream... streamArr) {
        Object[] objArrCopyOf = new Object[streamArr.length];
        int length = streamArr.length;
        int iCharacteristics = 336;
        int i = 0;
        int i2 = 0;
        boolean zIsParallel = false;
        long j = 0;
        while (i < length) {
            Stream stream = streamArr[i];
            zIsParallel |= stream.isParallel();
            Spliterator spliterator = stream.spliterator();
            spliterator.getClass();
            int i3 = i2 + 1;
            if (objArrCopyOf.length < i3) {
                objArrCopyOf = Arrays.copyOf(objArrCopyOf, AbstractC2981wu.a(objArrCopyOf.length, i3));
            }
            objArrCopyOf[i2] = spliterator;
            iCharacteristics &= spliterator.characteristics();
            long jEstimateSize = spliterator.estimateSize();
            long j2 = j + jEstimateSize;
            j = (((jEstimateSize ^ j) > 0L ? 1 : ((jEstimateSize ^ j) == 0L ? 0 : -1)) < 0) | ((j ^ j2) >= 0) ? j2 : ((j2 >>> 63) ^ 1) + Long.MAX_VALUE;
            i++;
            i2 = i3;
        }
        return (Stream) StreamSupport.stream(AbstractC1165be.a(AbstractC0551Hu.b(i2, objArrCopyOf).spliterator(), new Function() { // from class: eb3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return De0.a((Spliterator) obj);
            }
        }, iCharacteristics, j), zIsParallel).onClose(new Runnable() { // from class: fb3
            @Override // java.lang.Runnable
            public final void run() {
                De0.b(streamArr);
            }
        });
    }

    public static void b(Stream[] streamArr) {
        for (Stream stream : streamArr) {
            stream.close();
        }
    }

    public static Stream a(Iterable iterable) {
        if (iterable instanceof Collection) {
            return ((Collection) iterable).stream();
        }
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    public static /* synthetic */ Spliterator a(Spliterator spliterator) {
        return spliterator;
    }

    public static Stream a(Stream stream, Be0 be0) {
        stream.getClass();
        be0.getClass();
        boolean zIsParallel = stream.isParallel();
        Spliterator spliterator = stream.spliterator();
        if (!spliterator.hasCharacteristics(16384)) {
            return (Stream) StreamSupport.stream(new Ae0(spliterator.estimateSize(), spliterator.characteristics() & 80, Spliterators.iterator(spliterator), be0), zIsParallel).onClose(new db3(stream));
        }
        return (Stream) StreamSupport.stream(new C3212ze0(spliterator, 0L, be0), zIsParallel).onClose(new db3(stream));
    }

    public static Stream a(Stream stream, Stream stream2, BiFunction biFunction) {
        stream.getClass();
        stream2.getClass();
        biFunction.getClass();
        boolean z = stream.isParallel() || stream2.isParallel();
        Spliterator spliterator = stream.spliterator();
        Spliterator spliterator2 = stream2.spliterator();
        return (Stream) ((Stream) StreamSupport.stream(new C3126ye0(Math.min(spliterator.estimateSize(), spliterator2.estimateSize()), spliterator.characteristics() & spliterator2.characteristics() & 80, Spliterators.iterator(spliterator), Spliterators.iterator(spliterator2), biFunction), z).onClose(new db3(stream))).onClose(new db3(stream2));
    }
}
