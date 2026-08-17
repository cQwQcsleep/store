package androidx.compose.material3;

import androidx.collection.MutableIntList;
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
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Ref;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class TabRowKt$ScrollableTabRowImpl$1$1$2$1 implements MultiContentMeasurePolicy {
    final /* synthetic */ float $edgePadding;
    final /* synthetic */ float $minTabWidth;
    final /* synthetic */ TabRowKt$ScrollableTabRowImpl$1$scope$1$1 $scope;
    final /* synthetic */ ScrollableTabData $scrollableTabData;
    final /* synthetic */ int $selectedTabIndex;

    public TabRowKt$ScrollableTabRowImpl$1$1$2$1(float f, float f2, TabRowKt$ScrollableTabRowImpl$1$scope$1$1 tabRowKt$ScrollableTabRowImpl$1$scope$1$1, int i, ScrollableTabData scrollableTabData) {
        this.$edgePadding = f;
        this.$minTabWidth = f2;
        this.$scope = tabRowKt$ScrollableTabRowImpl$1$scope$1$1;
        this.$selectedTabIndex = i;
        this.$scrollableTabData = scrollableTabData;
    }

    public static Unit a(Ref.FloatRef floatRef, float f, List list, List list2, ScrollableTabData scrollableTabData, MeasureScope measureScope, int i, List list3, int i2, int i3, Placeable.PlacementScope placementScope) {
        floatRef.element = f;
        int size = list.size();
        for (int i4 = 0; i4 < size; i4++) {
            Placeable.PlacementScope.placeRelative$default(placementScope, (Placeable) list.get(i4), measureScope.mo4551roundToPx0680j_4(floatRef.element), 0, 0.0f, 4, null);
            floatRef.element = Dp.m6022constructorimpl(floatRef.element + ((TabPosition) list3.get(i4)).getWidth());
        }
        int size2 = list2.size();
        for (int i5 = 0; i5 < size2; i5++) {
            Placeable placeable = (Placeable) list2.get(i5);
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable, Math.max(0, (measureScope.mo4551roundToPx0680j_4(((TabPosition) list3.get(i2)).getWidth()) - placeable.getWidth()) / 2), i3 - placeable.getHeight(), 0.0f, 4, null);
        }
        scrollableTabData.onLaidOut(measureScope, i, list3, i2);
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo127measure3p2s80s(final MeasureScope measureScope, List<? extends List<? extends Measurable>> list, long j) {
        List<? extends Measurable> list2 = list.get(0);
        List<? extends Measurable> list3 = list.get(1);
        final int iMo4551roundToPx0680j_4 = measureScope.mo4551roundToPx0680j_4(this.$edgePadding);
        int size = list2.size();
        Integer numValueOf = 0;
        List<? extends Measurable> list4 = list2;
        int size2 = list4.size();
        for (int i = 0; i < size2; i++) {
            numValueOf = Integer.valueOf(Math.max(numValueOf.intValue(), list2.get(i).maxIntrinsicHeight(Integer.MAX_VALUE)));
        }
        final int iIntValue = numValueOf.intValue();
        int i2 = iMo4551roundToPx0680j_4 * 2;
        long jM5965copyZbe2FdA$default = Constraints.m5965copyZbe2FdA$default(j, measureScope.mo4551roundToPx0680j_4(this.$minTabWidth), 0, iIntValue, iIntValue, 2, null);
        Ref.FloatRef floatRef = new Ref.FloatRef();
        floatRef.element = this.$edgePadding;
        ArrayList arrayList = new ArrayList(list2.size());
        int size3 = list4.size();
        for (int i3 = 0; i3 < size3; i3++) {
            arrayList.add(list2.get(i3).mo4605measureBRTryo0(jM5965copyZbe2FdA$default));
        }
        MutableIntList mutableIntList = new MutableIntList(0, 1, (DefaultConstructorMarker) null);
        int size4 = list4.size();
        for (int i4 = 0; i4 < size4; i4++) {
            mutableIntList.add(list2.get(i4).maxIntrinsicWidth(Integer.MAX_VALUE));
        }
        float f = this.$minTabWidth;
        final ArrayList arrayList2 = new ArrayList(size);
        int iMo4551roundToPx0680j_5 = i2;
        for (int i5 = 0; i5 < size; i5++) {
            float fM6036unboximpl = ((Dp) ComparisonsKt.maxOf(Dp.m6020boximpl(f), Dp.m6020boximpl(measureScope.mo4554toDpu2uoSUM(((Placeable) arrayList.get(i5)).getWidth())))).m6036unboximpl();
            iMo4551roundToPx0680j_5 += measureScope.mo4551roundToPx0680j_4(fM6036unboximpl);
            TabPosition tabPosition = new TabPosition(floatRef.element, fM6036unboximpl, ((Dp) ComparisonsKt.maxOf(Dp.m6020boximpl(Dp.m6022constructorimpl(measureScope.mo4554toDpu2uoSUM(mutableIntList.get(i5)) - Dp.m6022constructorimpl(TabKt.getHorizontalTextPadding() * 2.0f))), Dp.m6020boximpl(Dp.m6022constructorimpl(24.0f)))).m6036unboximpl(), null);
            floatRef.element = Dp.m6022constructorimpl(floatRef.element + fM6036unboximpl);
            arrayList2.add(tabPosition);
        }
        this.$scope.setTabPositions(arrayList2);
        int i6 = this.$selectedTabIndex;
        final ArrayList arrayList3 = new ArrayList(list3.size());
        int i7 = 0;
        for (int size5 = list3.size(); i7 < size5; size5 = size5) {
            arrayList3.add(list3.get(i7).mo4605measureBRTryo0(Constraints.m5964copyZbe2FdA(j, 0, measureScope.mo4551roundToPx0680j_4(((TabPosition) arrayList2.get(i6)).getContentWidth()), 0, iIntValue)));
            i7++;
            arrayList = arrayList;
            floatRef = floatRef;
        }
        final Ref.FloatRef floatRef2 = floatRef;
        final ArrayList arrayList4 = arrayList;
        final float f2 = this.$edgePadding;
        final ScrollableTabData scrollableTabData = this.$scrollableTabData;
        final int i8 = this.$selectedTabIndex;
        return MeasureScope.layout$default(measureScope, iMo4551roundToPx0680j_5, iIntValue, null, new Function1() { // from class: androidx.compose.material3.k4
            public final Object invoke(Object obj) {
                return TabRowKt$ScrollableTabRowImpl$1$1$2$1.a(floatRef2, f2, arrayList4, arrayList3, scrollableTabData, measureScope, iMo4551roundToPx0680j_4, arrayList2, i8, iIntValue, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }
}
