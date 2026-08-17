package androidx.compose.material3;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Constraints;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class AlertDialogKt$AlertDialogFlowRow$1$1 implements MeasurePolicy {
    final /* synthetic */ float $crossAxisSpacing;
    final /* synthetic */ float $mainAxisSpacing;

    public AlertDialogKt$AlertDialogFlowRow$1$1(float f, float f2) {
        this.$mainAxisSpacing = f;
        this.$crossAxisSpacing = f2;
    }

    public static Unit a(List list, MeasureScope measureScope, float f, int i, List list2, Placeable.PlacementScope placementScope) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            List list3 = (List) list.get(i2);
            int size2 = list3.size();
            int[] iArr = new int[size2];
            int i3 = 0;
            while (i3 < size2) {
                iArr[i3] = ((Placeable) list3.get(i3)).getWidth() + (i3 < CollectionsKt.getLastIndex(list3) ? measureScope.mo4551roundToPx0680j_4(f) : 0);
                i3++;
            }
            int[] iArr2 = new int[size2];
            Arrangement.INSTANCE.getEnd().arrange(measureScope, i, iArr, measureScope.getLayoutDirection(), iArr2);
            int size3 = list3.size();
            for (int i4 = 0; i4 < size3; i4++) {
                Placeable.PlacementScope.place$default(placementScope, (Placeable) list3.get(i4), iArr2[i4], ((Number) list2.get(i2)).intValue(), 0.0f, 4, null);
            }
        }
        return Unit.INSTANCE;
    }

    private static final boolean measure_3p2s80s$canAddToCurrentSequence(List<Placeable> list, Ref.IntRef intRef, MeasureScope measureScope, float f, long j, Placeable placeable) {
        return list.isEmpty() || (intRef.element + measureScope.mo4551roundToPx0680j_4(f)) + placeable.getWidth() <= Constraints.m5975getMaxWidthimpl(j);
    }

    private static final void measure_3p2s80s$startNewSequence(List<List<Placeable>> list, Ref.IntRef intRef, MeasureScope measureScope, float f, List<Placeable> list2, List<Integer> list3, Ref.IntRef intRef2, List<Integer> list4, Ref.IntRef intRef3, Ref.IntRef intRef4) {
        if (!list.isEmpty()) {
            intRef.element += measureScope.mo4551roundToPx0680j_4(f);
        }
        list.add(0, CollectionsKt.toList(list2));
        list3.add(Integer.valueOf(intRef2.element));
        list4.add(Integer.valueOf(intRef.element));
        intRef.element += intRef2.element;
        intRef3.element = Math.max(intRef3.element, intRef4.element);
        list2.clear();
        intRef4.element = 0;
        intRef2.element = 0;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo14measure3p2s80s(final MeasureScope measureScope, List<? extends Measurable> list, long j) {
        List<? extends Measurable> list2 = list;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        Ref.IntRef intRef = new Ref.IntRef();
        Ref.IntRef intRef2 = new Ref.IntRef();
        ArrayList arrayList4 = new ArrayList();
        Ref.IntRef intRef3 = new Ref.IntRef();
        Ref.IntRef intRef4 = new Ref.IntRef();
        float f = this.$mainAxisSpacing;
        ArrayList arrayList5 = arrayList3;
        float f2 = this.$crossAxisSpacing;
        int size = list2.size();
        int i = 0;
        while (i < size) {
            ArrayList arrayList6 = arrayList;
            Placeable placeableMo4605measureBRTryo0 = list2.get(i).mo4605measureBRTryo0(j);
            int i2 = i;
            if (!measure_3p2s80s$canAddToCurrentSequence(arrayList4, intRef3, measureScope, f, j, placeableMo4605measureBRTryo0)) {
                Ref.IntRef intRef5 = intRef4;
                ArrayList arrayList7 = arrayList2;
                ArrayList arrayList8 = arrayList4;
                Ref.IntRef intRef6 = intRef;
                ArrayList arrayList9 = arrayList5;
                measure_3p2s80s$startNewSequence(arrayList6, intRef2, measureScope, f2, arrayList8, arrayList7, intRef5, arrayList9, intRef6, intRef3);
                arrayList4 = arrayList8;
                arrayList2 = arrayList7;
                intRef4 = intRef5;
                arrayList5 = arrayList9;
                intRef = intRef6;
                arrayList6 = arrayList6;
            }
            Ref.IntRef intRef7 = intRef2;
            if (!arrayList4.isEmpty()) {
                intRef3.element += measureScope.mo4551roundToPx0680j_4(f);
            }
            arrayList4.add(placeableMo4605measureBRTryo0);
            intRef3.element += placeableMo4605measureBRTryo0.getWidth();
            intRef4.element = Math.max(intRef4.element, placeableMo4605measureBRTryo0.getHeight());
            i = i2 + 1;
            list2 = list;
            intRef2 = intRef7;
            size = size;
            arrayList = arrayList6;
        }
        final ArrayList arrayList10 = arrayList;
        Ref.IntRef intRef8 = intRef2;
        if (!arrayList4.isEmpty()) {
            measure_3p2s80s$startNewSequence(arrayList10, intRef8, measureScope, this.$crossAxisSpacing, arrayList4, arrayList2, intRef4, arrayList5, intRef, intRef3);
        }
        final int iMax = Math.max(intRef.element, Constraints.m5977getMinWidthimpl(j));
        int iMax2 = Math.max(intRef8.element, Constraints.m5976getMinHeightimpl(j));
        final float f3 = this.$mainAxisSpacing;
        final ArrayList arrayList11 = arrayList5;
        return MeasureScope.layout$default(measureScope, iMax, iMax2, null, new Function1() { // from class: androidx.compose.material3.a
            public final Object invoke(Object obj) {
                return AlertDialogKt$AlertDialogFlowRow$1$1.a(arrayList10, measureScope, f3, iMax, arrayList11, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }
}
