package androidx.compose.foundation.text.contextmenu.provider;

import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.text.contextmenu.data.TextContextMenuSession;
import androidx.compose.foundation.text.contextmenu.provider.BasicTextContextMenuProviderKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.node.ComposeUiNode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function5;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0088\u0001\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00032V\u0010\u0005\u001aR\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u00102\u0011\u0010\u0011\u001a\r\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u0010H\u0001¢\u0006\u0002\u0010\u0012\u001a\u0090\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00142\u000e\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00032V\u0010\u0005\u001aR\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u00102\u0011\u0010\u0011\u001a\r\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u0010H\u0001¢\u0006\u0002\u0010\u0015\u001ae\u0010\u0016\u001a\u00020\u00172V\u0010\u0005\u001aR\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u0010H\u0001¢\u0006\u0002\u0010\u0018¨\u0006\u0019²\u0006\f\u0010\u001a\u001a\u0004\u0018\u00010\u000eX\u008a\u008e\u0002"}, d2 = {"ProvideBasicTextContextMenu", "", "providableCompositionLocal", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/foundation/text/contextmenu/provider/TextContextMenuProvider;", "contextMenu", "Lkotlin/Function3;", "Landroidx/compose/foundation/text/contextmenu/data/TextContextMenuSession;", "Lkotlin/ParameterName;", "name", "session", "Landroidx/compose/foundation/text/contextmenu/provider/TextContextMenuDataProvider;", "dataProvider", "Lkotlin/Function0;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "anchorLayoutCoordinates", "Landroidx/compose/runtime/Composable;", "content", "(Landroidx/compose/runtime/ProvidableCompositionLocal;Lkotlin/jvm/functions/Function5;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "modifier", "Landroidx/compose/ui/Modifier;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/ProvidableCompositionLocal;Lkotlin/jvm/functions/Function5;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "basicTextContextMenuProvider", "Landroidx/compose/foundation/text/contextmenu/provider/BasicTextContextMenuProvider;", "(Lkotlin/jvm/functions/Function5;Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/text/contextmenu/provider/BasicTextContextMenuProvider;", "foundation", "layoutCoordinates"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class BasicTextContextMenuProviderKt {
    public static final void ProvideBasicTextContextMenu(final Modifier modifier, final ProvidableCompositionLocal<TextContextMenuProvider> providableCompositionLocal, final Function5<? super TextContextMenuSession, ? super TextContextMenuDataProvider, ? super Function0<? extends LayoutCoordinates>, ? super Composer, ? super Integer, Unit> function5, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-714464401);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(providableCompositionLocal) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function5) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 2048 : 1024;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 1171) != 1170, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-714464401, i2, -1, "androidx.compose.foundation.text.contextmenu.provider.ProvideBasicTextContextMenu (BasicTextContextMenuProvider.kt:80)");
            }
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = SnapshotStateKt.mutableStateOf((Object) null, SnapshotStateKt.neverEqualPolicy());
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final MutableState mutableState = (MutableState) objRememberedValue;
            final BasicTextContextMenuProvider basicTextContextMenuProvider = basicTextContextMenuProvider(function5, composerStartRestartGroup, (i2 >> 6) & 14);
            CompositionLocalKt.CompositionLocalProvider(providableCompositionLocal.provides(basicTextContextMenuProvider), ComposableLambdaKt.rememberComposableLambda(274270255, true, new Function2() { // from class: pq0
                public final Object invoke(Object obj, Object obj2) {
                    return BasicTextContextMenuProviderKt.e(modifier, mutableState, function2, basicTextContextMenuProvider, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: qq0
                public final Object invoke(Object obj, Object obj2) {
                    return BasicTextContextMenuProviderKt.a(modifier, providableCompositionLocal, function5, function2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final LayoutCoordinates ProvideBasicTextContextMenu$lambda$2(MutableState<LayoutCoordinates> mutableState) {
        return (LayoutCoordinates) mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProvideBasicTextContextMenu$lambda$4$0$0(MutableState mutableState, LayoutCoordinates layoutCoordinates) {
        mutableState.setValue(layoutCoordinates);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LayoutCoordinates ProvideBasicTextContextMenu$lambda$4$1$0$0(MutableState mutableState) {
        LayoutCoordinates layoutCoordinatesProvideBasicTextContextMenu$lambda$2 = ProvideBasicTextContextMenu$lambda$2(mutableState);
        if (layoutCoordinatesProvideBasicTextContextMenu$lambda$2 != null) {
            return layoutCoordinatesProvideBasicTextContextMenu$lambda$2;
        }
        InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Required value was null.");
        wq6.a();
        return null;
    }

    public static Unit a(Modifier modifier, ProvidableCompositionLocal providableCompositionLocal, Function5 function5, Function2 function2, int i, Composer composer, int i2) {
        ProvideBasicTextContextMenu(modifier, providableCompositionLocal, function5, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final BasicTextContextMenuProvider basicTextContextMenuProvider(Function5<? super TextContextMenuSession, ? super TextContextMenuDataProvider, ? super Function0<? extends LayoutCoordinates>, ? super Composer, ? super Integer, Unit> function5, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(100861460, i, -1, "androidx.compose.foundation.text.contextmenu.provider.basicTextContextMenuProvider (BasicTextContextMenuProvider.kt:106)");
        }
        boolean z = (((i & 14) ^ 6) > 4 && composer.changed(function5)) || (i & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = new BasicTextContextMenuProvider(function5);
            composer.updateRememberedValue(objRememberedValue);
        }
        final BasicTextContextMenuProvider basicTextContextMenuProvider = (BasicTextContextMenuProvider) objRememberedValue;
        boolean zChanged = composer.changed(basicTextContextMenuProvider);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged || objRememberedValue2 == Composer.Companion.getEmpty()) {
            objRememberedValue2 = new Function1() { // from class: mq0
                public final Object invoke(Object obj) {
                    return BasicTextContextMenuProviderKt.basicTextContextMenuProvider$lambda$1$0(basicTextContextMenuProvider, (DisposableEffectScope) obj);
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        EffectsKt.DisposableEffect(basicTextContextMenuProvider, (Function1) objRememberedValue2, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return basicTextContextMenuProvider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult basicTextContextMenuProvider$lambda$1$0(final BasicTextContextMenuProvider basicTextContextMenuProvider, DisposableEffectScope disposableEffectScope) {
        return new DisposableEffectResult() { // from class: androidx.compose.foundation.text.contextmenu.provider.BasicTextContextMenuProviderKt$basicTextContextMenuProvider$lambda$1$0$$inlined$onDispose$1
            public void dispose() {
                basicTextContextMenuProvider.cancel();
            }
        };
    }

    public static Unit e(Modifier modifier, final MutableState mutableState, Function2 function2, BasicTextContextMenuProvider basicTextContextMenuProvider, Composer composer, int i) {
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(274270255, i, -1, "androidx.compose.foundation.text.contextmenu.provider.ProvideBasicTextContextMenu.<anonymous> (BasicTextContextMenuProvider.kt:87)");
            }
            Object objRememberedValue = composer.rememberedValue();
            Composer.Companion companion = Composer.Companion;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = new Function1() { // from class: nq0
                    public final Object invoke(Object obj) {
                        return BasicTextContextMenuProviderKt.ProvideBasicTextContextMenu$lambda$4$0$0(mutableState, (LayoutCoordinates) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            Modifier modifierOnGloballyPositioned = OnGloballyPositionedModifierKt.onGloballyPositioned(modifier, (Function1) objRememberedValue);
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), true);
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierOnGloballyPositioned);
            ComposeUiNode.Companion companion2 = ComposeUiNode.Companion;
            Function0 constructor = companion2.getConstructor();
            if (composer.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor);
            } else {
                composer.useNode();
            }
            Composer composer2 = Updater.constructor-impl(composer);
            Updater.set-impl(composer2, measurePolicyMaybeCachedBoxMeasurePolicy, companion2.getSetMeasurePolicy());
            Updater.set-impl(composer2, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
            Updater.init-impl(composer2, Integer.valueOf(iHashCode), companion2.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer2, companion2.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer2, modifierMaterializeModifier, companion2.getSetModifier());
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            function2.invoke(composer, 0);
            Object objRememberedValue2 = composer.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new Function0() { // from class: oq0
                    public final Object invoke() {
                        return BasicTextContextMenuProviderKt.ProvideBasicTextContextMenu$lambda$4$1$0$0(mutableState);
                    }
                };
                composer.updateRememberedValue(objRememberedValue2);
            }
            basicTextContextMenuProvider.ContextMenu((Function0) objRememberedValue2, composer, 6);
            composer.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit f(ProvidableCompositionLocal providableCompositionLocal, Function5 function5, Function2 function2, int i, Composer composer, int i2) {
        ProvideBasicTextContextMenu(providableCompositionLocal, function5, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void ProvideBasicTextContextMenu(ProvidableCompositionLocal<TextContextMenuProvider> providableCompositionLocal, Function5<? super TextContextMenuSession, ? super TextContextMenuDataProvider, ? super Function0<? extends LayoutCoordinates>, ? super Composer, ? super Integer, Unit> function5, Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        int i2;
        final ProvidableCompositionLocal<TextContextMenuProvider> providableCompositionLocal2;
        final Function5<? super TextContextMenuSession, ? super TextContextMenuDataProvider, ? super Function0<? extends LayoutCoordinates>, ? super Composer, ? super Integer, Unit> function6;
        final Function2<? super Composer, ? super Integer, Unit> function3;
        Composer composerStartRestartGroup = composer.startRestartGroup(2072681886);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(providableCompositionLocal) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function5) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 256 : 128;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 147) != 146, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2072681886, i2, -1, "androidx.compose.foundation.text.contextmenu.provider.ProvideBasicTextContextMenu (BasicTextContextMenuProvider.kt:64)");
            }
            int i3 = i2 << 3;
            providableCompositionLocal2 = providableCompositionLocal;
            function6 = function5;
            function3 = function2;
            ProvideBasicTextContextMenu(Modifier.Companion, providableCompositionLocal2, function6, function3, composerStartRestartGroup, (i3 & 112) | 6 | (i3 & 896) | (i3 & 7168));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            providableCompositionLocal2 = providableCompositionLocal;
            function6 = function5;
            function3 = function2;
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: lq0
                public final Object invoke(Object obj, Object obj2) {
                    return BasicTextContextMenuProviderKt.f(providableCompositionLocal2, function6, function3, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
