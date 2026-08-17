package androidx.compose.material3;

import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.WindowInsetsPaddingKt;
import androidx.compose.foundation.selection.SelectableGroupKt;
import androidx.compose.material3.DefaultShortNavigationBarOverride;
import androidx.compose.material3.tokens.NavigationBarTokens;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\u0004\u001a\u00020\u0005*\u00020\u0006H\u0017¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/material3/DefaultShortNavigationBarOverride;", "Landroidx/compose/material3/ShortNavigationBarOverride;", "<init>", "()V", "ShortNavigationBar", "", "Landroidx/compose/material3/ShortNavigationBarOverrideScope;", "(Landroidx/compose/material3/ShortNavigationBarOverrideScope;Landroidx/compose/runtime/Composer;I)V", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class DefaultShortNavigationBarOverride implements ShortNavigationBarOverride {
    public static final int $stable = 0;
    public static final DefaultShortNavigationBarOverride INSTANCE = new DefaultShortNavigationBarOverride();

    private DefaultShortNavigationBarOverride() {
    }

    public static Unit a(DefaultShortNavigationBarOverride defaultShortNavigationBarOverride, ShortNavigationBarOverrideScope shortNavigationBarOverrideScope, int i, Composer composer, int i2) {
        defaultShortNavigationBarOverride.ShortNavigationBar(shortNavigationBarOverrideScope, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.material3.ShortNavigationBarOverride
    public void ShortNavigationBar(final ShortNavigationBarOverrideScope shortNavigationBarOverrideScope, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(256157474);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(shortNavigationBarOverrideScope) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(256157474, i2, -1, "androidx.compose.material3.DefaultShortNavigationBarOverride.ShortNavigationBar (ShortNavigationBar.kt:122)");
            }
            SurfaceKt.m954SurfaceT9BRK9s(shortNavigationBarOverrideScope.getModifier(), null, shortNavigationBarOverrideScope.getContainerColor(), shortNavigationBarOverrideScope.getContentColor(), 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(236236519, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DefaultShortNavigationBarOverride.ShortNavigationBar.1
                public final void invoke(Composer composer2, int i3) {
                    Object centeredContentMeasurePolicy;
                    if (!composer2.shouldExecute((i3 & 3) != 2, i3 & 1)) {
                        composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(236236519, i3, -1, "androidx.compose.material3.DefaultShortNavigationBarOverride.ShortNavigationBar.<anonymous> (ShortNavigationBar.kt:124)");
                    }
                    Modifier modifierSelectableGroup = SelectableGroupKt.selectableGroup(SizeKt.defaultMinSize-VpY3zN4$default(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, shortNavigationBarOverrideScope.getWindowInsets()), 0.0f, NavigationBarTokens.INSTANCE.m1921getContainerHeightD9Ej5fM(), 1, (Object) null));
                    Function2<Composer, Integer, Unit> content = shortNavigationBarOverrideScope.getContent();
                    int arrangement = shortNavigationBarOverrideScope.getArrangement();
                    ShortNavigationBarArrangement.Companion companion = ShortNavigationBarArrangement.INSTANCE;
                    if (ShortNavigationBarArrangement.m867equalsimpl0(arrangement, companion.m872getEqualWeightLnnQw40())) {
                        centeredContentMeasurePolicy = new EqualWeightContentMeasurePolicy();
                    } else {
                        if (!ShortNavigationBarArrangement.m867equalsimpl0(arrangement, companion.m871getCenteredLnnQw40())) {
                            w01.a("Invalid ItemsArrangement value.");
                            return;
                        }
                        centeredContentMeasurePolicy = new CenteredContentMeasurePolicy();
                    }
                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                    CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierSelectableGroup);
                    ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                    Function0<ComposeUiNode> constructor = companion2.getConstructor();
                    if (composer2.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composer2.startReusableNode();
                    if (composer2.getInserting()) {
                        composer2.createNode(constructor);
                    } else {
                        composer2.useNode();
                    }
                    Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer2);
                    Updater.m2396setimpl(composerM2388constructorimpl, centeredContentMeasurePolicy, companion2.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                    content.invoke(composer2, 0);
                    composer2.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, 12582912, 114);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: si3
                public final Object invoke(Object obj, Object obj2) {
                    return DefaultShortNavigationBarOverride.a(this.b, shortNavigationBarOverrideScope, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
