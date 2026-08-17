package androidx.compose.foundation.lazy.layout;

import androidx.compose.foundation.lazy.layout.LazyLayoutKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.SaveableStateHolder;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.SubcomposeLayoutKt;
import androidx.compose.ui.layout.SubcomposeLayoutState;
import androidx.compose.ui.layout.SubcomposeMeasureScope;
import androidx.compose.ui.unit.Constraints;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u001aP\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\u001d\u0010\t\u001a\u0019\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\n¢\u0006\u0002\b\u000eH\u0007¢\u0006\u0002\u0010\u000f\u001a9\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\u0010H\u0007¢\u0006\u0002\u0010\u0011\"\u000e\u0010\u0012\u001a\u00020\u0013X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"LazyLayout", "", "itemProvider", "Lkotlin/Function0;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemProvider;", "modifier", "Landroidx/compose/ui/Modifier;", "prefetchState", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;", "measurePolicy", "Lkotlin/Function2;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/ui/layout/MeasureResult;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasurePolicy;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasurePolicy;Landroidx/compose/runtime/Composer;II)V", "MaxItemsToRetainForReuse", "", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class LazyLayoutKt {
    private static final int MaxItemsToRetainForReuse = 7;

    public static final void LazyLayout(final Function0<? extends LazyLayoutItemProvider> function0, final Modifier modifier, final LazyLayoutPrefetchState lazyLayoutPrefetchState, final LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy, Composer composer, final int i, final int i2) {
        int i3;
        Composer composerStartRestartGroup = composer.startRestartGroup(1055276397);
        if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changedInstance(function0) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i4 = i2 & 2;
        if (i4 != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(modifier) ? 32 : 16;
        }
        int i5 = i2 & 4;
        if (i5 != 0) {
            i3 |= 384;
        } else if ((i & 384) == 0) {
            i3 |= composerStartRestartGroup.changed(lazyLayoutPrefetchState) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i3 |= (i & 4096) == 0 ? composerStartRestartGroup.changed(lazyLayoutMeasurePolicy) : composerStartRestartGroup.changedInstance(lazyLayoutMeasurePolicy) ? 2048 : 1024;
        }
        if (composerStartRestartGroup.shouldExecute((i3 & 1171) != 1170, i3 & 1)) {
            if (i4 != 0) {
                modifier = Modifier.Companion;
            }
            if (i5 != 0) {
                lazyLayoutPrefetchState = null;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1055276397, i3, -1, "androidx.compose.foundation.lazy.layout.LazyLayout (LazyLayout.kt:111)");
            }
            final State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(function0, composerStartRestartGroup, i3 & 14);
            LazySaveableStateHolderKt.LazySaveableStateHolderProvider(ComposableLambdaKt.rememberComposableLambda(-933153643, true, new Function3() { // from class: hu8
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return LazyLayoutKt.a(lazyLayoutPrefetchState, modifier, lazyLayoutMeasurePolicy, stateRememberUpdatedState, (SaveableStateHolder) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        final Modifier modifier2 = modifier;
        final LazyLayoutPrefetchState lazyLayoutPrefetchState2 = lazyLayoutPrefetchState;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: iu8
                public final Object invoke(Object obj, Object obj2) {
                    return LazyLayoutKt.d(function0, modifier2, lazyLayoutPrefetchState2, lazyLayoutMeasurePolicy, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LazyLayoutItemProvider LazyLayout$lambda$1$0$0(State state) {
        return (LazyLayoutItemProvider) ((Function0) state.getValue()).invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult LazyLayout$lambda$1$2$0(final LazyLayoutPrefetchState lazyLayoutPrefetchState, LazyLayoutItemContentFactory lazyLayoutItemContentFactory, SubcomposeLayoutState subcomposeLayoutState, PrefetchScheduler prefetchScheduler, DisposableEffectScope disposableEffectScope) {
        lazyLayoutPrefetchState.setPrefetchHandleProvider$foundation(new PrefetchHandleProvider(lazyLayoutItemContentFactory, subcomposeLayoutState, prefetchScheduler));
        return new DisposableEffectResult() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutKt$LazyLayout$lambda$1$2$0$$inlined$onDispose$1
            public void dispose() {
                PrefetchHandleProvider prefetchHandleProvider = lazyLayoutPrefetchState.getPrefetchHandleProvider();
                if (prefetchHandleProvider != null) {
                    prefetchHandleProvider.onDisposed();
                }
                lazyLayoutPrefetchState.setPrefetchHandleProvider$foundation(null);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MeasureResult LazyLayout$lambda$1$3$0(LazyLayoutItemContentFactory lazyLayoutItemContentFactory, LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy, SubcomposeMeasureScope subcomposeMeasureScope, Constraints constraints) {
        return lazyLayoutMeasurePolicy.mo1053measure0kLqBqw(new LazyLayoutMeasureScopeImpl(lazyLayoutItemContentFactory, subcomposeMeasureScope), constraints.unbox-impl());
    }

    public static Unit a(final LazyLayoutPrefetchState lazyLayoutPrefetchState, Modifier modifier, final LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy, final State state, SaveableStateHolder saveableStateHolder, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-933153643, i, -1, "androidx.compose.foundation.lazy.layout.LazyLayout.<anonymous> (LazyLayout.kt:115)");
        }
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.Companion;
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = new LazyLayoutItemContentFactory(saveableStateHolder, new Function0() { // from class: ju8
                public final Object invoke() {
                    return LazyLayoutKt.LazyLayout$lambda$1$0$0(state);
                }
            });
            composer.updateRememberedValue(objRememberedValue);
        }
        final LazyLayoutItemContentFactory lazyLayoutItemContentFactory = (LazyLayoutItemContentFactory) objRememberedValue;
        Object objRememberedValue2 = composer.rememberedValue();
        if (objRememberedValue2 == companion.getEmpty()) {
            objRememberedValue2 = new SubcomposeLayoutState(new LazyLayoutItemReusePolicy(lazyLayoutItemContentFactory));
            composer.updateRememberedValue(objRememberedValue2);
        }
        final SubcomposeLayoutState subcomposeLayoutState = (SubcomposeLayoutState) objRememberedValue2;
        if (lazyLayoutPrefetchState != null) {
            composer.startReplaceGroup(1743490539);
            final PrefetchScheduler prefetchScheduler = lazyLayoutPrefetchState.getPrefetchScheduler();
            if (prefetchScheduler == null) {
                composer.startReplaceGroup(887527095);
                prefetchScheduler = PrefetchScheduler_androidKt.rememberDefaultPrefetchScheduler(composer, 0);
            } else {
                composer.startReplaceGroup(887526010);
            }
            composer.endReplaceGroup();
            Object[] objArr = {lazyLayoutPrefetchState, lazyLayoutItemContentFactory, subcomposeLayoutState, prefetchScheduler};
            boolean zChanged = composer.changed(lazyLayoutPrefetchState) | composer.changedInstance(lazyLayoutItemContentFactory) | composer.changedInstance(subcomposeLayoutState) | composer.changedInstance(prefetchScheduler);
            Object objRememberedValue3 = composer.rememberedValue();
            if (zChanged || objRememberedValue3 == companion.getEmpty()) {
                objRememberedValue3 = new Function1() { // from class: ku8
                    public final Object invoke(Object obj) {
                        return LazyLayoutKt.LazyLayout$lambda$1$2$0(lazyLayoutPrefetchState, lazyLayoutItemContentFactory, subcomposeLayoutState, prefetchScheduler, (DisposableEffectScope) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue3);
            }
            EffectsKt.DisposableEffect(objArr, (Function1) objRememberedValue3, composer, 0);
        } else {
            composer.startReplaceGroup(1737291469);
        }
        composer.endReplaceGroup();
        Modifier modifierTraversablePrefetchState = LazyLayoutPrefetchStateKt.traversablePrefetchState(modifier, lazyLayoutPrefetchState);
        boolean zChanged2 = composer.changed(lazyLayoutItemContentFactory) | composer.changed(lazyLayoutMeasurePolicy);
        Object objRememberedValue4 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue4 == companion.getEmpty()) {
            objRememberedValue4 = new Function2() { // from class: lu8
                public final Object invoke(Object obj, Object obj2) {
                    return LazyLayoutKt.LazyLayout$lambda$1$3$0(lazyLayoutItemContentFactory, lazyLayoutMeasurePolicy, (SubcomposeMeasureScope) obj, (Constraints) obj2);
                }
            };
            composer.updateRememberedValue(objRememberedValue4);
        }
        SubcomposeLayoutKt.SubcomposeLayout(subcomposeLayoutState, modifierTraversablePrefetchState, (Function2) objRememberedValue4, composer, SubcomposeLayoutState.$stable, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit c(Function0 function0, Modifier modifier, LazyLayoutPrefetchState lazyLayoutPrefetchState, Function2 function2, int i, int i2, Composer composer, int i3) {
        LazyLayout(function0, modifier, lazyLayoutPrefetchState, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit d(Function0 function0, Modifier modifier, LazyLayoutPrefetchState lazyLayoutPrefetchState, LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy, int i, int i2, Composer composer, int i3) {
        LazyLayout((Function0<? extends LazyLayoutItemProvider>) function0, modifier, lazyLayoutPrefetchState, lazyLayoutMeasurePolicy, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Please use overload with LazyLayoutMeasurePolicy")
    public static final /* synthetic */ void LazyLayout(final Function0 function0, Modifier modifier, LazyLayoutPrefetchState lazyLayoutPrefetchState, final Function2 function2, Composer composer, final int i, final int i2) {
        int i3;
        final Modifier modifier2;
        final LazyLayoutPrefetchState lazyLayoutPrefetchState2;
        Composer composerStartRestartGroup = composer.startRestartGroup(2002163445);
        if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changedInstance(function0) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i4 = i2 & 2;
        if (i4 != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(modifier) ? 32 : 16;
        }
        int i5 = i2 & 4;
        if (i5 != 0) {
            i3 |= 384;
        } else if ((i & 384) == 0) {
            i3 |= composerStartRestartGroup.changed(lazyLayoutPrefetchState) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function2) ? 2048 : 1024;
        }
        if (composerStartRestartGroup.shouldExecute((i3 & 1171) != 1170, i3 & 1)) {
            if (i4 != 0) {
                modifier = Modifier.Companion;
            }
            Modifier modifier3 = modifier;
            LazyLayoutPrefetchState lazyLayoutPrefetchState3 = i5 != 0 ? null : lazyLayoutPrefetchState;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2002163445, i3, -1, "androidx.compose.foundation.lazy.layout.LazyLayout (LazyLayout.kt:68)");
            }
            LazyLayout((Function0<? extends LazyLayoutItemProvider>) function0, modifier3, lazyLayoutPrefetchState3, new LazyLayoutKt$sam$androidx_compose_foundation_lazy_layout_LazyLayoutMeasurePolicy$0(function2), composerStartRestartGroup, i3 & 1022, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
            lazyLayoutPrefetchState2 = lazyLayoutPrefetchState3;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier2 = modifier;
            lazyLayoutPrefetchState2 = lazyLayoutPrefetchState;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gu8
                public final Object invoke(Object obj, Object obj2) {
                    return LazyLayoutKt.c(function0, modifier2, lazyLayoutPrefetchState2, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
