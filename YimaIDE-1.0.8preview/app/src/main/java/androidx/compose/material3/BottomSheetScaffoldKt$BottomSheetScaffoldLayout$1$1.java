package androidx.compose.material3;

import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MultiContentMeasurePolicy;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Constraints;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.math.MathKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class BottomSheetScaffoldKt$BottomSheetScaffoldLayout$1$1 implements MultiContentMeasurePolicy {
    final /* synthetic */ Function0<Float> $sheetOffset;
    final /* synthetic */ SheetState $sheetState;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SheetValue.values().length];
            try {
                iArr[SheetValue.PartiallyExpanded.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[SheetValue.Expanded.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[SheetValue.Hidden.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public BottomSheetScaffoldKt$BottomSheetScaffoldLayout$1$1(SheetState sheetState, Function0<Float> function0) {
        this.$sheetState = sheetState;
        this.$sheetOffset = function0;
    }

    public static Unit a(List list, int i, List list2, SheetState sheetState, Function0 function0, int i2, List list3, List list4, int i3, Placeable.PlacementScope placementScope) {
        Integer numValueOf;
        Integer numValueOf2;
        Integer numValueOf3;
        int iRoundToInt;
        if (!list.isEmpty()) {
            numValueOf = Integer.valueOf(((Placeable) list.get(0)).getWidth());
            int lastIndex = CollectionsKt.getLastIndex(list);
            if (1 <= lastIndex) {
                int i4 = 1;
                while (true) {
                    Integer numValueOf4 = Integer.valueOf(((Placeable) list.get(i4)).getWidth());
                    if (numValueOf4.compareTo(numValueOf) > 0) {
                        numValueOf = numValueOf4;
                    }
                    if (i4 == lastIndex) {
                        break;
                    }
                    i4++;
                }
            }
        } else {
            numValueOf = null;
        }
        int iMax = Math.max(0, (i - (numValueOf != null ? numValueOf.intValue() : 0)) / 2);
        if (!list2.isEmpty()) {
            numValueOf2 = Integer.valueOf(((Placeable) list2.get(0)).getWidth());
            int lastIndex2 = CollectionsKt.getLastIndex(list2);
            if (1 <= lastIndex2) {
                int i5 = 1;
                while (true) {
                    Integer numValueOf5 = Integer.valueOf(((Placeable) list2.get(i5)).getWidth());
                    if (numValueOf5.compareTo(numValueOf2) > 0) {
                        numValueOf2 = numValueOf5;
                    }
                    if (i5 == lastIndex2) {
                        break;
                    }
                    i5++;
                }
            }
        } else {
            numValueOf2 = null;
        }
        int iIntValue = numValueOf2 != null ? numValueOf2.intValue() : 0;
        if (!list2.isEmpty()) {
            numValueOf3 = Integer.valueOf(((Placeable) list2.get(0)).getHeight());
            int lastIndex3 = CollectionsKt.getLastIndex(list2);
            if (1 <= lastIndex3) {
                int i6 = 1;
                while (true) {
                    Integer numValueOf6 = Integer.valueOf(((Placeable) list2.get(i6)).getHeight());
                    if (numValueOf6.compareTo(numValueOf3) > 0) {
                        numValueOf3 = numValueOf6;
                    }
                    if (i6 == lastIndex3) {
                        break;
                    }
                    i6++;
                }
            }
        } else {
            numValueOf3 = null;
        }
        int iIntValue2 = numValueOf3 != null ? numValueOf3.intValue() : 0;
        int i7 = (i - iIntValue) / 2;
        int i8 = WhenMappings.$EnumSwitchMapping$0[sheetState.getCurrentValue().ordinal()];
        if (i8 == 1) {
            iRoundToInt = MathKt.roundToInt(((Number) function0.invoke()).floatValue()) - iIntValue2;
        } else {
            if (i8 != 2 && i8 != 3) {
                bu8.a();
                return null;
            }
            iRoundToInt = i2 - iIntValue2;
        }
        int size = list3.size();
        for (int i9 = 0; i9 < size; i9++) {
            Placeable.PlacementScope.placeRelative$default(placementScope, (Placeable) list3.get(i9), 0, i3, 0.0f, 4, null);
        }
        int size2 = list4.size();
        for (int i10 = 0; i10 < size2; i10++) {
            Placeable.PlacementScope.placeRelative$default(placementScope, (Placeable) list4.get(i10), 0, 0, 0.0f, 4, null);
        }
        int size3 = list.size();
        for (int i11 = 0; i11 < size3; i11++) {
            Placeable.PlacementScope.placeRelative$default(placementScope, (Placeable) list.get(i11), iMax, 0, 0.0f, 4, null);
        }
        int size4 = list2.size();
        for (int i12 = 0; i12 < size4; i12++) {
            Placeable.PlacementScope.placeRelative$default(placementScope, (Placeable) list2.get(i12), i7, iRoundToInt, 0.0f, 4, null);
        }
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
    public final MeasureResult mo127measure3p2s80s(MeasureScope measureScope, List<? extends List<? extends Measurable>> list, long j) {
        Integer numValueOf;
        List<? extends Measurable> list2 = list.get(0);
        int i = 1;
        List<? extends Measurable> list3 = list.get(1);
        List<? extends Measurable> list4 = list.get(2);
        List<? extends Measurable> list5 = list.get(3);
        final int iM5975getMaxWidthimpl = Constraints.m5975getMaxWidthimpl(j);
        final int iM5974getMaxHeightimpl = Constraints.m5974getMaxHeightimpl(j);
        long jM5965copyZbe2FdA$default = Constraints.m5965copyZbe2FdA$default(j, 0, 0, 0, 0, 10, null);
        final ArrayList arrayList = new ArrayList(list4.size());
        int size = list4.size();
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.add(list4.get(i2).mo4605measureBRTryo0(jM5965copyZbe2FdA$default));
        }
        final ArrayList arrayList2 = new ArrayList(list2.size());
        int size2 = list2.size();
        for (int i3 = 0; i3 < size2; i3++) {
            arrayList2.add(list2.get(i3).mo4605measureBRTryo0(jM5965copyZbe2FdA$default));
        }
        if (!arrayList2.isEmpty()) {
            numValueOf = Integer.valueOf(((Placeable) arrayList2.get(0)).getHeight());
            int lastIndex = CollectionsKt.getLastIndex(arrayList2);
            if (1 <= lastIndex) {
                while (true) {
                    Integer numValueOf2 = Integer.valueOf(((Placeable) arrayList2.get(i)).getHeight());
                    if (numValueOf2.compareTo(numValueOf) > 0) {
                        numValueOf = numValueOf2;
                    }
                    if (i == lastIndex) {
                        break;
                    }
                    i++;
                }
            }
        } else {
            numValueOf = null;
        }
        final int iIntValue = numValueOf != null ? numValueOf.intValue() : 0;
        long jM5965copyZbe2FdA$default2 = Constraints.m5965copyZbe2FdA$default(jM5965copyZbe2FdA$default, 0, 0, 0, iM5974getMaxHeightimpl - iIntValue, 7, null);
        final ArrayList arrayList3 = new ArrayList(list3.size());
        int size3 = list3.size();
        for (int i4 = 0; i4 < size3; i4++) {
            arrayList3.add(list3.get(i4).mo4605measureBRTryo0(jM5965copyZbe2FdA$default2));
        }
        final ArrayList arrayList4 = new ArrayList(list5.size());
        int size4 = list5.size();
        for (int i5 = 0; i5 < size4; i5++) {
            arrayList4.add(list5.get(i5).mo4605measureBRTryo0(jM5965copyZbe2FdA$default));
        }
        final SheetState sheetState = this.$sheetState;
        final Function0<Float> function0 = this.$sheetOffset;
        return MeasureScope.layout$default(measureScope, iM5975getMaxWidthimpl, iM5974getMaxHeightimpl, null, new Function1() { // from class: androidx.compose.material3.h
            public final Object invoke(Object obj) {
                return BottomSheetScaffoldKt$BottomSheetScaffoldLayout$1$1.a(arrayList, iM5975getMaxWidthimpl, arrayList4, sheetState, function0, iM5974getMaxHeightimpl, arrayList3, arrayList2, iIntValue, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }
}
