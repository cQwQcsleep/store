package androidx.compose.material3;

import androidx.compose.material3.DefaultWideNavigationRailOverride;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\u0004\u001a\u00020\u0005*\u00020\u0006H\u0017¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/material3/DefaultWideNavigationRailOverride;", "Landroidx/compose/material3/WideNavigationRailOverride;", "<init>", "()V", "WideNavigationRail", "", "Landroidx/compose/material3/WideNavigationRailOverrideScope;", "(Landroidx/compose/material3/WideNavigationRailOverrideScope;Landroidx/compose/runtime/Composer;I)V", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class DefaultWideNavigationRailOverride implements WideNavigationRailOverride {
    public static final int $stable = 0;
    public static final DefaultWideNavigationRailOverride INSTANCE = new DefaultWideNavigationRailOverride();

    private DefaultWideNavigationRailOverride() {
    }

    public static Unit a(DefaultWideNavigationRailOverride defaultWideNavigationRailOverride, WideNavigationRailOverrideScope wideNavigationRailOverrideScope, int i, Composer composer, int i2) {
        defaultWideNavigationRailOverride.WideNavigationRail(wideNavigationRailOverrideScope, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.material3.WideNavigationRailOverride
    public void WideNavigationRail(final WideNavigationRailOverrideScope wideNavigationRailOverrideScope, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1829525426);
        if ((i & 6) == 0) {
            i2 = ((i & 8) == 0 ? composerStartRestartGroup.changed(wideNavigationRailOverrideScope) : composerStartRestartGroup.changedInstance(wideNavigationRailOverrideScope) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1829525426, i2, -1, "androidx.compose.material3.DefaultWideNavigationRailOverride.WideNavigationRail (WideNavigationRail.kt:192)");
            }
            WideNavigationRailKt.WideNavigationRailLayout(wideNavigationRailOverrideScope.getModifier(), false, WideNavigationRailStateKt.isExpanded(wideNavigationRailOverrideScope.getState().getTargetValue()), wideNavigationRailOverrideScope.getColors(), wideNavigationRailOverrideScope.getShape(), wideNavigationRailOverrideScope.getHeader(), wideNavigationRailOverrideScope.getWindowInsets(), wideNavigationRailOverrideScope.getArrangement(), wideNavigationRailOverrideScope.getContent(), composerStartRestartGroup, 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: vj3
                public final Object invoke(Object obj, Object obj2) {
                    return DefaultWideNavigationRailOverride.a(this.b, wideNavigationRailOverrideScope, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
