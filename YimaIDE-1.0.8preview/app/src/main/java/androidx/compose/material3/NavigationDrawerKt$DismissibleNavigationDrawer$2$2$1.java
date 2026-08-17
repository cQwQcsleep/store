package androidx.compose.material3;

import androidx.compose.foundation.gestures.AnchoredDraggableKt;
import androidx.compose.foundation.gestures.AnchoredDraggableState;
import androidx.compose.foundation.gestures.DraggableAnchorsConfig;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.math.MathKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class NavigationDrawerKt$DismissibleNavigationDrawer$2$2$1 implements MeasurePolicy {
    final /* synthetic */ MutableState<Boolean> $anchorsInitialized$delegate;
    final /* synthetic */ DrawerState $drawerState;

    public NavigationDrawerKt$DismissibleNavigationDrawer$2$2$1(DrawerState drawerState, MutableState<Boolean> mutableState) {
        this.$drawerState = drawerState;
        this.$anchorsInitialized$delegate = mutableState;
    }

    public static Unit a(float f, DraggableAnchorsConfig draggableAnchorsConfig) {
        draggableAnchorsConfig.at(DrawerValue.Closed, f);
        draggableAnchorsConfig.at(DrawerValue.Open, 0.0f);
        return Unit.INSTANCE;
    }

    public static Unit b(DrawerState drawerState, Placeable placeable, Placeable placeable2, MutableState mutableState, Placeable.PlacementScope placementScope) {
        float fPositionOf = drawerState.getAnchoredDraggableState$material3().getAnchors().positionOf(DrawerValue.Closed);
        final float f = -placeable.getWidth();
        if (!NavigationDrawerKt.DismissibleNavigationDrawer$lambda$27(mutableState) || fPositionOf != f) {
            if (!NavigationDrawerKt.DismissibleNavigationDrawer$lambda$27(mutableState)) {
                NavigationDrawerKt.DismissibleNavigationDrawer$lambda$28(mutableState, true);
            }
            AnchoredDraggableState.updateAnchors$default(drawerState.getAnchoredDraggableState$material3(), AnchoredDraggableKt.DraggableAnchors(new Function1() { // from class: androidx.compose.material3.w2
                public final Object invoke(Object obj) {
                    return NavigationDrawerKt$DismissibleNavigationDrawer$2$2$1.a(f, (DraggableAnchorsConfig) obj);
                }
            }), (Object) null, 2, (Object) null);
        }
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, placeable.getWidth() + MathKt.roundToInt(drawerState.requireOffset$material3()), 0, 0.0f, 4, null);
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable, MathKt.roundToInt(drawerState.requireOffset$material3()), 0, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo14measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
        final Placeable placeableMo4605measureBRTryo0 = list.get(0).mo4605measureBRTryo0(j);
        final Placeable placeableMo4605measureBRTryo1 = list.get(1).mo4605measureBRTryo0(j);
        int width = placeableMo4605measureBRTryo1.getWidth();
        int height = placeableMo4605measureBRTryo1.getHeight();
        final DrawerState drawerState = this.$drawerState;
        final MutableState<Boolean> mutableState = this.$anchorsInitialized$delegate;
        return MeasureScope.layout$default(measureScope, width, height, null, new Function1() { // from class: androidx.compose.material3.v2
            public final Object invoke(Object obj) {
                return NavigationDrawerKt$DismissibleNavigationDrawer$2$2$1.b(drawerState, placeableMo4605measureBRTryo0, placeableMo4605measureBRTryo1, mutableState, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }
}
