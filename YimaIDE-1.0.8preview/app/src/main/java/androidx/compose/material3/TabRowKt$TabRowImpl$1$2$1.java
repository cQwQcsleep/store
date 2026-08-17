package androidx.compose.material3;

import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MultiContentMeasurePolicy;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class TabRowKt$TabRowImpl$1$2$1 implements MultiContentMeasurePolicy {
    final /* synthetic */ TabRowKt$TabRowImpl$1$scope$1$1 $scope;

    public TabRowKt$TabRowImpl$1$2$1(TabRowKt$TabRowImpl$1$scope$1$1 tabRowKt$TabRowImpl$1$scope$1$1) {
        this.$scope = tabRowKt$TabRowImpl$1$scope$1$1;
    }

    public static Unit a(List list, List list2, List list3, Ref.IntRef intRef, int i, Placeable.PlacementScope placementScope) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            Placeable.PlacementScope.placeRelative$default(placementScope, (Placeable) list.get(i2), i2 * intRef.element, 0, 0.0f, 4, null);
        }
        int size2 = list2.size();
        for (int i3 = 0; i3 < size2; i3++) {
            Placeable placeable = (Placeable) list2.get(i3);
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable, 0, i - placeable.getHeight(), 0.0f, 4, null);
        }
        int size3 = list3.size();
        for (int i4 = 0; i4 < size3; i4++) {
            Placeable placeable2 = (Placeable) list3.get(i4);
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, 0, i - placeable2.getHeight(), 0.0f, 4, null);
        }
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo127measure3p2s80s(MeasureScope measureScope, List<? extends List<? extends Measurable>> list, long j) {
        List<? extends Measurable> list2 = list.get(0);
        List<? extends Measurable> list3 = list.get(1);
        List<? extends Measurable> list4 = list.get(2);
        int iM5975getMaxWidthimpl = Constraints.m5975getMaxWidthimpl(j);
        int size = list2.size();
        final Ref.IntRef intRef = new Ref.IntRef();
        if (size > 0) {
            intRef.element = iM5975getMaxWidthimpl / size;
        }
        Integer numValueOf = 0;
        List<? extends Measurable> list5 = list2;
        int size2 = list5.size();
        for (int i = 0; i < size2; i++) {
            numValueOf = Integer.valueOf(Math.max(list2.get(i).maxIntrinsicHeight(intRef.element), numValueOf.intValue()));
        }
        final int iIntValue = numValueOf.intValue();
        TabRowKt$TabRowImpl$1$scope$1$1 tabRowKt$TabRowImpl$1$scope$1$1 = this.$scope;
        ArrayList arrayList = new ArrayList(size);
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.add(new TabPosition(Dp.m6022constructorimpl(measureScope.mo4554toDpu2uoSUM(intRef.element) * i2), measureScope.mo4554toDpu2uoSUM(intRef.element), ((Dp) ComparisonsKt.maxOf(Dp.m6020boximpl(Dp.m6022constructorimpl(measureScope.mo4554toDpu2uoSUM(Math.min(list2.get(i2).maxIntrinsicWidth(iIntValue), intRef.element)) - Dp.m6022constructorimpl(TabKt.getHorizontalTextPadding() * 2.0f))), Dp.m6020boximpl(Dp.m6022constructorimpl(24.0f)))).m6036unboximpl(), null));
        }
        tabRowKt$TabRowImpl$1$scope$1$1.setTabPositions(arrayList);
        final ArrayList arrayList2 = new ArrayList(list2.size());
        int size3 = list5.size();
        for (int i3 = 0; i3 < size3; i3++) {
            Measurable measurable = list2.get(i3);
            int i4 = intRef.element;
            int i5 = iIntValue;
            long jM5964copyZbe2FdA = Constraints.m5964copyZbe2FdA(j, i4, i4, i5, iIntValue);
            iIntValue = i5;
            arrayList2.add(measurable.mo4605measureBRTryo0(jM5964copyZbe2FdA));
        }
        final ArrayList arrayList3 = new ArrayList(list3.size());
        int size4 = list3.size();
        for (int i6 = 0; i6 < size4; i6++) {
            arrayList3.add(list3.get(i6).mo4605measureBRTryo0(Constraints.m5965copyZbe2FdA$default(j, 0, 0, 0, 0, 11, null)));
        }
        final ArrayList arrayList4 = new ArrayList(list4.size());
        int size5 = list4.size();
        for (int i7 = 0; i7 < size5; i7++) {
            Measurable measurable2 = list4.get(i7);
            int i8 = intRef.element;
            int i9 = iIntValue;
            iIntValue = i9;
            arrayList4.add(measurable2.mo4605measureBRTryo0(Constraints.m5964copyZbe2FdA(j, i8, i8, 0, i9)));
        }
        return MeasureScope.layout$default(measureScope, iM5975getMaxWidthimpl, iIntValue, null, new Function1() { // from class: androidx.compose.material3.n4
            public final Object invoke(Object obj) {
                return TabRowKt$TabRowImpl$1$2$1.a(arrayList2, arrayList3, arrayList4, intRef, iIntValue, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }
}
