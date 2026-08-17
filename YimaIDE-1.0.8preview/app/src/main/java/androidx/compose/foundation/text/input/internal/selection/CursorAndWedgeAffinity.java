package androidx.compose.foundation.text.input.internal.selection;

import androidx.compose.foundation.text.input.internal.WedgeAffinity;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0081@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\u0004\u0010\bB\u001b\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0004\u0010\u000bJ\u0010\u0010\u0010\u001a\u00020\u0007H\u0086\u0002¢\u0006\u0004\b\u0011\u0010\rJ\u0012\u0010\u0012\u001a\u0004\u0018\u00010\nH\u0086\u0002¢\u0006\u0004\b\u0013\u0010\u000fJ\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0013\u0010\t\u001a\u0004\u0018\u00010\n8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\u001a"}, d2 = {"Landroidx/compose/foundation/text/input/internal/selection/CursorAndWedgeAffinity;", "", "value", "", "constructor-impl", "(J)J", "cursor", "", "(I)J", "wedgeAffinity", "Landroidx/compose/foundation/text/input/internal/WedgeAffinity;", "(ILandroidx/compose/foundation/text/input/internal/WedgeAffinity;)J", "getCursor-impl", "(J)I", "getWedgeAffinity-impl", "(J)Landroidx/compose/foundation/text/input/internal/WedgeAffinity;", "component1", "component1-impl", "component2", "component2-impl", "equals", "", "other", "hashCode", "toString", "", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
@JvmInline
public final class CursorAndWedgeAffinity {
    private final long value;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[WedgeAffinity.values().length];
            try {
                iArr[WedgeAffinity.Start.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[WedgeAffinity.End.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private /* synthetic */ CursorAndWedgeAffinity(long j) {
        this.value = j;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ CursorAndWedgeAffinity m1623boximpl(long j) {
        return new CursorAndWedgeAffinity(j);
    }

    /* JADX INFO: renamed from: component1-impl, reason: not valid java name */
    public static final int m1624component1impl(long j) {
        return m1631getCursorimpl(j);
    }

    /* JADX INFO: renamed from: component2-impl, reason: not valid java name */
    public static final WedgeAffinity m1625component2impl(long j) {
        return m1632getWedgeAffinityimpl(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m1627constructorimpl(int i, WedgeAffinity wedgeAffinity) {
        int i2 = -1;
        int i3 = wedgeAffinity == null ? -1 : WhenMappings.$EnumSwitchMapping$0[wedgeAffinity.ordinal()];
        if (i3 != -1) {
            i2 = 1;
            if (i3 == 1) {
                i2 = 0;
            } else if (i3 != 2) {
                bu8.a();
                return 0L;
            }
        }
        return m1628constructorimpl((((long) i) << 32) | (((long) i2) & 4294967295L));
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m1629equalsimpl(long j, Object obj) {
        return (obj instanceof CursorAndWedgeAffinity) && j == ((CursorAndWedgeAffinity) obj).getValue();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m1630equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* JADX INFO: renamed from: getCursor-impl, reason: not valid java name */
    public static final int m1631getCursorimpl(long j) {
        return (int) (j >> 32);
    }

    /* JADX INFO: renamed from: getWedgeAffinity-impl, reason: not valid java name */
    public static final WedgeAffinity m1632getWedgeAffinityimpl(long j) {
        int i = (int) (j & 4294967295L);
        if (i < 0) {
            return null;
        }
        return i == 0 ? WedgeAffinity.Start : WedgeAffinity.End;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m1633hashCodeimpl(long j) {
        return Long.hashCode(j);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m1634toStringimpl(long j) {
        return "CursorAndWedgeAffinity(value=" + j + ')';
    }

    public boolean equals(Object other) {
        return m1629equalsimpl(this.value, other);
    }

    public int hashCode() {
        return m1633hashCodeimpl(this.value);
    }

    public String toString() {
        return m1634toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ long getValue() {
        return this.value;
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m1626constructorimpl(int i) {
        return m1628constructorimpl((((long) i) << 32) | 4294967295L);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m1628constructorimpl(long j) {
        return j;
    }
}
