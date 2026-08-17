package androidx.compose.runtime.internal;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0083@\u0018\u0000 ,2\u00020\u0001:\u0001,B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\t\b\u0016¢\u0006\u0004\b\u0004\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\bH\u0086\b¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\fH\u0086\b¢\u0006\u0004\b\r\u0010\u000eJ+\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u0012H\u0086\b\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0004\b\u0013\u0010\u0014J\u0018\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u0010H\u0086\b¢\u0006\u0004\b\u0017\u0010\u0018J$\u0010\u0019\u001a\u00020\u00102\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100\u001bH\u0082\b¢\u0006\u0004\b\u001c\u0010\u001dJ\u001f\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020\u0010H\u0002¢\u0006\u0004\b \u0010!J\u000f\u0010%\u001a\u00020&H\u0016¢\u0006\u0004\b'\u0010(J\u0013\u0010)\u001a\u00020\b2\b\u0010*\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010+\u001a\u00020\u0010HÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0016\u001a\u00020\u0010*\u00020\u00108Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0019\u0010\u001f\u001a\u00020\u0010*\u00020\u00108Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b$\u0010#\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006-"}, d2 = {"Landroidx/compose/runtime/internal/AtomicAwaitersCount;", "", "value", "Landroidx/compose/runtime/internal/AtomicInt;", "constructor-impl", "(Landroidx/compose/runtime/internal/AtomicInt;)Landroidx/compose/runtime/internal/AtomicInt;", "()Landroidx/compose/runtime/internal/AtomicInt;", "hasAwaiters", "", "hasAwaiters-impl", "(Landroidx/compose/runtime/internal/AtomicInt;)Z", "incrementVersionAndResetCount", "", "incrementVersionAndResetCount-impl", "(Landroidx/compose/runtime/internal/AtomicInt;)V", "incrementCountAndGetVersion", "", "ifFirstAwaiter", "Lkotlin/Function0;", "incrementCountAndGetVersion-impl", "(Landroidx/compose/runtime/internal/AtomicInt;Lkotlin/jvm/functions/Function0;)I", "decrementCount", "version", "decrementCount-impl", "(Landroidx/compose/runtime/internal/AtomicInt;I)V", "update", "calculation", "Lkotlin/Function1;", "update-impl", "(Landroidx/compose/runtime/internal/AtomicInt;Lkotlin/jvm/functions/Function1;)I", "pack", "count", "pack-impl", "(Landroidx/compose/runtime/internal/AtomicInt;II)I", "getVersion-impl", "(Landroidx/compose/runtime/internal/AtomicInt;I)I", "getCount-impl", "toString", "", "toString-impl", "(Landroidx/compose/runtime/internal/AtomicInt;)Ljava/lang/String;", "equals", "other", "hashCode", "Companion", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
@JvmInline
final class AtomicAwaitersCount {
    private static final int COUNT_BITS = 27;
    private static final int VERSION_BITS = 4;
    private final AtomicInt value;

    private /* synthetic */ AtomicAwaitersCount(AtomicInt atomicInt) {
        this.value = atomicInt;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ AtomicAwaitersCount m2513boximpl(AtomicInt atomicInt) {
        return new AtomicAwaitersCount(atomicInt);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static AtomicInt m2514constructorimpl() {
        return m2515constructorimpl(new AtomicInt(0));
    }

    /* JADX INFO: renamed from: decrementCount-impl, reason: not valid java name */
    public static final void m2516decrementCountimpl(AtomicInt atomicInt, int i) {
        int i2;
        do {
            i2 = atomicInt.get();
        } while (!atomicInt.compareAndSet(i2, ((i2 >>> 27) & 15) == i ? i2 - 1 : i2));
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2517equalsimpl(AtomicInt atomicInt, Object obj) {
        return (obj instanceof AtomicAwaitersCount) && Intrinsics.areEqual(atomicInt, ((AtomicAwaitersCount) obj).getValue());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2518equalsimpl0(AtomicInt atomicInt, AtomicInt atomicInt2) {
        return Intrinsics.areEqual(atomicInt, atomicInt2);
    }

    /* JADX INFO: renamed from: getCount-impl, reason: not valid java name */
    private static final int m2519getCountimpl(AtomicInt atomicInt, int i) {
        return 134217727 & i;
    }

    /* JADX INFO: renamed from: getVersion-impl, reason: not valid java name */
    private static final int m2520getVersionimpl(AtomicInt atomicInt, int i) {
        return (i >>> 27) & 15;
    }

    /* JADX INFO: renamed from: hasAwaiters-impl, reason: not valid java name */
    public static final boolean m2521hasAwaitersimpl(AtomicInt atomicInt) {
        return (atomicInt.get() & 134217727) > 0;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2522hashCodeimpl(AtomicInt atomicInt) {
        return atomicInt.hashCode();
    }

    /* JADX INFO: renamed from: incrementCountAndGetVersion-impl, reason: not valid java name */
    public static final int m2523incrementCountAndGetVersionimpl(AtomicInt atomicInt, Function0<Unit> function0) {
        int i;
        int i2;
        do {
            i = atomicInt.get();
            i2 = i + 1;
        } while (!atomicInt.compareAndSet(i, i2));
        if ((134217727 & i2) == 1) {
            function0.invoke();
        }
        return (i2 >>> 27) & 15;
    }

    /* JADX INFO: renamed from: incrementVersionAndResetCount-impl, reason: not valid java name */
    public static final void m2524incrementVersionAndResetCountimpl(AtomicInt atomicInt) {
        int i;
        do {
            i = atomicInt.get();
        } while (!atomicInt.compareAndSet(i, m2525packimpl(atomicInt, ((i >>> 27) & 15) + 1, 0)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: pack-impl, reason: not valid java name */
    public static final int m2525packimpl(AtomicInt atomicInt, int i, int i2) {
        return ((i & 15) << 27) | (134217727 & i2);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2526toStringimpl(AtomicInt atomicInt) {
        int i = atomicInt.get();
        return "AtomicAwaitersCount(version = " + ((i >>> 27) & 15) + ", count = " + (i & 134217727) + ')';
    }

    /* JADX INFO: renamed from: update-impl, reason: not valid java name */
    private static final int m2527updateimpl(AtomicInt atomicInt, Function1<? super Integer, Integer> function1) {
        int i;
        int iIntValue;
        do {
            i = atomicInt.get();
            iIntValue = ((Number) function1.invoke(Integer.valueOf(i))).intValue();
        } while (!atomicInt.compareAndSet(i, iIntValue));
        return iIntValue;
    }

    public boolean equals(Object other) {
        return m2517equalsimpl(this.value, other);
    }

    public int hashCode() {
        return m2522hashCodeimpl(this.value);
    }

    public String toString() {
        return m2526toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ AtomicInt getValue() {
        return this.value;
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static AtomicInt m2515constructorimpl(AtomicInt atomicInt) {
        return atomicInt;
    }
}
