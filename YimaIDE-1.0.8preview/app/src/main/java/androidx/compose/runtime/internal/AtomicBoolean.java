package androidx.compose.runtime.internal;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0081@\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\u0004\u0010\bJ\r\u0010\t\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\f\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\u000e\u0010\u000fJ\u0015\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0007¢\u0006\u0004\b\u0012\u0010\u0013J\u0013\u0010\u0014\u001a\u00020\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\u001a"}, d2 = {"Landroidx/compose/runtime/internal/AtomicBoolean;", "", "wrapped", "Landroidx/compose/runtime/internal/AtomicInt;", "constructor-impl", "(Landroidx/compose/runtime/internal/AtomicInt;)Landroidx/compose/runtime/internal/AtomicInt;", "value", "", "(Z)Landroidx/compose/runtime/internal/AtomicInt;", "get", "get-impl", "(Landroidx/compose/runtime/internal/AtomicInt;)Z", "set", "", "set-impl", "(Landroidx/compose/runtime/internal/AtomicInt;Z)V", "getAndSet", "newValue", "getAndSet-impl", "(Landroidx/compose/runtime/internal/AtomicInt;Z)Z", "equals", "other", "hashCode", "", "toString", "", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
@JvmInline
public final class AtomicBoolean {
    private final AtomicInt wrapped;

    private /* synthetic */ AtomicBoolean(AtomicInt atomicInt) {
        this.wrapped = atomicInt;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ AtomicBoolean m2529boximpl(AtomicInt atomicInt) {
        return new AtomicBoolean(atomicInt);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static AtomicInt m2531constructorimpl(boolean z) {
        return m2530constructorimpl(new AtomicInt(z ? 1 : 0));
    }

    /* JADX INFO: renamed from: constructor-impl$default, reason: not valid java name */
    public static /* synthetic */ AtomicInt m2532constructorimpl$default(AtomicInt atomicInt, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            atomicInt = new AtomicInt(0);
        }
        return m2530constructorimpl(atomicInt);
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2533equalsimpl(AtomicInt atomicInt, Object obj) {
        return (obj instanceof AtomicBoolean) && Intrinsics.areEqual(atomicInt, ((AtomicBoolean) obj).getWrapped());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2534equalsimpl0(AtomicInt atomicInt, AtomicInt atomicInt2) {
        return Intrinsics.areEqual(atomicInt, atomicInt2);
    }

    /* JADX INFO: renamed from: get-impl, reason: not valid java name */
    public static final boolean m2535getimpl(AtomicInt atomicInt) {
        return atomicInt.get() != 0;
    }

    /* JADX INFO: renamed from: getAndSet-impl, reason: not valid java name */
    public static final boolean m2536getAndSetimpl(AtomicInt atomicInt, boolean z) {
        return atomicInt.compareAndSet(1, z ? 1 : 0);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2537hashCodeimpl(AtomicInt atomicInt) {
        return atomicInt.hashCode();
    }

    /* JADX INFO: renamed from: set-impl, reason: not valid java name */
    public static final void m2538setimpl(AtomicInt atomicInt, boolean z) {
        atomicInt.set(z ? 1 : 0);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2539toStringimpl(AtomicInt atomicInt) {
        return "AtomicBoolean(wrapped=" + atomicInt + ')';
    }

    public boolean equals(Object other) {
        return m2533equalsimpl(this.wrapped, other);
    }

    public int hashCode() {
        return m2537hashCodeimpl(this.wrapped);
    }

    public String toString() {
        return m2539toStringimpl(this.wrapped);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ AtomicInt getWrapped() {
        return this.wrapped;
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static AtomicInt m2530constructorimpl(AtomicInt atomicInt) {
        return atomicInt;
    }
}
