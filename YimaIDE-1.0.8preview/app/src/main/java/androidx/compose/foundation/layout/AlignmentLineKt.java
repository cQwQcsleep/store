package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.AlignmentLineKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.HorizontalAlignmentLine;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnit;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a/\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\u0007\u0010\b\u001a/\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\t2\b\b\u0002\u0010\u0006\u001a\u00020\tH\u0007¢\u0006\u0004\b\n\u0010\u000b\u001a'\u0010\f\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\u000f\u0010\u0010\u001a'\u0010\f\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\tH\u0007¢\u0006\u0004\b\u0011\u0010\u0012\u001a;\u0010\u0013\u001a\u00020\u0014*\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002¢\u0006\u0004\b\u001a\u0010\u001b\"\u0018\u0010\u001c\u001a\u00020\u001d*\u00020\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f¨\u0006 "}, d2 = {"paddingFrom", "Landroidx/compose/ui/Modifier;", "alignmentLine", "Landroidx/compose/ui/layout/AlignmentLine;", "before", "Landroidx/compose/ui/unit/Dp;", "after", "paddingFrom-4j6BHR0", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/layout/AlignmentLine;FF)Landroidx/compose/ui/Modifier;", "Landroidx/compose/ui/unit/TextUnit;", "paddingFrom-Y_r0B1c", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/layout/AlignmentLine;JJ)Landroidx/compose/ui/Modifier;", "paddingFromBaseline", "top", "bottom", "paddingFromBaseline-VpY3zN4", "(Landroidx/compose/ui/Modifier;FF)Landroidx/compose/ui/Modifier;", "paddingFromBaseline-wCyjxdI", "(Landroidx/compose/ui/Modifier;JJ)Landroidx/compose/ui/Modifier;", "alignmentLineOffsetMeasure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "alignmentLineOffsetMeasure-tjqqzMA", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/AlignmentLine;FFLandroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "horizontal", "", "getHorizontal", "(Landroidx/compose/ui/layout/AlignmentLine;)Z", "foundation-layout"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AlignmentLineKt {
    public static Unit a(AlignmentLine alignmentLine, float f, int i, int i2, int i3, Placeable placeable, int i4, Placeable.PlacementScope placementScope) {
        int width;
        int i5;
        if (getHorizontal(alignmentLine)) {
            width = 0;
        } else {
            width = !Dp.equals-impl0(f, Dp.Companion.getUnspecified-D9Ej5fM()) ? i : (i2 - i3) - placeable.getWidth();
        }
        if (getHorizontal(alignmentLine)) {
            if (Dp.equals-impl0(f, Dp.Companion.getUnspecified-D9Ej5fM())) {
                i = (i4 - i3) - placeable.getHeight();
            }
            i5 = i;
        } else {
            i5 = 0;
        }
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable, width, i5, 0.0f, 4, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: alignmentLineOffsetMeasure-tjqqzMA, reason: not valid java name */
    public static final MeasureResult m747alignmentLineOffsetMeasuretjqqzMA(MeasureScope measureScope, final AlignmentLine alignmentLine, final float f, float f2, Measurable measurable, long j) {
        final Placeable placeable = measurable.measure-BRTryo0(getHorizontal(alignmentLine) ? Constraints.copy-Zbe2FdA$default(j, 0, 0, 0, 0, 11, (Object) null) : Constraints.copy-Zbe2FdA$default(j, 0, 0, 0, 0, 14, (Object) null));
        int i = placeable.get(alignmentLine);
        if (i == Integer.MIN_VALUE) {
            i = 0;
        }
        int height = getHorizontal(alignmentLine) ? placeable.getHeight() : placeable.getWidth();
        int i2 = (getHorizontal(alignmentLine) ? Constraints.getMaxHeight-impl(j) : Constraints.getMaxWidth-impl(j)) - height;
        final int iCoerceIn = RangesKt.coerceIn((!Float.isNaN(f) ? measureScope.roundToPx-0680j_4(f) : 0) - i, 0, i2);
        final int iCoerceIn2 = RangesKt.coerceIn(((!Float.isNaN(f2) ? measureScope.roundToPx-0680j_4(f2) : 0) - height) + i, 0, i2 - iCoerceIn);
        final int width = getHorizontal(alignmentLine) ? placeable.getWidth() : Math.max(placeable.getWidth() + iCoerceIn + iCoerceIn2, Constraints.getMinWidth-impl(j));
        final int iMax = getHorizontal(alignmentLine) ? Math.max(placeable.getHeight() + iCoerceIn + iCoerceIn2, Constraints.getMinHeight-impl(j)) : placeable.getHeight();
        return MeasureScope.layout$default(measureScope, width, iMax, (Map) null, new Function1() { // from class: i30
            public final Object invoke(Object obj) {
                return AlignmentLineKt.a(alignmentLine, f, iCoerceIn, width, iCoerceIn2, placeable, iMax, (Placeable.PlacementScope) obj);
            }
        }, 4, (Object) null);
    }

    private static final boolean getHorizontal(AlignmentLine alignmentLine) {
        return alignmentLine instanceof HorizontalAlignmentLine;
    }

    /* JADX INFO: renamed from: paddingFrom-4j6BHR0, reason: not valid java name */
    public static final Modifier m748paddingFrom4j6BHR0(Modifier modifier, final AlignmentLine alignmentLine, final float f, final float f2) {
        return modifier.then(new AlignmentLineOffsetDpElement(alignmentLine, f, f2, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.AlignmentLineKt$paddingFrom-4j6BHR0$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final void invoke(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("paddingFrom");
                inspectorInfo.getProperties().set("alignmentLine", alignmentLine);
                inspectorInfo.getProperties().set("before", Dp.box-impl(f));
                inspectorInfo.getProperties().set("after", Dp.box-impl(f2));
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((InspectorInfo) obj);
                return Unit.INSTANCE;
            }
        } : InspectableValueKt.getNoInspectorInfo(), null));
    }

    /* JADX INFO: renamed from: paddingFrom-4j6BHR0$default, reason: not valid java name */
    public static /* synthetic */ Modifier m749paddingFrom4j6BHR0$default(Modifier modifier, AlignmentLine alignmentLine, float f, float f2, int i, Object obj) {
        if ((i & 2) != 0) {
            f = Dp.Companion.getUnspecified-D9Ej5fM();
        }
        if ((i & 4) != 0) {
            f2 = Dp.Companion.getUnspecified-D9Ej5fM();
        }
        return m748paddingFrom4j6BHR0(modifier, alignmentLine, f, f2);
    }

    /* JADX INFO: renamed from: paddingFrom-Y_r0B1c, reason: not valid java name */
    public static final Modifier m750paddingFromY_r0B1c(Modifier modifier, final AlignmentLine alignmentLine, long j, long j2) {
        AlignmentLine alignmentLine2;
        final long j3;
        final long j4;
        Function1<InspectorInfo, Unit> noInspectorInfo;
        if (InspectableValueKt.isDebugInspectorInfoEnabled()) {
            j3 = j;
            j4 = j2;
            noInspectorInfo = new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.AlignmentLineKt$paddingFrom-Y_r0B1c$$inlined$debugInspectorInfo$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                public final void invoke(InspectorInfo inspectorInfo) {
                    inspectorInfo.setName("paddingFrom");
                    inspectorInfo.getProperties().set("alignmentLine", alignmentLine);
                    inspectorInfo.getProperties().set("before", TextUnit.box-impl(j3));
                    inspectorInfo.getProperties().set("after", TextUnit.box-impl(j4));
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((InspectorInfo) obj);
                    return Unit.INSTANCE;
                }
            };
            alignmentLine2 = alignmentLine;
        } else {
            alignmentLine2 = alignmentLine;
            j3 = j;
            j4 = j2;
            noInspectorInfo = InspectableValueKt.getNoInspectorInfo();
        }
        return modifier.then(new AlignmentLineOffsetTextUnitElement(alignmentLine2, j3, j4, noInspectorInfo, null));
    }

    /* JADX INFO: renamed from: paddingFrom-Y_r0B1c$default, reason: not valid java name */
    public static /* synthetic */ Modifier m751paddingFromY_r0B1c$default(Modifier modifier, AlignmentLine alignmentLine, long j, long j2, int i, Object obj) {
        if ((i & 2) != 0) {
            j = TextUnit.Companion.getUnspecified-XSAIIZE();
        }
        long j3 = j;
        if ((i & 4) != 0) {
            j2 = TextUnit.Companion.getUnspecified-XSAIIZE();
        }
        return m750paddingFromY_r0B1c(modifier, alignmentLine, j3, j2);
    }

    /* JADX INFO: renamed from: paddingFromBaseline-VpY3zN4, reason: not valid java name */
    public static final Modifier m752paddingFromBaselineVpY3zN4(Modifier modifier, float f, float f2) {
        return modifier.then(!Float.isNaN(f) ? m749paddingFrom4j6BHR0$default(Modifier.Companion, androidx.compose.ui.layout.AlignmentLineKt.getFirstBaseline(), f, 0.0f, 4, null) : Modifier.Companion).then(!Float.isNaN(f2) ? m749paddingFrom4j6BHR0$default(Modifier.Companion, androidx.compose.ui.layout.AlignmentLineKt.getLastBaseline(), 0.0f, f2, 2, null) : Modifier.Companion);
    }

    /* JADX INFO: renamed from: paddingFromBaseline-VpY3zN4$default, reason: not valid java name */
    public static /* synthetic */ Modifier m753paddingFromBaselineVpY3zN4$default(Modifier modifier, float f, float f2, int i, Object obj) {
        if ((i & 1) != 0) {
            f = Dp.Companion.getUnspecified-D9Ej5fM();
        }
        if ((i & 2) != 0) {
            f2 = Dp.Companion.getUnspecified-D9Ej5fM();
        }
        return m752paddingFromBaselineVpY3zN4(modifier, f, f2);
    }

    /* JADX INFO: renamed from: paddingFromBaseline-wCyjxdI, reason: not valid java name */
    public static final Modifier m754paddingFromBaselinewCyjxdI(Modifier modifier, long j, long j2) {
        return modifier.then(TextUnit.getRawType-impl(j) == 0 ? Modifier.Companion : m751paddingFromY_r0B1c$default(Modifier.Companion, androidx.compose.ui.layout.AlignmentLineKt.getFirstBaseline(), j, 0L, 4, null)).then(TextUnit.getRawType-impl(j2) == 0 ? Modifier.Companion : m751paddingFromY_r0B1c$default(Modifier.Companion, androidx.compose.ui.layout.AlignmentLineKt.getLastBaseline(), 0L, j2, 2, null));
    }

    /* JADX INFO: renamed from: paddingFromBaseline-wCyjxdI$default, reason: not valid java name */
    public static /* synthetic */ Modifier m755paddingFromBaselinewCyjxdI$default(Modifier modifier, long j, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = TextUnit.Companion.getUnspecified-XSAIIZE();
        }
        if ((i & 2) != 0) {
            j2 = TextUnit.Companion.getUnspecified-XSAIIZE();
        }
        return m754paddingFromBaselinewCyjxdI(modifier, j, j2);
    }
}
