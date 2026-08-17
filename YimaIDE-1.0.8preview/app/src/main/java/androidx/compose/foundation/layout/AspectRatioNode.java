package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntSize;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\r\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ#\u0010\u0011\u001a\u00020\u0012*\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u001c\u0010\u001a\u001a\u00020\u001b*\u00020\u001c2\u0006\u0010\u0014\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001bH\u0016J\u001c\u0010\u001f\u001a\u00020\u001b*\u00020\u001c2\u0006\u0010\u0014\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001bH\u0016J\u001c\u0010 \u001a\u00020\u001b*\u00020\u001c2\u0006\u0010\u0014\u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\u001bH\u0016J\u001c\u0010\"\u001a\u00020\u001b*\u00020\u001c2\u0006\u0010\u0014\u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\u001bH\u0016J\u0013\u0010#\u001a\u00020$*\u00020\u0017H\u0002¢\u0006\u0004\b%\u0010&J\u001b\u0010'\u001a\u00020$*\u00020\u00172\u0006\u0010(\u001a\u00020\u0006H\u0002¢\u0006\u0004\b)\u0010*J\u001b\u0010+\u001a\u00020$*\u00020\u00172\u0006\u0010(\u001a\u00020\u0006H\u0002¢\u0006\u0004\b,\u0010*J\u001b\u0010-\u001a\u00020$*\u00020\u00172\u0006\u0010(\u001a\u00020\u0006H\u0002¢\u0006\u0004\b.\u0010*J\u001b\u0010/\u001a\u00020$*\u00020\u00172\u0006\u0010(\u001a\u00020\u0006H\u0002¢\u0006\u0004\b0\u0010*R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u00061"}, d2 = {"Landroidx/compose/foundation/layout/AspectRatioNode;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "aspectRatio", "", "matchHeightConstraintsFirst", "", "<init>", "(FZ)V", "getAspectRatio", "()F", "setAspectRatio", "(F)V", "getMatchHeightConstraintsFirst", "()Z", "setMatchHeightConstraintsFirst", "(Z)V", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicWidth", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "height", "maxIntrinsicWidth", "minIntrinsicHeight", "width", "maxIntrinsicHeight", "findSize", "Landroidx/compose/ui/unit/IntSize;", "findSize-ToXhtMw", "(J)J", "tryMaxWidth", "enforceConstraints", "tryMaxWidth-JN-0ABg", "(JZ)J", "tryMaxHeight", "tryMaxHeight-JN-0ABg", "tryMinWidth", "tryMinWidth-JN-0ABg", "tryMinHeight", "tryMinHeight-JN-0ABg", "foundation-layout"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class AspectRatioNode extends Modifier.Node implements LayoutModifierNode {
    private float aspectRatio;
    private boolean matchHeightConstraintsFirst;

    public AspectRatioNode(float f, boolean z) {
        this.aspectRatio = f;
        this.matchHeightConstraintsFirst = z;
    }

    public static Unit a(Placeable placeable, Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable, 0, 0, 0.0f, 4, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: findSize-ToXhtMw, reason: not valid java name */
    private final long m801findSizeToXhtMw(long j) {
        if (this.matchHeightConstraintsFirst) {
            long jM802tryMaxHeightJN0ABg = m802tryMaxHeightJN0ABg(j, true);
            IntSize.Companion companion = IntSize.Companion;
            if (!IntSize.equals-impl0(jM802tryMaxHeightJN0ABg, companion.getZero-YbymL2g())) {
                return jM802tryMaxHeightJN0ABg;
            }
            long jM803tryMaxWidthJN0ABg = m803tryMaxWidthJN0ABg(j, true);
            if (!IntSize.equals-impl0(jM803tryMaxWidthJN0ABg, companion.getZero-YbymL2g())) {
                return jM803tryMaxWidthJN0ABg;
            }
            long jM804tryMinHeightJN0ABg = m804tryMinHeightJN0ABg(j, true);
            if (!IntSize.equals-impl0(jM804tryMinHeightJN0ABg, companion.getZero-YbymL2g())) {
                return jM804tryMinHeightJN0ABg;
            }
            long jM805tryMinWidthJN0ABg = m805tryMinWidthJN0ABg(j, true);
            if (!IntSize.equals-impl0(jM805tryMinWidthJN0ABg, companion.getZero-YbymL2g())) {
                return jM805tryMinWidthJN0ABg;
            }
            long jM802tryMaxHeightJN0ABg2 = m802tryMaxHeightJN0ABg(j, false);
            if (!IntSize.equals-impl0(jM802tryMaxHeightJN0ABg2, companion.getZero-YbymL2g())) {
                return jM802tryMaxHeightJN0ABg2;
            }
            long jM803tryMaxWidthJN0ABg2 = m803tryMaxWidthJN0ABg(j, false);
            if (!IntSize.equals-impl0(jM803tryMaxWidthJN0ABg2, companion.getZero-YbymL2g())) {
                return jM803tryMaxWidthJN0ABg2;
            }
            long jM804tryMinHeightJN0ABg2 = m804tryMinHeightJN0ABg(j, false);
            if (!IntSize.equals-impl0(jM804tryMinHeightJN0ABg2, companion.getZero-YbymL2g())) {
                return jM804tryMinHeightJN0ABg2;
            }
            long jM805tryMinWidthJN0ABg2 = m805tryMinWidthJN0ABg(j, false);
            if (!IntSize.equals-impl0(jM805tryMinWidthJN0ABg2, companion.getZero-YbymL2g())) {
                return jM805tryMinWidthJN0ABg2;
            }
        } else {
            long jM803tryMaxWidthJN0ABg3 = m803tryMaxWidthJN0ABg(j, true);
            IntSize.Companion companion2 = IntSize.Companion;
            if (!IntSize.equals-impl0(jM803tryMaxWidthJN0ABg3, companion2.getZero-YbymL2g())) {
                return jM803tryMaxWidthJN0ABg3;
            }
            long jM802tryMaxHeightJN0ABg3 = m802tryMaxHeightJN0ABg(j, true);
            if (!IntSize.equals-impl0(jM802tryMaxHeightJN0ABg3, companion2.getZero-YbymL2g())) {
                return jM802tryMaxHeightJN0ABg3;
            }
            long jM805tryMinWidthJN0ABg3 = m805tryMinWidthJN0ABg(j, true);
            if (!IntSize.equals-impl0(jM805tryMinWidthJN0ABg3, companion2.getZero-YbymL2g())) {
                return jM805tryMinWidthJN0ABg3;
            }
            long jM804tryMinHeightJN0ABg3 = m804tryMinHeightJN0ABg(j, true);
            if (!IntSize.equals-impl0(jM804tryMinHeightJN0ABg3, companion2.getZero-YbymL2g())) {
                return jM804tryMinHeightJN0ABg3;
            }
            long jM803tryMaxWidthJN0ABg4 = m803tryMaxWidthJN0ABg(j, false);
            if (!IntSize.equals-impl0(jM803tryMaxWidthJN0ABg4, companion2.getZero-YbymL2g())) {
                return jM803tryMaxWidthJN0ABg4;
            }
            long jM802tryMaxHeightJN0ABg4 = m802tryMaxHeightJN0ABg(j, false);
            if (!IntSize.equals-impl0(jM802tryMaxHeightJN0ABg4, companion2.getZero-YbymL2g())) {
                return jM802tryMaxHeightJN0ABg4;
            }
            long jM805tryMinWidthJN0ABg4 = m805tryMinWidthJN0ABg(j, false);
            if (!IntSize.equals-impl0(jM805tryMinWidthJN0ABg4, companion2.getZero-YbymL2g())) {
                return jM805tryMinWidthJN0ABg4;
            }
            long jM804tryMinHeightJN0ABg4 = m804tryMinHeightJN0ABg(j, false);
            if (!IntSize.equals-impl0(jM804tryMinHeightJN0ABg4, companion2.getZero-YbymL2g())) {
                return jM804tryMinHeightJN0ABg4;
            }
        }
        return IntSize.Companion.getZero-YbymL2g();
    }

    /* JADX INFO: renamed from: tryMaxHeight-JN-0ABg, reason: not valid java name */
    private final long m802tryMaxHeightJN0ABg(long j, boolean z) {
        int iRound;
        int i = Constraints.getMaxHeight-impl(j);
        return (i == Integer.MAX_VALUE || (iRound = Math.round(((float) i) * this.aspectRatio)) <= 0 || (z && !AspectRatioKt.m800isSatisfiedByNN6EwU(j, iRound, i))) ? IntSize.Companion.getZero-YbymL2g() : IntSize.constructor-impl((((long) iRound) << 32) | (((long) i) & 4294967295L));
    }

    /* JADX INFO: renamed from: tryMaxWidth-JN-0ABg, reason: not valid java name */
    private final long m803tryMaxWidthJN0ABg(long j, boolean z) {
        int iRound;
        int i = Constraints.getMaxWidth-impl(j);
        return (i == Integer.MAX_VALUE || (iRound = Math.round(((float) i) / this.aspectRatio)) <= 0 || (z && !AspectRatioKt.m800isSatisfiedByNN6EwU(j, i, iRound))) ? IntSize.Companion.getZero-YbymL2g() : IntSize.constructor-impl((((long) i) << 32) | (((long) iRound) & 4294967295L));
    }

    /* JADX INFO: renamed from: tryMinHeight-JN-0ABg, reason: not valid java name */
    private final long m804tryMinHeightJN0ABg(long j, boolean z) {
        int i = Constraints.getMinHeight-impl(j);
        int iRound = Math.round(i * this.aspectRatio);
        return (iRound <= 0 || (z && !AspectRatioKt.m800isSatisfiedByNN6EwU(j, iRound, i))) ? IntSize.Companion.getZero-YbymL2g() : IntSize.constructor-impl((((long) iRound) << 32) | (((long) i) & 4294967295L));
    }

    /* JADX INFO: renamed from: tryMinWidth-JN-0ABg, reason: not valid java name */
    private final long m805tryMinWidthJN0ABg(long j, boolean z) {
        int i = Constraints.getMinWidth-impl(j);
        int iRound = Math.round(i / this.aspectRatio);
        return (iRound <= 0 || (z && !AspectRatioKt.m800isSatisfiedByNN6EwU(j, i, iRound))) ? IntSize.Companion.getZero-YbymL2g() : IntSize.constructor-impl((((long) i) << 32) | (((long) iRound) & 4294967295L));
    }

    public final float getAspectRatio() {
        return this.aspectRatio;
    }

    public final boolean getMatchHeightConstraintsFirst() {
        return this.matchHeightConstraintsFirst;
    }

    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return i != Integer.MAX_VALUE ? Math.round(i / this.aspectRatio) : intrinsicMeasurable.maxIntrinsicHeight(i);
    }

    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return i != Integer.MAX_VALUE ? Math.round(i * this.aspectRatio) : intrinsicMeasurable.maxIntrinsicWidth(i);
    }

    /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
    public MeasureResult m806measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        long jM801findSizeToXhtMw = m801findSizeToXhtMw(j);
        if (!IntSize.equals-impl0(jM801findSizeToXhtMw, IntSize.Companion.getZero-YbymL2g())) {
            j = Constraints.Companion.fixed-JhjzzOo((int) (jM801findSizeToXhtMw >> 32), (int) (jM801findSizeToXhtMw & 4294967295L));
        }
        final Placeable placeable = measurable.measure-BRTryo0(j);
        return MeasureScope.layout$default(measureScope, placeable.getWidth(), placeable.getHeight(), (Map) null, new Function1() { // from class: androidx.compose.foundation.layout.a
            public final Object invoke(Object obj) {
                return AspectRatioNode.a(placeable, (Placeable.PlacementScope) obj);
            }
        }, 4, (Object) null);
    }

    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return i != Integer.MAX_VALUE ? Math.round(i / this.aspectRatio) : intrinsicMeasurable.minIntrinsicHeight(i);
    }

    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return i != Integer.MAX_VALUE ? Math.round(i * this.aspectRatio) : intrinsicMeasurable.minIntrinsicWidth(i);
    }

    public final void setAspectRatio(float f) {
        this.aspectRatio = f;
    }

    public final void setMatchHeightConstraintsFirst(boolean z) {
        this.matchHeightConstraintsFirst = z;
    }
}
