package androidx.compose.foundation.layout;

import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0081@\u0018\u00002\u00020\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B)\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007¢\u0006\u0004\b\u0004\u0010\u000bB\u0019\b\u0016\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u0004\u0010\u000fJ\r\u0010\u0016\u001a\u00020\u0000¢\u0006\u0004\b\u0017\u0010\u0005J\u0015\u0010\u0018\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u0019\u0010\u000fJ\u0015\u0010\u001a\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u001b\u0010\u001cJ\u0015\u0010\u001d\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u001e\u0010\u001cJ5\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u0007¢\u0006\u0004\b \u0010!J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020\u0007HÖ\u0001J\t\u0010&\u001a\u00020'HÖ\u0001R\u0010\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010R\u0012\u0010\u0006\u001a\u00020\u00078Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0012\u0010\b\u001a\u00020\u00078Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0012R\u0012\u0010\t\u001a\u00020\u00078Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0012R\u0012\u0010\n\u001a\u00020\u00078Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0012\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006("}, d2 = {"Landroidx/compose/foundation/layout/OrientationIndependentConstraints;", "", "value", "Landroidx/compose/ui/unit/Constraints;", "constructor-impl", "(J)J", "mainAxisMin", "", "mainAxisMax", "crossAxisMin", "crossAxisMax", "(IIII)J", "c", "orientation", "Landroidx/compose/foundation/layout/LayoutOrientation;", "(JLandroidx/compose/foundation/layout/LayoutOrientation;)J", "J", "getMainAxisMin-impl", "(J)I", "getMainAxisMax-impl", "getCrossAxisMin-impl", "getCrossAxisMax-impl", "stretchCrossAxis", "stretchCrossAxis-q4ezo7Y", "toBoxConstraints", "toBoxConstraints-OenEA2s", "maxWidth", "maxWidth-impl", "(JLandroidx/compose/foundation/layout/LayoutOrientation;)I", "maxHeight", "maxHeight-impl", "copy", "copy-yUG9Ft0", "(JIIII)J", "equals", "", "other", "hashCode", "toString", "", "foundation-layout"}, k = 1, mv = {2, 0, 0}, xi = 48)
@JvmInline
public final class OrientationIndependentConstraints {
    private final long value;

    private /* synthetic */ OrientationIndependentConstraints(long j) {
        this.value = j;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ OrientationIndependentConstraints m895boximpl(long j) {
        return new OrientationIndependentConstraints(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m898constructorimpl(long j, LayoutOrientation layoutOrientation) {
        LayoutOrientation layoutOrientation2 = LayoutOrientation.Horizontal;
        return m896constructorimpl(layoutOrientation == layoutOrientation2 ? Constraints.getMinWidth-impl(j) : Constraints.getMinHeight-impl(j), layoutOrientation == layoutOrientation2 ? Constraints.getMaxWidth-impl(j) : Constraints.getMaxHeight-impl(j), layoutOrientation == layoutOrientation2 ? Constraints.getMinHeight-impl(j) : Constraints.getMinWidth-impl(j), layoutOrientation == layoutOrientation2 ? Constraints.getMaxHeight-impl(j) : Constraints.getMaxWidth-impl(j));
    }

    /* JADX INFO: renamed from: copy-yUG9Ft0, reason: not valid java name */
    public static final long m899copyyUG9Ft0(long j, int i, int i2, int i3, int i4) {
        return m896constructorimpl(i, i2, i3, i4);
    }

    /* JADX INFO: renamed from: copy-yUG9Ft0$default, reason: not valid java name */
    public static /* synthetic */ long m900copyyUG9Ft0$default(long j, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = Constraints.getMinWidth-impl(j);
        }
        int i6 = i;
        if ((i5 & 2) != 0) {
            i2 = Constraints.getMaxWidth-impl(j);
        }
        int i7 = i2;
        if ((i5 & 4) != 0) {
            i3 = Constraints.getMinHeight-impl(j);
        }
        int i8 = i3;
        if ((i5 & 8) != 0) {
            i4 = Constraints.getMaxHeight-impl(j);
        }
        return m899copyyUG9Ft0(j, i6, i7, i8, i4);
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m901equalsimpl(long j, Object obj) {
        return (obj instanceof OrientationIndependentConstraints) && Constraints.equals-impl0(j, ((OrientationIndependentConstraints) obj).getValue());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m902equalsimpl0(long j, long j2) {
        return Constraints.equals-impl0(j, j2);
    }

    /* JADX INFO: renamed from: getCrossAxisMax-impl, reason: not valid java name */
    public static final int m903getCrossAxisMaximpl(long j) {
        return Constraints.getMaxHeight-impl(j);
    }

    /* JADX INFO: renamed from: getCrossAxisMin-impl, reason: not valid java name */
    public static final int m904getCrossAxisMinimpl(long j) {
        return Constraints.getMinHeight-impl(j);
    }

    /* JADX INFO: renamed from: getMainAxisMax-impl, reason: not valid java name */
    public static final int m905getMainAxisMaximpl(long j) {
        return Constraints.getMaxWidth-impl(j);
    }

    /* JADX INFO: renamed from: getMainAxisMin-impl, reason: not valid java name */
    public static final int m906getMainAxisMinimpl(long j) {
        return Constraints.getMinWidth-impl(j);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m907hashCodeimpl(long j) {
        return Constraints.hashCode-impl(j);
    }

    /* JADX INFO: renamed from: maxHeight-impl, reason: not valid java name */
    public static final int m908maxHeightimpl(long j, LayoutOrientation layoutOrientation) {
        return layoutOrientation == LayoutOrientation.Horizontal ? Constraints.getMaxHeight-impl(j) : Constraints.getMaxWidth-impl(j);
    }

    /* JADX INFO: renamed from: maxWidth-impl, reason: not valid java name */
    public static final int m909maxWidthimpl(long j, LayoutOrientation layoutOrientation) {
        return layoutOrientation == LayoutOrientation.Horizontal ? Constraints.getMaxWidth-impl(j) : Constraints.getMaxHeight-impl(j);
    }

    /* JADX INFO: renamed from: stretchCrossAxis-q4ezo7Y, reason: not valid java name */
    public static final long m910stretchCrossAxisq4ezo7Y(long j) {
        return m896constructorimpl(Constraints.getMinWidth-impl(j), Constraints.getMaxWidth-impl(j), Constraints.getMaxHeight-impl(j) != Integer.MAX_VALUE ? Constraints.getMaxHeight-impl(j) : Constraints.getMinHeight-impl(j), Constraints.getMaxHeight-impl(j));
    }

    /* JADX INFO: renamed from: toBoxConstraints-OenEA2s, reason: not valid java name */
    public static final long m911toBoxConstraintsOenEA2s(long j, LayoutOrientation layoutOrientation) {
        return layoutOrientation == LayoutOrientation.Horizontal ? ConstraintsKt.Constraints(Constraints.getMinWidth-impl(j), Constraints.getMaxWidth-impl(j), Constraints.getMinHeight-impl(j), Constraints.getMaxHeight-impl(j)) : ConstraintsKt.Constraints(Constraints.getMinHeight-impl(j), Constraints.getMaxHeight-impl(j), Constraints.getMinWidth-impl(j), Constraints.getMaxWidth-impl(j));
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m912toStringimpl(long j) {
        return "OrientationIndependentConstraints(value=" + ((Object) Constraints.toString-impl(j)) + ')';
    }

    public boolean equals(Object other) {
        return m901equalsimpl(this.value, other);
    }

    public int hashCode() {
        return m907hashCodeimpl(this.value);
    }

    public String toString() {
        return m912toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ long getValue() {
        return this.value;
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m896constructorimpl(int i, int i2, int i3, int i4) {
        return m897constructorimpl(ConstraintsKt.Constraints(i, i2, i3, i4));
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static long m897constructorimpl(long j) {
        return j;
    }
}
