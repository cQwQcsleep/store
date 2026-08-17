package androidx.compose.ui.layout;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0081@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0012\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b\u001c\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\t8Æ\u0002¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\t8Æ\u0002¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0012\u0010\u000e\u001a\u00020\t8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u000bR\u0012\u0010\u0010\u001a\u00020\t8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000b\u0088\u0001\u0002¨\u0006\u001d"}, d2 = {"Landroidx/compose/ui/layout/ValueInsets;", "", "packedValue", "", "constructor-impl", "(J)J", "getPackedValue", "()J", "left", "", "getLeft-impl", "(J)I", "top", "getTop-impl", "right", "getRight-impl", "bottom", "getBottom-impl", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "equals", "", "other", "equals-impl", "(JLjava/lang/Object;)Z", "hashCode", "hashCode-impl", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
@JvmInline
public final class ValueInsets {
    private final long packedValue;

    private /* synthetic */ ValueInsets(long j) {
        this.packedValue = j;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ ValueInsets m4744boximpl(long j) {
        return new ValueInsets(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m4745constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m4746equalsimpl(long j, Object obj) {
        return (obj instanceof ValueInsets) && j == ((ValueInsets) obj).m4754unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m4747equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* JADX INFO: renamed from: getBottom-impl, reason: not valid java name */
    public static final int m4748getBottomimpl(long j) {
        return (int) (j & 65535);
    }

    /* JADX INFO: renamed from: getLeft-impl, reason: not valid java name */
    public static final int m4749getLeftimpl(long j) {
        return (int) ((j >>> 48) & 65535);
    }

    /* JADX INFO: renamed from: getRight-impl, reason: not valid java name */
    public static final int m4750getRightimpl(long j) {
        return (int) ((j >>> 16) & 65535);
    }

    /* JADX INFO: renamed from: getTop-impl, reason: not valid java name */
    public static final int m4751getTopimpl(long j) {
        return (int) ((j >>> 32) & 65535);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m4752hashCodeimpl(long j) {
        return Long.hashCode(j);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m4753toStringimpl(long j) {
        return "ValueInsets(" + ((int) ((j >>> 48) & 65535)) + ", " + ((int) ((j >>> 32) & 65535)) + ", " + ((int) ((j >>> 16) & 65535)) + ", " + ((int) (j & 65535)) + ')';
    }

    public boolean equals(Object obj) {
        return m4746equalsimpl(this.packedValue, obj);
    }

    public final long getPackedValue() {
        return this.packedValue;
    }

    public int hashCode() {
        return m4752hashCodeimpl(this.packedValue);
    }

    public String toString() {
        return m4753toStringimpl(this.packedValue);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m4754unboximpl() {
        return this.packedValue;
    }
}
