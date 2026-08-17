package androidx.compose.material3;

import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.material3.internal.FloatProducer;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class NavigationDrawerKt$DismissibleDrawerSheet$2 implements Function3<DrawerPredictiveBackState, Composer, Integer, Unit> {
    final /* synthetic */ Function3<ColumnScope, Composer, Integer, Unit> $content;
    final /* synthetic */ long $drawerContainerColor;
    final /* synthetic */ long $drawerContentColor;
    final /* synthetic */ Shape $drawerShape;
    final /* synthetic */ DrawerState $drawerState;
    final /* synthetic */ float $drawerTonalElevation;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ WindowInsets $windowInsets;

    public NavigationDrawerKt$DismissibleDrawerSheet$2(WindowInsets windowInsets, Modifier modifier, Shape shape, long j, long j2, float f, DrawerState drawerState, Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3) {
        this.$windowInsets = windowInsets;
        this.$modifier = modifier;
        this.$drawerShape = shape;
        this.$drawerContainerColor = j;
        this.$drawerContentColor = j2;
        this.$drawerTonalElevation = f;
        this.$drawerState = drawerState;
        this.$content = function3;
    }

    public static float a(DrawerState drawerState) {
        return drawerState.getAnchoredDraggableState$material3().getOffset();
    }

    public final void invoke(DrawerPredictiveBackState drawerPredictiveBackState, Composer composer, int i) {
        int i2;
        if ((i & 6) == 0) {
            i2 = i | (composer.changed(drawerPredictiveBackState) ? 4 : 2);
        } else {
            i2 = i;
        }
        if (!composer.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1623455535, i2, -1, "androidx.compose.material3.DismissibleDrawerSheet.<anonymous> (NavigationDrawer.kt:732)");
        }
        WindowInsets windowInsets = this.$windowInsets;
        Modifier modifier = this.$modifier;
        Shape shape = this.$drawerShape;
        long j = this.$drawerContainerColor;
        long j2 = this.$drawerContentColor;
        float f = this.$drawerTonalElevation;
        boolean zChanged = composer.changed(this.$drawerState);
        final DrawerState drawerState = this.$drawerState;
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new FloatProducer() { // from class: androidx.compose.material3.u2
                @Override // androidx.compose.material3.internal.FloatProducer
                public final float invoke() {
                    return NavigationDrawerKt$DismissibleDrawerSheet$2.a(drawerState);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        NavigationDrawerKt.m665DrawerSheetcm3T3N0(drawerPredictiveBackState, windowInsets, modifier, shape, j, j2, f, (FloatProducer) objRememberedValue, this.$content, composer, i2 & 14, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(DrawerPredictiveBackState drawerPredictiveBackState, Composer composer, Integer num) {
        invoke(drawerPredictiveBackState, composer, num.intValue());
        return Unit.INSTANCE;
    }
}
