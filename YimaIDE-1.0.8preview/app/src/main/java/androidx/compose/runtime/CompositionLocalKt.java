package androidx.compose.runtime;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a0\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006\u001a \u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006\u001a+\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u0002H\u00020\n¢\u0006\u0002\b\f\u001a<\u0010\r\u001a\u00020\u000e2\u001a\u0010\u000f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00110\u0010\"\u0006\u0012\u0002\b\u00030\u00112\u0011\u0010\u0012\u001a\r\u0012\u0004\u0012\u00020\u000e0\u0006¢\u0006\u0002\b\u0013H\u0007¢\u0006\u0002\u0010\u0014\u001a,\u0010\r\u001a\u00020\u000e2\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u00112\u0011\u0010\u0012\u001a\r\u0012\u0004\u0012\u00020\u000e0\u0006¢\u0006\u0002\b\u0013H\u0007¢\u0006\u0002\u0010\u0016\u001a(\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u00182\u0011\u0010\u0012\u001a\r\u0012\u0004\u0012\u00020\u000e0\u0006¢\u0006\u0002\b\u0013H\u0007¢\u0006\u0002\u0010\u0019\u001a3\u0010\u001a\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u00022\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u00112\u0011\u0010\u0012\u001a\r\u0012\u0004\u0012\u0002H\u00020\u0006¢\u0006\u0002\b\u0013H\u0087\b¢\u0006\u0002\u0010\u001b\u001aC\u0010\u001c\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u00022\u001a\u0010\u000f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00110\u0010\"\u0006\u0012\u0002\b\u00030\u00112\u0011\u0010\u0012\u001a\r\u0012\u0004\u0012\u0002H\u00020\u0006¢\u0006\u0002\b\u0013H\u0087\b¢\u0006\u0002\u0010\u001d¨\u0006\u001e"}, d2 = {"compositionLocalOf", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "T", "policy", "Landroidx/compose/runtime/SnapshotMutationPolicy;", "defaultFactory", "Lkotlin/Function0;", "staticCompositionLocalOf", "compositionLocalWithComputedDefaultOf", "defaultComputation", "Lkotlin/Function1;", "Landroidx/compose/runtime/CompositionLocalAccessorScope;", "Lkotlin/ExtensionFunctionType;", "CompositionLocalProvider", "", "values", "", "Landroidx/compose/runtime/ProvidedValue;", "content", "Landroidx/compose/runtime/Composable;", "([Landroidx/compose/runtime/ProvidedValue;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "value", "(Landroidx/compose/runtime/ProvidedValue;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "context", "Landroidx/compose/runtime/CompositionLocalContext;", "(Landroidx/compose/runtime/CompositionLocalContext;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "withCompositionLocal", "(Landroidx/compose/runtime/ProvidedValue;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)Ljava/lang/Object;", "withCompositionLocals", "([Landroidx/compose/runtime/ProvidedValue;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)Ljava/lang/Object;", "runtime"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class CompositionLocalKt {
    public static final void CompositionLocalProvider(final CompositionLocalContext compositionLocalContext, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-1162003659);
        int i2 = (i & 6) == 0 ? (composerStartRestartGroup.changed(compositionLocalContext) ? 4 : 2) | i : i;
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1162003659, i2, -1, "androidx.compose.runtime.CompositionLocalProvider (CompositionLocal.kt:416)");
            }
            PersistentCompositionLocalMap compositionLocals$runtime = compositionLocalContext.getCompositionLocals();
            ArrayList arrayList = new ArrayList(compositionLocals$runtime.size());
            for (Map.Entry<CompositionLocal<Object>, ValueHolder<Object>> entry : compositionLocals$runtime.entrySet()) {
                arrayList.add(entry.getValue().toProvided(entry.getKey()));
            }
            ProvidedValue[] providedValueArr = (ProvidedValue[]) arrayList.toArray(new ProvidedValue[0]);
            CompositionLocalProvider((ProvidedValue<?>[]) Arrays.copyOf(providedValueArr, providedValueArr.length), function2, composerStartRestartGroup, i2 & 112);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ro2
                public final Object invoke(Object obj, Object obj2) {
                    return CompositionLocalKt.b(compositionLocalContext, function2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static Unit a(ProvidedValue providedValue, Function2 function2, int i, Composer composer, int i2) {
        CompositionLocalProvider((ProvidedValue<?>) providedValue, (Function2<? super Composer, ? super Integer, Unit>) function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static Unit b(CompositionLocalContext compositionLocalContext, Function2 function2, int i, Composer composer, int i2) {
        CompositionLocalProvider(compositionLocalContext, (Function2<? super Composer, ? super Integer, Unit>) function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static Unit c(ProvidedValue[] providedValueArr, Function2 function2, int i, Composer composer, int i2) {
        CompositionLocalProvider((ProvidedValue<?>[]) providedValueArr, (Function2<? super Composer, ? super Integer, Unit>) function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final <T> ProvidableCompositionLocal<T> compositionLocalOf(SnapshotMutationPolicy<T> snapshotMutationPolicy, Function0<? extends T> function0) {
        return new DynamicProvidableCompositionLocal(snapshotMutationPolicy, function0);
    }

    public static /* synthetic */ ProvidableCompositionLocal compositionLocalOf$default(SnapshotMutationPolicy snapshotMutationPolicy, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            snapshotMutationPolicy = SnapshotStateKt.structuralEqualityPolicy();
        }
        return compositionLocalOf(snapshotMutationPolicy, function0);
    }

    public static final <T> ProvidableCompositionLocal<T> compositionLocalWithComputedDefaultOf(Function1<? super CompositionLocalAccessorScope, ? extends T> function1) {
        return new ComputedProvidableCompositionLocal(function1);
    }

    public static final <T> ProvidableCompositionLocal<T> staticCompositionLocalOf(Function0<? extends T> function0) {
        return new StaticProvidableCompositionLocal(function0);
    }

    public static final <T> T withCompositionLocal(ProvidedValue<?> providedValue, Function2<? super Composer, ? super Integer, ? extends T> function2, Composer composer, int i) {
        composer.startProvider(providedValue);
        T t = (T) function2.invoke(composer, Integer.valueOf((i >> 3) & 14));
        composer.endProvider();
        return t;
    }

    public static final <T> T withCompositionLocals(ProvidedValue<?>[] providedValueArr, Function2<? super Composer, ? super Integer, ? extends T> function2, Composer composer, int i) {
        composer.startProviders(providedValueArr);
        T t = (T) function2.invoke(composer, Integer.valueOf((i >> 3) & 14));
        composer.endProvider();
        return t;
    }

    public static final void CompositionLocalProvider(final ProvidedValue<?> providedValue, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-149765515);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-149765515, i, -1, "androidx.compose.runtime.CompositionLocalProvider (CompositionLocal.kt:395)");
        }
        composerStartRestartGroup.startProvider(providedValue);
        function2.invoke(composerStartRestartGroup, Integer.valueOf((i >> 3) & 14));
        composerStartRestartGroup.endProvider();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: po2
                public final Object invoke(Object obj, Object obj2) {
                    return CompositionLocalKt.a(providedValue, function2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void CompositionLocalProvider(final ProvidedValue<?>[] providedValueArr, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(415205898);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(415205898, i, -1, "androidx.compose.runtime.CompositionLocalProvider (CompositionLocal.kt:375)");
        }
        composerStartRestartGroup.startProviders(providedValueArr);
        function2.invoke(composerStartRestartGroup, Integer.valueOf((i >> 3) & 14));
        composerStartRestartGroup.endProviders();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: qo2
                public final Object invoke(Object obj, Object obj2) {
                    return CompositionLocalKt.c(providedValueArr, function2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
