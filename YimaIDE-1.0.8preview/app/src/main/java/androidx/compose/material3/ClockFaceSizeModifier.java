package androidx.compose.material3;

import androidx.compose.material3.ClockFaceSizeModifier;
import androidx.compose.material3.tokens.TimePickerTokens;
import androidx.compose.ui.layout.LayoutModifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J#\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Landroidx/compose/material3/ClockFaceSizeModifier;", "Landroidx/compose/ui/layout/LayoutModifier;", "<init>", "()V", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ClockFaceSizeModifier implements LayoutModifier {
    public static final int $stable = 0;

    public static Unit a(Placeable placeable, Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope.place$default(placementScope, placeable, 0, 0, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
    public MeasureResult mo220measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        float clockDialMinContainerSize;
        float fMo4554toDpu2uoSUM = measureScope.mo4554toDpu2uoSUM(Constraints.m5974getMaxHeightimpl(j));
        if (Dp.m6021compareTo0680j_4(fMo4554toDpu2uoSUM, TimePickerKt.TimePickerMaxHeight) >= 0) {
            clockDialMinContainerSize = TimePickerTokens.INSTANCE.m2195getClockDialContainerSizeD9Ej5fM();
        } else {
            clockDialMinContainerSize = Dp.m6021compareTo0680j_4(fMo4554toDpu2uoSUM, TimePickerKt.TimePickerMidHeight) >= 0 ? TimePickerKt.ClockDialMidContainerSize : TimePickerKt.getClockDialMinContainerSize();
        }
        int iMo4551roundToPx0680j_4 = measureScope.mo4551roundToPx0680j_4(clockDialMinContainerSize);
        final Placeable placeableMo4605measureBRTryo0 = measurable.mo4605measureBRTryo0(Constraints.INSTANCE.m5985fixedJhjzzOo(iMo4551roundToPx0680j_4, iMo4551roundToPx0680j_4));
        return MeasureScope.layout$default(measureScope, placeableMo4605measureBRTryo0.getWidth(), placeableMo4605measureBRTryo0.getHeight(), null, new Function1() { // from class: z02
            public final Object invoke(Object obj) {
                return ClockFaceSizeModifier.a(placeableMo4605measureBRTryo0, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }
}
