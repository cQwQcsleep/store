package androidx.compose.material3;

import android.view.View;
import androidx.compose.foundation.DarkThemeKt;
import androidx.compose.material3.WideNavigationRail_androidKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0000\u001a`\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u0006\u0010\u0006\u001a\u00020\u00012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0011\u0010\r\u001a\r\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\b\u000eH\u0001¢\u0006\u0002\u0010\u000f¨\u0006\u0010²\u0006\u0015\u0010\u0011\u001a\r\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\b\u000eX\u008a\u0084\u0002"}, d2 = {"createDefaultModalWideNavigationRailProperties", "Landroidx/compose/material3/ModalWideNavigationRailProperties;", "ModalWideNavigationRailDialog", "", "onDismissRequest", "Lkotlin/Function0;", "properties", "onPredictiveBack", "Lkotlin/Function1;", "", "onPredictiveBackCancelled", "predictiveBackState", "Landroidx/compose/material3/RailPredictiveBackState;", "content", "Landroidx/compose/runtime/Composable;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/material3/ModalWideNavigationRailProperties;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/material3/RailPredictiveBackState;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "material3", "currentContent"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class WideNavigationRail_androidKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final void ModalWideNavigationRailDialog(Function0<Unit> function0, final ModalWideNavigationRailProperties modalWideNavigationRailProperties, final Function1<? super Float, Unit> function1, final Function0<Unit> function2, final RailPredictiveBackState railPredictiveBackState, final Function2<? super Composer, ? super Integer, Unit> function3, Composer composer, final int i) {
        int i2;
        int i3;
        int i4;
        final Function0<Unit> function4 = function0;
        Composer composerStartRestartGroup = composer.startRestartGroup(2015914411);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(function4) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(modalWideNavigationRailProperties) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function1) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changed(railPredictiveBackState) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function3) ? 131072 : 65536;
        }
        if (composerStartRestartGroup.shouldExecute((74899 & i2) != 74898, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2015914411, i2, -1, "androidx.compose.material3.ModalWideNavigationRailDialog (WideNavigationRail.android.kt:124)");
            }
            View view = (View) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalView());
            Density density = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
            final LayoutDirection layoutDirection = (LayoutDirection) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalLayoutDirection());
            CompositionContext compositionContextRememberCompositionContext = ComposablesKt.rememberCompositionContext(composerStartRestartGroup, 0);
            State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(function3, composerStartRestartGroup, (i2 >> 15) & 14);
            Object[] objArr = new Object[0];
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = new Function0() { // from class: dnf
                    public final Object invoke() {
                        return WideNavigationRail_androidKt.c();
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            UUID uuid = (UUID) RememberSaveableKt.rememberSaveable(objArr, (Function0) objRememberedValue, composerStartRestartGroup, 48);
            boolean zIsSystemInDarkTheme = DarkThemeKt.isSystemInDarkTheme(composerStartRestartGroup, 0);
            boolean zChanged = composerStartRestartGroup.changed(view) | composerStartRestartGroup.changed(density);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue2 == companion.getEmpty()) {
                i3 = 0;
                function4 = function0;
                ModalWideNavigationRailDialogWrapper modalWideNavigationRailDialogWrapper = new ModalWideNavigationRailDialogWrapper(function4, modalWideNavigationRailProperties, view, layoutDirection, density, uuid, function1, function2, railPredictiveBackState, zIsSystemInDarkTheme);
                WideNavigationRail_androidKt$ModalWideNavigationRailDialog$dialog$1$1$1 wideNavigationRail_androidKt$ModalWideNavigationRailDialog$dialog$1$1$1 = new WideNavigationRail_androidKt$ModalWideNavigationRailDialog$dialog$1$1$1(stateRememberUpdatedState);
                i4 = 1;
                modalWideNavigationRailDialogWrapper.setContent(compositionContextRememberCompositionContext, ComposableLambdaKt.composableLambdaInstance(-406777160, true, wideNavigationRail_androidKt$ModalWideNavigationRailDialog$dialog$1$1$1));
                composerStartRestartGroup.updateRememberedValue(modalWideNavigationRailDialogWrapper);
                objRememberedValue2 = modalWideNavigationRailDialogWrapper;
            } else {
                i4 = 1;
                i3 = 0;
                function4 = function0;
            }
            final ModalWideNavigationRailDialogWrapper modalWideNavigationRailDialogWrapper2 = (ModalWideNavigationRailDialogWrapper) objRememberedValue2;
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(modalWideNavigationRailDialogWrapper2);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue3 == companion.getEmpty()) {
                objRememberedValue3 = new Function1() { // from class: androidx.compose.material3.t5
                    public final Object invoke(Object obj) {
                        return WideNavigationRail_androidKt.b(modalWideNavigationRailDialogWrapper2, (DisposableEffectScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            EffectsKt.DisposableEffect(modalWideNavigationRailDialogWrapper2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue3, composerStartRestartGroup, i3);
            int i5 = (composerStartRestartGroup.changedInstance(modalWideNavigationRailDialogWrapper2) ? 1 : 0) | ((i2 & 14) == 4 ? i4 : i3);
            if ((i2 & 112) != 32) {
                i4 = i3;
            }
            int i6 = i5 | i4 | (composerStartRestartGroup.changed(layoutDirection.ordinal()) ? 1 : 0);
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (i6 != 0 || objRememberedValue4 == companion.getEmpty()) {
                objRememberedValue4 = new Function0() { // from class: androidx.compose.material3.u5
                    public final Object invoke() {
                        return WideNavigationRail_androidKt.d(modalWideNavigationRailDialogWrapper2, function4, modalWideNavigationRailProperties, layoutDirection);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            EffectsKt.SideEffect((Function0) objRememberedValue4, composerStartRestartGroup, i3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: enf
                public final Object invoke(Object obj, Object obj2) {
                    return WideNavigationRail_androidKt.a(function4, modalWideNavigationRailProperties, function1, function2, railPredictiveBackState, function3, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Function2<Composer, Integer, Unit> ModalWideNavigationRailDialog$lambda$0(State<? extends Function2<? super Composer, ? super Integer, Unit>> state) {
        return state.getValue();
    }

    public static Unit a(Function0 function0, ModalWideNavigationRailProperties modalWideNavigationRailProperties, Function1 function1, Function0 function2, RailPredictiveBackState railPredictiveBackState, Function2 function3, int i, Composer composer, int i2) {
        ModalWideNavigationRailDialog(function0, modalWideNavigationRailProperties, function1, function2, railPredictiveBackState, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static DisposableEffectResult b(final ModalWideNavigationRailDialogWrapper modalWideNavigationRailDialogWrapper, DisposableEffectScope disposableEffectScope) {
        modalWideNavigationRailDialogWrapper.show();
        return new DisposableEffectResult() { // from class: androidx.compose.material3.WideNavigationRail_androidKt$ModalWideNavigationRailDialog$lambda$7$lambda$6$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                modalWideNavigationRailDialogWrapper.dismiss();
                modalWideNavigationRailDialogWrapper.disposeComposition();
            }
        };
    }

    public static UUID c() {
        return UUID.randomUUID();
    }

    public static final ModalWideNavigationRailProperties createDefaultModalWideNavigationRailProperties() {
        return new ModalWideNavigationRailProperties(false, 1, null);
    }

    public static Unit d(ModalWideNavigationRailDialogWrapper modalWideNavigationRailDialogWrapper, Function0 function0, ModalWideNavigationRailProperties modalWideNavigationRailProperties, LayoutDirection layoutDirection) {
        modalWideNavigationRailDialogWrapper.updateParameters(function0, modalWideNavigationRailProperties, layoutDirection);
        return Unit.INSTANCE;
    }
}
