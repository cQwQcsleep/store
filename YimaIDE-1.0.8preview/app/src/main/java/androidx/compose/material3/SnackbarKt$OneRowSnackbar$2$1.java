package androidx.compose.material3;

import androidx.compose.material3.tokens.SnackbarTokens;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.util.ListUtilsKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SnackbarKt$OneRowSnackbar$2$1 implements MeasurePolicy {
    final /* synthetic */ String $actionTag;
    final /* synthetic */ String $dismissActionTag;
    final /* synthetic */ String $textTag;

    public SnackbarKt$OneRowSnackbar$2$1(String str, String str2, String str3) {
        this.$actionTag = str;
        this.$dismissActionTag = str2;
        this.$textTag = str3;
    }

    public static Unit a(Placeable placeable, int i, Placeable placeable2, int i2, int i3, Placeable placeable3, int i4, int i5, Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable, 0, i, 0.0f, 4, null);
        if (placeable2 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, i2, i3, 0.0f, 4, null);
        }
        if (placeable3 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable3, i4, i5, 0.0f, 4, null);
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:59:0x012b A[PHI: r0 r4
      0x012b: PHI (r0v10 int) = (r0v9 int), (r0v15 int), (r0v15 int) binds: [B:62:0x014d, B:55:0x011c, B:57:0x0126] A[DONT_GENERATE, DONT_INLINE]
      0x012b: PHI (r4v4 int) = (r4v3 int), (r4v12 int), (r4v12 int) binds: [B:62:0x014d, B:55:0x011c, B:57:0x0126] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo14measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
        Measurable measurable;
        Measurable measurable2;
        int iMo4551roundToPx0680j_4;
        int iMax;
        int height;
        int i;
        MeasureScope measureScope2 = measureScope;
        int iMin = Math.min(Constraints.m5975getMaxWidthimpl(j), measureScope2.mo4551roundToPx0680j_4(SnackbarKt.ContainerMaxWidth));
        String str = this.$actionTag;
        List<? extends Measurable> list2 = list;
        int size = list2.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                measurable = null;
                break;
            }
            measurable = list.get(i2);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable), str)) {
                break;
            }
            i2++;
        }
        Measurable measurable3 = measurable;
        Placeable placeableMo4605measureBRTryo0 = measurable3 != null ? measurable3.mo4605measureBRTryo0(j) : null;
        String str2 = this.$dismissActionTag;
        int size2 = list2.size();
        int i3 = 0;
        while (true) {
            if (i3 >= size2) {
                measurable2 = null;
                break;
            }
            measurable2 = list.get(i3);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable2), str2)) {
                break;
            }
            i3++;
        }
        Measurable measurable4 = measurable2;
        final Placeable placeableMo4605measureBRTryo1 = measurable4 != null ? measurable4.mo4605measureBRTryo0(j) : null;
        int width = placeableMo4605measureBRTryo0 != null ? placeableMo4605measureBRTryo0.getWidth() : 0;
        int height2 = placeableMo4605measureBRTryo0 != null ? placeableMo4605measureBRTryo0.getHeight() : 0;
        int width2 = placeableMo4605measureBRTryo1 != null ? placeableMo4605measureBRTryo1.getWidth() : 0;
        int height3 = placeableMo4605measureBRTryo1 != null ? placeableMo4605measureBRTryo1.getHeight() : 0;
        int iCoerceAtLeast = RangesKt.coerceAtLeast(((iMin - width) - width2) - (width2 == 0 ? measureScope2.mo4551roundToPx0680j_4(SnackbarKt.TextEndExtraSpacing) : 0), Constraints.m5977getMinWidthimpl(j));
        String str3 = this.$textTag;
        int size3 = list2.size();
        int i4 = 0;
        while (i4 < size3) {
            Measurable measurable5 = list.get(i4);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable5), str3)) {
                int i5 = height3;
                final Placeable placeableMo4605measureBRTryo2 = measurable5.mo4605measureBRTryo0(Constraints.m5965copyZbe2FdA$default(j, 0, iCoerceAtLeast, 0, 0, 9, null));
                int i6 = placeableMo4605measureBRTryo2.get(AlignmentLineKt.getFirstBaseline());
                int i7 = placeableMo4605measureBRTryo2.get(AlignmentLineKt.getLastBaseline());
                boolean z = true;
                boolean z2 = (i6 == Integer.MIN_VALUE || i7 == Integer.MIN_VALUE) ? false : true;
                if (i6 != i7 && z2) {
                    z = false;
                }
                final int i8 = iMin - width2;
                final int i9 = i8 - width;
                if (z) {
                    iMax = Math.max(measureScope2.mo4551roundToPx0680j_4(SnackbarTokens.INSTANCE.m2127getSingleLineContainerHeightD9Ej5fM()), Math.max(height2, i5));
                    iMo4551roundToPx0680j_4 = (iMax - placeableMo4605measureBRTryo2.getHeight()) / 2;
                    if (placeableMo4605measureBRTryo0 == null || (i = placeableMo4605measureBRTryo0.get(AlignmentLineKt.getFirstBaseline())) == Integer.MIN_VALUE) {
                        height = 0;
                    } else {
                        height = (i6 + iMo4551roundToPx0680j_4) - i;
                    }
                } else {
                    iMo4551roundToPx0680j_4 = measureScope2.mo4551roundToPx0680j_4(SnackbarKt.HeightToFirstLine) - i6;
                    iMax = Math.max(measureScope2.mo4551roundToPx0680j_4(SnackbarTokens.INSTANCE.m2128getTwoLinesContainerHeightD9Ej5fM()), placeableMo4605measureBRTryo2.getHeight() + iMo4551roundToPx0680j_4);
                    if (placeableMo4605measureBRTryo0 != null) {
                        height = (iMax - placeableMo4605measureBRTryo0.getHeight()) / 2;
                    } else {
                        height = 0;
                    }
                }
                final int i10 = height;
                final int i11 = iMo4551roundToPx0680j_4;
                final int height4 = placeableMo4605measureBRTryo1 != null ? (iMax - placeableMo4605measureBRTryo1.getHeight()) / 2 : 0;
                final Placeable placeable = placeableMo4605measureBRTryo0;
                return MeasureScope.layout$default(measureScope2, iMin, iMax, null, new Function1() { // from class: androidx.compose.material3.e4
                    public final Object invoke(Object obj) {
                        return SnackbarKt$OneRowSnackbar$2$1.a(placeableMo4605measureBRTryo2, i11, placeableMo4605measureBRTryo1, i8, height4, placeable, i9, i10, (Placeable.PlacementScope) obj);
                    }
                }, 4, null);
            }
            i4++;
            height3 = height3;
            measureScope2 = measureScope;
        }
        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
        wq6.a();
        return null;
    }
}
