package androidx.compose.material3;

import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.util.ListUtilsKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class TimePickerDialogKt$TimePickerCustomLayout$measurePolicy$1$1 implements MeasurePolicy {
    public static final TimePickerDialogKt$TimePickerCustomLayout$measurePolicy$1$1 INSTANCE = new TimePickerDialogKt$TimePickerCustomLayout$measurePolicy$1$1();

    public static Unit a(boolean z, int i, Placeable placeable, int i2, Placeable placeable2, int i3, int i4, int i5, MeasureScope measureScope, Placeable placeable3, int i6, int i7, int i8, int i9, Placeable.PlacementScope placementScope) {
        if (z) {
            int height = i4 - ((((i + placeable.getHeight()) + i2) + placeable2.getHeight()) + i3);
            int iMo4551roundToPx0680j_4 = i4 >= i5 ? measureScope.mo4551roundToPx0680j_4(Dp.m6022constructorimpl(16.0f)) : 0;
            Placeable.PlacementScope.place$default(placementScope, placeable3, i6, i6, 0.0f, 4, null);
            int i10 = height / 2;
            int i11 = i + i10;
            Placeable.PlacementScope.place$default(placementScope, placeable, i7, i11, 0.0f, 4, null);
            Placeable.PlacementScope.place$default(placementScope, placeable2, i7, (((i11 + placeable.getHeight()) + i2) - iMo4551roundToPx0680j_4) + i10, 0.0f, 4, null);
        } else {
            Placeable.PlacementScope.place$default(placementScope, placeable3, i6, i8, 0.0f, 4, null);
            int width = (i9 - placeable.getWidth()) / 2;
            int height2 = i8 + placeable3.getHeight();
            Placeable.PlacementScope.place$default(placementScope, placeable, width, height2, 0.0f, 4, null);
            Placeable.PlacementScope.place$default(placementScope, placeable2, (i9 - placeable2.getWidth()) / 2, height2 + placeable.getHeight(), 0.0f, 4, null);
        }
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo14measure3p2s80s(final MeasureScope measureScope, List<? extends Measurable> list, long j) {
        int height;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Measurable measurable = list.get(i);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable), "title")) {
                List<? extends Measurable> list2 = list;
                int size2 = list2.size();
                int i2 = 0;
                while (i2 < size2) {
                    Measurable measurable2 = list.get(i2);
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable2), "timePickerContent")) {
                        int size3 = list2.size();
                        int i3 = 0;
                        while (i3 < size3) {
                            Measurable measurable3 = list.get(i3);
                            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable3), "actions")) {
                                final int iMo4551roundToPx0680j_4 = measureScope.mo4551roundToPx0680j_4(Dp.m6022constructorimpl(24.0f));
                                final int iMo4551roundToPx0680j_5 = measureScope.mo4551roundToPx0680j_4(Dp.m6022constructorimpl(384.0f));
                                final int iMo4551roundToPx0680j_6 = measureScope.mo4551roundToPx0680j_4(Dp.m6022constructorimpl(24.0f));
                                final int iMo4551roundToPx0680j_7 = measureScope.mo4551roundToPx0680j_4(Dp.m6022constructorimpl(16.0f));
                                final int iMo4551roundToPx0680j_8 = measureScope.mo4551roundToPx0680j_4(Dp.m6022constructorimpl(4.0f));
                                final int iMo4551roundToPx0680j_9 = measureScope.mo4551roundToPx0680j_4(Dp.m6022constructorimpl(8.0f));
                                final int iMo4551roundToPx0680j_10 = measureScope.mo4551roundToPx0680j_4(Dp.m6022constructorimpl(24.0f));
                                int iMo4551roundToPx0680j_11 = measureScope.mo4551roundToPx0680j_4(Dp.m6022constructorimpl(24.0f));
                                final Placeable placeableMo4605measureBRTryo0 = measurable2.mo4605measureBRTryo0(Constraints.m5965copyZbe2FdA$default(j, 0, 0, 0, 0, 11, null));
                                boolean z = placeableMo4605measureBRTryo0.getWidth() > placeableMo4605measureBRTryo0.getHeight() && ((float) placeableMo4605measureBRTryo0.getHeight()) >= MathKt.truncate(measureScope.mo4557toPx0680j_4(TimePickerKt.getClockDialMinContainerSize()));
                                final int width = (z ? placeableMo4605measureBRTryo0.getWidth() : placeableMo4605measureBRTryo0.getWidth()) + (iMo4551roundToPx0680j_4 * 2);
                                final boolean z2 = z;
                                final Placeable placeableMo4605measureBRTryo1 = measurable3.mo4605measureBRTryo0(Constraints.m5965copyZbe2FdA$default(j, 0, placeableMo4605measureBRTryo0.getWidth(), 0, 0, 8, null));
                                final Placeable placeableMo4605measureBRTryo2 = measurable.mo4605measureBRTryo0(Constraints.m5965copyZbe2FdA$default(j, 0, placeableMo4605measureBRTryo0.getWidth(), 0, 0, 8, null));
                                if (z2) {
                                    height = placeableMo4605measureBRTryo0.getHeight() + placeableMo4605measureBRTryo1.getHeight() + iMo4551roundToPx0680j_9 + iMo4551roundToPx0680j_7 + iMo4551roundToPx0680j_8;
                                    if (Constraints.m5970getHasBoundedHeightimpl(j)) {
                                        height = Constraints.m5974getMaxHeightimpl(j);
                                    }
                                } else {
                                    height = placeableMo4605measureBRTryo2.getHeight() + iMo4551roundToPx0680j_10 + placeableMo4605measureBRTryo0.getHeight() + placeableMo4605measureBRTryo1.getHeight() + iMo4551roundToPx0680j_11;
                                }
                                final int i4 = height;
                                return MeasureScope.layout$default(measureScope, width, i4, null, new Function1() { // from class: androidx.compose.material3.x4
                                    public final Object invoke(Object obj) {
                                        return TimePickerDialogKt$TimePickerCustomLayout$measurePolicy$1$1.a(z2, iMo4551roundToPx0680j_7, placeableMo4605measureBRTryo0, iMo4551roundToPx0680j_8, placeableMo4605measureBRTryo1, iMo4551roundToPx0680j_9, i4, iMo4551roundToPx0680j_5, measureScope, placeableMo4605measureBRTryo2, iMo4551roundToPx0680j_6, iMo4551roundToPx0680j_4, iMo4551roundToPx0680j_10, width, (Placeable.PlacementScope) obj);
                                    }
                                }, 4, null);
                            }
                            i3++;
                            measureScope = measureScope;
                        }
                        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
                        wq6.a();
                        return null;
                    }
                    i2++;
                    measureScope = measureScope;
                }
                ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
                wq6.a();
                return null;
            }
        }
        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
        wq6.a();
        return null;
    }
}
