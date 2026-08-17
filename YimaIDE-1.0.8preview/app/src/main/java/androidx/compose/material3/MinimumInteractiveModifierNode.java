package androidx.compose.material3;

import androidx.compose.material3.MinimumInteractiveModifierNode;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Dp;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J#\u0010\n\u001a\u00020\u000b*\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0014\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007H\u0002R\u001c\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Landroidx/compose/material3/MinimumInteractiveModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Landroidx/compose/ui/node/LayoutModifierNode;", "<init>", "()V", "alignmentLinesCache", "", "Landroidx/compose/ui/layout/AlignmentLine;", "", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "updateAlignmentLines", "", "sizePx", "placeable", "Landroidx/compose/ui/layout/Placeable;", "getAlignmentLinesCache", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class MinimumInteractiveModifierNode extends Modifier.Node implements CompositionLocalConsumerModifierNode, LayoutModifierNode {
    public static final int $stable = 8;
    private Map<AlignmentLine, Integer> alignmentLinesCache;

    public static Unit a(int i, Placeable placeable, int i2, Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope.place$default(placementScope, placeable, MathKt.roundToInt((i - placeable.getWidth()) / 2.0f), MathKt.roundToInt((i2 - placeable.getHeight()) / 2.0f), 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    private final Map<AlignmentLine, Integer> getAlignmentLinesCache() {
        Map<AlignmentLine, Integer> map = this.alignmentLinesCache;
        if (map != null) {
            return map;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(2);
        this.alignmentLinesCache = linkedHashMap;
        return linkedHashMap;
    }

    private final void updateAlignmentLines(int sizePx, Placeable placeable) {
        Map<AlignmentLine, Integer> alignmentLinesCache = getAlignmentLinesCache();
        alignmentLinesCache.put(InteractiveComponentSizeKt.getMinimumInteractiveLeftAlignmentLine(), Integer.valueOf(RangesKt.coerceAtLeast(Math.round((sizePx - placeable.getWidth()) / 2.0f), 0)));
        alignmentLinesCache.put(InteractiveComponentSizeKt.getMinimumInteractiveTopAlignmentLine(), Integer.valueOf(RangesKt.coerceAtLeast(Math.round((sizePx - placeable.getHeight()) / 2.0f), 0)));
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
    public MeasureResult mo628measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        float fM6022constructorimpl = Dp.m6022constructorimpl(RangesKt.coerceAtLeast(((Dp) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, InteractiveComponentSizeKt.getLocalMinimumInteractiveComponentSize())).m6036unboximpl(), Dp.m6022constructorimpl(0.0f)));
        final Placeable placeableMo4605measureBRTryo0 = measurable.mo4605measureBRTryo0(j);
        boolean z = getIsAttached() && !Float.isNaN(fM6022constructorimpl) && Dp.m6021compareTo0680j_4(fM6022constructorimpl, Dp.m6022constructorimpl(0.0f)) > 0;
        int iMo4551roundToPx0680j_4 = Float.isNaN(fM6022constructorimpl) ? 0 : measureScope.mo4551roundToPx0680j_4(fM6022constructorimpl);
        final int iMax = z ? Math.max(placeableMo4605measureBRTryo0.getWidth(), iMo4551roundToPx0680j_4) : placeableMo4605measureBRTryo0.getWidth();
        final int iMax2 = z ? Math.max(placeableMo4605measureBRTryo0.getHeight(), iMo4551roundToPx0680j_4) : placeableMo4605measureBRTryo0.getHeight();
        if (z) {
            updateAlignmentLines(iMo4551roundToPx0680j_4, placeableMo4605measureBRTryo0);
        }
        Map<AlignmentLine, Integer> mapEmptyMap = this.alignmentLinesCache;
        if (mapEmptyMap == null) {
            mapEmptyMap = MapsKt.emptyMap();
        }
        return measureScope.layout(iMax, iMax2, mapEmptyMap, new Function1() { // from class: l2a
            public final Object invoke(Object obj) {
                return MinimumInteractiveModifierNode.a(iMax, placeableMo4605measureBRTryo0, iMax2, (Placeable.PlacementScope) obj);
            }
        });
    }
}
