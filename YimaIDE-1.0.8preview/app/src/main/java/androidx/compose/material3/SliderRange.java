package androidx.compose.material3;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0081@\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0011\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\t8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\t8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u000f\u0010\u000b\u001a\u0004\b\u0010\u0010\r\u0088\u0001\u0002¨\u0006\u001b"}, d2 = {"Landroidx/compose/material3/SliderRange;", "", "packedValue", "", "constructor-impl", "(J)J", "getPackedValue", "()J", "start", "", "getStart$annotations", "()V", "getStart-impl", "(J)F", "endInclusive", "getEndInclusive$annotations", "getEndInclusive-impl", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "equals", "", "other", "hashCode", "", "Companion", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
@JvmInline
public final class SliderRange {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final long Unspecified = SliderKt.SliderRange(Float.NaN, Float.NaN);
    private final long packedValue;

    private /* synthetic */ SliderRange(long j) {
        this.packedValue = j;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ SliderRange m925boximpl(long j) {
        return new SliderRange(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m926constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m927equalsimpl(long j, Object obj) {
        return (obj instanceof SliderRange) && j == ((SliderRange) obj).m933unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m928equalsimpl0(long j, long j2) {
        return j == j2;
    }

    public static /* synthetic */ void getEndInclusive$annotations() {
    }

    /* JADX INFO: renamed from: getEndInclusive-impl, reason: not valid java name */
    public static final float m929getEndInclusiveimpl(long j) {
        if (j != Unspecified) {
            return Float.intBitsToFloat((int) (j & 4294967295L));
        }
        k2d.a("SliderRange is unspecified");
        return 0.0f;
    }

    public static /* synthetic */ void getStart$annotations() {
    }

    /* JADX INFO: renamed from: getStart-impl, reason: not valid java name */
    public static final float m930getStartimpl(long j) {
        if (j != Unspecified) {
            return Float.intBitsToFloat((int) (j >> 32));
        }
        k2d.a("SliderRange is unspecified");
        return 0.0f;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m931hashCodeimpl(long j) {
        return Long.hashCode(j);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m932toStringimpl(long j) {
        if (!SliderKt.m920isSpecifiedIf1S1O4(j)) {
            return "FloatRange.Unspecified";
        }
        return m930getStartimpl(j) + ".." + m929getEndInclusiveimpl(j);
    }

    public boolean equals(Object other) {
        return m927equalsimpl(this.packedValue, other);
    }

    public final long getPackedValue() {
        return this.packedValue;
    }

    public int hashCode() {
        return m931hashCodeimpl(this.packedValue);
    }

    public String toString() {
        return m932toStringimpl(this.packedValue);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m933unboximpl() {
        return this.packedValue;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001e\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\t\u0012\u0004\b\u0006\u0010\u0003\u001a\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Landroidx/compose/material3/SliderRange$Companion;", "", "<init>", "()V", "Unspecified", "Landroidx/compose/material3/SliderRange;", "getUnspecified-FYbKRX4$annotations", "getUnspecified-FYbKRX4", "()J", "J", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: renamed from: getUnspecified-FYbKRX4$annotations, reason: not valid java name */
        public static /* synthetic */ void m934getUnspecifiedFYbKRX4$annotations() {
        }

        /* JADX INFO: renamed from: getUnspecified-FYbKRX4, reason: not valid java name */
        public final long m935getUnspecifiedFYbKRX4() {
            return SliderRange.Unspecified;
        }

        private Companion() {
        }
    }
}
