package androidx.compose.material3;

import androidx.compose.foundation.gestures.AnchoredDraggableKt;
import androidx.compose.foundation.gestures.AnchoredDraggableState;
import androidx.compose.foundation.gestures.DraggableAnchorsConfig;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
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

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class NavigationDrawerKt$ModalNavigationDrawer$2$6$1 implements MeasurePolicy {
    final /* synthetic */ MutableState<Boolean> $anchorsInitialized$delegate;
    final /* synthetic */ DrawerState $drawerState;
    final /* synthetic */ float $maxValue;
    final /* synthetic */ MutableFloatState $minValue$delegate;

    public NavigationDrawerKt$ModalNavigationDrawer$2$6$1(DrawerState drawerState, MutableState<Boolean> mutableState, MutableFloatState mutableFloatState, float f) {
        this.$drawerState = drawerState;
        this.$anchorsInitialized$delegate = mutableState;
        this.$minValue$delegate = mutableFloatState;
        this.$maxValue = f;
    }

    public static Unit a(float f, MutableFloatState mutableFloatState, DraggableAnchorsConfig draggableAnchorsConfig) {
        draggableAnchorsConfig.at(DrawerValue.Closed, mutableFloatState.getFloatValue());
        draggableAnchorsConfig.at(DrawerValue.Open, f);
        return Unit.INSTANCE;
    }

    public static Unit b(DrawerState drawerState, int i, List list, MutableState mutableState, final MutableFloatState mutableFloatState, final float f, Placeable.PlacementScope placementScope) {
        float fPositionOf = drawerState.getAnchoredDraggableState$material3().getAnchors().positionOf(DrawerValue.Closed);
        float f2 = -i;
        if (!NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$5(mutableState) || fPositionOf != f2) {
            if (!NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$5(mutableState)) {
                NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$6(mutableState, true);
            }
            mutableFloatState.setFloatValue(f2);
            AnchoredDraggableState.updateAnchors$default(drawerState.getAnchoredDraggableState$material3(), AnchoredDraggableKt.DraggableAnchors(new Function1() { // from class: androidx.compose.material3.a3
                public final Object invoke(Object obj) {
                    return NavigationDrawerKt$ModalNavigationDrawer$2$6$1.a(f, mutableFloatState, (DraggableAnchorsConfig) obj);
                }
            }), (Object) null, 2, (Object) null);
        }
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            Placeable.PlacementScope.placeRelative$default(placementScope, (Placeable) list.get(i2), 0, 0, 0.0f, 4, null);
        }
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo14measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
        Integer numValueOf;
        long jM5965copyZbe2FdA$default = Constraints.m5965copyZbe2FdA$default(j, 0, 0, 0, 0, 10, null);
        final ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(list.get(i).mo4605measureBRTryo0(jM5965copyZbe2FdA$default));
        }
        Integer num = null;
        int i2 = 1;
        if (!arrayList.isEmpty()) {
            numValueOf = Integer.valueOf(((Placeable) arrayList.get(0)).getWidth());
            int lastIndex = CollectionsKt.getLastIndex(arrayList);
            if (1 <= lastIndex) {
                int i3 = 1;
                while (true) {
                    Integer numValueOf2 = Integer.valueOf(((Placeable) arrayList.get(i3)).getWidth());
                    if (numValueOf2.compareTo(numValueOf) > 0) {
                        numValueOf = numValueOf2;
                    }
                    if (i3 == lastIndex) {
                        break;
                    }
                    i3++;
                }
            }
        } else {
            numValueOf = null;
        }
        final int iIntValue = numValueOf != null ? numValueOf.intValue() : 0;
        if (!arrayList.isEmpty()) {
            Integer numValueOf3 = Integer.valueOf(((Placeable) arrayList.get(0)).getHeight());
            int lastIndex2 = CollectionsKt.getLastIndex(arrayList);
            if (1 <= lastIndex2) {
                while (true) {
                    Integer numValueOf4 = Integer.valueOf(((Placeable) arrayList.get(i2)).getHeight());
                    if (numValueOf4.compareTo(numValueOf3) > 0) {
                        numValueOf3 = numValueOf4;
                    }
                    if (i2 == lastIndex2) {
                        break;
                    }
                    i2++;
                }
            }
            num = numValueOf3;
        }
        int iIntValue2 = num != null ? num.intValue() : 0;
        final DrawerState drawerState = this.$drawerState;
        final MutableState<Boolean> mutableState = this.$anchorsInitialized$delegate;
        final MutableFloatState mutableFloatState = this.$minValue$delegate;
        final float f = this.$maxValue;
        return MeasureScope.layout$default(measureScope, iIntValue, iIntValue2, null, new Function1() { // from class: androidx.compose.material3.z2
            public final Object invoke(Object obj) {
                return NavigationDrawerKt$ModalNavigationDrawer$2$6$1.b(drawerState, iIntValue, arrayList, mutableState, mutableFloatState, f, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }
}
